package com.dihaitech.acomp.controller.action.manager;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.service.IManagerService;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 管理员 Action 测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class TestManagerAction extends CommonTestAction {
	private ManagerAction test;
	private IManagerService managerService;
	
	@Override
	public String getNameSpace() {
		return "/admin/manager/managerAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ManagerAction) proxy.getAction();
		if(null == managerService)
			managerService =(IManagerService) applicationContext.getBean("managerService");
	}
	
	private Manager buildTestModel(){
	
		Manager manager = new Manager();
		manager.setUsername("测试");
		manager.setPassword("测试");
		manager.setEmail("测试");
		manager.setNickname("测试");
		manager.setStatus(1);
		manager.setRoleId(1);
		manager.setCreator("测试");
        manager.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        manager.setLogintime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		manager.setLoginip("测试");
	    return manager;
	}
	private Manager addTestManager(){
		Manager manager = buildTestModel();
	    managerService.addSave(manager);
	    return manager;
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
		Manager manager = buildTestModel();
	    test.setManager(manager);
		String res = test.addSave();
		managerService.deleteByIds(" where id = "+manager.getId());
		Assert.assertNotNull(test.getManager());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Manager manager = addTestManager();
		request.setParameter("id", ""+manager.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Manager a = (Manager) request.getAttribute("manager");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(manager.getId(), a.getId());
		managerService.deleteByIds(" where id = "+manager.getId());
	}
	@Test
	public void testEditSave(){
		
		Manager manager1 = addTestManager();
	    Manager manager2 = buildTestModel();
	    manager2.setId(manager1.getId());
		test.setManager(manager2);
		String edit_save_res = test.editSave();
		managerService.selectManagerById(manager1);
		Assert.assertEquals("editSave", edit_save_res);
		
		managerService.deleteByIds(" where id = "+manager1.getId());
	}
	@Test
	public void testDelete(){
		Manager manager = addTestManager();
		request.setParameter("id", manager.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(managerService.selectManagerById(manager));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Manager manager0 = addTestManager();
		ids[0]=manager0.getId()+"";
		Manager manager1 = addTestManager();
		ids[1]=manager1.getId()+"";
		Manager manager2 = addTestManager();
		ids[2]=manager2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(managerService.selectManagerById(manager0));
	}
}