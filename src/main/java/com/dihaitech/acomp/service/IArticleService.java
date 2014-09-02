package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.util.Page;

/**
 * 文章 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
public interface IArticleService {
	/**
	 * 查询 Article Page 对象
	 * @param article
	 * @param pageSize
	 * @return
	 */
	public Page selectArticle(Article article, int pageSize);

	/**
	 * 分页查找 文章
	 * @param article
	 * @param page
	 * @return
	 */
	public List<Article> selectArticle(Article article, Page page);
	
	/**
	 * 查询所有 文章
	 * @return
	 */
	public List<Article> selectAll();
	
	/**
	 * 根据 ID 查找 文章 
	 * @param article
	 * @return
	 */
	public Article selectArticleById(Article article);
	
	/**
	 * 添加 文章 
	 * @param article
	 * @return
	 */
	public int addSave(Article article);
	
	/**
	 * 修改 文章 
	 * @param article
	 * @return
	 */
	public int editSave(Article article);
	
	/**
	 * 根据 ID 删除 文章 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
