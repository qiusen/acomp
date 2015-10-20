package com.dihaiboyun.cms.controller.action.templeteTag;

import java.util.Date;
import java.util.List;

import com.dihaiboyun.cms.common.Property;
import com.dihaiboyun.cms.controller.action.BaseAction;
import com.dihaiboyun.cms.model.TempleteTag;
import com.dihaiboyun.cms.service.ITempleteTagService;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.common.util.TypeUtil;
import com.dihaiboyun.common.util.json.JSONUtil;
import com.dihaiboyun.tserver.managercenter.Manager;

/**
 * 模板标签Action
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
 @SuppressWarnings("serial")
public class TempleteTagAction extends BaseAction {
	private TempleteTag templeteTag = new TempleteTag();
	private ITempleteTagService templeteTagService;
	
	public TempleteTag getTempleteTag() {
		return templeteTag;
	}

	public void setTempleteTag(TempleteTag templeteTag) {
		this.templeteTag = templeteTag;
	}
	public ITempleteTagService getTempleteTagService() {
		return templeteTagService;
	}

	public void setTempleteTagService(ITempleteTagService templeteTagService) {
		this.templeteTagService = templeteTagService;
	}
	/* 
	 * 模板标签查询
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

			Page pageInfo = templeteTagService.selectTempleteTag(templeteTag,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<TempleteTag> resultList = this.templeteTagService.selectTempleteTag(templeteTag,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","templeteTagAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("TempleteTag json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 模板标签
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 模板标签
	 * @return
	 */
	public String addSave(){
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		templeteTag.setCreateuser(managerVO.getNickname());
		templeteTag.setCreatetime(new Date());
		templeteTag.setUpdateuser(managerVO.getNickname());
		templeteTag.setUpdatetime(new Date());
		
		templeteTagService.addSave(templeteTag);
		return "addSave";
	}
	
	/**
	 * 修改 模板标签
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			templeteTag.setId(id);
		}else{
			return null;
		}
		
		TempleteTag templeteTagVO = templeteTagService.selectTempleteTagById(templeteTag);
		this.getRequest().setAttribute("templeteTag", templeteTagVO);
		return "edit";
	}
	
	/**
	 * 保存修改 模板标签
	 * @return
	 */
	public String editSave(){
		
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		templeteTag.setUpdateuser(managerVO.getNickname());
		templeteTag.setUpdatetime(new Date());
		
		templeteTagService.editSave(templeteTag);
		return "editSave";
	}
	
	/**
	 * 删除 模板标签
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		templeteTagService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 模板标签
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
			templeteTagService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}