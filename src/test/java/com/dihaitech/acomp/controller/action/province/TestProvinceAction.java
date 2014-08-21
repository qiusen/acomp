package com.dihaitech.acomp.controller.action.province;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Province;
import com.dihaitech.acomp.service.IProvinceService;

/**
 * 省 Action 测试
 * 
 * @author cg
 * @since 2014-08-20
 */
public class TestProvinceAction extends CommonTestAction {
	private ProvinceAction test;
	private IProvinceService provinceService;
	
	@Override
	public String getNameSpace() {
		return "/admin/province/provinceAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ProvinceAction) proxy.getAction();
		if(null == provinceService)
			provinceService =(IProvinceService) applicationContext.getBean("provinceService");
	}
	
	private Province buildTestModel(){
	
		Province province = new Province();
		province.setCode("测试");
		province.setName("测试");
	    return province;
	}
	private Province addTestProvince(){
		Province province = buildTestModel();
	    provinceService.addSave(province);
	    return province;
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
		Province province = buildTestModel();
	    test.setProvince(province);
		String res = test.addSave();
		provinceService.deleteByIds(" where id = "+province.getId());
		Assert.assertNotNull(test.getProvince());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Province province = addTestProvince();
		request.setParameter("id", ""+province.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Province a = (Province) request.getAttribute("province");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(province.getId(), a.getId());
		provinceService.deleteByIds(" where id = "+province.getId());
	}
	@Test
	public void testEditSave(){
		
		Province province1 = addTestProvince();
	    Province province2 = buildTestModel();
	    province2.setId(province1.getId());
		test.setProvince(province2);
		String edit_save_res = test.editSave();
		provinceService.selectProvinceById(province1);
		Assert.assertEquals("editSave", edit_save_res);
		
		provinceService.deleteByIds(" where id = "+province1.getId());
	}
	@Test
	public void testDelete(){
		Province province = addTestProvince();
		request.setParameter("id", province.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(provinceService.selectProvinceById(province));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Province province0 = addTestProvince();
		ids[0]=province0.getId()+"";
		Province province1 = addTestProvince();
		ids[1]=province1.getId()+"";
		Province province2 = addTestProvince();
		ids[2]=province2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(provinceService.selectProvinceById(province0));
	}
}