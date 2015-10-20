package com.dihaiboyun.cms.model;

import java.util.Date;

/**
 * 频道
 * 
 * @author cg
 *
 * @date 2014-08-27
 */
@SuppressWarnings("serial")
public class Channel extends BaseModel{
	
	/**
	 * CODE
	 */
	private String code;
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 摘要
	 */
	private String brief;
	
	/**
	 * 模板ID
	 */
	private Integer templeteId;
	
	/**
	 * 状态
	 */
	private Integer status;
	
	/**
	 * 排序
	 */
	private Integer ordernum;
	
	/**
	 * 创建人
	 */
	private String createuser;
	
	/**
	 * 创建时间
	 */
	private Date createtime;
	
	/**
	 * 修改人
	 */
	private String updateuser;
	
	/**
	 * 修改时间
	 */
	private Date updatetime;
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
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public Integer getTempleteId() {
		return templeteId;
	}
	public void setTempleteId(Integer templeteId) {
		this.templeteId = templeteId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(Integer ordernum) {
		this.ordernum = ordernum;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getUpdateuser() {
		return updateuser;
	}
	public void setUpdateuser(String updateuser) {
		this.updateuser = updateuser;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
}
