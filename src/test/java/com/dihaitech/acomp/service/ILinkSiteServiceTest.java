package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;

/**
 * 友链网站 Service 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkSiteServiceTest extends BaseTestCase{
	@Autowired
	private ILinkSiteService linkSiteService;
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
	public void selectLinkSitePage(){
		addTestLinkSite();
	    Page p = linkSiteService.selectLinkSite(new LinkSite(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectLinkSitePageList(){
		
		addTestLinkSite();
		
		LinkSite obj = new LinkSite();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<LinkSite> linkSiteList = linkSiteService.selectLinkSite(obj, page);
		Assert.assertTrue(linkSiteList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestLinkSite();
		
		Assert.assertTrue("fail: linkSite`s total is bad res!", linkSiteService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		LinkSite linkSite = buildTestModel();
		int pr = linkSiteService.selectAll().size();
		int res = linkSiteService.addSave(linkSite);
		int s = linkSiteService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(linkSiteService.selectLinkSiteById(linkSite));
		Assert.assertTrue("Fail:call linkSiteService insert fail!", s==pr+1);
	}

	@Test
	public void selectLinkSiteById(){
		
		LinkSite linkSite = addTestLinkSite();
		
		LinkSite tmp = new LinkSite();
		tmp.setId(linkSite.getId());
		Assert.assertNotNull(linkSiteService.selectLinkSiteById(tmp));
	}

	@Test
	public void editSave(){
		
		LinkSite linkSite1 = addTestLinkSite();
		
	    LinkSite linkSite2 = linkSiteService.selectLinkSiteById(linkSite1);
		linkSite2.setSiteName("测试2");
		linkSite2.setSiteUrl("测试2");
		linkSite2.setSiteLogo("测试2");
		linkSite2.setDescription("测试2");
		linkSite2.setContact("测试2");
		linkSite2.setQq("测试2");
		linkSite2.setTel("测试2");
		linkSite2.setMobile("测试2");
		linkSite2.setEmail("测试2");
		linkSite2.setCreateuser("测试2");
        linkSite2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkSite2.setUpdateuser("测试2");
        linkSite2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkSiteService.editSave(linkSite2);
		
		LinkSite tmp = linkSiteService.selectLinkSiteById(linkSite2);
		Assert.assertNotEquals(linkSite1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(linkSite2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
	}
	
	@Test
	public void deleteByIds(){
		
		LinkSite linkSite = addTestLinkSite();
		
		LinkSite obj = new LinkSite();
		obj.setId(linkSite.getId());
		Assert.assertNotNull(linkSiteService.selectLinkSiteById(obj));
		int res = linkSiteService.deleteByIds("where id = "+linkSite.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkSiteService.selectLinkSiteById(obj));
	}
}