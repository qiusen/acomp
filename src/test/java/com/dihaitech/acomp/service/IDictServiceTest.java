package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Dict;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 字典 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-25
 */
public class IDictServiceTest extends BaseTestCase{
	@Autowired
	private IDictService dictService;
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
	    dictService.addSave(dict);
	    return dict;
	}
	
	@Test
	public void selectDictPage(){
		addTestDict();
	    Page p = dictService.selectDict(new Dict(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectDictPageList(){
		
		addTestDict();
		
		Dict obj = new Dict();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Dict> dictList = dictService.selectDict(obj, page);
		Assert.assertTrue(dictList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestDict();
		
		Assert.assertTrue("fail: dict`s total is bad res!", dictService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Dict dict = buildTestModel();
		int pr = dictService.selectAll().size();
		int res = dictService.addSave(dict);
		int s = dictService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(dictService.selectDictById(dict));
		Assert.assertTrue("Fail:call dictService insert fail!", s==pr+1);
	}

	@Test
	public void selectDictById(){
		
		Dict dict = addTestDict();
		
		Dict tmp = new Dict();
		tmp.setId(dict.getId());
		Assert.assertNotNull(dictService.selectDictById(tmp));
	}

	@Test
	public void editSave(){
		
		Dict dict1 = addTestDict();
		
	    Dict dict2 = dictService.selectDictById(dict1);
		dict2.setCode("测试2");
		dict2.setName("测试2");
		dict2.setGroup("测试2");
		dict2.setValue("测试2");
		dict2.setCreateuser("测试2");
        dict2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		dict2.setUpdateuser("测试2");
        dict2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		dictService.editSave(dict2);
		
		Dict tmp = dictService.selectDictById(dict2);
		Assert.assertNotEquals(dict1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(dict2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
	}
	
	@Test
	public void deleteByIds(){
		
		Dict dict = addTestDict();
		
		Dict obj = new Dict();
		obj.setId(dict.getId());
		Assert.assertNotNull(dictService.selectDictById(obj));
		int res = dictService.deleteByIds("where id = "+dict.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(dictService.selectDictById(obj));
	}
}