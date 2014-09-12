package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.util.Page;

/**
 * 友链网站 业务接口
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public interface ILinkSiteService {
	/**
	 * 查询 LinkSite Page 对象
	 * @param linkSite
	 * @param pageSize
	 * @return
	 */
	public Page selectLinkSite(LinkSite linkSite, int pageSize);

	/**
	 * 分页查找 友链网站
	 * @param linkSite
	 * @param page
	 * @return
	 */
	public List<LinkSite> selectLinkSite(LinkSite linkSite, Page page);
	
	/**
	 * 查询所有 友链网站
	 * @return
	 */
	public List<LinkSite> selectAll();
	
	/**
	 * 根据 ID 查找 友链网站 
	 * @param linkSite
	 * @return
	 */
	public LinkSite selectLinkSiteById(LinkSite linkSite);
	
	/**
	 * 添加 友链网站 
	 * @param linkSite
	 * @return
	 */
	public int addSave(LinkSite linkSite);
	
	/**
	 * 修改 友链网站 
	 * @param linkSite
	 * @return
	 */
	public int editSave(LinkSite linkSite);
	
	/**
	 * 根据 ID 删除 友链网站 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * 根据状态获取所有友链网站
	 * @param linkSite
	 * @return
	 */
	public List<LinkSite> selectLinkSiteByStatus(LinkSite linkSite);
}
