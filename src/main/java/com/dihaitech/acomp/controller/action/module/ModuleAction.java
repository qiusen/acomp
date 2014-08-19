package com.dihaitech.acomp.controller.action.module;

import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Module;
import com.dihaitech.acomp.service.IModuleService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 模块Action
 * 
 * @author cg
 *
 * @date 2014-08-19
 */
 @SuppressWarnings("serial")
public class ModuleAction extends BaseAction {
	private Module module = new Module();
	private IModuleService moduleService;
	
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}
	public IModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(IModuleService moduleService) {
		this.moduleService = moduleService;
	}
	/* 
	 * 模块查询
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

			Page pageInfo = moduleService.selectModule(module,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Module> resultList = this.moduleService.selectModule(module,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","moduleAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Module json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 模块
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 模块
	 * @return
	 */
	public String addSave(){
		moduleService.addSave(module);
		return "addSave";
	}
	
	/**
	 * 修改 模块
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			module.setId(id);
		}else{
			return null;
		}
		
		Module moduleVO = moduleService.selectModuleById(module);
		this.getRequest().setAttribute("module", moduleVO);
		return "edit";
	}
	
	/**
	 * 保存修改 模块
	 * @return
	 */
	public String editSave(){
		moduleService.editSave(module);
		return "editSave";
	}
	
	/**
	 * 删除 模块
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		moduleService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 模块
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
			moduleService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}