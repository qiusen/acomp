package com.dihaiboyun.cms.dao;

import java.util.List;


import com.dihaiboyun.cms.model.LinkSite;

/**
 * 友链网站 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-09-04
 */
public interface ILinkSiteDAO {

	/**
	 * 根据条件查询友链网站 条数
	 * 
	 * @param linkSite
	 * @return
	 */
	public Long getLinkSiteCount(LinkSite linkSite);

	/**
	 * 分页查找友链网站
	 * 
	 * @param linkSite
	 * @param page
	 * @return
	 */
	public List<LinkSite> selectLinkSiteLike(LinkSite linkSite);

	/**
	 * 添加友链网站
	 * 
	 * @param linkSite
	 * @return
	 */
	public int addSaveLinkSite(LinkSite linkSite);

	/**
	 * 根据ID获取指定的友链网站信息
	 * 
	 * @param linkSite
	 * @return
	 */
	public LinkSite selectLinkSiteById(LinkSite linkSite);

	/**
	 * 修改友链网站
	 * 
	 * @param linkSite
	 * @return
	 */
	public int editSaveLinkSite(LinkSite linkSite);

	/**
	 * 根据ID删除指定的友链网站
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有友链网站
	 * 
	 * @return
	 */
	public List<LinkSite> selectAll();
	
	/**
	 * 根据状态获取所有友链网站
	 * @param linkSite
	 * @return
	 */
	public List<LinkSite> selectLinkSiteByStatus(LinkSite linkSite);
	
	/**
	 * 根据IDS获取所有友链网站
	 * @param linkSite
	 * @return
	 */
	public List<LinkSite> selectLinkSiteByIds(LinkSite linkSite);
}
