package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ILinkPageDAO;
import com.dihaiboyun.cms.model.LinkPage;
import com.dihaiboyun.cms.service.ILinkPageService;

/**
 * 友链页面 业务接口 ILinkPageService 实现类
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public class LinkPageServiceImpl implements ILinkPageService {

	@Resource
	private ILinkPageDAO linkPageDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#addSave(com.dihaiboyun.cms.model.LinkPage)
	 */
	public int addSave(LinkPage linkPage) {
		return linkPageDAO.addSaveLinkPage(linkPage);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkPageDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#editSave(com.dihaiboyun.cms.model.LinkPage)
	 */
	public int editSave(LinkPage linkPage) {
		return linkPageDAO.editSaveLinkPage(linkPage);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ILinkPageService#selectAll()
	 */
	public List<LinkPage> selectAll() {
		return linkPageDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#selectLinkPage(com.dihaiboyun.cms.model.LinkPage, int)
	 */
	public Page selectLinkPage(LinkPage linkPage, int pageSize) {
		return new Page(linkPageDAO.getLinkPageCount(linkPage), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#selectLinkPage(com.dihaiboyun.cms.model.LinkPage, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<LinkPage> selectLinkPage(LinkPage linkPage, Page page) {
		linkPage.setStart(page.getFirstItemPos());
		linkPage.setPageSize(page.getPageSize());
		return linkPageDAO.selectLinkPageLike(linkPage);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkPageService#selectLinkPageById(com.dihaiboyun.cms.model.LinkPage)
	 */
	public LinkPage selectLinkPageById(LinkPage linkPage) {
		return linkPageDAO.selectLinkPageById(linkPage);
	}
}
