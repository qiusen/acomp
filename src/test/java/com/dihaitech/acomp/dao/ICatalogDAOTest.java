package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Catalog;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 目录 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class ICatalogDAOTest extends BaseTestCase {
	@Resource
	private ICatalogDAO catalogDAO;
	private Catalog buildTestModel(){
	
		Catalog catalog = new Catalog();
		catalog.setCatalogname("测试");
		catalog.setStatus(123);
		catalog.setOrdernum(123);
		catalog.setMenuId(123);
        catalog.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return catalog;
	}
	private Catalog addTestCatalog(){
		Catalog catalog = buildTestModel();
	    catalogDAO.addSaveCatalog(catalog);
	    return catalog;
	}
	@Test
	public void getCatalogCount() {
		addTestCatalog();
		Catalog total_obj = new Catalog();
		Long total = catalogDAO.getCatalogCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectCatalogLike() {
		addTestCatalog();
		Catalog tmp = new Catalog();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Catalog> l = catalogDAO.selectCatalogLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectCatalogById() {
		Catalog obj = addTestCatalog();
		Catalog tmp = new Catalog();
		tmp.setId(obj.getId());
		Catalog res = catalogDAO.selectCatalogById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveCatalog(){
		Catalog obj = buildTestModel();
		int res = catalogDAO.addSaveCatalog(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		catalogDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveCatalog(){
		Catalog add_obj = addTestCatalog();
		add_obj.setCatalogname("测试2");
		add_obj.setStatus(21);
		add_obj.setOrdernum(21);
		add_obj.setMenuId(21);
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = catalogDAO.editSaveCatalog(add_obj);
		Assert.assertTrue(edit_res>0);
		
		catalogDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Catalog obj = addTestCatalog();
		
		int res = catalogDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(catalogDAO.selectCatalogById(obj));
	}
}