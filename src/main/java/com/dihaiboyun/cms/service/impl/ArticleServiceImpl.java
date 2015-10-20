package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IArticleDAO;
import com.dihaiboyun.cms.model.Article;
import com.dihaiboyun.cms.service.IArticleService;

/**
 * 文章 业务接口 IArticleService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
public class ArticleServiceImpl implements IArticleService {

	@Resource
	private IArticleDAO articleDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#addSave(com.dihaiboyun.cms.model.Article)
	 */
	public int addSave(Article article) {
		return articleDAO.addSaveArticle(article);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#editSave(com.dihaiboyun.cms.model.Article)
	 */
	public int editSave(Article article) {
		return articleDAO.editSaveArticle(article);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IArticleService#selectAll()
	 */
	public List<Article> selectAll() {
		return articleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectArticle(com.dihaiboyun.cms.model.Article, int)
	 */
	public Page selectArticle(Article article, int pageSize) {
		return new Page(articleDAO.getArticleCount(article), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectArticle(com.dihaiboyun.cms.model.Article, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Article> selectArticle(Article article, Page page) {
		article.setStart(page.getFirstItemPos());
		article.setPageSize(page.getPageSize());
		return articleDAO.selectArticleLike(article);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectArticleById(com.dihaiboyun.cms.model.Article)
	 */
	public Article selectArticleById(Article article) {
		return articleDAO.selectArticleById(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectPreviousArticleList(com.dihaiboyun.cms.model.Article)
	 */
	@Override
	public List<Article> selectPreviousArticleList(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectPreviousArticleList(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectPreviousArticle(com.dihaiboyun.cms.model.Article)
	 */
	@Override
	public Article selectPreviousArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectPreviousArticle(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectNextArticle(com.dihaiboyun.cms.model.Article)
	 */
	@Override
	public Article selectNextArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectNextArticle(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleService#selectArticleByColumnCodes(com.dihaiboyun.cms.model.Article)
	 */
	@Override
	public List<Article> selectArticleByColumnCodes(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByColumnCodes(article);
	}
}
