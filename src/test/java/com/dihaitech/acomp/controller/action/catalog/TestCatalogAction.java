package com.dihaitech.acomp.controller.action.catalog;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Catalog;
import com.dihaitech.acomp.service.ICatalogService;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 目录 Action 测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class TestCatalogAction extends CommonTestAction {
	private CatalogAction test;
	private ICatalogService catalogService;
	
	@Override
	public String getNameSpace() {
		return "/admin/catalog/catalogAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (CatalogAction) proxy.getAction();
		if(null == catalogService)
			catalogService =(ICatalogService) applicationContext.getBean("catalogService");
	}
	
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
		Catalog catalog = buildTestModel();
	    test.setCatalog(catalog);
		String res = test.addSave();
		catalogService.deleteByIds(" where id = "+catalog.getId());
		Assert.assertNotNull(test.getCatalog());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Catalog catalog = addTestCatalog();
		request.setParameter("id", ""+catalog.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Catalog a = (Catalog) request.getAttribute("catalog");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(catalog.getId(), a.getId());
		catalogService.deleteByIds(" where id = "+catalog.getId());
	}
	@Test
	public void testEditSave(){
		
		Catalog catalog1 = addTestCatalog();
	    Catalog catalog2 = buildTestModel();
	    catalog2.setId(catalog1.getId());
		test.setCatalog(catalog2);
		String edit_save_res = test.editSave();
		catalogService.selectCatalogById(catalog1);
		Assert.assertEquals("editSave", edit_save_res);
		
		catalogService.deleteByIds(" where id = "+catalog1.getId());
	}
	@Test
	public void testDelete(){
		Catalog catalog = addTestCatalog();
		request.setParameter("id", catalog.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(catalogService.selectCatalogById(catalog));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Catalog catalog0 = addTestCatalog();
		ids[0]=catalog0.getId()+"";
		Catalog catalog1 = addTestCatalog();
		ids[1]=catalog1.getId()+"";
		Catalog catalog2 = addTestCatalog();
		ids[2]=catalog2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(catalogService.selectCatalogById(catalog0));
	}
}