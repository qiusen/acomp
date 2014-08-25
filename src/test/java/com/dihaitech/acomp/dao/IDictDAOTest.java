package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Dict;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 字典 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-25
 */
public class IDictDAOTest extends BaseTestCase {
	@Resource
	private IDictDAO dictDAO;
	private Dict buildTestModel(){
	
		Dict dict = new Dict();
		dict.setCode("测试");
		dict.setName("测试");
		dict.setGroup("测试");
		dict.setValue("测试");
		dict.setCreateuser("测试");
        dict.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		dict.setUpdateuser("测试");
        dict.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return dict;
	}
	private Dict addTestDict(){
		Dict dict = buildTestModel();
	    dictDAO.addSaveDict(dict);
	    return dict;
	}
	@Test
	public void getDictCount() {
		addTestDict();
		Dict total_obj = new Dict();
		Long total = dictDAO.getDictCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectDictLike() {
		addTestDict();
		Dict tmp = new Dict();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Dict> l = dictDAO.selectDictLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectDictById() {
		Dict obj = addTestDict();
		Dict tmp = new Dict();
		tmp.setId(obj.getId());
		Dict res = dictDAO.selectDictById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveDict(){
		Dict obj = buildTestModel();
		int res = dictDAO.addSaveDict(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		dictDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveDict(){
		Dict add_obj = addTestDict();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		add_obj.setGroup("测试2");
		add_obj.setValue("测试2");
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = dictDAO.editSaveDict(add_obj);
		Assert.assertTrue(edit_res>0);
		
		dictDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Dict obj = addTestDict();
		
		int res = dictDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(dictDAO.selectDictById(obj));
	}
}