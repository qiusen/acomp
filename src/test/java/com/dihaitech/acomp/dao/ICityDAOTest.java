package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.City;
import com.dihaitech.acomp.util.Page;


/**
 * 市 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class ICityDAOTest extends BaseTestCase {
	@Resource
	private ICityDAO cityDAO;
	private City buildTestModel(){
	
		City city = new City();
		city.setCode("测试");
		city.setName("测试");
		city.setProvinceCode("测试");
	    return city;
	}
	private City addTestCity(){
		City city = buildTestModel();
	    cityDAO.addSaveCity(city);
	    return city;
	}
	@Test
	public void getCityCount() {
		addTestCity();
		City total_obj = new City();
		Long total = cityDAO.getCityCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectCityLike() {
		addTestCity();
		City tmp = new City();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<City> l = cityDAO.selectCityLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectCityById() {
		City obj = addTestCity();
		City tmp = new City();
		tmp.setId(obj.getId());
		City res = cityDAO.selectCityById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveCity(){
		City obj = buildTestModel();
		int res = cityDAO.addSaveCity(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		cityDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveCity(){
		City add_obj = addTestCity();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		add_obj.setProvinceCode("测试2");
		
		int edit_res = cityDAO.editSaveCity(add_obj);
		Assert.assertTrue(edit_res>0);
		
		cityDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		City obj = addTestCity();
		
		int res = cityDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(cityDAO.selectCityById(obj));
	}
}