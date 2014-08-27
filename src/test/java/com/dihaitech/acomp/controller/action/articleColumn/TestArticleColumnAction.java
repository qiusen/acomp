package com.dihaitech.acomp.controller.action.articleColumn;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.service.IArticleColumnService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章栏目 Action 测试
 * 
 * @author cg
 * @since 2014-08-27
 */
public class TestArticleColumnAction extends CommonTestAction {
	private ArticleColumnAction test;
	private IArticleColumnService articleColumnService;
	
	@Override
	public String getNameSpace() {
		return "/admin/articleColumn/articleColumnAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ArticleColumnAction) proxy.getAction();
		if(null == articleColumnService)
			articleColumnService =(IArticleColumnService) applicationContext.getBean("articleColumnService");
	}
	
	private ArticleColumn buildTestModel(){
	
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setCode("测试");
		articleColumn.setName("测试");
		articleColumn.setChannelId(1);
		articleColumn.setStatus(1);
		articleColumn.setOrdernum(1);
		articleColumn.setCreateuser("测试");
        articleColumn.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleColumn.setUpdateuser("测试");
        articleColumn.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return articleColumn;
	}
	private ArticleColumn addTestArticleColumn(){
		ArticleColumn articleColumn = buildTestModel();
	    articleColumnService.addSave(articleColumn);
	    return articleColumn;
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
		ArticleColumn articleColumn = buildTestModel();
	    test.setArticleColumn(articleColumn);
		String res = test.addSave();
		articleColumnService.deleteByIds(" where id = "+articleColumn.getId());
		Assert.assertNotNull(test.getArticleColumn());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		ArticleColumn articleColumn = addTestArticleColumn();
		request.setParameter("id", ""+articleColumn.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		ArticleColumn a = (ArticleColumn) request.getAttribute("articleColumn");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(articleColumn.getId(), a.getId());
		articleColumnService.deleteByIds(" where id = "+articleColumn.getId());
	}
	@Test
	public void testEditSave(){
		
		ArticleColumn articleColumn1 = addTestArticleColumn();
	    ArticleColumn articleColumn2 = buildTestModel();
	    articleColumn2.setId(articleColumn1.getId());
		test.setArticleColumn(articleColumn2);
		String edit_save_res = test.editSave();
		articleColumnService.selectArticleColumnById(articleColumn1);
		Assert.assertEquals("editSave", edit_save_res);
		
		articleColumnService.deleteByIds(" where id = "+articleColumn1.getId());
	}
	@Test
	public void testDelete(){
		ArticleColumn articleColumn = addTestArticleColumn();
		request.setParameter("id", articleColumn.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(articleColumnService.selectArticleColumnById(articleColumn));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		ArticleColumn articleColumn0 = addTestArticleColumn();
		ids[0]=articleColumn0.getId()+"";
		ArticleColumn articleColumn1 = addTestArticleColumn();
		ids[1]=articleColumn1.getId()+"";
		ArticleColumn articleColumn2 = addTestArticleColumn();
		ids[2]=articleColumn2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(articleColumnService.selectArticleColumnById(articleColumn0));
	}
}