package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.IArticleDAO;
import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.service.IArticleService;

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
	 * @see com.dihaitech.acomp.service.IArticleService#addSave(com.dihaitech.acomp.model.Article)
	 */
	public int addSave(Article article) {
		return articleDAO.addSaveArticle(article);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#editSave(com.dihaitech.acomp.model.Article)
	 */
	public int editSave(Article article) {
		return articleDAO.editSaveArticle(article);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IArticleService#selectAll()
	 */
	public List<Article> selectAll() {
		return articleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectArticle(com.dihaitech.acomp.model.Article, int)
	 */
	public Page selectArticle(Article article, int pageSize) {
		return new Page(articleDAO.getArticleCount(article), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectArticle(com.dihaitech.acomp.model.Article, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Article> selectArticle(Article article, Page page) {
		article.setStart(page.getFirstItemPos());
		article.setPageSize(page.getPageSize());
		return articleDAO.selectArticleLike(article);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectArticleById(com.dihaitech.acomp.model.Article)
	 */
	public Article selectArticleById(Article article) {
		return articleDAO.selectArticleById(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectPreviousArticleList(com.dihaitech.acomp.model.Article)
	 */
	@Override
	public List<Article> selectPreviousArticleList(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectPreviousArticleList(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectPreviousArticle(com.dihaitech.acomp.model.Article)
	 */
	@Override
	public Article selectPreviousArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectPreviousArticle(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectNextArticle(com.dihaitech.acomp.model.Article)
	 */
	@Override
	public Article selectNextArticle(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectNextArticle(article);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleService#selectArticleByColumnCodes(com.dihaitech.acomp.model.Article)
	 */
	@Override
	public List<Article> selectArticleByColumnCodes(Article article) {
		// TODO Auto-generated method stub
		return articleDAO.selectArticleByColumnCodes(article);
	}
}
