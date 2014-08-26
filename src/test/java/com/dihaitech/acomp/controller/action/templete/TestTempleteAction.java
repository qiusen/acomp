package com.dihaitech.acomp.controller.action.templete;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.ITempleteService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 模板 Action 测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class TestTempleteAction extends CommonTestAction {
	private TempleteAction test;
	private ITempleteService templeteService;
	
	@Override
	public String getNameSpace() {
		return "/admin/templete/templeteAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (TempleteAction) proxy.getAction();
		if(null == templeteService)
			templeteService =(ITempleteService) applicationContext.getBean("templeteService");
	}
	
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
	    templeteService.addSave(templete);
	    return templete;
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
		Templete templete = buildTestModel();
	    test.setTemplete(templete);
		String res = test.addSave();
		templeteService.deleteByIds(" where id = "+templete.getId());
		Assert.assertNotNull(test.getTemplete());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Templete templete = addTestTemplete();
		request.setParameter("id", ""+templete.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Templete a = (Templete) request.getAttribute("templete");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(templete.getId(), a.getId());
		templeteService.deleteByIds(" where id = "+templete.getId());
	}
	@Test
	public void testEditSave(){
		
		Templete templete1 = addTestTemplete();
	    Templete templete2 = buildTestModel();
	    templete2.setId(templete1.getId());
		test.setTemplete(templete2);
		String edit_save_res = test.editSave();
		templeteService.selectTempleteById(templete1);
		Assert.assertEquals("editSave", edit_save_res);
		
		templeteService.deleteByIds(" where id = "+templete1.getId());
	}
	@Test
	public void testDelete(){
		Templete templete = addTestTemplete();
		request.setParameter("id", templete.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(templeteService.selectTempleteById(templete));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Templete templete0 = addTestTemplete();
		ids[0]=templete0.getId()+"";
		Templete templete1 = addTestTemplete();
		ids[1]=templete1.getId()+"";
		Templete templete2 = addTestTemplete();
		ids[2]=templete2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(templeteService.selectTempleteById(templete0));
	}
}