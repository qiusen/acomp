package com.dihaitech.acomp.controller.action.linkPage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.model.LinkRelation;
import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.ILinkPageService;
import com.dihaitech.acomp.service.ILinkRelationService;
import com.dihaitech.acomp.service.ILinkSiteService;
import com.dihaitech.acomp.service.ITempleteService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 友链页面Action
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
 @SuppressWarnings("serial")
public class LinkPageAction extends BaseAction {
	private LinkPage linkPage = new LinkPage();
	private ILinkPageService linkPageService;
	
	private ITempleteService templeteService;
	
	private ILinkRelationService linkRelationService;
	
	private ILinkSiteService linkSiteService;
	
	public LinkPage getLinkPage() {
		return linkPage;
	}

	public void setLinkPage(LinkPage linkPage) {
		this.linkPage = linkPage;
	}
	public ILinkPageService getLinkPageService() {
		return linkPageService;
	}

	public void setLinkPageService(ILinkPageService linkPageService) {
		this.linkPageService = linkPageService;
	}
	
	public ITempleteService getTempleteService() {
		return templeteService;
	}

	public void setTempleteService(ITempleteService templeteService) {
		this.templeteService = templeteService;
	}


	public ILinkRelationService getLinkRelationService() {
		return linkRelationService;
	}

	public void setLinkRelationService(ILinkRelationService linkRelationService) {
		this.linkRelationService = linkRelationService;
	}

	public ILinkSiteService getLinkSiteService() {
		return linkSiteService;
	}

	public void setLinkSiteService(ILinkSiteService linkSiteService) {
		this.linkSiteService = linkSiteService;
	}

	/* 
	 * 友链页面查询
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

			Page pageInfo = linkPageService.selectLinkPage(linkPage,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<LinkPage> resultList = this.linkPageService.selectLinkPage(linkPage,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","linkPageAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("LinkPage json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			if(resultList!=null && resultList.size()>0){
				LinkPage linkPageTemp = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					linkPageTemp = resultList.get(i);
					if(i==0){
						strbuf.append("'" + linkPageTemp.getTempleteId() + "'");
					}else{
						strbuf.append(", '" + linkPageTemp.getTempleteId() + "'");
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
	 * 添加 友链页面
	 * @return
	 */
	public String add(){
		
		Templete templete = new Templete();
		templete.setType(5);	//友链模板
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
		LinkSite linkSite = new LinkSite();
		linkSite.setStatus(1);
		List<LinkSite> linkSiteList = linkSiteService.selectLinkSiteByStatus(linkSite);
		this.getRequest().setAttribute("linkSiteData", JSONUtil.objectArrayToJson(linkSiteList));
		
		return "add";
	}
	
	/**
	 * 保存添加 友链页面
	 * @return
	 */
	public String addSave(){
		
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		linkPage.setCreateuser(manager.getNickname());
		linkPage.setCreatetime(new Date());
		linkPage.setUpdateuser(manager.getNickname());
		linkPage.setUpdatetime(new Date());
		
		String pageName = this.getRequest().getParameter("pageName");
		String includePath = Property.BLOCK_INCLUDE_PATH + Property.BLOCK_LINK_FOLDER + "/" + pageName + ".html";
		linkPage.setIncludePath(includePath);
		
		linkPageService.addSave(linkPage);
		
		
		String links = this.getRequest().getParameter("links");
		if(links == null){
			links = "";
		}
		
		String[] siteIdS = links.split(",");
		//友链的排序
		int index = 1;
		for(String siteIdStr : siteIdS){
			if(!siteIdStr.equals("")){
				Integer siteId = new Integer(siteIdStr);
				LinkRelation relation = new LinkRelation();
				relation.setSiteId(siteId);
				relation.setPageId(linkPage.getId());
				relation.setOrdernum(index);
				
				index++;
				relation.setCreateuser(manager.getNickname());
				relation.setCreatetime(new Date());
				//存储该友链页面与友链的关联关系
				linkRelationService.addSave(relation);
			}
		}
		
		
		return "addSave";
	}
	
	/**
	 * 修改 友链页面
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			linkPage.setId(id);
		}else{
			return null;
		}
		
		LinkPage linkPageVO = linkPageService.selectLinkPageById(linkPage);
		this.getRequest().setAttribute("linkPage", linkPageVO);
		
		Templete templete = new Templete();
		templete.setType(5);	//友链模板
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
		LinkSite linkSite = new LinkSite();
		linkSite.setStatus(1);
		List<LinkSite> linkSiteList = linkSiteService.selectLinkSiteByStatus(linkSite);
		
		
		LinkRelation linkRelation = new LinkRelation();
		linkRelation.setPageId(id);
		List<LinkRelation> linkRelationList = linkRelationService.selectLinkRelationByPageId(linkRelation);
		
		if(linkSiteList!=null && linkSiteList.size()>0 && linkRelationList!=null && linkRelationList.size()>0){
			List<LinkSite> alreadyLinkRelationSiteList = new ArrayList<LinkSite>();
			LinkSite linkSiteTemp = null;
			LinkRelation linkRelationTemp = null;
			
			for(int i=0;i<linkRelationList.size();i++){
				linkRelationTemp = linkRelationList.get(i);
				for(int j=0;j<linkSiteList.size();j++){
					linkSiteTemp = linkSiteList.get(j);
					if(linkSiteTemp.getId().intValue() == linkRelationTemp.getSiteId().intValue()){
						alreadyLinkRelationSiteList.add(linkSiteTemp);
						linkSiteList.remove(j);
						i--;
						break;
					}
				}
			}
			

			this.getRequest().setAttribute("alreadyLinkRelationSiteData", JSONUtil.objectArrayToJson(alreadyLinkRelationSiteList));
		}
		
		this.getRequest().setAttribute("linkSiteData", JSONUtil.objectArrayToJson(linkSiteList));
		
		
		return "edit";
	}
	
	/**
	 * 保存修改 友链页面
	 * @return
	 */
	public String editSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		linkPage.setUpdateuser(manager.getNickname());
		linkPage.setUpdatetime(new Date());
		
		linkPageService.editSave(linkPage);
		
		LinkRelation relationPO = new LinkRelation();
		relationPO.setPageId(linkPage.getId());
		linkRelationService.deleteByPageId(relationPO);
		
		String links = this.getRequest().getParameter("links");
		if(links == null){
			links = "";
		}
		
		
		String[] siteIdS = links.split(",");
		//友链的排序
		int index = 1;
		for(String siteIdStr : siteIdS){
			if(!siteIdStr.equals("")){
				Integer siteId = new Integer(siteIdStr);
				LinkRelation relation = new LinkRelation();
				relation.setSiteId(siteId);
				relation.setPageId(linkPage.getId());
				relation.setOrdernum(index);
				
				index++;
				relation.setCreateuser(manager.getNickname());
				relation.setCreatetime(new Date());
				//存储该友链页面与友链的关联关系
				linkRelationService.addSave(relation);
			}
		}
		
		return "editSave";
	}
	
	/**
	 * 删除 友链页面
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		linkPageService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 友链页面
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
			linkPageService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}

	
}