package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 文章类别 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-28
 */
public class IArticleCategoryServiceTest extends BaseTestCase{
	@Autowired
	private IArticleCategoryService articleCategoryService;
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
	    articleCategoryService.addSave(articleCategory);
	    return articleCategory;
	}
	
	@Test
	public void selectArticleCategoryPage(){
		addTestArticleCategory();
	    Page p = articleCategoryService.selectArticleCategory(new ArticleCategory(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectArticleCategoryPageList(){
		
		addTestArticleCategory();
		
		ArticleCategory obj = new ArticleCategory();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<ArticleCategory> articleCategoryList = articleCategoryService.selectArticleCategory(obj, page);
		Assert.assertTrue(articleCategoryList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestArticleCategory();
		
		Assert.assertTrue("fail: articleCategory`s total is bad res!", articleCategoryService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		ArticleCategory articleCategory = buildTestModel();
		int pr = articleCategoryService.selectAll().size();
		int res = articleCategoryService.addSave(articleCategory);
		int s = articleCategoryService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(articleCategoryService.selectArticleCategoryById(articleCategory));
		Assert.assertTrue("Fail:call articleCategoryService insert fail!", s==pr+1);
	}

	@Test
	public void selectArticleCategoryById(){
		
		ArticleCategory articleCategory = addTestArticleCategory();
		
		ArticleCategory tmp = new ArticleCategory();
		tmp.setId(articleCategory.getId());
		Assert.assertNotNull(articleCategoryService.selectArticleCategoryById(tmp));
	}

	@Test
	public void editSave(){
		
		ArticleCategory articleCategory1 = addTestArticleCategory();
		
	    ArticleCategory articleCategory2 = articleCategoryService.selectArticleCategoryById(articleCategory1);
		articleCategory2.setCode("测试2");
		articleCategory2.setName("测试2");
		articleCategory2.setColumnId(21);
		articleCategory2.setStatus(21);
		articleCategory2.setOrdernum(21);
		articleCategory2.setCreateuser("测试2");
        articleCategory2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleCategory2.setUpdateuser("测试2");
        articleCategory2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleCategoryService.editSave(articleCategory2);
		
		ArticleCategory tmp = articleCategoryService.selectArticleCategoryById(articleCategory2);
		Assert.assertNotEquals(articleCategory1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(articleCategory2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(articleCategory2.getOrdernum(),equalTo(tmp.getOrdernum()));
	}
	
	@Test
	public void deleteByIds(){
		
		ArticleCategory articleCategory = addTestArticleCategory();
		
		ArticleCategory obj = new ArticleCategory();
		obj.setId(articleCategory.getId());
		Assert.assertNotNull(articleCategoryService.selectArticleCategoryById(obj));
		int res = articleCategoryService.deleteByIds("where id = "+articleCategory.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleCategoryService.selectArticleCategoryById(obj));
	}
}