package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.LinkRelation;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 友链网页与网站关系 DAO 接口测试
 * 
 * @author cg
 * @since 2014-09-04
 */
public class ILinkRelationDAOTest extends BaseTestCase {
	@Resource
	private ILinkRelationDAO linkRelationDAO;
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
	    linkRelationDAO.addSaveLinkRelation(linkRelation);
	    return linkRelation;
	}
	@Test
	public void getLinkRelationCount() {
		addTestLinkRelation();
		LinkRelation total_obj = new LinkRelation();
		Long total = linkRelationDAO.getLinkRelationCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectLinkRelationLike() {
		addTestLinkRelation();
		LinkRelation tmp = new LinkRelation();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<LinkRelation> l = linkRelationDAO.selectLinkRelationLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectLinkRelationById() {
		LinkRelation obj = addTestLinkRelation();
		LinkRelation tmp = new LinkRelation();
		tmp.setId(obj.getId());
		LinkRelation res = linkRelationDAO.selectLinkRelationById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveLinkRelation(){
		LinkRelation obj = buildTestModel();
		int res = linkRelationDAO.addSaveLinkRelation(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		linkRelationDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveLinkRelation(){
		LinkRelation add_obj = addTestLinkRelation();
		add_obj.setPageId(21);
		add_obj.setSiteId(21);
		add_obj.setOrdernum(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = linkRelationDAO.editSaveLinkRelation(add_obj);
		Assert.assertTrue(edit_res>0);
		
		linkRelationDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		LinkRelation obj = addTestLinkRelation();
		
		int res = linkRelationDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(linkRelationDAO.selectLinkRelationById(obj));
	}
}