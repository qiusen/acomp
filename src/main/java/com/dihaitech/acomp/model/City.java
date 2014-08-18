package com.dihaitech.acomp.model;


/**
 * 市
 * 
 * @author cg
 * 
 * @date 2014-03-01
 */
public class City extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3324142275569533316L;

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 所属省编号
	 */
	private String provinceCode;

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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}
}
