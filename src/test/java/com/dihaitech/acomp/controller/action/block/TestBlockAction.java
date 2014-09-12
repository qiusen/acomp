package com.dihaitech.acomp.controller.action.block;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.Block;
import com.dihaitech.acomp.service.IBlockService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 块 Action 测试
 * 
 * @author cg
 * @since 2014-09-02
 */
public class TestBlockAction extends CommonTestAction {
	private BlockAction test;
	private IBlockService blockService;
	
	@Override
	public String getNameSpace() {
		return "/admin/block/blockAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (BlockAction) proxy.getAction();
		if(null == blockService)
			blockService =(IBlockService) applicationContext.getBean("blockService");
	}
	
	private Block buildTestModel(){
	
		Block block = new Block();
		block.setName("测试");
		block.setFileName("测试");
		block.setType(1);
		block.setDescription("测试");
		block.setIncludePath("测试");
		block.setSourceType(1);
		block.setDataType(1);
		block.setCondition("测试");
		block.setTempleteId(1);
		block.setCount(1);
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
	public void testExecute() throws Exception {
		String result = null;
		if (null != proxy)
			result = proxy.execute();

		Assert.assertEquals("success", result);

	}
	
	@Test
	public void testAdd(){
		String res = test.add();

		Assert.assertEquals("add", res);
	}

	@Test
	public void testAddSave() {
		Block block = buildTestModel();
	    test.setBlock(block);
		String res = test.addSave();
		blockService.deleteByIds(" where id = "+block.getId());
		Assert.assertNotNull(test.getBlock());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Block block = addTestBlock();
		request.setParameter("id", ""+block.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Block a = (Block) request.getAttribute("block");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(block.getId(), a.getId());
		blockService.deleteByIds(" where id = "+block.getId());
	}
	@Test
	public void testEditSave(){
		
		Block block1 = addTestBlock();
	    Block block2 = buildTestModel();
	    block2.setId(block1.getId());
		test.setBlock(block2);
		String edit_save_res = test.editSave();
		blockService.selectBlockById(block1);
		Assert.assertEquals("editSave", edit_save_res);
		
		blockService.deleteByIds(" where id = "+block1.getId());
	}
	@Test
	public void testDelete(){
		Block block = addTestBlock();
		request.setParameter("id", block.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(blockService.selectBlockById(block));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Block block0 = addTestBlock();
		ids[0]=block0.getId()+"";
		Block block1 = addTestBlock();
		ids[1]=block1.getId()+"";
		Block block2 = addTestBlock();
		ids[2]=block2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(blockService.selectBlockById(block0));
	}
}