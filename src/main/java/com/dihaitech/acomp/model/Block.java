package com.dihaitech.acomp.model;

import java.util.Date;

/**
 * 块
 * 
 * @author cg
 *
 * @date 2014-09-02
 */
@SuppressWarnings("serial")
public class Block extends BaseModel{
	
	/**
	 * 名称
	 */
	private String name;
	
	/**
	 * 文件名称
	 */
	private String fileName;
	
	/**
	 * 类型：1、自动块；2、手动块
	 */
	private Integer type;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 包含地址
	 */
	private String includePath;
	
	/**
	 * 数据来源类型：1、数据筛选；2、外部接口
	 */
	private Integer sourceType;
	
	/**
	 * 数据类型：1、文章
	 */
	private Integer dataType;
	
	/**
	 * 筛选条件
	 */
	private String condition;
	
	/**
	 * 模板ID
	 */
	private Integer templeteId;
	
	/**
	 * 数据条数
	 */
	private Integer count;
	
	/**
	 * 外部接口地址
	 */
	private String interfaceUrl;
	
	/**
	 * 块内容
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
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIncludePath() {
		return includePath;
	}
	public void setIncludePath(String includePath) {
		this.includePath = includePath;
	}
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public Integer getTempleteId() {
		return templeteId;
	}
	public void setTempleteId(Integer templeteId) {
		this.templeteId = templeteId;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getInterfaceUrl() {
		return interfaceUrl;
	}
	public void setInterfaceUrl(String interfaceUrl) {
		this.interfaceUrl = interfaceUrl;
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
