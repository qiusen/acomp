package com.dihaiboyun.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.dihaiboyun.cms.dao.ICityDAO;
import com.dihaiboyun.cms.model.City;
import com.dihaiboyun.cms.service.ICityService;
import com.dihaiboyun.common.util.Page;

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
	 * @see com.dihaiboyun.cms.service.ICityService#addSave(com.dihaiboyun.cms.model.City)
	 */
	public int addSave(City city) {
		return cityDAO.addSaveCity(city);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return cityDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#editSave(com.dihaiboyun.cms.model.City)
	 */
	public int editSave(City city) {
		return cityDAO.editSaveCity(city);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ICityService#selectAll()
	 */
	public List<City> selectAll() {
		return cityDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCity(com.dihaiboyun.cms.model.City, int)
	 */
	public Page selectCity(City city, int pageSize) {
		return new Page(cityDAO.getCityCount(city), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCity(com.dihaiboyun.cms.model.City, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<City> selectCity(City city, Page page) {
		city.setStart(page.getFirstItemPos());
		city.setPageSize(page.getPageSize());
		return cityDAO.selectCityLike(city);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCityById(com.dihaiboyun.cms.model.City)
	 */
	public City selectCityById(City city) {
		return cityDAO.selectCityById(city);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCityByProvinceCode(com.dihaiboyun.cms.model.City)
	 */
	@Override
	public List<City> selectCityByProvinceCode(City city) {
		// TODO Auto-generated method stub
		return cityDAO.selectCityByProvinceCode(city);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCityByCodes(com.dihaiboyun.cms.model.City)
	 */
	@Override
	public List<City> selectCityByCodes(City city) {
		// TODO Auto-generated method stub
		return cityDAO.selectCityByCodes(city);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ICityService#selectCityByCode(com.dihaiboyun.cms.model.City)
	 */
	@Override
	public City selectCityByCode(City city) {
		// TODO Auto-generated method stub
		return cityDAO.selectCityByCode(city);
	}
}
