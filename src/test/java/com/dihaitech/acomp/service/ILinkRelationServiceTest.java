package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkRelation;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 友链网页与网站关系 Service 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkRelationServiceTest extends BaseTestCase{
	@Autowired
	private ILinkRelationService linkRelationService;
	private LinkRelation buildTestModel(){
	
		LinkRelation linkRelation = new LinkRelation();
		linkRelation.setPageId(123);
		linkRelation.setSiteId(123);
		linkRelation.setOrdernum(123);
		linkRelation.setCreateuser("测试");
        linkRelation.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkRelation.setUpdateuser("测试");
        linkRelation.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return linkRelation;
	}
	private LinkRelation addTestLinkRelation(){
		LinkRelation linkRelation = buildTestModel();
	    linkRelationService.addSave(linkRelation);
	    return linkRelation;
	}
	
	@Test
	public void selectLinkRelationPage(){
		addTestLinkRelation();
	    Page p = linkRelationService.selectLinkRelation(new LinkRelation(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectLinkRelationPageList(){
		
		addTestLinkRelation();
		
		LinkRelation obj = new LinkRelation();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<LinkRelation> linkRelationList = linkRelationService.selectLinkRelation(obj, page);
		Assert.assertTrue(linkRelationList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestLinkRelation();
		
		Assert.assertTrue("fail: linkRelation`s total is bad res!", linkRelationService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		LinkRelation linkRelation = buildTestModel();
		int pr = linkRelationService.selectAll().size();
		int res = linkRelationService.addSave(linkRelation);
		int s = linkRelationService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(linkRelationService.selectLinkRelationById(linkRelation));
		Assert.assertTrue("Fail:call linkRelationService insert fail!", s==pr+1);
	}

	@Test
	public void selectLinkRelationById(){
		
		LinkRelation linkRelation = addTestLinkRelation();
		
		LinkRelation tmp = new LinkRelation();
		tmp.setId(linkRelation.getId());
		Assert.assertNotNull(linkRelationService.selectLinkRelationById(tmp));
	}

	@Test
	public void editSave(){
		
		LinkRelation linkRelation1 = addTestLinkRelation();
		
	    LinkRelation linkRelation2 = linkRelationService.selectLinkRelationById(linkRelation1);
		linkRelation2.setPageId(21);
		linkRelation2.setSiteId(21);
		linkRelation2.setOrdernum(21);
		linkRelation2.setCreateuser("测试2");
        linkRelation2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkRelation2.setUpdateuser("测试2");
        linkRelation2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		linkRelationService.editSave(linkRelation2);
		
		LinkRelation tmp = linkRelationService.selectLinkRelationById(linkRelation2);
		Assert.assertNotEquals(linkRelation1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(linkRelation2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(linkRelation2.getOrdernum(),equalTo(tmp.getOrdernum()));
	}
	
	@Test
	public void deleteByIds(){
		
		LinkRelation linkRelation = addTestLinkRelation();
		
		LinkRelation obj = new LinkRelation();
		obj.setId(linkRelation.getId());
		Assert.assertNotNull(linkRelationService.selectLinkRelationById(obj));
		int res = linkRelationService.deleteByIds("where id = "+linkRelation.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkRelationService.selectLinkRelationById(obj));
	}
}