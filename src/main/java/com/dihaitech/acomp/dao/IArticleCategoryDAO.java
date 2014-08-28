package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.ArticleCategory;

/**
 * 文章类别 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-28
 */
public interface IArticleCategoryDAO {

	/**
	 * 根据条件查询文章类别 条数
	 * 
	 * @param articleCategory
	 * @return
	 */
	public Long getArticleCategoryCount(ArticleCategory articleCategory);

	/**
	 * 分页查找文章类别
	 * 
	 * @param articleCategory
	 * @param page
	 * @return
	 */
	public List<ArticleCategory> selectArticleCategoryLike(ArticleCategory articleCategory);

	/**
	 * 添加文章类别
	 * 
	 * @param articleCategory
	 * @return
	 */
	public int addSaveArticleCategory(ArticleCategory articleCategory);

	/**
	 * 根据ID获取指定的文章类别信息
	 * 
	 * @param articleCategory
	 * @return
	 */
	public ArticleCategory selectArticleCategoryById(ArticleCategory articleCategory);

	/**
	 * 修改文章类别
	 * 
	 * @param articleCategory
	 * @return
	 */
	public int editSaveArticleCategory(ArticleCategory articleCategory);

	/**
	 * 根据ID删除指定的文章类别
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有文章类别
	 * 
	 * @return
	 */
	public List<ArticleCategory> selectAll();
}
