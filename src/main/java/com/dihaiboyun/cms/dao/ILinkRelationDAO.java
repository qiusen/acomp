package com.dihaiboyun.cms.dao;

import java.util.List;


import com.dihaiboyun.cms.model.LinkRelation;

/**
 * 友链网页与网站关系 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-09-04
 */
public interface ILinkRelationDAO {

	/**
	 * 根据条件查询友链网页与网站关系 条数
	 * 
	 * @param linkRelation
	 * @return
	 */
	public Long getLinkRelationCount(LinkRelation linkRelation);

	/**
	 * 分页查找友链网页与网站关系
	 * 
	 * @param linkRelation
	 * @param page
	 * @return
	 */
	public List<LinkRelation> selectLinkRelationLike(LinkRelation linkRelation);

	/**
	 * 添加友链网页与网站关系
	 * 
	 * @param linkRelation
	 * @return
	 */
	public int addSaveLinkRelation(LinkRelation linkRelation);

	/**
	 * 根据ID获取指定的友链网页与网站关系信息
	 * 
	 * @param linkRelation
	 * @return
	 */
	public LinkRelation selectLinkRelationById(LinkRelation linkRelation);

	/**
	 * 修改友链网页与网站关系
	 * 
	 * @param linkRelation
	 * @return
	 */
	public int editSaveLinkRelation(LinkRelation linkRelation);

	/**
	 * 根据ID删除指定的友链网页与网站关系
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有友链网页与网站关系
	 * 
	 * @return
	 */
	public List<LinkRelation> selectAll();
	
	/**
	 * 根据页面ID获取所有关系
	 * @param linkRelation
	 * @return
	 */
	public List<LinkRelation> selectLinkRelationByPageId(LinkRelation linkRelation);
	
	/**
	 * 根据页面ID删除所有关系
	 * @param linkRelation
	 * @return
	 */
	public int deleteByPageId(LinkRelation linkRelation);
}
