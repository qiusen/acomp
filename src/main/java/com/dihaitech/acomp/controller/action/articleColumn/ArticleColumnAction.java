package com.dihaitech.acomp.controller.action.articleColumn;

import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.service.IArticleColumnService;
import com.dihaitech.acomp.service.IChannelService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 文章栏目Action
 * 
 * @author cg
 *
 * @date 2014-08-27
 */
 @SuppressWarnings("serial")
public class ArticleColumnAction extends BaseAction {
	private ArticleColumn articleColumn = new ArticleColumn();
	private IArticleColumnService articleColumnService;
	
	private IChannelService channelService;
	
	public ArticleColumn getArticleColumn() {
		return articleColumn;
	}

	public void setArticleColumn(ArticleColumn articleColumn) {
		this.articleColumn = articleColumn;
	}
	public IArticleColumnService getArticleColumnService() {
		return articleColumnService;
	}

	public void setArticleColumnService(IArticleColumnService articleColumnService) {
		this.articleColumnService = articleColumnService;
	}
	
	
	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	/* 
	 * 文章栏目查询
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

			Page pageInfo = articleColumnService.selectArticleColumn(articleColumn,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<ArticleColumn> resultList = this.articleColumnService.selectArticleColumn(articleColumn,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","articleColumnAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("ArticleColumn json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			if(resultList!=null && resultList.size()>0){
				ArticleColumn articleColumn = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					articleColumn = resultList.get(i);
					if(i==0){
						strbuf.append(articleColumn.getChannelId());
					}else{
						strbuf.append(", " + articleColumn.getChannelId());
					}
				}
				
				Channel channel = new Channel();
				channel.setIdStr(strbuf.toString());
				List<Channel> channelList = channelService.selectChannelByIds(channel);
				this.getRequest().setAttribute("channelList", channelList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 文章栏目
	 * @return
	 */
	public String add(){
		Channel channel = new Channel();
		channel.setStatus(1);	//有效
		List<Channel> channelList = channelService.selectChannelByStatus(channel);
		this.getRequest().setAttribute("channelList", channelList);
		
		return "add";
	}
	
	/**
	 * 保存添加 文章栏目
	 * @return
	 */
	public String addSave(){
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		articleColumn.setCreateuser(managerVO.getNickname());
		articleColumn.setCreatetime(new Date());
		articleColumn.setUpdateuser(managerVO.getNickname());
		articleColumn.setUpdatetime(new Date());
		
		articleColumnService.addSave(articleColumn);
		return "addSave";
	}
	
	/**
	 * 修改 文章栏目
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			articleColumn.setId(id);
		}else{
			return null;
		}
		
		ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnById(articleColumn);
		this.getRequest().setAttribute("articleColumn", articleColumnVO);
		
		Channel channel = new Channel();
		channel.setStatus(1);	//有效
		List<Channel> channelList = channelService.selectChannelByStatus(channel);
		this.getRequest().setAttribute("channelList", channelList);
		
		return "edit";
	}
	
	/**
	 * 保存修改 文章栏目
	 * @return
	 */
	public String editSave(){
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		articleColumn.setUpdateuser(managerVO.getNickname());
		articleColumn.setUpdatetime(new Date());
		
		articleColumnService.editSave(articleColumn);
		return "editSave";
	}
	
	/**
	 * 删除 文章栏目
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		articleColumnService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 文章栏目
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
			articleColumnService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}