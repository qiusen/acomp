package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Province;
import com.dihaitech.acomp.util.Page;

/**
 * 省 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class IProvinceDAOTest extends BaseTestCase {
	@Resource
	private IProvinceDAO provinceDAO;
	private Province buildTestModel(){
	
		Province province = new Province();
		province.setCode("测试");
		province.setName("测试");
	    return province;
	}
	private Province addTestProvince(){
		Province province = buildTestModel();
	    provinceDAO.addSaveProvince(province);
	    return province;
	}
	@Test
	public void getProvinceCount() {
		addTestProvince();
		Province total_obj = new Province();
		Long total = provinceDAO.getProvinceCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectProvinceLike() {
		addTestProvince();
		Province tmp = new Province();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Province> l = provinceDAO.selectProvinceLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectProvinceById() {
		Province obj = addTestProvince();
		Province tmp = new Province();
		tmp.setId(obj.getId());
		Province res = provinceDAO.selectProvinceById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveProvince(){
		Province obj = buildTestModel();
		int res = provinceDAO.addSaveProvince(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		provinceDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveProvince(){
		Province add_obj = addTestProvince();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		
		int edit_res = provinceDAO.editSaveProvince(add_obj);
		Assert.assertTrue(edit_res>0);
		
		provinceDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Province obj = addTestProvince();
		
		int res = provinceDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(provinceDAO.selectProvinceById(obj));
	}
}