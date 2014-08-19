package com.dihaitech.acomp.service;

import static org.hamcrest.CoreMatchers.equalTo;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Menu;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 菜单 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IMenuServiceTest extends BaseTestCase{
	@Autowired
	private IMenuService menuService;
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
	public void selectMenuPage(){
		addTestMenu();
	    Page p = menuService.selectMenu(new Menu(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectMenuPageList(){
		
		addTestMenu();
		
		Menu obj = new Menu();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Menu> menuList = menuService.selectMenu(obj, page);
		Assert.assertTrue(menuList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestMenu();
		
		Assert.assertTrue("fail: menu`s total is bad res!", menuService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Menu menu = buildTestModel();
		int pr = menuService.selectAll().size();
		int res = menuService.addSave(menu);
		int s = menuService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(menuService.selectMenuById(menu));
		Assert.assertTrue("Fail:call menuService insert fail!", s==pr+1);
	}

	@Test
	public void selectMenuById(){
		
		Menu menu = addTestMenu();
		
		Menu tmp = new Menu();
		tmp.setId(menu.getId());
		Assert.assertNotNull(menuService.selectMenuById(tmp));
	}

	@Test
	public void editSave(){
		
		Menu menu1 = addTestMenu();
		
	    Menu menu2 = menuService.selectMenuById(menu1);
		menu2.setMenuname("测试2");
		menu2.setStatus(21);
		menu2.setOrdernum(21);
        menu2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		menuService.editSave(menu2);
		
		Menu tmp = menuService.selectMenuById(menu2);
		Assert.assertNotEquals(menu1.getMenuname(), tmp.getMenuname());
		Assert.assertEquals(menu2.getMenuname(), tmp.getMenuname());
		Assert.assertEquals("测试2", tmp.getMenuname());
		Assert.assertThat(menu2.getOrdernum(),equalTo(tmp.getOrdernum()));
	}
	
	@Test
	public void deleteByIds(){
		
		Menu menu = addTestMenu();
		
		Menu obj = new Menu();
		obj.setId(menu.getId());
		Assert.assertNotNull(menuService.selectMenuById(obj));
		int res = menuService.deleteByIds("where id = "+menu.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(menuService.selectMenuById(obj));
	}
}