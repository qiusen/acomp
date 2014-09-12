package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.IBlockDAO;
import com.dihaitech.acomp.model.Block;
import com.dihaitech.acomp.service.IBlockService;

/**
 * 块 业务接口 IBlockService 实现类
 * 
 * @author cg
 *
 * @date 2014-09-02
 */
public class BlockServiceImpl implements IBlockService {

	@Resource
	private IBlockDAO blockDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#addSave(com.dihaitech.acomp.model.Block)
	 */
	public int addSave(Block block) {
		return blockDAO.addSaveBlock(block);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return blockDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#editSave(com.dihaitech.acomp.model.Block)
	 */
	public int editSave(Block block) {
		return blockDAO.editSaveBlock(block);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IBlockService#selectAll()
	 */
	public List<Block> selectAll() {
		return blockDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#selectBlock(com.dihaitech.acomp.model.Block, int)
	 */
	public Page selectBlock(Block block, int pageSize) {
		return new Page(blockDAO.getBlockCount(block), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#selectBlock(com.dihaitech.acomp.model.Block, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Block> selectBlock(Block block, Page page) {
		block.setStart(page.getFirstItemPos());
		block.setPageSize(page.getPageSize());
		return blockDAO.selectBlockLike(block);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IBlockService#selectBlockById(com.dihaitech.acomp.model.Block)
	 */
	public Block selectBlockById(Block block) {
		return blockDAO.selectBlockById(block);
	}
}
