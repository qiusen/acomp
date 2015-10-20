package com.dihaiboyun.cms.service;

import java.util.List;


import com.dihaiboyun.cms.model.TempleteTag;
import com.dihaiboyun.common.util.Page;

/**
 * 模板标签 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
public interface ITempleteTagService {
	/**
	 * 查询 TempleteTag Page 对象
	 * @param templeteTag
	 * @param pageSize
	 * @return
	 */
	public Page selectTempleteTag(TempleteTag templeteTag, int pageSize);

	/**
	 * 分页查找 模板标签
	 * @param templeteTag
	 * @param page
	 * @return
	 */
	public List<TempleteTag> selectTempleteTag(TempleteTag templeteTag, Page page);
	
	/**
	 * 查询所有 模板标签
	 * @return
	 */
	public List<TempleteTag> selectAll();
	
	/**
	 * 根据 ID 查找 模板标签 
	 * @param templeteTag
	 * @return
	 */
	public TempleteTag selectTempleteTagById(TempleteTag templeteTag);
	
	/**
	 * 添加 模板标签 
	 * @param templeteTag
	 * @return
	 */
	public int addSave(TempleteTag templeteTag);
	
	/**
	 * 修改 模板标签 
	 * @param templeteTag
	 * @return
	 */
	public int editSave(TempleteTag templeteTag);
	
	/**
	 * 根据 ID 删除 模板标签 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
