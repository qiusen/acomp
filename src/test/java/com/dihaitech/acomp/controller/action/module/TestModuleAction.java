package com.dihaitech.acomp.controller.action.module;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Module;
import com.dihaitech.acomp.service.IModuleService;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 模块 Action 测试
 * 
 * @author cg
 * @since 2014-08-19
 */
public class TestModuleAction extends CommonTestAction {
	private ModuleAction test;
	private IModuleService moduleService;
	
	@Override
	public String getNameSpace() {
		return "/admin/module/moduleAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ModuleAction) proxy.getAction();
		if(null == moduleService)
			moduleService =(IModuleService) applicationContext.getBean("moduleService");
	}
	
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
		Module module = buildTestModel();
	    test.setModule(module);
		String res = test.addSave();
		moduleService.deleteByIds(" where id = "+module.getId());
		Assert.assertNotNull(test.getModule());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Module module = addTestModule();
		request.setParameter("id", ""+module.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Module a = (Module) request.getAttribute("module");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(module.getId(), a.getId());
		moduleService.deleteByIds(" where id = "+module.getId());
	}
	@Test
	public void testEditSave(){
		
		Module module1 = addTestModule();
	    Module module2 = buildTestModel();
	    module2.setId(module1.getId());
		test.setModule(module2);
		String edit_save_res = test.editSave();
		moduleService.selectModuleById(module1);
		Assert.assertEquals("editSave", edit_save_res);
		
		moduleService.deleteByIds(" where id = "+module1.getId());
	}
	@Test
	public void testDelete(){
		Module module = addTestModule();
		request.setParameter("id", module.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(moduleService.selectModuleById(module));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Module module0 = addTestModule();
		ids[0]=module0.getId()+"";
		Module module1 = addTestModule();
		ids[1]=module1.getId()+"";
		Module module2 = addTestModule();
		ids[2]=module2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(moduleService.selectModuleById(module0));
	}
}