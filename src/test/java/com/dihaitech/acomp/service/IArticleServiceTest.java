package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 文章 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class IArticleServiceTest extends BaseTestCase{
	@Autowired
	private IArticleService articleService;
	private Article buildTestModel(){
	
		Article article = new Article();
		article.setColumnCode("测试");
		article.setCategoryCode("测试");
		article.setTitle("测试");
		article.setAuth("测试");
		article.setShortTitle("测试");
		article.setArticleImg("测试");
		article.setBrief("测试");
		article.setContent("测试");
		article.setStatus(123);
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
	    return article;
	}
	
	@Test
	public void selectArticlePage(){
		addTestArticle();
	    Page p = articleService.selectArticle(new Article(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectArticlePageList(){
		
		addTestArticle();
		
		Article obj = new Article();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Article> articleList = articleService.selectArticle(obj, page);
		Assert.assertTrue(articleList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestArticle();
		
		Assert.assertTrue("fail: article`s total is bad res!", articleService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Article article = buildTestModel();
		int pr = articleService.selectAll().size();
		int res = articleService.addSave(article);
		int s = articleService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(articleService.selectArticleById(article));
		Assert.assertTrue("Fail:call articleService insert fail!", s==pr+1);
	}

	@Test
	public void selectArticleById(){
		
		Article article = addTestArticle();
		
		Article tmp = new Article();
		tmp.setId(article.getId());
		Assert.assertNotNull(articleService.selectArticleById(tmp));
	}

	@Test
	public void editSave(){
		
		Article article1 = addTestArticle();
		
	    Article article2 = articleService.selectArticleById(article1);
		article2.setColumnCode("测试2");
		article2.setCategoryCode("测试2");
		article2.setTitle("测试2");
		article2.setAuth("测试2");
		article2.setShortTitle("测试2");
		article2.setArticleImg("测试2");
		article2.setBrief("测试2");
		article2.setContent("测试2");
		article2.setStatus(21);
		article2.setCreateuser("测试2");
        article2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		article2.setUpdateuser("测试2");
        article2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleService.editSave(article2);
		
		Article tmp = articleService.selectArticleById(article2);
		Assert.assertNotEquals(article1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(article2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(article2.getStatus(),equalTo(tmp.getStatus()));
	}
	
	@Test
	public void deleteByIds(){
		
		Article article = addTestArticle();
		
		Article obj = new Article();
		obj.setId(article.getId());
		Assert.assertNotNull(articleService.selectArticleById(obj));
		int res = articleService.deleteByIds("where id = "+article.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleService.selectArticleById(obj));
	}
}