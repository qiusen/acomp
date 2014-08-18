package com.dihaitech.acomp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.acomp.dao.IManagerDAO;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.service.IManagerService;
import com.dihaitech.acomp.util.Page;

/**
 * 管理员 业务接口 IManagerService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class ManagerServiceImpl implements IManagerService {

	@Resource
	private IManagerDAO managerDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#addSave(com.dihaitech.acomp.model.Manager)
	 */
	public int addSave(Manager manager) {
		return managerDAO.addSaveManager(manager);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return managerDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#editSave(com.dihaitech.acomp.model.Manager)
	 */
	public int editSave(Manager manager) {
		return managerDAO.editSaveManager(manager);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IManagerService#selectAll()
	 */
	public List<Manager> selectAll() {
		return managerDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#selectManager(com.dihaitech.acomp.model.Manager, int)
	 */
	public Page selectManager(Manager manager, int pageSize) {
		return new Page(managerDAO.getManagerCount(manager), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#selectManager(com.dihaitech.acomp.model.Manager, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Manager> selectManager(Manager manager, Page page) {
		manager.setStart(page.getFirstItemPos());
		manager.setPageSize(page.getPageSize());
		return managerDAO.selectManagerLike(manager);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IManagerService#selectManagerById(com.dihaitech.acomp.model.Manager)
	 */
	public Manager selectManagerById(Manager manager) {
		return managerDAO.selectManagerById(manager);
	}
}
