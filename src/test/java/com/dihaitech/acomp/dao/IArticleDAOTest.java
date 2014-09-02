package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class IArticleDAOTest extends BaseTestCase {
	@Resource
	private IArticleDAO articleDAO;
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
	    articleDAO.addSaveArticle(article);
	    return article;
	}
	@Test
	public void getArticleCount() {
		addTestArticle();
		Article total_obj = new Article();
		Long total = articleDAO.getArticleCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectArticleLike() {
		addTestArticle();
		Article tmp = new Article();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Article> l = articleDAO.selectArticleLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectArticleById() {
		Article obj = addTestArticle();
		Article tmp = new Article();
		tmp.setId(obj.getId());
		Article res = articleDAO.selectArticleById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveArticle(){
		Article obj = buildTestModel();
		int res = articleDAO.addSaveArticle(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		articleDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveArticle(){
		Article add_obj = addTestArticle();
		add_obj.setColumnCode("测试2");
		add_obj.setCategoryCode("测试2");
		add_obj.setTitle("测试2");
		add_obj.setAuth("测试2");
		add_obj.setShortTitle("测试2");
		add_obj.setArticleImg("测试2");
		add_obj.setBrief("测试2");
		add_obj.setContent("测试2");
		add_obj.setStatus(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = articleDAO.editSaveArticle(add_obj);
		Assert.assertTrue(edit_res>0);
		
		articleDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Article obj = addTestArticle();
		
		int res = articleDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleDAO.selectArticleById(obj));
	}
}