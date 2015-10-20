package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ITempleteDAO;
import com.dihaiboyun.cms.model.Templete;
import com.dihaiboyun.cms.service.ITempleteService;

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
	 * @see com.dihaiboyun.cms.service.ITempleteService#addSave(com.dihaiboyun.cms.model.Templete)
	 */
	public int addSave(Templete templete) {
		return templeteDAO.addSaveTemplete(templete);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return templeteDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#editSave(com.dihaiboyun.cms.model.Templete)
	 */
	public int editSave(Templete templete) {
		return templeteDAO.editSaveTemplete(templete);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ITempleteService#selectAll()
	 */
	public List<Templete> selectAll() {
		return templeteDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#selectTemplete(com.dihaiboyun.cms.model.Templete, int)
	 */
	public Page selectTemplete(Templete templete, int pageSize) {
		return new Page(templeteDAO.getTempleteCount(templete), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#selectTemplete(com.dihaiboyun.cms.model.Templete, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Templete> selectTemplete(Templete templete, Page page) {
		templete.setStart(page.getFirstItemPos());
		templete.setPageSize(page.getPageSize());
		return templeteDAO.selectTempleteLike(templete);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#selectTempleteById(com.dihaiboyun.cms.model.Templete)
	 */
	public Templete selectTempleteById(Templete templete) {
		return templeteDAO.selectTempleteById(templete);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#selectTempleteByType(com.dihaiboyun.cms.model.Templete)
	 */
	@Override
	public List<Templete> selectTempleteByType(Templete templete) {
		// TODO Auto-generated method stub
		return templeteDAO.selectTempleteByType(templete);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteService#selectTempleteByIds(com.dihaiboyun.cms.model.Templete)
	 */
	@Override
	public List<Templete> selectTempleteByIds(Templete templete) {
		// TODO Auto-generated method stub
		return templeteDAO.selectTempleteByIds(templete);
	}
}
