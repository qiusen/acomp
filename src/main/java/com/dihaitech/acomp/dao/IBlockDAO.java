package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.Block;

/**
 * 块 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-09-02
 */
public interface IBlockDAO {

	/**
	 * 根据条件查询块 条数
	 * 
	 * @param block
	 * @return
	 */
	public Long getBlockCount(Block block);

	/**
	 * 分页查找块
	 * 
	 * @param block
	 * @param page
	 * @return
	 */
	public List<Block> selectBlockLike(Block block);

	/**
	 * 添加块
	 * 
	 * @param block
	 * @return
	 */
	public int addSaveBlock(Block block);

	/**
	 * 根据ID获取指定的块信息
	 * 
	 * @param block
	 * @return
	 */
	public Block selectBlockById(Block block);

	/**
	 * 修改块
	 * 
	 * @param block
	 * @return
	 */
	public int editSaveBlock(Block block);

	/**
	 * 根据ID删除指定的块
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有块
	 * 
	 * @return
	 */
	public List<Block> selectAll();
}
