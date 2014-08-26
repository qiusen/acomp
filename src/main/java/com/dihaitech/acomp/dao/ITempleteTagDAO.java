package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.TempleteTag;

/**
 * 模板标签 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-26
 */
public interface ITempleteTagDAO {

	/**
	 * 根据条件查询模板标签 条数
	 * 
	 * @param templeteTag
	 * @return
	 */
	public Long getTempleteTagCount(TempleteTag templeteTag);

	/**
	 * 分页查找模板标签
	 * 
	 * @param templeteTag
	 * @param page
	 * @return
	 */
	public List<TempleteTag> selectTempleteTagLike(TempleteTag templeteTag);

	/**
	 * 添加模板标签
	 * 
	 * @param templeteTag
	 * @return
	 */
	public int addSaveTempleteTag(TempleteTag templeteTag);

	/**
	 * 根据ID获取指定的模板标签信息
	 * 
	 * @param templeteTag
	 * @return
	 */
	public TempleteTag selectTempleteTagById(TempleteTag templeteTag);

	/**
	 * 修改模板标签
	 * 
	 * @param templeteTag
	 * @return
	 */
	public int editSaveTempleteTag(TempleteTag templeteTag);

	/**
	 * 根据ID删除指定的模板标签
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有模板标签
	 * 
	 * @return
	 */
	public List<TempleteTag> selectAll();
}
