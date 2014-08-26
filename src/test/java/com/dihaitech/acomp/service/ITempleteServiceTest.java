package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 模板 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class ITempleteServiceTest extends BaseTestCase{
	@Autowired
	private ITempleteService templeteService;
	private Templete buildTestModel(){
	
		Templete templete = new Templete();
		templete.setName("测试");
		templete.setType(123);
		templete.setContent("测试");
		templete.setCreateuser("测试");
        templete.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templete.setUpdateuser("测试");
        templete.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return templete;
	}
	private Templete addTestTemplete(){
		Templete templete = buildTestModel();
	    templeteService.addSave(templete);
	    return templete;
	}
	
	@Test
	public void selectTempletePage(){
		addTestTemplete();
	    Page p = templeteService.selectTemplete(new Templete(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectTempletePageList(){
		
		addTestTemplete();
		
		Templete obj = new Templete();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Templete> templeteList = templeteService.selectTemplete(obj, page);
		Assert.assertTrue(templeteList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestTemplete();
		
		Assert.assertTrue("fail: templete`s total is bad res!", templeteService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Templete templete = buildTestModel();
		int pr = templeteService.selectAll().size();
		int res = templeteService.addSave(templete);
		int s = templeteService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(templeteService.selectTempleteById(templete));
		Assert.assertTrue("Fail:call templeteService insert fail!", s==pr+1);
	}

	@Test
	public void selectTempleteById(){
		
		Templete templete = addTestTemplete();
		
		Templete tmp = new Templete();
		tmp.setId(templete.getId());
		Assert.assertNotNull(templeteService.selectTempleteById(tmp));
	}

	@Test
	public void editSave(){
		
		Templete templete1 = addTestTemplete();
		
	    Templete templete2 = templeteService.selectTempleteById(templete1);
		templete2.setName("测试2");
		templete2.setType(21);
		templete2.setContent("测试2");
		templete2.setCreateuser("测试2");
        templete2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templete2.setUpdateuser("测试2");
        templete2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templeteService.editSave(templete2);
		
		Templete tmp = templeteService.selectTempleteById(templete2);
		Assert.assertNotEquals(templete1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(templete2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(templete2.getType(),equalTo(tmp.getType()));
	}
	
	@Test
	public void deleteByIds(){
		
		Templete templete = addTestTemplete();
		
		Templete obj = new Templete();
		obj.setId(templete.getId());
		Assert.assertNotNull(templeteService.selectTempleteById(obj));
		int res = templeteService.deleteByIds("where id = "+templete.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(templeteService.selectTempleteById(obj));
	}
}