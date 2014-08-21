package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Province;
import com.dihaitech.acomp.util.Page;

/**
 * 省 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class IProvinceServiceTest extends BaseTestCase{
	@Autowired
	private IProvinceService provinceService;
	private Province buildTestModel(){
	
		Province province = new Province();
		province.setCode("测试");
		province.setName("测试");
	    return province;
	}
	private Province addTestProvince(){
		Province province = buildTestModel();
	    provinceService.addSave(province);
	    return province;
	}
	
	@Test
	public void selectProvincePage(){
		addTestProvince();
	    Page p = provinceService.selectProvince(new Province(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectProvincePageList(){
		
		addTestProvince();
		
		Province obj = new Province();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Province> provinceList = provinceService.selectProvince(obj, page);
		Assert.assertTrue(provinceList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestProvince();
		
		Assert.assertTrue("fail: province`s total is bad res!", provinceService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Province province = buildTestModel();
		int pr = provinceService.selectAll().size();
		int res = provinceService.addSave(province);
		int s = provinceService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(provinceService.selectProvinceById(province));
		Assert.assertTrue("Fail:call provinceService insert fail!", s==pr+1);
	}

	@Test
	public void selectProvinceById(){
		
		Province province = addTestProvince();
		
		Province tmp = new Province();
		tmp.setId(province.getId());
		Assert.assertNotNull(provinceService.selectProvinceById(tmp));
	}

	@Test
	public void editSave(){
		
		Province province1 = addTestProvince();
		
	    Province province2 = provinceService.selectProvinceById(province1);
		province2.setCode("测试2");
		province2.setName("测试2");
		provinceService.editSave(province2);
		
		Province tmp = provinceService.selectProvinceById(province2);
		Assert.assertNotEquals(province1.getName(), tmp.getName());
		Assert.assertEquals(province2.getName(), tmp.getName());
		Assert.assertEquals("测试2", tmp.getName());
	}
	
	@Test
	public void deleteByIds(){
		
		Province province = addTestProvince();
		
		Province obj = new Province();
		obj.setId(province.getId());
		Assert.assertNotNull(provinceService.selectProvinceById(obj));
		int res = provinceService.deleteByIds("where id = "+province.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(provinceService.selectProvinceById(obj));
	}
}