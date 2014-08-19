package com.dihaitech.acomp.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Role;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;
/**
 * 角色 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IRoleServiceTest extends BaseTestCase{
	@Autowired
	private IRoleService roleService;
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
	    roleService.addSave(role);
	    return role;
	}
	
	@Test
	public void selectRolePage(){
		addTestRole();
	    Page p = roleService.selectRole(new Role(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectRolePageList(){
		
		addTestRole();
		
		Role obj = new Role();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Role> roleList = roleService.selectRole(obj, page);
		Assert.assertTrue(roleList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestRole();
		
		Assert.assertTrue("fail: role`s total is bad res!", roleService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Role role = buildTestModel();
		int pr = roleService.selectAll().size();
		int res = roleService.addSave(role);
		int s = roleService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(roleService.selectRoleById(role));
		Assert.assertTrue("Fail:call roleService insert fail!", s==pr+1);
	}

	@Test
	public void selectRoleById(){
		
		Role role = addTestRole();
		
		Role tmp = new Role();
		tmp.setId(role.getId());
		Assert.assertNotNull(roleService.selectRoleById(tmp));
	}

	@Test
	public void editSave(){
		
		Role role1 = addTestRole();
		
	    Role role2 = roleService.selectRoleById(role1);
		role2.setRolename("测试2");
		role2.setStatus(21);
        role2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		role2.setRights("测试2");
		roleService.editSave(role2);
		
		Role tmp = roleService.selectRoleById(role2);
		Assert.assertNotEquals(role1.getRights(), tmp.getRights());
		Assert.assertEquals(role2.getRights(), tmp.getRights());
		Assert.assertEquals("测试2", tmp.getRights());
		Assert.assertThat(role2.getStatus(),equalTo(tmp.getStatus()));
	}
	
	@Test
	public void deleteByIds(){
		
		Role role = addTestRole();
		
		Role obj = new Role();
		obj.setId(role.getId());
		Assert.assertNotNull(roleService.selectRoleById(obj));
		int res = roleService.deleteByIds("where id = "+role.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(roleService.selectRoleById(obj));
	}
}