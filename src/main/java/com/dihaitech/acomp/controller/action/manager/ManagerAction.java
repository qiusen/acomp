package com.dihaitech.acomp.controller.action.manager;

import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.service.IManagerService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 管理员Action
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
 @SuppressWarnings("serial")
public class ManagerAction extends BaseAction {
	private Manager manager = new Manager();
	private IManagerService managerService;
	
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public IManagerService getManagerService() {
		return managerService;
	}

	public void setManagerService(IManagerService managerService) {
		this.managerService = managerService;
	}
	/* 
	 * 管理员查询
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

			Page pageInfo = managerService.selectManager(manager,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Manager> resultList = this.managerService.selectManager(manager,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","managerAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Manager json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 管理员
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 管理员
	 * @return
	 */
	public String addSave(){
		managerService.addSave(manager);
		return "addSave";
	}
	
	/**
	 * 修改 管理员
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			manager.setId(id);
		}else{
			return null;
		}
		
		Manager managerVO = managerService.selectManagerById(manager);
		this.getRequest().setAttribute("manager", managerVO);
		return "edit";
	}
	
	/**
	 * 保存修改 管理员
	 * @return
	 */
	public String editSave(){
		managerService.editSave(manager);
		return "editSave";
	}
	
	/**
	 * 删除 管理员
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		managerService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 管理员
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
			managerService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}