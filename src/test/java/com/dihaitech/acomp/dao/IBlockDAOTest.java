package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Block;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 块 DAO 接口测试
 * 
 * @author cg
 * @since 2014-09-02
 */
public class IBlockDAOTest extends BaseTestCase {
	@Resource
	private IBlockDAO blockDAO;
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
	    blockDAO.addSaveBlock(block);
	    return block;
	}
	@Test
	public void getBlockCount() {
		addTestBlock();
		Block total_obj = new Block();
		Long total = blockDAO.getBlockCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectBlockLike() {
		addTestBlock();
		Block tmp = new Block();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Block> l = blockDAO.selectBlockLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectBlockById() {
		Block obj = addTestBlock();
		Block tmp = new Block();
		tmp.setId(obj.getId());
		Block res = blockDAO.selectBlockById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveBlock(){
		Block obj = buildTestModel();
		int res = blockDAO.addSaveBlock(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		blockDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveBlock(){
		Block add_obj = addTestBlock();
		add_obj.setName("测试2");
		add_obj.setFileName("测试2");
		add_obj.setType(21);
		add_obj.setDescription("测试2");
		add_obj.setIncludePath("测试2");
		add_obj.setSourceType(21);
		add_obj.setDataType(21);
		add_obj.setCondition("测试2");
		add_obj.setTempleteId(21);
		add_obj.setCount(21);
		add_obj.setInterfaceUrl("测试2");
		add_obj.setContent("测试2");
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = blockDAO.editSaveBlock(add_obj);
		Assert.assertTrue(edit_res>0);
		
		blockDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Block obj = addTestBlock();
		
		int res = blockDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(blockDAO.selectBlockById(obj));
	}
}