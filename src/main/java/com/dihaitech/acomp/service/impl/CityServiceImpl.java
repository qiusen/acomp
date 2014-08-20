package com.dihaitech.acomp.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaitech.acomp.dao.ICityDAO;
import com.dihaitech.acomp.model.City;
import com.dihaitech.acomp.service.ICityService;
import com.dihaitech.acomp.util.Page;

/**
 * 市 业务接口 ICityService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-20
 */
public class CityServiceImpl implements ICityService {

	@Resource
	private ICityDAO cityDAO;

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#addSave(com.dihaitech.acomp.model.City)
	 */
	public int addSave(City city) {
		return cityDAO.addSaveCity(city);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return cityDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#editSave(com.dihaitech.acomp.model.City)
	 */
	public int editSave(City city) {
		return cityDAO.editSaveCity(city);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ICityService#selectAll()
	 */
	public List<City> selectAll() {
		return cityDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#selectCity(com.dihaitech.acomp.model.City, int)
	 */
	public Page selectCity(City city, int pageSize) {
		return new Page(cityDAO.getCityCount(city), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#selectCity(com.dihaitech.acomp.model.City, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<City> selectCity(City city, Page page) {
		city.setStart(page.getFirstItemPos());
		city.setPageSize(page.getPageSize());
		return cityDAO.selectCityLike(city);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#selectCityById(com.dihaitech.acomp.model.City)
	 */
	public City selectCityById(City city) {
		return cityDAO.selectCityById(city);
	}


	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ICityService#selectCityByProvinceCode(com.dihaitech.acomp.model.City)
	 */
	@Override
	public List<City> selectCityByProvinceCode(City city) {
		// TODO Auto-generated method stub
		return cityDAO.selectCityByProvinceCode(city);
	}
}