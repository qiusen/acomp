package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.ILinkRelationDAO;
import com.dihaitech.acomp.model.LinkRelation;
import com.dihaitech.acomp.service.ILinkRelationService;

/**
 * 友链网页与网站关系 业务接口 ILinkRelationService 实现类
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public class LinkRelationServiceImpl implements ILinkRelationService {

	@Resource
	private ILinkRelationDAO linkRelationDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#addSave(com.dihaitech.acomp.model.LinkRelation)
	 */
	public int addSave(LinkRelation linkRelation) {
		return linkRelationDAO.addSaveLinkRelation(linkRelation);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkRelationDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#editSave(com.dihaitech.acomp.model.LinkRelation)
	 */
	public int editSave(LinkRelation linkRelation) {
		return linkRelationDAO.editSaveLinkRelation(linkRelation);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ILinkRelationService#selectAll()
	 */
	public List<LinkRelation> selectAll() {
		return linkRelationDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#selectLinkRelation(com.dihaitech.acomp.model.LinkRelation, int)
	 */
	public Page selectLinkRelation(LinkRelation linkRelation, int pageSize) {
		return new Page(linkRelationDAO.getLinkRelationCount(linkRelation), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#selectLinkRelation(com.dihaitech.acomp.model.LinkRelation, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<LinkRelation> selectLinkRelation(LinkRelation linkRelation, Page page) {
		linkRelation.setStart(page.getFirstItemPos());
		linkRelation.setPageSize(page.getPageSize());
		return linkRelationDAO.selectLinkRelationLike(linkRelation);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#selectLinkRelationById(com.dihaitech.acomp.model.LinkRelation)
	 */
	public LinkRelation selectLinkRelationById(LinkRelation linkRelation) {
		return linkRelationDAO.selectLinkRelationById(linkRelation);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#selectLinkRelationByPageId(com.dihaitech.acomp.model.LinkRelation)
	 */
	@Override
	public List<LinkRelation> selectLinkRelationByPageId(
			LinkRelation linkRelation) {
		// TODO Auto-generated method stub
		return linkRelationDAO.selectLinkRelationByPageId(linkRelation);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ILinkRelationService#deleteByPageId(com.dihaitech.acomp.model.LinkRelation)
	 */
	@Override
	public int deleteByPageId(LinkRelation linkRelation) {
		// TODO Auto-generated method stub
		return linkRelationDAO.deleteByPageId(linkRelation);
	}
}
