package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;

/**
 * 管理员 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IManagerDAOTest extends BaseTestCase {
	@Resource
	private IManagerDAO managerDAO;
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
	    managerDAO.addSaveManager(manager);
	    return manager;
	}
	@Test
	public void getManagerCount() {
		addTestManager();
		Manager total_obj = new Manager();
		Long total = managerDAO.getManagerCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectManagerLike() {
		addTestManager();
		Manager tmp = new Manager();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Manager> l = managerDAO.selectManagerLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectManagerById() {
		Manager obj = addTestManager();
		Manager tmp = new Manager();
		tmp.setId(obj.getId());
		Manager res = managerDAO.selectManagerById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveManager(){
		Manager obj = buildTestModel();
		int res = managerDAO.addSaveManager(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		managerDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveManager(){
		Manager add_obj = addTestManager();
		add_obj.setUsername("测试2");
		add_obj.setPassword("测试2");
		add_obj.setEmail("测试2");
		add_obj.setNickname("测试2");
		add_obj.setStatus(21);
		add_obj.setRoleId(21);
		add_obj.setCreator("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
        add_obj.setLogintime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setLoginip("测试2");
		
		int edit_res = managerDAO.editSaveManager(add_obj);
		Assert.assertTrue(edit_res>0);
		
		managerDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Manager obj = addTestManager();
		
		int res = managerDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(managerDAO.selectManagerById(obj));
	}
}