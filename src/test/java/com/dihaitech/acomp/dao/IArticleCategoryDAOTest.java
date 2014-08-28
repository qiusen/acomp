package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章类别 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class IArticleCategoryDAOTest extends BaseTestCase {
	@Resource
	private IArticleCategoryDAO articleCategoryDAO;
	private ArticleCategory buildTestModel(){
	
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setCode("测试");
		articleCategory.setName("测试");
		articleCategory.setColumnId(123);
		articleCategory.setStatus(123);
		articleCategory.setOrdernum(123);
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
	    articleCategoryDAO.addSaveArticleCategory(articleCategory);
	    return articleCategory;
	}
	@Test
	public void getArticleCategoryCount() {
		addTestArticleCategory();
		ArticleCategory total_obj = new ArticleCategory();
		Long total = articleCategoryDAO.getArticleCategoryCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectArticleCategoryLike() {
		addTestArticleCategory();
		ArticleCategory tmp = new ArticleCategory();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<ArticleCategory> l = articleCategoryDAO.selectArticleCategoryLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectArticleCategoryById() {
		ArticleCategory obj = addTestArticleCategory();
		ArticleCategory tmp = new ArticleCategory();
		tmp.setId(obj.getId());
		ArticleCategory res = articleCategoryDAO.selectArticleCategoryById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveArticleCategory(){
		ArticleCategory obj = buildTestModel();
		int res = articleCategoryDAO.addSaveArticleCategory(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		articleCategoryDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveArticleCategory(){
		ArticleCategory add_obj = addTestArticleCategory();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		add_obj.setColumnId(21);
		add_obj.setStatus(21);
		add_obj.setOrdernum(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = articleCategoryDAO.editSaveArticleCategory(add_obj);
		Assert.assertTrue(edit_res>0);
		
		articleCategoryDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		ArticleCategory obj = addTestArticleCategory();
		
		int res = articleCategoryDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleCategoryDAO.selectArticleCategoryById(obj));
	}
}