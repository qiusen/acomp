package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.ILinkPageDAO;
import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.service.ILinkPageService;

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
	 * @see com.dihaitech.acomp.service.ILinkPageService#addSave(com.dihaitech.acomp.model.LinkPage)
	 */
	public int addSave(LinkPage linkPage) {
		return linkPageDAO.addSaveLinkPage(linkPage);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkPageService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkPageDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkPageService#editSave(com.dihaitech.acomp.model.LinkPage)
	 */
	public int editSave(LinkPage linkPage) {
		return linkPageDAO.editSaveLinkPage(linkPage);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ILinkPageService#selectAll()
	 */
	public List<LinkPage> selectAll() {
		return linkPageDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkPageService#selectLinkPage(com.dihaitech.acomp.model.LinkPage, int)
	 */
	public Page selectLinkPage(LinkPage linkPage, int pageSize) {
		return new Page(linkPageDAO.getLinkPageCount(linkPage), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkPageService#selectLinkPage(com.dihaitech.acomp.model.LinkPage, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<LinkPage> selectLinkPage(LinkPage linkPage, Page page) {
		linkPage.setStart(page.getFirstItemPos());
		linkPage.setPageSize(page.getPageSize());
		return linkPageDAO.selectLinkPageLike(linkPage);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkPageService#selectLinkPageById(com.dihaitech.acomp.model.LinkPage)
	 */
	public LinkPage selectLinkPageById(LinkPage linkPage) {
		return linkPageDAO.selectLinkPageById(linkPage);
	}
}
