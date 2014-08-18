package com.dihaitech.acomp.model;


/**
 * 省
 * 
 * @author cg
 * 
 * @date 2014-03-01
 */
public class Province extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4638250152878779566L;

	/**
	 * 编号
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

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
}
