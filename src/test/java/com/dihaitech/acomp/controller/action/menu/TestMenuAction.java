package com.dihaitech.acomp.controller.action.menu;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Menu;
import com.dihaitech.acomp.service.IMenuService;
import com.dihaitech.acomp.util.DateUtil;


/**
 * 菜单 Action 测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class TestMenuAction extends CommonTestAction {
	private MenuAction test;
	private IMenuService menuService;
	
	@Override
	public String getNameSpace() {
		return "/admin/menu/menuAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (MenuAction) proxy.getAction();
		if(null == menuService)
			menuService =(IMenuService) applicationContext.getBean("menuService");
	}
	
	private Menu buildTestModel(){
	
		Menu menu = new Menu();
		menu.setMenuname("测试");
		menu.setStatus(123);
		menu.setOrdernum(123);
        menu.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return menu;
	}
	private Menu addTestMenu(){
		Menu menu = buildTestModel();
	    menuService.addSave(menu);
	    return menu;
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
		Menu menu = buildTestModel();
	    test.setMenu(menu);
		String res = test.addSave();
		menuService.deleteByIds(" where id = "+menu.getId());
		Assert.assertNotNull(test.getMenu());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Menu menu = addTestMenu();
		request.setParameter("id", ""+menu.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Menu a = (Menu) request.getAttribute("menu");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(menu.getId(), a.getId());
		menuService.deleteByIds(" where id = "+menu.getId());
	}
	@Test
	public void testEditSave(){
		
		Menu menu1 = addTestMenu();
	    Menu menu2 = buildTestModel();
	    menu2.setId(menu1.getId());
		test.setMenu(menu2);
		String edit_save_res = test.editSave();
		menuService.selectMenuById(menu1);
		Assert.assertEquals("editSave", edit_save_res);
		
		menuService.deleteByIds(" where id = "+menu1.getId());
	}
	@Test
	public void testDelete(){
		Menu menu = addTestMenu();
		request.setParameter("id", menu.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(menuService.selectMenuById(menu));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Menu menu0 = addTestMenu();
		ids[0]=menu0.getId()+"";
		Menu menu1 = addTestMenu();
		ids[1]=menu1.getId()+"";
		Menu menu2 = addTestMenu();
		ids[2]=menu2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(menuService.selectMenuById(menu0));
	}
}