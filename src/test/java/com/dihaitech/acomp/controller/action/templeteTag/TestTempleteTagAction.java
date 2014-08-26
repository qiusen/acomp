package com.dihaitech.acomp.controller.action.templeteTag;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.TempleteTag;
import com.dihaitech.acomp.service.ITempleteTagService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 模板标签 Action 测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class TestTempleteTagAction extends CommonTestAction {
	private TempleteTagAction test;
	private ITempleteTagService templeteTagService;
	
	@Override
	public String getNameSpace() {
		return "/admin/templeteTag/templeteTagAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (TempleteTagAction) proxy.getAction();
		if(null == templeteTagService)
			templeteTagService =(ITempleteTagService) applicationContext.getBean("templeteTagService");
	}
	
	private TempleteTag buildTestModel(){
	
		TempleteTag templeteTag = new TempleteTag();
		templeteTag.setName("测试");
		templeteTag.setCode("测试");
		templeteTag.setType(123);
		templeteTag.setCreateuser("测试");
        templeteTag.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templeteTag.setUpdateuser("测试");
        templeteTag.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return templeteTag;
	}
	private TempleteTag addTestTempleteTag(){
		TempleteTag templeteTag = buildTestModel();
	    templeteTagService.addSave(templeteTag);
	    return templeteTag;
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
		TempleteTag templeteTag = buildTestModel();
	    test.setTempleteTag(templeteTag);
		String res = test.addSave();
		templeteTagService.deleteByIds(" where id = "+templeteTag.getId());
		Assert.assertNotNull(test.getTempleteTag());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		TempleteTag templeteTag = addTestTempleteTag();
		request.setParameter("id", ""+templeteTag.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		TempleteTag a = (TempleteTag) request.getAttribute("templeteTag");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(templeteTag.getId(), a.getId());
		templeteTagService.deleteByIds(" where id = "+templeteTag.getId());
	}
	@Test
	public void testEditSave(){
		
		TempleteTag templeteTag1 = addTestTempleteTag();
	    TempleteTag templeteTag2 = buildTestModel();
	    templeteTag2.setId(templeteTag1.getId());
		test.setTempleteTag(templeteTag2);
		String edit_save_res = test.editSave();
		templeteTagService.selectTempleteTagById(templeteTag1);
		Assert.assertEquals("editSave", edit_save_res);
		
		templeteTagService.deleteByIds(" where id = "+templeteTag1.getId());
	}
	@Test
	public void testDelete(){
		TempleteTag templeteTag = addTestTempleteTag();
		request.setParameter("id", templeteTag.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(templeteTagService.selectTempleteTagById(templeteTag));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		TempleteTag templeteTag0 = addTestTempleteTag();
		ids[0]=templeteTag0.getId()+"";
		TempleteTag templeteTag1 = addTestTempleteTag();
		ids[1]=templeteTag1.getId()+"";
		TempleteTag templeteTag2 = addTestTempleteTag();
		ids[2]=templeteTag2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(templeteTagService.selectTempleteTagById(templeteTag0));
	}
}