package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.util.Page;

/**
 * 模板 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
public interface ITempleteService {
	/**
	 * 查询 Templete Page 对象
	 * @param templete
	 * @param pageSize
	 * @return
	 */
	public Page selectTemplete(Templete templete, int pageSize);

	/**
	 * 分页查找 模板
	 * @param templete
	 * @param page
	 * @return
	 */
	public List<Templete> selectTemplete(Templete templete, Page page);
	
	/**
	 * 查询所有 模板
	 * @return
	 */
	public List<Templete> selectAll();
	
	/**
	 * 根据 ID 查找 模板 
	 * @param templete
	 * @return
	 */
	public Templete selectTempleteById(Templete templete);
	
	/**
	 * 添加 模板 
	 * @param templete
	 * @return
	 */
	public int addSave(Templete templete);
	
	/**
	 * 修改 模板 
	 * @param templete
	 * @return
	 */
	public int editSave(Templete templete);
	
	/**
	 * 根据 ID 删除 模板 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
	
	/**
	 * @param templete
	 * @return
	 */
	public List<Templete> selectTempleteByType(Templete templete);
	
	
	/**
	 * @param templete
	 * @return
	 */
	public List<Templete> selectTempleteByIds(Templete templete);
}
