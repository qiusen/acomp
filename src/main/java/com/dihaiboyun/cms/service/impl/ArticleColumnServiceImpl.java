package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IArticleColumnDAO;
import com.dihaiboyun.cms.model.ArticleColumn;
import com.dihaiboyun.cms.service.IArticleColumnService;

/**
 * 文章栏目 业务接口 IArticleColumnService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-27
 */
public class ArticleColumnServiceImpl implements IArticleColumnService {

	@Resource
	private IArticleColumnDAO articleColumnDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#addSave(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	public int addSave(ArticleColumn articleColumn) {
		return articleColumnDAO.addSaveArticleColumn(articleColumn);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleColumnDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#editSave(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	public int editSave(ArticleColumn articleColumn) {
		return articleColumnDAO.editSaveArticleColumn(articleColumn);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IArticleColumnService#selectAll()
	 */
	public List<ArticleColumn> selectAll() {
		return articleColumnDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumn(com.dihaiboyun.cms.model.ArticleColumn, int)
	 */
	public Page selectArticleColumn(ArticleColumn articleColumn, int pageSize) {
		return new Page(articleColumnDAO.getArticleColumnCount(articleColumn), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumn(com.dihaiboyun.cms.model.ArticleColumn, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<ArticleColumn> selectArticleColumn(ArticleColumn articleColumn, Page page) {
		articleColumn.setStart(page.getFirstItemPos());
		articleColumn.setPageSize(page.getPageSize());
		return articleColumnDAO.selectArticleColumnLike(articleColumn);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumnById(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	public ArticleColumn selectArticleColumnById(ArticleColumn articleColumn) {
		return articleColumnDAO.selectArticleColumnById(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumnByChannelId(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByChannelId(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByChannelId(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumnByIds(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByIds(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByIds(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumnByCode(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	@Override
	public ArticleColumn selectArticleColumnByCode(ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByCode(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleColumnService#selectArticleColumnByCodes(com.dihaiboyun.cms.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByCodes(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByCodes(articleColumn);
	}
}
