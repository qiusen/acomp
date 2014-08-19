package com.dihaitech.acomp.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Module;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 模块 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-19
 */
public class IModuleServiceTest extends BaseTestCase{
	@Autowired
	private IModuleService moduleService;
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
	    moduleService.addSave(module);
	    return module;
	}
	
	@Test
	public void selectModulePage(){
		addTestModule();
	    Page p = moduleService.selectModule(new Module(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectModulePageList(){
		
		addTestModule();
		
		Module obj = new Module();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Module> moduleList = moduleService.selectModule(obj, page);
		Assert.assertTrue(moduleList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestModule();
		
		Assert.assertTrue("fail: module`s total is bad res!", moduleService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Module module = buildTestModel();
		int pr = moduleService.selectAll().size();
		int res = moduleService.addSave(module);
		int s = moduleService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(moduleService.selectModuleById(module));
		Assert.assertTrue("Fail:call moduleService insert fail!", s==pr+1);
	}

	@Test
	public void selectModuleById(){
		
		Module module = addTestModule();
		
		Module tmp = new Module();
		tmp.setId(module.getId());
		Assert.assertNotNull(moduleService.selectModuleById(tmp));
	}

	@Test
	public void editSave(){
		
		Module module1 = addTestModule();
		
	    Module module2 = moduleService.selectModuleById(module1);
		module2.setModulename("测试2");
		module2.setModuleurl("测试2");
		module2.setModuleact("测试2");
		module2.setCatalogId(21);
		module2.setStatus(21);
        module2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		moduleService.editSave(module2);
		
		Module tmp = moduleService.selectModuleById(module2);
		Assert.assertNotEquals(module1.getModuleact(), tmp.getModuleact());
		Assert.assertEquals(module2.getModuleact(), tmp.getModuleact());
		Assert.assertEquals("测试2", tmp.getModuleact());
		Assert.assertThat(module2.getStatus(),equalTo(tmp.getStatus()));
	}
	
	@Test
	public void deleteByIds(){
		
		Module module = addTestModule();
		
		Module obj = new Module();
		obj.setId(module.getId());
		Assert.assertNotNull(moduleService.selectModuleById(obj));
		int res = moduleService.deleteByIds("where id = "+module.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(moduleService.selectModuleById(obj));
	}
}