package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Role;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;

/**
 * 角色 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IRoleDAOTest extends BaseTestCase {
	@Resource
	private IRoleDAO roleDAO;
	private Role buildTestModel(){
	
		Role role = new Role();
		role.setRolename("测试");
		role.setStatus(123);
        role.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		role.setRights("测试");
	    return role;
	}
	private Role addTestRole(){
		Role role = buildTestModel();
	    roleDAO.addSaveRole(role);
	    return role;
	}
	@Test
	public void getRoleCount() {
		addTestRole();
		Role total_obj = new Role();
		Long total = roleDAO.getRoleCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectRoleLike() {
		addTestRole();
		Role tmp = new Role();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Role> l = roleDAO.selectRoleLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectRoleById() {
		Role obj = addTestRole();
		Role tmp = new Role();
		tmp.setId(obj.getId());
		Role res = roleDAO.selectRoleById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveRole(){
		Role obj = buildTestModel();
		int res = roleDAO.addSaveRole(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		roleDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveRole(){
		Role add_obj = addTestRole();
		add_obj.setRolename("测试2");
		add_obj.setStatus(21);
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setRights("测试2");
		
		int edit_res = roleDAO.editSaveRole(add_obj);
		Assert.assertTrue(edit_res>0);
		
		roleDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Role obj = addTestRole();
		
		int res = roleDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(roleDAO.selectRoleById(obj));
	}
}