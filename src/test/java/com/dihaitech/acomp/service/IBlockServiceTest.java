package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Block;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 块 Service 接口测试
 * 
 * @author cg
 * @since 2014-09-02
 */
public class IBlockServiceTest extends BaseTestCase{
	@Autowired
	private IBlockService blockService;
	private Block buildTestModel(){
	
		Block block = new Block();
		block.setName("测试");
		block.setFileName("测试");
		block.setType(123);
		block.setDescription("测试");
		block.setIncludePath("测试");
		block.setSourceType(123);
		block.setDataType(123);
		block.setCondition("测试");
		block.setTempleteId(123);
		block.setCount(123);
		block.setInterfaceUrl("测试");
		block.setContent("测试");
		block.setCreateuser("测试");
        block.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		block.setUpdateuser("测试");
        block.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return block;
	}
	private Block addTestBlock(){
		Block block = buildTestModel();
	    blockService.addSave(block);
	    return block;
	}
	
	@Test
	public void selectBlockPage(){
		addTestBlock();
	    Page p = blockService.selectBlock(new Block(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectBlockPageList(){
		
		addTestBlock();
		
		Block obj = new Block();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Block> blockList = blockService.selectBlock(obj, page);
		Assert.assertTrue(blockList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestBlock();
		
		Assert.assertTrue("fail: block`s total is bad res!", blockService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Block block = buildTestModel();
		int pr = blockService.selectAll().size();
		int res = blockService.addSave(block);
		int s = blockService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(blockService.selectBlockById(block));
		Assert.assertTrue("Fail:call blockService insert fail!", s==pr+1);
	}

	@Test
	public void selectBlockById(){
		
		Block block = addTestBlock();
		
		Block tmp = new Block();
		tmp.setId(block.getId());
		Assert.assertNotNull(blockService.selectBlockById(tmp));
	}

	@Test
	public void editSave(){
		
		Block block1 = addTestBlock();
		
	    Block block2 = blockService.selectBlockById(block1);
		block2.setName("测试2");
		block2.setFileName("测试2");
		block2.setType(21);
		block2.setDescription("测试2");
		block2.setIncludePath("测试2");
		block2.setSourceType(21);
		block2.setDataType(21);
		block2.setCondition("测试2");
		block2.setTempleteId(21);
		block2.setCount(21);
		block2.setInterfaceUrl("测试2");
		block2.setContent("测试2");
		block2.setCreateuser("测试2");
        block2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		block2.setUpdateuser("测试2");
        block2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		blockService.editSave(block2);
		
		Block tmp = blockService.selectBlockById(block2);
		Assert.assertNotEquals(block1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(block2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(block2.getCount(),equalTo(tmp.getCount()));
	}
	
	@Test
	public void deleteByIds(){
		
		Block block = addTestBlock();
		
		Block obj = new Block();
		obj.setId(block.getId());
		Assert.assertNotNull(blockService.selectBlockById(obj));
		int res = blockService.deleteByIds("where id = "+block.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(blockService.selectBlockById(obj));
	}
}