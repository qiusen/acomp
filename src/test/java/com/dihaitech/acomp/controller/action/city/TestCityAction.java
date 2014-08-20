package com.dihaitech.acomp.controller.action.city;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.City;
import com.dihaitech.acomp.service.ICityService;

/**
 * 市 Action 测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class TestCityAction extends CommonTestAction {
	private CityAction test;
	private ICityService cityService;
	
	@Override
	public String getNameSpace() {
		return "/admin/city/cityAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (CityAction) proxy.getAction();
		if(null == cityService)
			cityService =(ICityService) applicationContext.getBean("cityService");
	}
	
	private City buildTestModel(){
	
		City city = new City();
		city.setCode("测试");
		city.setName("测试");
		city.setProvinceCode("测试");
	    return city;
	}
	private City addTestCity(){
		City city = buildTestModel();
	    cityService.addSave(city);
	    return city;
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
		City city = buildTestModel();
	    test.setCity(city);
		String res = test.addSave();
		cityService.deleteByIds(" where id = "+city.getId());
		Assert.assertNotNull(test.getCity());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		City city = addTestCity();
		request.setParameter("id", ""+city.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		City a = (City) request.getAttribute("city");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(city.getId(), a.getId());
		cityService.deleteByIds(" where id = "+city.getId());
	}
	@Test
	public void testEditSave(){
		
		City city1 = addTestCity();
	    City city2 = buildTestModel();
	    city2.setId(city1.getId());
		test.setCity(city2);
		String edit_save_res = test.editSave();
		cityService.selectCityById(city1);
		Assert.assertEquals("editSave", edit_save_res);
		
		cityService.deleteByIds(" where id = "+city1.getId());
	}
	@Test
	public void testDelete(){
		City city = addTestCity();
		request.setParameter("id", city.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(cityService.selectCityById(city));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		City city0 = addTestCity();
		ids[0]=city0.getId()+"";
		City city1 = addTestCity();
		ids[1]=city1.getId()+"";
		City city2 = addTestCity();
		ids[2]=city2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(cityService.selectCityById(city0));
	}
}