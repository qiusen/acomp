package com.dihaiboyun.cms.controller.action.templete;

import java.util.Date;
import java.util.List;

import com.dihaiboyun.cms.common.Property;
import com.dihaiboyun.cms.controller.action.BaseAction;
import com.dihaiboyun.cms.model.Templete;
import com.dihaiboyun.cms.service.ITempleteService;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.common.util.TypeUtil;
import com.dihaiboyun.common.util.json.JSONUtil;
import com.dihaiboyun.tserver.managercenter.Manager;

/**
 * 模板Action
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
 @SuppressWarnings("serial")
public class TempleteAction extends BaseAction {
	private Templete templete = new Templete();
	private ITempleteService templeteService;
	
	public Templete getTemplete() {
		return templete;
	}

	public void setTemplete(Templete templete) {
		this.templete = templete;
	}
	public ITempleteService getTempleteService() {
		return templeteService;
	}

	public void setTempleteService(ITempleteService templeteService) {
		this.templeteService = templeteService;
	}
	/* 
	 * 模板查询
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

			Page pageInfo = templeteService.selectTemplete(templete,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Templete> resultList = this.templeteService.selectTemplete(templete,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","templeteAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Templete json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 模板
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 模板
	 * @return
	 */
	public String addSave(){
		
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		templete.setCreateuser(managerVO.getNickname());
		templete.setCreatetime(new Date());
		templete.setUpdateuser(managerVO.getNickname());
		templete.setUpdatetime(new Date());
		
		templeteService.addSave(templete);
		return "addSave";
	}
	
	/**
	 * 修改 模板
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			templete.setId(id);
		}else{
			return null;
		}
		
		Templete templeteVO = templeteService.selectTempleteById(templete);
		this.getRequest().setAttribute("templete", templeteVO);
		return "edit";
	}
	
	/**
	 * 保存修改 模板
	 * @return
	 */
	public String editSave(){
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		templete.setUpdateuser(managerVO.getNickname());
		templete.setUpdatetime(new Date());
		
		templeteService.editSave(templete);
		return "editSave";
	}
	
	/**
	 * 删除 模板
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		templeteService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 模板
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
			templeteService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}