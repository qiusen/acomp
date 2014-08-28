package com.dihaitech.acomp.controller.action.articleCategory;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.service.IArticleCategoryService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章类别 Action 测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class TestArticleCategoryAction extends CommonTestAction {
	private ArticleCategoryAction test;
	private IArticleCategoryService articleCategoryService;
	
	@Override
	public String getNameSpace() {
		return "/admin/articleCategory/articleCategoryAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ArticleCategoryAction) proxy.getAction();
		if(null == articleCategoryService)
			articleCategoryService =(IArticleCategoryService) applicationContext.getBean("articleCategoryService");
	}
	
	private ArticleCategory buildTestModel(){
	
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setCode("测试");
		articleCategory.setName("测试");
		articleCategory.setColumnId(1);
		articleCategory.setStatus(1);
		articleCategory.setOrdernum(1);
		articleCategory.setCreateuser("测试");
        articleCategory.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleCategory.setUpdateuser("测试");
        articleCategory.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return articleCategory;
	}
	private ArticleCategory addTestArticleCategory(){
		ArticleCategory articleCategory = buildTestModel();
	    articleCategoryService.addSave(articleCategory);
	    return articleCategory;
	}
	
	@Test
	public void testExecute() throws Exception {
		String result = null;
		if (null != proxy)
			result = proxy.execute();

		Assert.assertEquals("success", result);

	}
	
	@Test
	public void testAdd(){
		String res = test.add();

		Assert.assertEquals("add", res);
	}

	@Test
	public void testAddSave() {
		ArticleCategory articleCategory = buildTestModel();
	    test.setArticleCategory(articleCategory);
		String res = test.addSave();
		articleCategoryService.deleteByIds(" where id = "+articleCategory.getId());
		Assert.assertNotNull(test.getArticleCategory());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		ArticleCategory articleCategory = addTestArticleCategory();
		request.setParameter("id", ""+articleCategory.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		ArticleCategory a = (ArticleCategory) request.getAttribute("articleCategory");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(articleCategory.getId(), a.getId());
		articleCategoryService.deleteByIds(" where id = "+articleCategory.getId());
	}
	@Test
	public void testEditSave(){
		
		ArticleCategory articleCategory1 = addTestArticleCategory();
	    ArticleCategory articleCategory2 = buildTestModel();
	    articleCategory2.setId(articleCategory1.getId());
		test.setArticleCategory(articleCategory2);
		String edit_save_res = test.editSave();
		articleCategoryService.selectArticleCategoryById(articleCategory1);
		Assert.assertEquals("editSave", edit_save_res);
		
		articleCategoryService.deleteByIds(" where id = "+articleCategory1.getId());
	}
	@Test
	public void testDelete(){
		ArticleCategory articleCategory = addTestArticleCategory();
		request.setParameter("id", articleCategory.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(articleCategoryService.selectArticleCategoryById(articleCategory));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		ArticleCategory articleCategory0 = addTestArticleCategory();
		ids[0]=articleCategory0.getId()+"";
		ArticleCategory articleCategory1 = addTestArticleCategory();
		ids[1]=articleCategory1.getId()+"";
		ArticleCategory articleCategory2 = addTestArticleCategory();
		ids[2]=articleCategory2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(articleCategoryService.selectArticleCategoryById(articleCategory0));
	}
}