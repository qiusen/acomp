package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.ILinkSiteDAO;
import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.service.ILinkSiteService;

/**
 * 友链网站 业务接口 ILinkSiteService 实现类
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public class LinkSiteServiceImpl implements ILinkSiteService {

	@Resource
	private ILinkSiteDAO linkSiteDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#addSave(com.dihaitech.acomp.model.LinkSite)
	 */
	public int addSave(LinkSite linkSite) {
		return linkSiteDAO.addSaveLinkSite(linkSite);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkSiteDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#editSave(com.dihaitech.acomp.model.LinkSite)
	 */
	public int editSave(LinkSite linkSite) {
		return linkSiteDAO.editSaveLinkSite(linkSite);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ILinkSiteService#selectAll()
	 */
	public List<LinkSite> selectAll() {
		return linkSiteDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#selectLinkSite(com.dihaitech.acomp.model.LinkSite, int)
	 */
	public Page selectLinkSite(LinkSite linkSite, int pageSize) {
		return new Page(linkSiteDAO.getLinkSiteCount(linkSite), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#selectLinkSite(com.dihaitech.acomp.model.LinkSite, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<LinkSite> selectLinkSite(LinkSite linkSite, Page page) {
		linkSite.setStart(page.getFirstItemPos());
		linkSite.setPageSize(page.getPageSize());
		return linkSiteDAO.selectLinkSiteLike(linkSite);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#selectLinkSiteById(com.dihaitech.acomp.model.LinkSite)
	 */
	public LinkSite selectLinkSiteById(LinkSite linkSite) {
		return linkSiteDAO.selectLinkSiteById(linkSite);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#selectLinkSiteByStatus(com.dihaitech.acomp.model.LinkSite)
	 */
	@Override
	public List<LinkSite> selectLinkSiteByStatus(LinkSite linkSite) {
		// TODO Auto-generated method stub
		return linkSiteDAO.selectLinkSiteByStatus(linkSite);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkSiteService#selectLinkSiteByIds(com.dihaitech.acomp.model.LinkSite)
	 */
	@Override
	public List<LinkSite> selectLinkSiteByIds(LinkSite linkSite) {
		// TODO Auto-generated method stub
		return linkSiteDAO.selectLinkSiteByIds(linkSite);
	}
}
