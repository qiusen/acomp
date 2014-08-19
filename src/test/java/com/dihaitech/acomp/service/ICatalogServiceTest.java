package com.dihaitech.acomp.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Catalog;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 目录 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class ICatalogServiceTest extends BaseTestCase{
	@Autowired
	private ICatalogService catalogService;
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
	    catalogService.addSave(catalog);
	    return catalog;
	}
	
	@Test
	public void selectCatalogPage(){
		addTestCatalog();
	    Page p = catalogService.selectCatalog(new Catalog(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectCatalogPageList(){
		
		addTestCatalog();
		
		Catalog obj = new Catalog();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Catalog> catalogList = catalogService.selectCatalog(obj, page);
		Assert.assertTrue(catalogList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestCatalog();
		
		Assert.assertTrue("fail: catalog`s total is bad res!", catalogService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Catalog catalog = buildTestModel();
		int pr = catalogService.selectAll().size();
		int res = catalogService.addSave(catalog);
		int s = catalogService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(catalogService.selectCatalogById(catalog));
		Assert.assertTrue("Fail:call catalogService insert fail!", s==pr+1);
	}

	@Test
	public void selectCatalogById(){
		
		Catalog catalog = addTestCatalog();
		
		Catalog tmp = new Catalog();
		tmp.setId(catalog.getId());
		Assert.assertNotNull(catalogService.selectCatalogById(tmp));
	}

	@Test
	public void editSave(){
		
		Catalog catalog1 = addTestCatalog();
		
	    Catalog catalog2 = catalogService.selectCatalogById(catalog1);
		catalog2.setCatalogname("测试2");
		catalog2.setStatus(21);
		catalog2.setOrdernum(21);
		catalog2.setMenuId(21);
        catalog2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		catalogService.editSave(catalog2);
		
		Catalog tmp = catalogService.selectCatalogById(catalog2);
		Assert.assertNotEquals(catalog1.getCatalogname(), tmp.getCatalogname());
		Assert.assertEquals(catalog2.getCatalogname(), tmp.getCatalogname());
		Assert.assertEquals("测试2", tmp.getCatalogname());
		Assert.assertThat(catalog2.getMenuId(),equalTo(tmp.getMenuId()));
	}
	
	@Test
	public void deleteByIds(){
		
		Catalog catalog = addTestCatalog();
		
		Catalog obj = new Catalog();
		obj.setId(catalog.getId());
		Assert.assertNotNull(catalogService.selectCatalogById(obj));
		int res = catalogService.deleteByIds("where id = "+catalog.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(catalogService.selectCatalogById(obj));
	}
}