package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.util.Page;

/**
 * 友链页面 业务接口
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
public interface ILinkPageService {
	/**
	 * 查询 LinkPage Page 对象
	 * @param linkPage
	 * @param pageSize
	 * @return
	 */
	public Page selectLinkPage(LinkPage linkPage, int pageSize);

	/**
	 * 分页查找 友链页面
	 * @param linkPage
	 * @param page
	 * @return
	 */
	public List<LinkPage> selectLinkPage(LinkPage linkPage, Page page);
	
	/**
	 * 查询所有 友链页面
	 * @return
	 */
	public List<LinkPage> selectAll();
	
	/**
	 * 根据 ID 查找 友链页面 
	 * @param linkPage
	 * @return
	 */
	public LinkPage selectLinkPageById(LinkPage linkPage);
	
	/**
	 * 添加 友链页面 
	 * @param linkPage
	 * @return
	 */
	public int addSave(LinkPage linkPage);
	
	/**
	 * 修改 友链页面 
	 * @param linkPage
	 * @return
	 */
	public int editSave(LinkPage linkPage);
	
	/**
	 * 根据 ID 删除 友链页面 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
