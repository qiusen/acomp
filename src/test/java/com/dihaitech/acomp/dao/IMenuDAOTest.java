package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Menu;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;


/**
 * 菜单 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-18
 */
public class IMenuDAOTest extends BaseTestCase {
	@Resource
	private IMenuDAO menuDAO;
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
	    menuDAO.addSaveMenu(menu);
	    return menu;
	}
	@Test
	public void getMenuCount() {
		addTestMenu();
		Menu total_obj = new Menu();
		Long total = menuDAO.getMenuCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectMenuLike() {
		addTestMenu();
		Menu tmp = new Menu();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Menu> l = menuDAO.selectMenuLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectMenuById() {
		Menu obj = addTestMenu();
		Menu tmp = new Menu();
		tmp.setId(obj.getId());
		Menu res = menuDAO.selectMenuById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveMenu(){
		Menu obj = buildTestModel();
		int res = menuDAO.addSaveMenu(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		menuDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveMenu(){
		Menu add_obj = addTestMenu();
		add_obj.setMenuname("测试2");
		add_obj.setStatus(21);
		add_obj.setOrdernum(21);
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = menuDAO.editSaveMenu(add_obj);
		Assert.assertTrue(edit_res>0);
		
		menuDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Menu obj = addTestMenu();
		
		int res = menuDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(menuDAO.selectMenuById(obj));
	}
}