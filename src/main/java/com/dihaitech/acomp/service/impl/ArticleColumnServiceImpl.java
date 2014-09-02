package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.IArticleColumnDAO;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.service.IArticleColumnService;

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
	 * @see com.dihaitech.acomp.service.IArticleColumnService#addSave(com.dihaitech.acomp.model.ArticleColumn)
	 */
	public int addSave(ArticleColumn articleColumn) {
		return articleColumnDAO.addSaveArticleColumn(articleColumn);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleColumnDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#editSave(com.dihaitech.acomp.model.ArticleColumn)
	 */
	public int editSave(ArticleColumn articleColumn) {
		return articleColumnDAO.editSaveArticleColumn(articleColumn);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IArticleColumnService#selectAll()
	 */
	public List<ArticleColumn> selectAll() {
		return articleColumnDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumn(com.dihaitech.acomp.model.ArticleColumn, int)
	 */
	public Page selectArticleColumn(ArticleColumn articleColumn, int pageSize) {
		return new Page(articleColumnDAO.getArticleColumnCount(articleColumn), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumn(com.dihaitech.acomp.model.ArticleColumn, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<ArticleColumn> selectArticleColumn(ArticleColumn articleColumn, Page page) {
		articleColumn.setStart(page.getFirstItemPos());
		articleColumn.setPageSize(page.getPageSize());
		return articleColumnDAO.selectArticleColumnLike(articleColumn);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumnById(com.dihaitech.acomp.model.ArticleColumn)
	 */
	public ArticleColumn selectArticleColumnById(ArticleColumn articleColumn) {
		return articleColumnDAO.selectArticleColumnById(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumnByChannelId(com.dihaitech.acomp.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByChannelId(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByChannelId(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumnByIds(com.dihaitech.acomp.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByIds(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByIds(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumnByCode(com.dihaitech.acomp.model.ArticleColumn)
	 */
	@Override
	public ArticleColumn selectArticleColumnByCode(ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByCode(articleColumn);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleColumnService#selectArticleColumnByCodes(com.dihaitech.acomp.model.ArticleColumn)
	 */
	@Override
	public List<ArticleColumn> selectArticleColumnByCodes(
			ArticleColumn articleColumn) {
		// TODO Auto-generated method stub
		return articleColumnDAO.selectArticleColumnByCodes(articleColumn);
	}
}
