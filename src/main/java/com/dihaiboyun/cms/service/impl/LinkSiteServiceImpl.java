package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ILinkSiteDAO;
import com.dihaiboyun.cms.model.LinkSite;
import com.dihaiboyun.cms.service.ILinkSiteService;

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
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#addSave(com.dihaiboyun.cms.model.LinkSite)
	 */
	public int addSave(LinkSite linkSite) {
		return linkSiteDAO.addSaveLinkSite(linkSite);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkSiteDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#editSave(com.dihaiboyun.cms.model.LinkSite)
	 */
	public int editSave(LinkSite linkSite) {
		return linkSiteDAO.editSaveLinkSite(linkSite);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ILinkSiteService#selectAll()
	 */
	public List<LinkSite> selectAll() {
		return linkSiteDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#selectLinkSite(com.dihaiboyun.cms.model.LinkSite, int)
	 */
	public Page selectLinkSite(LinkSite linkSite, int pageSize) {
		return new Page(linkSiteDAO.getLinkSiteCount(linkSite), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#selectLinkSite(com.dihaiboyun.cms.model.LinkSite, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<LinkSite> selectLinkSite(LinkSite linkSite, Page page) {
		linkSite.setStart(page.getFirstItemPos());
		linkSite.setPageSize(page.getPageSize());
		return linkSiteDAO.selectLinkSiteLike(linkSite);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#selectLinkSiteById(com.dihaiboyun.cms.model.LinkSite)
	 */
	public LinkSite selectLinkSiteById(LinkSite linkSite) {
		return linkSiteDAO.selectLinkSiteById(linkSite);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#selectLinkSiteByStatus(com.dihaiboyun.cms.model.LinkSite)
	 */
	@Override
	public List<LinkSite> selectLinkSiteByStatus(LinkSite linkSite) {
		// TODO Auto-generated method stub
		return linkSiteDAO.selectLinkSiteByStatus(linkSite);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkSiteService#selectLinkSiteByIds(com.dihaiboyun.cms.model.LinkSite)
	 */
	@Override
	public List<LinkSite> selectLinkSiteByIds(LinkSite linkSite) {
		// TODO Auto-generated method stub
		return linkSiteDAO.selectLinkSiteByIds(linkSite);
	}
}
