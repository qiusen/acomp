package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkPage;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 友链页面 Service 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkPageServiceTest extends BaseTestCase{
	@Autowired
	private ILinkPageService linkPageService;
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
	    linkPageService.addSave(linkPage);
	    return linkPage;
	}
	
	@Test
	public void selectLinkPagePage(){
		addTestLinkPage();
	    Page p = linkPageService.selectLinkPage(new LinkPage(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectLinkPagePageList(){
		
		addTestLinkPage();
		
		LinkPage obj = new LinkPage();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<LinkPage> linkPageList = linkPageService.selectLinkPage(obj, page);
		Assert.assertTrue(linkPageList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestLinkPage();
		
		Assert.assertTrue("fail: linkPage`s total is bad res!", linkPageService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		LinkPage linkPage = buildTestModel();
		int pr = linkPageService.selectAll().size();
		int res = linkPageService.addSave(linkPage);
		int s = linkPageService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(linkPageService.selectLinkPageById(linkPage));
		Assert.assertTrue("Fail:call linkPageService insert fail!", s==pr+1);
	}

	@Test
	public void selectLinkPageById(){
		
		LinkPage linkPage = addTestLinkPage();
		
		LinkPage tmp = new LinkPage();
		tmp.setId(linkPage.getId());
		Assert.assertNotNull(linkPageService.selectLinkPageById(tmp));
	}

	@Test
	public void editSave(){
		
		LinkPage linkPage1 = addTestLinkPage();
		
	    LinkPage linkPage2 = linkPageService.selectLinkPageById(linkPage1);
		linkPage2.setPageName("测试2");
		linkPage2.setIncludePath("测试2");
		linkPage2.setDescription("测试2");
		linkPage2.setTempleteId(21);
		linkPage2.setCreateuser("测试2");
        linkPage2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkPage2.setUpdateuser("测试2");
        linkPage2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkPageService.editSave(linkPage2);
		
		LinkPage tmp = linkPageService.selectLinkPageById(linkPage2);
		Assert.assertNotEquals(linkPage1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(linkPage2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(linkPage2.getTempleteId(),equalTo(tmp.getTempleteId()));
	}
	
	@Test
	public void deleteByIds(){
		
		LinkPage linkPage = addTestLinkPage();
		
		LinkPage obj = new LinkPage();
		obj.setId(linkPage.getId());
		Assert.assertNotNull(linkPageService.selectLinkPageById(obj));
		int res = linkPageService.deleteByIds("where id = "+linkPage.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkPageService.selectLinkPageById(obj));
	}
}