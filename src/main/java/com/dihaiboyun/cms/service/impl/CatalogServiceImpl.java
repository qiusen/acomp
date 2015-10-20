package com.dihaiboyun.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaiboyun.cms.dao.ICatalogDAO;
import com.dihaiboyun.cms.model.Catalog;
import com.dihaiboyun.cms.service.ICatalogService;
import com.dihaiboyun.common.util.Page;

/**
 * 目录 业务接口 ICatalogService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-18
 */
public class CatalogServiceImpl implements ICatalogService {

	@Resource
	private ICatalogDAO catalogDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#addSave(com.dihaiboyun.cms.model.Catalog)
	 */
	public int addSave(Catalog catalog) {
		return catalogDAO.addSaveCatalog(catalog);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return catalogDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#editSave(com.dihaiboyun.cms.model.Catalog)
	 */
	public int editSave(Catalog catalog) {
		return catalogDAO.editSaveCatalog(catalog);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ICatalogService#selectAll()
	 */
	public List<Catalog> selectAll() {
		return catalogDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectCatalog(com.dihaiboyun.cms.model.Catalog, int)
	 */
	public Page selectCatalog(Catalog catalog, int pageSize) {
		return new Page(catalogDAO.getCatalogCount(catalog), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectCatalog(com.dihaiboyun.cms.model.Catalog, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Catalog> selectCatalog(Catalog catalog, Page page) {
		catalog.setStart(page.getFirstItemPos());
		catalog.setPageSize(page.getPageSize());
		return catalogDAO.selectCatalogLike(catalog);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectCatalogById(com.dihaiboyun.cms.model.Catalog)
	 */
	public Catalog selectCatalogById(Catalog catalog) {
		return catalogDAO.selectCatalogById(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectCatalogByIdsStatus(com.dihaiboyun.cms.model.Catalog)
	 */
	@Override
	public List<Catalog> selectCatalogByIdsStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectCatalogByIdsStatus(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectAllByStatus(com.dihaiboyun.cms.model.Catalog)
	 */
	@Override
	public List<Catalog> selectAllByStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectAllByStatus(catalog);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICatalogService#selectAllByMenuStatus(com.dihaiboyun.cms.model.Catalog)
	 */
	@Override
	public List<Catalog> selectAllByMenuStatus(Catalog catalog) {
		// TODO Auto-generated method stub
		return catalogDAO.selectAllByMenuStatus(catalog);
	}
}
