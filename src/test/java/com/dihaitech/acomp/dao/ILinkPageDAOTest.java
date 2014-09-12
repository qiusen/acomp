package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 友链页面 DAO 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkPageDAOTest extends BaseTestCase {
	@Resource
	private ILinkPageDAO linkPageDAO;
	private LinkPage buildTestModel(){
	
		LinkPage linkPage = new LinkPage();
		linkPage.setPageName("测试");
		linkPage.setIncludePath("测试");
		linkPage.setDescription("测试");
		linkPage.setTempleteId(123);
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
	    linkPageDAO.addSaveLinkPage(linkPage);
	    return linkPage;
	}
	@Test
	public void getLinkPageCount() {
		addTestLinkPage();
		LinkPage total_obj = new LinkPage();
		Long total = linkPageDAO.getLinkPageCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectLinkPageLike() {
		addTestLinkPage();
		LinkPage tmp = new LinkPage();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<LinkPage> l = linkPageDAO.selectLinkPageLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectLinkPageById() {
		LinkPage obj = addTestLinkPage();
		LinkPage tmp = new LinkPage();
		tmp.setId(obj.getId());
		LinkPage res = linkPageDAO.selectLinkPageById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveLinkPage(){
		LinkPage obj = buildTestModel();
		int res = linkPageDAO.addSaveLinkPage(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		linkPageDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveLinkPage(){
		LinkPage add_obj = addTestLinkPage();
		add_obj.setPageName("测试2");
		add_obj.setIncludePath("测试2");
		add_obj.setDescription("测试2");
		add_obj.setTempleteId(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = linkPageDAO.editSaveLinkPage(add_obj);
		Assert.assertTrue(edit_res>0);
		
		linkPageDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		LinkPage obj = addTestLinkPage();
		
		int res = linkPageDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkPageDAO.selectLinkPageById(obj));
	}
}