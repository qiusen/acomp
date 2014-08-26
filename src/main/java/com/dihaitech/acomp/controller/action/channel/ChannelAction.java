package com.dihaitech.acomp.controller.action.channel;

import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.IChannelService;
import com.dihaitech.acomp.service.ITempleteService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 频道Action
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
 @SuppressWarnings("serial")
public class ChannelAction extends BaseAction {
	private Channel channel = new Channel();
	private IChannelService channelService;
	
	private ITempleteService templeteService;
	
	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}
	
	
	public ITempleteService getTempleteService() {
		return templeteService;
	}

	public void setTempleteService(ITempleteService templeteService) {
		this.templeteService = templeteService;
	}

	/* 
	 * 频道查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = channelService.selectChannel(channel,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Channel> resultList = this.channelService.selectChannel(channel,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","channelAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Channel json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			if(resultList!=null && resultList.size()>0){
				Channel channel = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					channel = resultList.get(i);
					if(i==0){
						strbuf.append(channel.getTempleteId());
					}else{
						strbuf.append(", " + channel.getTempleteId());
					}
				}
				
				Templete templete = new Templete();
				templete.setIdStr(strbuf.toString());
				List<Templete> templeteList = templeteService.selectTempleteByIds(templete);
				this.getRequest().setAttribute("templeteList", templeteList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 频道
	 * @return
	 */
	public String add(){
		
		Templete templete = new Templete();
		templete.setType(1);
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
		return "add";
	}
	
	/**
	 * 保存添加 频道
	 * @return
	 */
	public String addSave(){
		
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		channel.setCreateuser(managerVO.getNickname());
		channel.setCreatetime(new Date());
		channel.setUpdateuser(managerVO.getNickname());
		channel.setUpdatetime(new Date());
		
		channelService.addSave(channel);
		return "addSave";
	}
	
	/**
	 * 修改 频道
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			channel.setId(id);
		}else{
			return null;
		}
		
		Channel channelVO = channelService.selectChannelById(channel);
		this.getRequest().setAttribute("channel", channelVO);
		
		Templete templete = new Templete();
		templete.setType(1);
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
		return "edit";
	}
	
	/**
	 * 保存修改 频道
	 * @return
	 */
	public String editSave(){
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		channel.setUpdateuser(managerVO.getNickname());
		channel.setUpdatetime(new Date());
		
		channelService.editSave(channel);
		return "editSave";
	}
	
	/**
	 * 删除 频道
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		channelService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 频道
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			channelService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}