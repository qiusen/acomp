package com.dihaitech.acomp.controller.action.article;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.service.IArticleService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章 Action 测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class TestArticleAction extends CommonTestAction {
	private ArticleAction test;
	private IArticleService articleService;
	
	@Override
	public String getNameSpace() {
		return "/admin/article/articleAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ArticleAction) proxy.getAction();
		if(null == articleService)
			articleService =(IArticleService) applicationContext.getBean("articleService");
	}
	
	private Article buildTestModel(){
	
		Article article = new Article();
		article.setColumnCode("测试");
		article.setCategoryCode("测试");
		article.setTitle("测试");
		article.setAuth("测试");
		article.setTempleteId(1);
		article.setShortTitle("测试");
		article.setArticleImg("测试");
		article.setBrief("测试");
		article.setContent("测试");
		article.setStatus(1);
		article.setCreateuser("测试");
        article.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		article.setUpdateuser("测试");
        article.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return article;
	}
	private Article addTestArticle(){
		Article article = buildTestModel();
	    articleService.addSave(article);
	    System.out.println("===================="+article.getId());
	    return article;
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
		Article article = buildTestModel();
	    test.setArticle(article);
		String res = test.addSave();
		articleService.deleteByIds(" where id = "+article.getId());
		Assert.assertNotNull(test.getArticle());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Article article = addTestArticle();
		request.setParameter("id", ""+article.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Article a = (Article) request.getAttribute("article");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(article.getId(), a.getId());
		articleService.deleteByIds(" where id = "+article.getId());
	}
	@Test
	public void testEditSave(){
		
		Article article1 = addTestArticle();
	    Article article2 = buildTestModel();
	    article2.setId(article1.getId());
		test.setArticle(article2);
		String edit_save_res = test.editSave();
		articleService.selectArticleById(article1);
		Assert.assertEquals("editSave", edit_save_res);
		
		articleService.deleteByIds(" where id = "+article1.getId());
	}
//	@Test
//	public void testDelete(){
//		Article article = addTestArticle();
//		request.setParameter("id", article.getId().toString());
//		System.out.println("--------"+article.getId().toString());
//		String res = test.delete();
//		Assert.assertEquals("deleteSuccess", res);
//		Assert.assertNull(articleService.selectArticleById(article));
//		
//	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Article article0 = addTestArticle();
		ids[0]=article0.getId()+"";
		Article article1 = addTestArticle();
		ids[1]=article1.getId()+"";
		Article article2 = addTestArticle();
		ids[2]=article2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(articleService.selectArticleById(article0));
	}
}