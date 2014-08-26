package com.dihaitech.acomp.model;

import java.util.Date;

/**
 * 模板
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
@SuppressWarnings("serial")
public class Templete extends BaseModel{
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 类型：1、首页；2、列表页；3、文章页
	 */
	private Integer type;
	
	/**
	 * 内容
	 */
	private String content;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
