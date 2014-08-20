package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.City;
import com.dihaitech.acomp.util.Page;


/**
 * 市 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class ICityServiceTest extends BaseTestCase{
	@Autowired
	private ICityService cityService;
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
	public void selectCityPage(){
		addTestCity();
	    Page p = cityService.selectCity(new City(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectCityPageList(){
		
		addTestCity();
		
		City obj = new City();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<City> cityList = cityService.selectCity(obj, page);
		Assert.assertTrue(cityList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestCity();
		
		Assert.assertTrue("fail: city`s total is bad res!", cityService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		City city = buildTestModel();
		int pr = cityService.selectAll().size();
		int res = cityService.addSave(city);
		int s = cityService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(cityService.selectCityById(city));
		Assert.assertTrue("Fail:call cityService insert fail!", s==pr+1);
	}

	@Test
	public void selectCityById(){
		
		City city = addTestCity();
		
		City tmp = new City();
		tmp.setId(city.getId());
		Assert.assertNotNull(cityService.selectCityById(tmp));
	}

	@Test
	public void editSave(){
		
		City city1 = addTestCity();
		
	    City city2 = cityService.selectCityById(city1);
		city2.setCode("测试2");
		city2.setName("测试2");
		city2.setProvinceCode("测试2");
		cityService.editSave(city2);
		
		City tmp = cityService.selectCityById(city2);
		Assert.assertNotEquals(city1.getProvinceCode(), tmp.getProvinceCode());
		Assert.assertEquals(city2.getProvinceCode(), tmp.getProvinceCode());
		Assert.assertEquals("测试2", tmp.getProvinceCode());
	}
	
	@Test
	public void deleteByIds(){
		
		City city = addTestCity();
		
		City obj = new City();
		obj.setId(city.getId());
		Assert.assertNotNull(cityService.selectCityById(obj));
		int res = cityService.deleteByIds("where id = "+city.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(cityService.selectCityById(obj));
	}
}