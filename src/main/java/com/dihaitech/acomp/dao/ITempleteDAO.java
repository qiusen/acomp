package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.Templete;

/**
 * 模板 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-26
 */
public interface ITempleteDAO {

	/**
	 * 根据条件查询模板 条数
	 * 
	 * @param templete
	 * @return
	 */
	public Long getTempleteCount(Templete templete);

	/**
	 * 分页查找模板
	 * 
	 * @param templete
	 * @param page
	 * @return
	 */
	public List<Templete> selectTempleteLike(Templete templete);

	/**
	 * 添加模板
	 * 
	 * @param templete
	 * @return
	 */
	public int addSaveTemplete(Templete templete);

	/**
	 * 根据ID获取指定的模板信息
	 * 
	 * @param templete
	 * @return
	 */
	public Templete selectTempleteById(Templete templete);

	/**
	 * 修改模板
	 * 
	 * @param templete
	 * @return
	 */
	public int editSaveTemplete(Templete templete);

	/**
	 * 根据ID删除指定的模板
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有模板
	 * 
	 * @return
	 */
	public List<Templete> selectAll();
	
	/**
	 * @param templete
	 * @return
	 */
	public List<Templete> selectTempleteByType(Templete templete);
}
