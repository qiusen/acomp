package com.dihaiboyun.cms.dwr;

import java.util.List;

import com.dihaiboyun.cms.model.ArticleCategory;
import com.dihaiboyun.cms.model.ArticleColumn;
import com.dihaiboyun.cms.service.IArticleCategoryService;
import com.dihaiboyun.cms.service.IArticleColumnService;

/**
 * 文章类别AJAX
 * @author qiusen
 *
 */
public class ArticleCategoryAjax {

	private IArticleColumnService articleColumnService;
	
	private IArticleCategoryService articleCategoryService;
	
	public IArticleColumnService getArticleColumnService() {
		return articleColumnService;
	}

	public void setArticleColumnService(IArticleColumnService articleColumnService) {
		this.articleColumnService = articleColumnService;
	}

	public IArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(
			IArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
	}

	/**
	 * 根据栏目编码，获取所有类别
	 * @param code
	 * @return
	 */
	public List<ArticleCategory> selectArticleCategoryByColumnCode(String code){
		List<ArticleCategory> articleCategoryList = null;
		
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setCode(code);
		ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnByCode(articleColumn);
		
		int columnId = articleColumnVO.getId();
		
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setColumnId(columnId);
		
		articleCategoryList = articleCategoryService.selectArticleCategoryByColumnId(articleCategory);
		
		return articleCategoryList;
	}
}
