package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IArticleCategoryDAO;
import com.dihaiboyun.cms.model.ArticleCategory;
import com.dihaiboyun.cms.service.IArticleCategoryService;

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
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#addSave(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	public int addSave(ArticleCategory articleCategory) {
		return articleCategoryDAO.addSaveArticleCategory(articleCategory);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return articleCategoryDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#editSave(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	public int editSave(ArticleCategory articleCategory) {
		return articleCategoryDAO.editSaveArticleCategory(articleCategory);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IArticleCategoryService#selectAll()
	 */
	public List<ArticleCategory> selectAll() {
		return articleCategoryDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategory(com.dihaiboyun.cms.model.ArticleCategory, int)
	 */
	public Page selectArticleCategory(ArticleCategory articleCategory, int pageSize) {
		return new Page(articleCategoryDAO.getArticleCategoryCount(articleCategory), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategory(com.dihaiboyun.cms.model.ArticleCategory, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<ArticleCategory> selectArticleCategory(ArticleCategory articleCategory, Page page) {
		articleCategory.setStart(page.getFirstItemPos());
		articleCategory.setPageSize(page.getPageSize());
		return articleCategoryDAO.selectArticleCategoryLike(articleCategory);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategoryById(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	public ArticleCategory selectArticleCategoryById(ArticleCategory articleCategory) {
		return articleCategoryDAO.selectArticleCategoryById(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategoryByColumnId(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	@Override
	public List<ArticleCategory> selectArticleCategoryByColumnId(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByColumnId(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategoryByCode(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	@Override
	public ArticleCategory selectArticleCategoryByCode(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByCode(articleCategory);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IArticleCategoryService#selectArticleCategoryByCodes(com.dihaiboyun.cms.model.ArticleCategory)
	 */
	@Override
	public List<ArticleCategory> selectArticleCategoryByCodes(
			ArticleCategory articleCategory) {
		// TODO Auto-generated method stub
		return articleCategoryDAO.selectArticleCategoryByCodes(articleCategory);
	}
}
