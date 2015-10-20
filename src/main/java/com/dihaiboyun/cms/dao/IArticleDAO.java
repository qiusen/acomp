package com.dihaiboyun.cms.dao;

import java.util.List;


import com.dihaiboyun.cms.model.Article;

/**
 * 文章 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-28
 */
public interface IArticleDAO {

	/**
	 * 根据条件查询文章 条数
	 * 
	 * @param article
	 * @return
	 */
	public Long getArticleCount(Article article);

	/**
	 * 分页查找文章
	 * 
	 * @param article
	 * @param page
	 * @return
	 */
	public List<Article> selectArticleLike(Article article);

	/**
	 * 添加文章
	 * 
	 * @param article
	 * @return
	 */
	public int addSaveArticle(Article article);

	/**
	 * 根据ID获取指定的文章信息
	 * 
	 * @param article
	 * @return
	 */
	public Article selectArticleById(Article article);

	/**
	 * 修改文章
	 * 
	 * @param article
	 * @return
	 */
	public int editSaveArticle(Article article);

	/**
	 * 根据ID删除指定的文章
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有文章
	 * 
	 * @return
	 */
	public List<Article> selectAll();
	
	/**
	 * 获取前两篇文章
	 * @param article
	 * @return
	 */
	public List<Article> selectPreviousArticleList(Article article);
	
	/**
	 * 前一篇
	 * @param article
	 * @return
	 */
	public Article selectPreviousArticle(Article article);
	
	/**
	 * 后一篇
	 * @param article
	 * @return
	 */
	public Article selectNextArticle(Article article);
	
	/**
	 * 根据文章栏目CODES查询
	 * @param article
	 * @return
	 */
	public List<Article> selectArticleByColumnCodes(Article article);
}
