package com.dihaitech.acomp.controller.action.linkPage;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.service.ILinkPageService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 友链页面 Action 测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class TestLinkPageAction extends CommonTestAction {
	private LinkPageAction test;
	private ILinkPageService linkPageService;
	
	@Override
	public String getNameSpace() {
		return "/admin/linkPage/linkPageAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (LinkPageAction) proxy.getAction();
		if(null == linkPageService)
			linkPageService =(ILinkPageService) applicationContext.getBean("linkPageService");
	}
	
	private LinkPage buildTestModel(){
	
		LinkPage linkPage = new LinkPage();
		linkPage.setPageName("测试");
		linkPage.setIncludePath("测试");
		linkPage.setDescription("测试");
		linkPage.setTempleteId(1);
		linkPage.setCreateuser("测试");
        linkPage.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkPage.setUpdateuser("测试");
        linkPage.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return linkPage;
	}
	private LinkPage addTestLinkPage(){
		LinkPage linkPage = buildTestModel();
	    linkPageService.addSave(linkPage);
	    return linkPage;
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
		LinkPage linkPage = buildTestModel();
	    test.setLinkPage(linkPage);
		String res = test.addSave();
		linkPageService.deleteByIds(" where id = "+linkPage.getId());
		Assert.assertNotNull(test.getLinkPage());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		LinkPage linkPage = addTestLinkPage();
		request.setParameter("id", ""+linkPage.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		LinkPage a = (LinkPage) request.getAttribute("linkPage");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(linkPage.getId(), a.getId());
		linkPageService.deleteByIds(" where id = "+linkPage.getId());
	}
	@Test
	public void testEditSave(){
		
		LinkPage linkPage1 = addTestLinkPage();
	    LinkPage linkPage2 = buildTestModel();
	    linkPage2.setId(linkPage1.getId());
		test.setLinkPage(linkPage2);
		String edit_save_res = test.editSave();
		linkPageService.selectLinkPageById(linkPage1);
		Assert.assertEquals("editSave", edit_save_res);
		
		linkPageService.deleteByIds(" where id = "+linkPage1.getId());
	}
	@Test
	public void testDelete(){
		LinkPage linkPage = addTestLinkPage();
		request.setParameter("id", linkPage.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(linkPageService.selectLinkPageById(linkPage));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		LinkPage linkPage0 = addTestLinkPage();
		ids[0]=linkPage0.getId()+"";
		LinkPage linkPage1 = addTestLinkPage();
		ids[1]=linkPage1.getId()+"";
		LinkPage linkPage2 = addTestLinkPage();
		ids[2]=linkPage2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(linkPageService.selectLinkPageById(linkPage0));
	}
}