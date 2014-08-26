package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.TempleteTag;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 模板标签 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class ITempleteTagDAOTest extends BaseTestCase {
	@Resource
	private ITempleteTagDAO templeteTagDAO;
	private TempleteTag buildTestModel(){
	
		TempleteTag templeteTag = new TempleteTag();
		templeteTag.setName("测试");
		templeteTag.setCode("测试");
		templeteTag.setType(123);
		templeteTag.setCreateuser("测试");
        templeteTag.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templeteTag.setUpdateuser("测试");
        templeteTag.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return templeteTag;
	}
	private TempleteTag addTestTempleteTag(){
		TempleteTag templeteTag = buildTestModel();
	    templeteTagDAO.addSaveTempleteTag(templeteTag);
	    return templeteTag;
	}
	@Test
	public void getTempleteTagCount() {
		addTestTempleteTag();
		TempleteTag total_obj = new TempleteTag();
		Long total = templeteTagDAO.getTempleteTagCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectTempleteTagLike() {
		addTestTempleteTag();
		TempleteTag tmp = new TempleteTag();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<TempleteTag> l = templeteTagDAO.selectTempleteTagLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectTempleteTagById() {
		TempleteTag obj = addTestTempleteTag();
		TempleteTag tmp = new TempleteTag();
		tmp.setId(obj.getId());
		TempleteTag res = templeteTagDAO.selectTempleteTagById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveTempleteTag(){
		TempleteTag obj = buildTestModel();
		int res = templeteTagDAO.addSaveTempleteTag(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		templeteTagDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveTempleteTag(){
		TempleteTag add_obj = addTestTempleteTag();
		add_obj.setName("测试2");
		add_obj.setCode("测试2");
		add_obj.setType(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = templeteTagDAO.editSaveTempleteTag(add_obj);
		Assert.assertTrue(edit_res>0);
		
		templeteTagDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		TempleteTag obj = addTestTempleteTag();
		
		int res = templeteTagDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(templeteTagDAO.selectTempleteTagById(obj));
	}
}