package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IBlockDAO;
import com.dihaiboyun.cms.model.Block;
import com.dihaiboyun.cms.service.IBlockService;

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
	 * @see com.dihaiboyun.cms.service.IBlockService#addSave(com.dihaiboyun.cms.model.Block)
	 */
	public int addSave(Block block) {
		return blockDAO.addSaveBlock(block);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IBlockService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return blockDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IBlockService#editSave(com.dihaiboyun.cms.model.Block)
	 */
	public int editSave(Block block) {
		return blockDAO.editSaveBlock(block);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IBlockService#selectAll()
	 */
	public List<Block> selectAll() {
		return blockDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IBlockService#selectBlock(com.dihaiboyun.cms.model.Block, int)
	 */
	public Page selectBlock(Block block, int pageSize) {
		return new Page(blockDAO.getBlockCount(block), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IBlockService#selectBlock(com.dihaiboyun.cms.model.Block, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Block> selectBlock(Block block, Page page) {
		block.setStart(page.getFirstItemPos());
		block.setPageSize(page.getPageSize());
		return blockDAO.selectBlockLike(block);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IBlockService#selectBlockById(com.dihaiboyun.cms.model.Block)
	 */
	public Block selectBlockById(Block block) {
		return blockDAO.selectBlockById(block);
	}
}
