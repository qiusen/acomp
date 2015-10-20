package com.dihaiboyun.cms.controller.action.linkPage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dihaiboyun.cms.common.Property;
import com.dihaiboyun.cms.controller.action.BaseAction;
import com.dihaiboyun.cms.model.LinkPage;
import com.dihaiboyun.cms.model.LinkRelation;
import com.dihaiboyun.cms.model.LinkSite;
import com.dihaiboyun.cms.model.Templete;
import com.dihaiboyun.cms.service.ILinkPageService;
import com.dihaiboyun.cms.service.ILinkRelationService;
import com.dihaiboyun.cms.service.ILinkSiteService;
import com.dihaiboyun.cms.service.ITempleteService;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.common.util.TypeUtil;
import com.dihaiboyun.common.util.json.JSONUtil;
import com.dihaiboyun.tserver.managercenter.Manager;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

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
		
		String fileFolder = Property.FILE_PUBLISH_PATH + Property.BLOCK_FOLDER + "/" + Property.BLOCK_LINK_FOLDER;
		File folder = new File(fileFolder);
		if(!folder.exists()){
			folder.mkdirs();
		}
		String filePath = fileFolder + "/" + pageName + ".html";
		publishFile(linkPage, filePath);
		
		
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
		}else{
			this.getRequest().setAttribute("alreadyLinkRelationSiteData", "[]");
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
		
		String fileFolder = Property.FILE_PUBLISH_PATH + Property.BLOCK_FOLDER + "/" + Property.BLOCK_LINK_FOLDER;
		File folder = new File(fileFolder);
		if(!folder.exists()){
			folder.mkdirs();
		}
		int i = linkPage.getIncludePath().lastIndexOf("/");
		String filePath = fileFolder + "/" + linkPage.getIncludePath().substring(i + 1);
		publishFile(linkPage, filePath);
		return "editSave";
	}
	
	/**
	 * 发布文件
	 * @return
	 */
	private boolean publishFile(LinkPage linkPage, String filePath ){
		boolean success = false;
		
		LinkRelation linkRelation = new LinkRelation();
		linkRelation.setPageId(linkPage.getId());
		List<LinkRelation> linkRelationList = linkRelationService.selectLinkRelationByPageId(linkRelation);
		List<LinkSite> linkSiteList = null;
		if(linkRelationList!=null && linkRelationList.size()>0){
			LinkRelation linkRelationTemp = null;
			StringBuffer strbuf = new StringBuffer();
			for(int i=0;i<linkRelationList.size();i++){
				linkRelationTemp = linkRelationList.get(i);
				if(i==0){
					strbuf.append(linkRelationTemp.getSiteId());
				}else{
					strbuf.append("," + linkRelationTemp.getSiteId());
				}
			}
			
			LinkSite linkSite = new LinkSite();
			linkSite.setIdStr(strbuf.toString());
			linkSiteList = linkSiteService.selectLinkSiteByIds(linkSite);
			
			
		}
		
		//数据
		Map<String, Object> rootMap=new HashMap<String, Object>();
		rootMap.put("linkSiteList", linkSiteList);
		rootMap.put("linkPage", linkPage);
		
		//模板
		Templete templete = new Templete();
		templete.setId(linkPage.getTempleteId());
		Templete templeteVO = templeteService.selectTempleteById(templete);
		String templeteContent = templeteVO.getContent();
		
		//写文件
		PrintWriter printWriter = null;
		
		try{
			printWriter = new PrintWriter(
				new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filePath),"utf-8")));
			
			Configuration cfg = new Configuration();
			StringTemplateLoader stringLoader = new StringTemplateLoader();

			stringLoader.putTemplate("linkTemplete", templeteContent);
			cfg.setTemplateLoader(stringLoader);
			freemarker.template.Template t = cfg.getTemplate("linkTemplete","utf-8");
			t.process(rootMap, printWriter);
			printWriter.flush();
			
			success = true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(printWriter!=null){
				printWriter.close();
				printWriter = null;
			}
		}
		
		return success;
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