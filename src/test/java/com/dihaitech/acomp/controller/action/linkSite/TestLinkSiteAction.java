package com.dihaitech.acomp.controller.action.linkSite;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.service.ILinkSiteService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 友链网站 Action 测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class TestLinkSiteAction extends CommonTestAction {
	private LinkSiteAction test;
	private ILinkSiteService linkSiteService;
	
	@Override
	public String getNameSpace() {
		return "/admin/linkSite/linkSiteAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (LinkSiteAction) proxy.getAction();
		if(null == linkSiteService)
			linkSiteService =(ILinkSiteService) applicationContext.getBean("linkSiteService");
	}
	
	private LinkSite buildTestModel(){
	
		LinkSite linkSite = new LinkSite();
		linkSite.setSiteName("测试");
		linkSite.setSiteUrl("测试");
		linkSite.setSiteLogo("测试");
		linkSite.setDescription("测试");
		linkSite.setContact("测试");
		linkSite.setQq("测试");
		linkSite.setTel("测试");
		linkSite.setMobile("测试");
		linkSite.setEmail("测试");
		linkSite.setCreateuser("测试");
        linkSite.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkSite.setUpdateuser("测试");
        linkSite.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return linkSite;
	}
	private LinkSite addTestLinkSite(){
		LinkSite linkSite = buildTestModel();
	    linkSiteService.addSave(linkSite);
	    return linkSite;
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
		LinkSite linkSite = buildTestModel();
	    test.setLinkSite(linkSite);
		String res = test.addSave();
		linkSiteService.deleteByIds(" where id = "+linkSite.getId());
		Assert.assertNotNull(test.getLinkSite());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		LinkSite linkSite = addTestLinkSite();
		request.setParameter("id", ""+linkSite.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		LinkSite a = (LinkSite) request.getAttribute("linkSite");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(linkSite.getId(), a.getId());
		linkSiteService.deleteByIds(" where id = "+linkSite.getId());
	}
	@Test
	public void testEditSave(){
		
		LinkSite linkSite1 = addTestLinkSite();
	    LinkSite linkSite2 = buildTestModel();
	    linkSite2.setId(linkSite1.getId());
		test.setLinkSite(linkSite2);
		String edit_save_res = test.editSave();
		linkSiteService.selectLinkSiteById(linkSite1);
		Assert.assertEquals("editSave", edit_save_res);
		
		linkSiteService.deleteByIds(" where id = "+linkSite1.getId());
	}
	@Test
	public void testDelete(){
		LinkSite linkSite = addTestLinkSite();
		request.setParameter("id", linkSite.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(linkSiteService.selectLinkSiteById(linkSite));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		LinkSite linkSite0 = addTestLinkSite();
		ids[0]=linkSite0.getId()+"";
		LinkSite linkSite1 = addTestLinkSite();
		ids[1]=linkSite1.getId()+"";
		LinkSite linkSite2 = addTestLinkSite();
		ids[2]=linkSite2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(linkSiteService.selectLinkSiteById(linkSite0));
	}
}