package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.TempleteTag;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 模板标签 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class ITempleteTagServiceTest extends BaseTestCase{
	@Autowired
	private ITempleteTagService templeteTagService;
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
	    templeteTagService.addSave(templeteTag);
	    return templeteTag;
	}
	
	@Test
	public void selectTempleteTagPage(){
		addTestTempleteTag();
	    Page p = templeteTagService.selectTempleteTag(new TempleteTag(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectTempleteTagPageList(){
		
		addTestTempleteTag();
		
		TempleteTag obj = new TempleteTag();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<TempleteTag> templeteTagList = templeteTagService.selectTempleteTag(obj, page);
		Assert.assertTrue(templeteTagList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestTempleteTag();
		
		Assert.assertTrue("fail: templeteTag`s total is bad res!", templeteTagService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		TempleteTag templeteTag = buildTestModel();
		int pr = templeteTagService.selectAll().size();
		int res = templeteTagService.addSave(templeteTag);
		int s = templeteTagService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(templeteTagService.selectTempleteTagById(templeteTag));
		Assert.assertTrue("Fail:call templeteTagService insert fail!", s==pr+1);
	}

	@Test
	public void selectTempleteTagById(){
		
		TempleteTag templeteTag = addTestTempleteTag();
		
		TempleteTag tmp = new TempleteTag();
		tmp.setId(templeteTag.getId());
		Assert.assertNotNull(templeteTagService.selectTempleteTagById(tmp));
	}

	@Test
	public void editSave(){
		
		TempleteTag templeteTag1 = addTestTempleteTag();
		
	    TempleteTag templeteTag2 = templeteTagService.selectTempleteTagById(templeteTag1);
		templeteTag2.setName("测试2");
		templeteTag2.setCode("测试2");
		templeteTag2.setType(21);
		templeteTag2.setCreateuser("测试2");
        templeteTag2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templeteTag2.setUpdateuser("测试2");
        templeteTag2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		templeteTagService.editSave(templeteTag2);
		
		TempleteTag tmp = templeteTagService.selectTempleteTagById(templeteTag2);
		Assert.assertNotEquals(templeteTag1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(templeteTag2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(templeteTag2.getType(),equalTo(tmp.getType()));
	}
	
	@Test
	public void deleteByIds(){
		
		TempleteTag templeteTag = addTestTempleteTag();
		
		TempleteTag obj = new TempleteTag();
		obj.setId(templeteTag.getId());
		Assert.assertNotNull(templeteTagService.selectTempleteTagById(obj));
		int res = templeteTagService.deleteByIds("where id = "+templeteTag.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(templeteTagService.selectTempleteTagById(obj));
	}
}