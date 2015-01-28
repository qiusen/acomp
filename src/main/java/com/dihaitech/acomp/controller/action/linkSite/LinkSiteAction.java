package com.dihaitech.acomp.controller.action.linkSite;

import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.service.ILinkSiteService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;
import com.dihaitech.tserver.managercenter.Manager;

/**
 * 友链网站Action
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
 @SuppressWarnings("serial")
public class LinkSiteAction extends BaseAction {
	private LinkSite linkSite = new LinkSite();
	private ILinkSiteService linkSiteService;
	
	public LinkSite getLinkSite() {
		return linkSite;
	}

	public void setLinkSite(LinkSite linkSite) {
		this.linkSite = linkSite;
	}
	public ILinkSiteService getLinkSiteService() {
		return linkSiteService;
	}

	public void setLinkSiteService(ILinkSiteService linkSiteService) {
		this.linkSiteService = linkSiteService;
	}
	/* 
	 * 友链网站查询
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

			Page pageInfo = linkSiteService.selectLinkSite(linkSite,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<LinkSite> resultList = this.linkSiteService.selectLinkSite(linkSite,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","linkSiteAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("LinkSite json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 友链网站
	 * @return
	 */
	public String add(){
		return "add";
	}
	
	/**
	 * 保存添加 友链网站
	 * @return
	 */
	public String addSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		linkSite.setCreateuser(manager.getNickname());
		linkSite.setCreatetime(new Date());
		linkSite.setUpdateuser(manager.getNickname());
		linkSite.setUpdatetime(new Date());
		
		linkSiteService.addSave(linkSite);
		return "addSave";
	}
	
	/**
	 * 修改 友链网站
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			linkSite.setId(id);
		}else{
			return null;
		}
		
		LinkSite linkSiteVO = linkSiteService.selectLinkSiteById(linkSite);
		this.getRequest().setAttribute("linkSite", linkSiteVO);
		return "edit";
	}
	
	/**
	 * 保存修改 友链网站
	 * @return
	 */
	public String editSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		linkSite.setUpdateuser(manager.getNickname());
		linkSite.setUpdatetime(new Date());
		
		linkSiteService.editSave(linkSite);
		return "editSave";
	}
	
	/**
	 * 删除 友链网站
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		linkSiteService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 友链网站
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
			linkSiteService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}