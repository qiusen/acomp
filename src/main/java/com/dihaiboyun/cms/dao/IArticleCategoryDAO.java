package com.dihaiboyun.cms.dao;

import java.util.List;


import com.dihaiboyun.cms.model.ArticleCategory;

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
	
	/**
	 * 根据栏目ID获取所有类别
	 * @param articleCategory
	 * @return
	 */
	public List<ArticleCategory> selectArticleCategoryByColumnId(ArticleCategory articleCategory);
	
	/**
	 * 根据CODE查询
	 * @param articleCategory
	 * @return
	 */
	public ArticleCategory selectArticleCategoryByCode(ArticleCategory articleCategory);
	
	/**
	 * 根据CODE拼凑字符串，查询
	 * @param articleCategory
	 * @return
	 */
	public List<ArticleCategory> selectArticleCategoryByCodes(ArticleCategory articleCategory);
}
