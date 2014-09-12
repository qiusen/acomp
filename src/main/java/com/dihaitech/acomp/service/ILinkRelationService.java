package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.LinkRelation;
import com.dihaitech.acomp.util.Page;

/**
 * 友链网页与网站关系 业务接口
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public interface ILinkRelationService {
	/**
	 * 查询 LinkRelation Page 对象
	 * @param linkRelation
	 * @param pageSize
	 * @return
	 */
	public Page selectLinkRelation(LinkRelation linkRelation, int pageSize);

	/**
	 * 分页查找 友链网页与网站关系
	 * @param linkRelation
	 * @param page
	 * @return
	 */
	public List<LinkRelation> selectLinkRelation(LinkRelation linkRelation, Page page);
	
	/**
	 * 查询所有 友链网页与网站关系
	 * @return
	 */
	public List<LinkRelation> selectAll();
	
	/**
	 * 根据 ID 查找 友链网页与网站关系 
	 * @param linkRelation
	 * @return
	 */
	public LinkRelation selectLinkRelationById(LinkRelation linkRelation);
	
	/**
	 * 添加 友链网页与网站关系 
	 * @param linkRelation
	 * @return
	 */
	public int addSave(LinkRelation linkRelation);
	
	/**
	 * 修改 友链网页与网站关系 
	 * @param linkRelation
	 * @return
	 */
	public int editSave(LinkRelation linkRelation);
	
	/**
	 * 根据 ID 删除 友链网页与网站关系 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
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
