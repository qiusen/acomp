package com.dihaiboyun.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaiboyun.cms.dao.IRoleDAO;
import com.dihaiboyun.cms.model.Role;
import com.dihaiboyun.cms.service.IRoleService;
import com.dihaiboyun.common.util.Page;

/**
 * 角色 业务接口 IRoleService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class RoleServiceImpl implements IRoleService {

	@Resource
	private IRoleDAO roleDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#addSave(com.dihaiboyun.cms.model.Role)
	 */
	public int addSave(Role role) {
		return roleDAO.addSaveRole(role);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return roleDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#editSave(com.dihaiboyun.cms.model.Role)
	 */
	public int editSave(Role role) {
		return roleDAO.editSaveRole(role);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IRoleService#selectAll()
	 */
	public List<Role> selectAll() {
		return roleDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#selectRole(com.dihaiboyun.cms.model.Role, int)
	 */
	public Page selectRole(Role role, int pageSize) {
		return new Page(roleDAO.getRoleCount(role), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#selectRole(com.dihaiboyun.cms.model.Role, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Role> selectRole(Role role, Page page) {
		role.setStart(page.getFirstItemPos());
		role.setPageSize(page.getPageSize());
		return roleDAO.selectRoleLike(role);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#selectRoleById(com.dihaiboyun.cms.model.Role)
	 */
	public Role selectRoleById(Role role) {
		return roleDAO.selectRoleById(role);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#selectAllByStatus(com.dihaiboyun.cms.model.Role)
	 */
	@Override
	public List<Role> selectAllByStatus(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.selectAllByStatus(role);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IRoleService#editRights(com.dihaiboyun.cms.model.Role)
	 */
	@Override
	public int editRights(Role role) {
		// TODO Auto-generated method stub
		return roleDAO.editRights(role);
	}
}
