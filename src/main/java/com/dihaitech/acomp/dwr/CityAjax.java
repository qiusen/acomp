package com.dihaitech.acomp.dwr;

import java.util.List;

import com.dihaitech.acomp.model.City;
import com.dihaitech.acomp.service.ICityService;

/**
 * 城市AJAX
 * @author nathan
 *
 */
public class CityAjax {
	
	private ICityService cityService;
	

	public ICityService getCityService() {
		return cityService;
	}


	public void setCityService(ICityService cityService) {
		this.cityService = cityService;
	}


	/**
	 * 根据省编号查询下属市
	 * @param provinceCode
	 * @return
	 */
	public List<City> selectCityByProvinceCode(String provinceCode){
		City city = new City();
		city.setProvinceCode(provinceCode);
		List<City> cityList = cityService.selectCityByProvinceCode(city);
		
		return cityList;
	}
}
