package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkSite;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 友链网站 DAO 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkSiteDAOTest extends BaseTestCase {
	@Resource
	private ILinkSiteDAO linkSiteDAO;
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
	    linkSiteDAO.addSaveLinkSite(linkSite);
	    return linkSite;
	}
	@Test
	public void getLinkSiteCount() {
		addTestLinkSite();
		LinkSite total_obj = new LinkSite();
		Long total = linkSiteDAO.getLinkSiteCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectLinkSiteLike() {
		addTestLinkSite();
		LinkSite tmp = new LinkSite();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<LinkSite> l = linkSiteDAO.selectLinkSiteLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectLinkSiteById() {
		LinkSite obj = addTestLinkSite();
		LinkSite tmp = new LinkSite();
		tmp.setId(obj.getId());
		LinkSite res = linkSiteDAO.selectLinkSiteById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveLinkSite(){
		LinkSite obj = buildTestModel();
		int res = linkSiteDAO.addSaveLinkSite(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		linkSiteDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveLinkSite(){
		LinkSite add_obj = addTestLinkSite();
		add_obj.setSiteName("测试2");
		add_obj.setSiteUrl("测试2");
		add_obj.setSiteLogo("测试2");
		add_obj.setDescription("测试2");
		add_obj.setContact("测试2");
		add_obj.setQq("测试2");
		add_obj.setTel("测试2");
		add_obj.setMobile("测试2");
		add_obj.setEmail("测试2");
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = linkSiteDAO.editSaveLinkSite(add_obj);
		Assert.assertTrue(edit_res>0);
		
		linkSiteDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		LinkSite obj = addTestLinkSite();
		
		int res = linkSiteDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkSiteDAO.selectLinkSiteById(obj));
	}
}