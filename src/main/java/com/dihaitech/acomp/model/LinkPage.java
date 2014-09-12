package com.dihaitech.acomp.model;

import java.util.Date;

/**
 * 友链页面
 * 
 * @author cg
 *
 * @date 2014-09-04
 */
@SuppressWarnings("serial")
public class LinkPage extends BaseModel{
	
	/**
	 * 页面名称
	 */
	private String pageName;
	
	/**
	 * 包含地址
	 */
	private String includePath;
	
	/**
	 * 网站简介
	 */
	private String description;
	
	/**
	 * 模板ID
	 */
	private Integer templeteId;
	
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
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getIncludePath() {
		return includePath;
	}
	public void setIncludePath(String includePath) {
		this.includePath = includePath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getTempleteId() {
		return templeteId;
	}
	public void setTempleteId(Integer templeteId) {
		this.templeteId = templeteId;
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
