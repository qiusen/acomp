package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 文章栏目 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-27
 */
public class IArticleColumnServiceTest extends BaseTestCase{
	@Autowired
	private IArticleColumnService articleColumnService;
	private ArticleColumn buildTestModel(){
	
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setCode("测试");
		articleColumn.setName("测试");
		articleColumn.setChannelId(123);
		articleColumn.setStatus(123);
		articleColumn.setOrdernum(123);
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
	public void selectArticleColumnPage(){
		addTestArticleColumn();
	    Page p = articleColumnService.selectArticleColumn(new ArticleColumn(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectArticleColumnPageList(){
		
		addTestArticleColumn();
		
		ArticleColumn obj = new ArticleColumn();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumn(obj, page);
		Assert.assertTrue(articleColumnList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestArticleColumn();
		
		Assert.assertTrue("fail: articleColumn`s total is bad res!", articleColumnService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		ArticleColumn articleColumn = buildTestModel();
		int pr = articleColumnService.selectAll().size();
		int res = articleColumnService.addSave(articleColumn);
		int s = articleColumnService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(articleColumnService.selectArticleColumnById(articleColumn));
		Assert.assertTrue("Fail:call articleColumnService insert fail!", s==pr+1);
	}

	@Test
	public void selectArticleColumnById(){
		
		ArticleColumn articleColumn = addTestArticleColumn();
		
		ArticleColumn tmp = new ArticleColumn();
		tmp.setId(articleColumn.getId());
		Assert.assertNotNull(articleColumnService.selectArticleColumnById(tmp));
	}

	@Test
	public void editSave(){
		
		ArticleColumn articleColumn1 = addTestArticleColumn();
		
	    ArticleColumn articleColumn2 = articleColumnService.selectArticleColumnById(articleColumn1);
		articleColumn2.setCode("测试2");
		articleColumn2.setName("测试2");
		articleColumn2.setChannelId(21);
		articleColumn2.setStatus(21);
		articleColumn2.setOrdernum(21);
		articleColumn2.setCreateuser("测试2");
        articleColumn2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleColumn2.setUpdateuser("测试2");
        articleColumn2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		articleColumnService.editSave(articleColumn2);
		
		ArticleColumn tmp = articleColumnService.selectArticleColumnById(articleColumn2);
		Assert.assertNotEquals(articleColumn1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(articleColumn2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(articleColumn2.getOrdernum(),equalTo(tmp.getOrdernum()));
	}
	
	@Test
	public void deleteByIds(){
		
		ArticleColumn articleColumn = addTestArticleColumn();
		
		ArticleColumn obj = new ArticleColumn();
		obj.setId(articleColumn.getId());
		Assert.assertNotNull(articleColumnService.selectArticleColumnById(obj));
		int res = articleColumnService.deleteByIds("where id = "+articleColumn.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleColumnService.selectArticleColumnById(obj));
	}
}