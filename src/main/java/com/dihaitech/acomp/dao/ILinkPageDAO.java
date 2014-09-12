package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.LinkPage;

/**
 * 友链页面 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-09-04
 */
public interface ILinkPageDAO {

	/**
	 * 根据条件查询友链页面 条数
	 * 
	 * @param linkPage
	 * @return
	 */
	public Long getLinkPageCount(LinkPage linkPage);

	/**
	 * 分页查找友链页面
	 * 
	 * @param linkPage
	 * @param page
	 * @return
	 */
	public List<LinkPage> selectLinkPageLike(LinkPage linkPage);

	/**
	 * 添加友链页面
	 * 
	 * @param linkPage
	 * @return
	 */
	public int addSaveLinkPage(LinkPage linkPage);

	/**
	 * 根据ID获取指定的友链页面信息
	 * 
	 * @param linkPage
	 * @return
	 */
	public LinkPage selectLinkPageById(LinkPage linkPage);

	/**
	 * 修改友链页面
	 * 
	 * @param linkPage
	 * @return
	 */
	public int editSaveLinkPage(LinkPage linkPage);

	/**
	 * 根据ID删除指定的友链页面
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有友链页面
	 * 
	 * @return
	 */
	public List<LinkPage> selectAll();
}
