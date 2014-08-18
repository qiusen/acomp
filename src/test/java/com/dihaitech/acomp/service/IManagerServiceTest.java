package com.dihaitech.acomp.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;
/**
 * 管理员 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IManagerServiceTest extends BaseTestCase{
	@Autowired
	private IManagerService managerService;
	private Manager buildTestModel(){
	
		Manager manager = new Manager();
		manager.setUsername("测试");
		manager.setPassword("测试");
		manager.setEmail("测试");
		manager.setNickname("测试");
		manager.setStatus(123);
		manager.setRoleId(123);
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
	public void selectManagerPage(){
		addTestManager();
	    Page p = managerService.selectManager(new Manager(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectManagerPageList(){
		
		addTestManager();
		
		Manager obj = new Manager();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Manager> managerList = managerService.selectManager(obj, page);
		Assert.assertTrue(managerList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestManager();
		
		Assert.assertTrue("fail: manager`s total is bad res!", managerService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Manager manager = buildTestModel();
		int pr = managerService.selectAll().size();
		int res = managerService.addSave(manager);
		int s = managerService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(managerService.selectManagerById(manager));
		Assert.assertTrue("Fail:call managerService insert fail!", s==pr+1);
	}

	@Test
	public void selectManagerById(){
		
		Manager manager = addTestManager();
		
		Manager tmp = new Manager();
		tmp.setId(manager.getId());
		Assert.assertNotNull(managerService.selectManagerById(tmp));
	}

	@Test
	public void editSave(){
		
		Manager manager1 = addTestManager();
		
	    Manager manager2 = managerService.selectManagerById(manager1);
		manager2.setUsername("测试2");
		manager2.setPassword("测试2");
		manager2.setEmail("测试2");
		manager2.setNickname("测试2");
		manager2.setStatus(21);
		manager2.setRoleId(21);
		manager2.setCreator("测试2");
        manager2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        manager2.setLogintime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		manager2.setLoginip("测试2");
		managerService.editSave(manager2);
		
		Manager tmp = managerService.selectManagerById(manager2);
		Assert.assertNotEquals(manager1.getLoginip(), tmp.getLoginip());
		Assert.assertEquals(manager2.getLoginip(), tmp.getLoginip());
		Assert.assertEquals("测试2", tmp.getLoginip());
		Assert.assertThat(manager2.getRoleId(),equalTo(tmp.getRoleId()));
	}
	
	@Test
	public void deleteByIds(){
		
		Manager manager = addTestManager();
		
		Manager obj = new Manager();
		obj.setId(manager.getId());
		Assert.assertNotNull(managerService.selectManagerById(obj));
		int res = managerService.deleteByIds("where id = "+manager.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(managerService.selectManagerById(obj));
	}
}