package com.dihaiboyun.cms.model;

import java.util.Date;

/**
 * 友链网站
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
@SuppressWarnings("serial")
public class LinkSite extends BaseModel{
	
	/**
	 * 网站名称
	 */
	private String siteName;
	
	/**
	 * 网站URL
	 */
	private String siteUrl;
	
	/**
	 * 网站LOGO
	 */
	private String siteLogo;
	
	/**
	 * 网站简介
	 */
	private String description;
	
	/**
	 * 联系人
	 */
	private String contact;
	
	/**
	 * QQ
	 */
	private String qq;
	
	/**
	 * 座机
	 */
	private String tel;
	
	/**
	 * 手机
	 */
	private String mobile;
	
	/**
	 * EMAIL
	 */
	private String email;
	
	/**
	 * 状态：1、有效；0、无效
	 */
	private int status;
	
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
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getSiteUrl() {
		return siteUrl;
	}
	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}
	public String getSiteLogo() {
		return siteLogo;
	}
	public void setSiteLogo(String siteLogo) {
		this.siteLogo = siteLogo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
