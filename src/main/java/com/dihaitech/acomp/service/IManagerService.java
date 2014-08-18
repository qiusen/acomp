package com.dihaitech.acomp.service;

import java.util.List;

import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.util.Page;

/**
 * 管理员 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public interface IManagerService {
	/**
	 * 查询 Manager Page 对象
	 * @param manager
	 * @param pageSize
	 * @return
	 */
	public Page selectManager(Manager manager, int pageSize);

	/**
	 * 分页查找 管理员
	 * @param manager
	 * @param page
	 * @return
	 */
	public List<Manager> selectManager(Manager manager, Page page);
	
	/**
	 * 查询所有 管理员
	 * @return
	 */
	public List<Manager> selectAll();
	
	/**
	 * 根据 ID 查找 管理员 
	 * @param manager
	 * @return
	 */
	public Manager selectManagerById(Manager manager);
	
	/**
	 * 添加 管理员 
	 * @param manager
	 * @return
	 */
	public int addSave(Manager manager);
	
	/**
	 * 修改 管理员 
	 * @param manager
	 * @return
	 */
	public int editSave(Manager manager);
	
	/**
	 * 根据 ID 删除 管理员 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
