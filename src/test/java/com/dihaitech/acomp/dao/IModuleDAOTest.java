package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Module;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 模块 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-19
 */
public class IModuleDAOTest extends BaseTestCase {
	@Resource
	private IModuleDAO moduleDAO;
	private Module buildTestModel(){
	
		Module module = new Module();
		module.setModulename("测试");
		module.setModuleurl("测试");
		module.setModuleact("测试");
		module.setCatalogId(123);
		module.setStatus(123);
        module.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return module;
	}
	private Module addTestModule(){
		Module module = buildTestModel();
	    moduleDAO.addSaveModule(module);
	    return module;
	}
	@Test
	public void getModuleCount() {
		addTestModule();
		Module total_obj = new Module();
		Long total = moduleDAO.getModuleCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectModuleLike() {
		addTestModule();
		Module tmp = new Module();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Module> l = moduleDAO.selectModuleLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectModuleById() {
		Module obj = addTestModule();
		Module tmp = new Module();
		tmp.setId(obj.getId());
		Module res = moduleDAO.selectModuleById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveModule(){
		Module obj = buildTestModel();
		int res = moduleDAO.addSaveModule(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		moduleDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveModule(){
		Module add_obj = addTestModule();
		add_obj.setModulename("测试2");
		add_obj.setModuleurl("测试2");
		add_obj.setModuleact("测试2");
		add_obj.setCatalogId(21);
		add_obj.setStatus(21);
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = moduleDAO.editSaveModule(add_obj);
		Assert.assertTrue(edit_res>0);
		
		moduleDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Module obj = addTestModule();
		
		int res = moduleDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(moduleDAO.selectModuleById(obj));
	}
}