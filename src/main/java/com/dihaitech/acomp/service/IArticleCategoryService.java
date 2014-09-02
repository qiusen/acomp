package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.util.Page;

/**
 * 文章类别 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
public interface IArticleCategoryService {
	/**
	 * 查询 ArticleCategory Page 对象
	 * @param articleCategory
	 * @param pageSize
	 * @return
	 */
	public Page selectArticleCategory(ArticleCategory articleCategory, int pageSize);

	/**
	 * 分页查找 文章类别
	 * @param articleCategory
	 * @param page
	 * @return
	 */
	public List<ArticleCategory> selectArticleCategory(ArticleCategory articleCategory, Page page);
	
	/**
	 * 查询所有 文章类别
	 * @return
	 */
	public List<ArticleCategory> selectAll();
	
	/**
	 * 根据 ID 查找 文章类别 
	 * @param articleCategory
	 * @return
	 */
	public ArticleCategory selectArticleCategoryById(ArticleCategory articleCategory);
	
	/**
	 * 添加 文章类别 
	 * @param articleCategory
	 * @return
	 */
	public int addSave(ArticleCategory articleCategory);
	
	/**
	 * 修改 文章类别 
	 * @param articleCategory
	 * @return
	 */
	public int editSave(ArticleCategory articleCategory);
	
	/**
	 * 根据 ID 删除 文章类别 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
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
