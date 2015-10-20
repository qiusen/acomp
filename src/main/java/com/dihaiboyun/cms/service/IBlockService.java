package com.dihaiboyun.cms.service;

import java.util.List;


import com.dihaiboyun.cms.model.Block;
import com.dihaiboyun.common.util.Page;

/**
 * 块 业务接口
 * 
 * @author cg
 *
 * @date 2014-09-02
 */
public interface IBlockService {
	/**
	 * 查询 Block Page 对象
	 * @param block
	 * @param pageSize
	 * @return
	 */
	public Page selectBlock(Block block, int pageSize);

	/**
	 * 分页查找 块
	 * @param block
	 * @param page
	 * @return
	 */
	public List<Block> selectBlock(Block block, Page page);
	
	/**
	 * 查询所有 块
	 * @return
	 */
	public List<Block> selectAll();
	
	/**
	 * 根据 ID 查找 块 
	 * @param block
	 * @return
	 */
	public Block selectBlockById(Block block);
	
	/**
	 * 添加 块 
	 * @param block
	 * @return
	 */
	public int addSave(Block block);
	
	/**
	 * 修改 块 
	 * @param block
	 * @return
	 */
	public int editSave(Block block);
	
	/**
	 * 根据 ID 删除 块 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
