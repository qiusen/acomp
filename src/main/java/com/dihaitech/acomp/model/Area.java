package com.dihaitech.acomp.model;


/**
 * 区
 * 
 * @author cg
 * 
 * @date 2014-03-01
 */
public class Area extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8651459206355736696L;

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 所属市编号
	 */
	private String cityCode;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}
