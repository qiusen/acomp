package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ILinkRelationDAO;
import com.dihaiboyun.cms.model.LinkRelation;
import com.dihaiboyun.cms.service.ILinkRelationService;

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
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#addSave(com.dihaiboyun.cms.model.LinkRelation)
	 */
	public int addSave(LinkRelation linkRelation) {
		return linkRelationDAO.addSaveLinkRelation(linkRelation);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return linkRelationDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#editSave(com.dihaiboyun.cms.model.LinkRelation)
	 */
	public int editSave(LinkRelation linkRelation) {
		return linkRelationDAO.editSaveLinkRelation(linkRelation);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ILinkRelationService#selectAll()
	 */
	public List<LinkRelation> selectAll() {
		return linkRelationDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#selectLinkRelation(com.dihaiboyun.cms.model.LinkRelation, int)
	 */
	public Page selectLinkRelation(LinkRelation linkRelation, int pageSize) {
		return new Page(linkRelationDAO.getLinkRelationCount(linkRelation), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#selectLinkRelation(com.dihaiboyun.cms.model.LinkRelation, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<LinkRelation> selectLinkRelation(LinkRelation linkRelation, Page page) {
		linkRelation.setStart(page.getFirstItemPos());
		linkRelation.setPageSize(page.getPageSize());
		return linkRelationDAO.selectLinkRelationLike(linkRelation);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#selectLinkRelationById(com.dihaiboyun.cms.model.LinkRelation)
	 */
	public LinkRelation selectLinkRelationById(LinkRelation linkRelation) {
		return linkRelationDAO.selectLinkRelationById(linkRelation);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#selectLinkRelationByPageId(com.dihaiboyun.cms.model.LinkRelation)
	 */
	@Override
	public List<LinkRelation> selectLinkRelationByPageId(
			LinkRelation linkRelation) {
		// TODO Auto-generated method stub
		return linkRelationDAO.selectLinkRelationByPageId(linkRelation);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILinkRelationService#deleteByPageId(com.dihaiboyun.cms.model.LinkRelation)
	 */
	@Override
	public int deleteByPageId(LinkRelation linkRelation) {
		// TODO Auto-generated method stub
		return linkRelationDAO.deleteByPageId(linkRelation);
	}
}
