package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 模板 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class ITempleteDAOTest extends BaseTestCase {
	@Resource
	private ITempleteDAO templeteDAO;
	private Templete buildTestModel(){
	
		Templete templete = new Templete();
		templete.setName("测试");
		templete.setType(123);
		templete.setContent("测试");
		templete.setCreateuser("测试");
        templete.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templete.setUpdateuser("测试");
        templete.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return templete;
	}
	private Templete addTestTemplete(){
		Templete templete = buildTestModel();
	    templeteDAO.addSaveTemplete(templete);
	    return templete;
	}
	@Test
	public void getTempleteCount() {
		addTestTemplete();
		Templete total_obj = new Templete();
		Long total = templeteDAO.getTempleteCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectTempleteLike() {
		addTestTemplete();
		Templete tmp = new Templete();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Templete> l = templeteDAO.selectTempleteLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectTempleteById() {
		Templete obj = addTestTemplete();
		Templete tmp = new Templete();
		tmp.setId(obj.getId());
		Templete res = templeteDAO.selectTempleteById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveTemplete(){
		Templete obj = buildTestModel();
		int res = templeteDAO.addSaveTemplete(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		templeteDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveTemplete(){
		Templete add_obj = addTestTemplete();
		add_obj.setName("测试2");
		add_obj.setType(21);
		add_obj.setContent("测试2");
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = templeteDAO.editSaveTemplete(add_obj);
		Assert.assertTrue(edit_res>0);
		
		templeteDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Templete obj = addTestTemplete();
		
		int res = templeteDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(templeteDAO.selectTempleteById(obj));
	}
}