package com.dihaitech.acomp.controller.action.role;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Role;
import com.dihaitech.acomp.service.IRoleService;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 角色 Action 测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class TestRoleAction extends CommonTestAction {
	private RoleAction test;
	private IRoleService roleService;
	
	@Override
	public String getNameSpace() {
		return "/admin/role/roleAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (RoleAction) proxy.getAction();
		if(null == roleService)
			roleService =(IRoleService) applicationContext.getBean("roleService");
	}
	
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
		Role role = buildTestModel();
	    test.setRole(role);
		String res = test.addSave();
		roleService.deleteByIds(" where id = "+role.getId());
		Assert.assertNotNull(test.getRole());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Role role = addTestRole();
		request.setParameter("id", ""+role.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Role a = (Role) request.getAttribute("role");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(role.getId(), a.getId());
		roleService.deleteByIds(" where id = "+role.getId());
	}
	@Test
	public void testEditSave(){
		
		Role role1 = addTestRole();
	    Role role2 = buildTestModel();
	    role2.setId(role1.getId());
		test.setRole(role2);
		String edit_save_res = test.editSave();
		roleService.selectRoleById(role1);
		Assert.assertEquals("editSave", edit_save_res);
		
		roleService.deleteByIds(" where id = "+role1.getId());
	}
	@Test
	public void testDelete(){
		Role role = addTestRole();
		request.setParameter("id", role.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(roleService.selectRoleById(role));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Role role0 = addTestRole();
		ids[0]=role0.getId()+"";
		Role role1 = addTestRole();
		ids[1]=role1.getId()+"";
		Role role2 = addTestRole();
		ids[2]=role2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(roleService.selectRoleById(role0));
	}
}