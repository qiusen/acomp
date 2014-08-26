package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.ITempleteDAO;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.ITempleteService;

/**
 * 模板 业务接口 ITempleteService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
public class TempleteServiceImpl implements ITempleteService {

	@Resource
	private ITempleteDAO templeteDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#addSave(com.dihaitech.acomp.model.Templete)
	 */
	public int addSave(Templete templete) {
		return templeteDAO.addSaveTemplete(templete);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return templeteDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#editSave(com.dihaitech.acomp.model.Templete)
	 */
	public int editSave(Templete templete) {
		return templeteDAO.editSaveTemplete(templete);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ITempleteService#selectAll()
	 */
	public List<Templete> selectAll() {
		return templeteDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#selectTemplete(com.dihaitech.acomp.model.Templete, int)
	 */
	public Page selectTemplete(Templete templete, int pageSize) {
		return new Page(templeteDAO.getTempleteCount(templete), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#selectTemplete(com.dihaitech.acomp.model.Templete, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Templete> selectTemplete(Templete templete, Page page) {
		templete.setStart(page.getFirstItemPos());
		templete.setPageSize(page.getPageSize());
		return templeteDAO.selectTempleteLike(templete);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#selectTempleteById(com.dihaitech.acomp.model.Templete)
	 */
	public Templete selectTempleteById(Templete templete) {
		return templeteDAO.selectTempleteById(templete);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#selectTempleteByType(com.dihaitech.acomp.model.Templete)
	 */
	@Override
	public List<Templete> selectTempleteByType(Templete templete) {
		// TODO Auto-generated method stub
		return templeteDAO.selectTempleteByType(templete);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteService#selectTempleteByIds(com.dihaitech.acomp.model.Templete)
	 */
	@Override
	public List<Templete> selectTempleteByIds(Templete templete) {
		// TODO Auto-generated method stub
		return templeteDAO.selectTempleteByIds(templete);
	}
}
