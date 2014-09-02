package com.dihaitech.acomp.model;

import java.util.Date;

/**
 * 文章
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
@SuppressWarnings("serial")
public class Article extends BaseModel{
	
	/**
	 * 栏目编码
	 */
	private String columnCode;
	
	/**
	 * 类别编码
	 */
	private String categoryCode;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 作者
	 */
	private String auth;
	
	/**
	 * 短标题
	 */
	private String shortTitle;
	
	/**
	 * 导读图
	 */
	private String articleImg;
	
	/**
	 * 摘要
	 */
	private String brief;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 文章状态
	 */
	private Integer status;
	
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
	public String getColumnCode() {
		return columnCode;
	}
	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}
	public String getShortTitle() {
		return shortTitle;
	}
	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}
	public String getArticleImg() {
		return articleImg;
	}
	public void setArticleImg(String articleImg) {
		this.articleImg = articleImg;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
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
