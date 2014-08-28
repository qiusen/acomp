package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.ArticleColumn;

/**
 * 文章栏目 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-27
 */
public interface IArticleColumnDAO {

	/**
	 * 根据条件查询文章栏目 条数
	 * 
	 * @param articleColumn
	 * @return
	 */
	public Long getArticleColumnCount(ArticleColumn articleColumn);

	/**
	 * 分页查找文章栏目
	 * 
	 * @param articleColumn
	 * @param page
	 * @return
	 */
	public List<ArticleColumn> selectArticleColumnLike(ArticleColumn articleColumn);

	/**
	 * 添加文章栏目
	 * 
	 * @param articleColumn
	 * @return
	 */
	public int addSaveArticleColumn(ArticleColumn articleColumn);

	/**
	 * 根据ID获取指定的文章栏目信息
	 * 
	 * @param articleColumn
	 * @return
	 */
	public ArticleColumn selectArticleColumnById(ArticleColumn articleColumn);

	/**
	 * 修改文章栏目
	 * 
	 * @param articleColumn
	 * @return
	 */
	public int editSaveArticleColumn(ArticleColumn articleColumn);

	/**
	 * 根据ID删除指定的文章栏目
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有文章栏目
	 * 
	 * @return
	 */
	public List<ArticleColumn> selectAll();
	
	/**
	 * 根据频道ID获取所有栏目
	 * @param articleColumn
	 * @return
	 */
	public List<ArticleColumn> selectArticleColumnByChannelId(ArticleColumn articleColumn);
	
	/**
	 * 根据IDS获取所有栏目
	 * @param articleColumn
	 * @return
	 */
	public List<ArticleColumn> selectArticleColumnByIds(ArticleColumn articleColumn);
}
