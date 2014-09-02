package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.IArticleCategoryDAO;
import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.service.IArticleCategoryService;

/**
 * 文章类别 业务接口 IArticleCategoryService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
public class ArticleCategoryServiceImpl implements IArticleCategoryService {

	@Resource
	private IArticleCategoryDAO articleCategoryDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#addSave(com.dihaitech.acomp.model.ArticleCategory)
	 */
	public int addSave(ArticleCategory articleCategory) {
		return articleCategoryDAO.addSaveArticleCategory(articleCategory);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleCategoryDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#editSave(com.dihaitech.acomp.model.ArticleCategory)
	 */
	public int editSave(ArticleCategory articleCategory) {
		return articleCategoryDAO.editSaveArticleCategory(articleCategory);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IArticleCategoryService#selectAll()
	 */
	public List<ArticleCategory> selectAll() {
		return articleCategoryDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategory(com.dihaitech.acomp.model.ArticleCategory, int)
	 */
	public Page selectArticleCategory(ArticleCategory articleCategory, int pageSize) {
		return new Page(articleCategoryDAO.getArticleCategoryCount(articleCategory), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategory(com.dihaitech.acomp.model.ArticleCategory, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<ArticleCategory> selectArticleCategory(ArticleCategory articleCategory, Page page) {
		articleCategory.setStart(page.getFirstItemPos());
		articleCategory.setPageSize(page.getPageSize());
		return articleCategoryDAO.selectArticleCategoryLike(articleCategory);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategoryById(com.dihaitech.acomp.model.ArticleCategory)
	 */
	public ArticleCategory selectArticleCategoryById(ArticleCategory articleCategory) {
		return articleCategoryDAO.selectArticleCategoryById(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategoryByColumnId(com.dihaitech.acomp.model.ArticleCategory)
	 */
	@Override
	public List<ArticleCategory> selectArticleCategoryByColumnId(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByColumnId(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategoryByCode(com.dihaitech.acomp.model.ArticleCategory)
	 */
	@Override
	public ArticleCategory selectArticleCategoryByCode(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByCode(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IArticleCategoryService#selectArticleCategoryByCodes(com.dihaitech.acomp.model.ArticleCategory)
	 */
	@Override
	public List<ArticleCategory> selectArticleCategoryByCodes(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByCodes(articleCategory);
	}
}
