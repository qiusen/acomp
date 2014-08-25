package com.dihaitech.acomp.controller.action.dict;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.Dict;
import com.dihaitech.acomp.service.IDictService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 字典 Action 测试
 * 
 * @author cg
 * @since 2014-08-25
 */
public class TestDictAction extends CommonTestAction {
	private DictAction test;
	private IDictService dictService;
	
	@Override
	public String getNameSpace() {
		return "/admin/dict/dictAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (DictAction) proxy.getAction();
		if(null == dictService)
			dictService =(IDictService) applicationContext.getBean("dictService");
	}
	
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
		Dict dict = buildTestModel();
	    test.setDict(dict);
		String res = test.addSave();
		dictService.deleteByIds(" where id = "+dict.getId());
		Assert.assertNotNull(test.getDict());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Dict dict = addTestDict();
		request.setParameter("id", ""+dict.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Dict a = (Dict) request.getAttribute("dict");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(dict.getId(), a.getId());
		dictService.deleteByIds(" where id = "+dict.getId());
	}
	@Test
	public void testEditSave(){
		
		Dict dict1 = addTestDict();
	    Dict dict2 = buildTestModel();
	    dict2.setId(dict1.getId());
		test.setDict(dict2);
		String edit_save_res = test.editSave();
		dictService.selectDictById(dict1);
		Assert.assertEquals("editSave", edit_save_res);
		
		dictService.deleteByIds(" where id = "+dict1.getId());
	}
	@Test
	public void testDelete(){
		Dict dict = addTestDict();
		request.setParameter("id", dict.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(dictService.selectDictById(dict));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Dict dict0 = addTestDict();
		ids[0]=dict0.getId()+"";
		Dict dict1 = addTestDict();
		ids[1]=dict1.getId()+"";
		Dict dict2 = addTestDict();
		ids[2]=dict2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(dictService.selectDictById(dict0));
	}
}