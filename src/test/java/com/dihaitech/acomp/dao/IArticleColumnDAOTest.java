package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 文章栏目 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-27
 */
public class IArticleColumnDAOTest extends BaseTestCase {
	@Resource
	private IArticleColumnDAO articleColumnDAO;
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
	    articleColumnDAO.addSaveArticleColumn(articleColumn);
	    return articleColumn;
	}
	@Test
	public void getArticleColumnCount() {
		addTestArticleColumn();
		ArticleColumn total_obj = new ArticleColumn();
		Long total = articleColumnDAO.getArticleColumnCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectArticleColumnLike() {
		addTestArticleColumn();
		ArticleColumn tmp = new ArticleColumn();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<ArticleColumn> l = articleColumnDAO.selectArticleColumnLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectArticleColumnById() {
		ArticleColumn obj = addTestArticleColumn();
		ArticleColumn tmp = new ArticleColumn();
		tmp.setId(obj.getId());
		ArticleColumn res = articleColumnDAO.selectArticleColumnById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveArticleColumn(){
		ArticleColumn obj = buildTestModel();
		int res = articleColumnDAO.addSaveArticleColumn(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		articleColumnDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveArticleColumn(){
		ArticleColumn add_obj = addTestArticleColumn();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		add_obj.setChannelId(21);
		add_obj.setStatus(21);
		add_obj.setOrdernum(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = articleColumnDAO.editSaveArticleColumn(add_obj);
		Assert.assertTrue(edit_res>0);
		
		articleColumnDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		ArticleColumn obj = addTestArticleColumn();
		
		int res = articleColumnDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(articleColumnDAO.selectArticleColumnById(obj));
	}
}