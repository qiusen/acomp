package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.util.Page;

/**
 * 文章栏目 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-27
 */
public interface IArticleColumnService {
	/**
	 * 查询 ArticleColumn Page 对象
	 * @param articleColumn
	 * @param pageSize
	 * @return
	 */
	public Page selectArticleColumn(ArticleColumn articleColumn, int pageSize);

	/**
	 * 分页查找 文章栏目
	 * @param articleColumn
	 * @param page
	 * @return
	 */
	public List<ArticleColumn> selectArticleColumn(ArticleColumn articleColumn, Page page);
	
	/**
	 * 查询所有 文章栏目
	 * @return
	 */
	public List<ArticleColumn> selectAll();
	
	/**
	 * 根据 ID 查找 文章栏目 
	 * @param articleColumn
	 * @return
	 */
	public ArticleColumn selectArticleColumnById(ArticleColumn articleColumn);
	
	/**
	 * 添加 文章栏目 
	 * @param articleColumn
	 * @return
	 */
	public int addSave(ArticleColumn articleColumn);
	
	/**
	 * 修改 文章栏目 
	 * @param articleColumn
	 * @return
	 */
	public int editSave(ArticleColumn articleColumn);
	
	/**
	 * 根据 ID 删除 文章栏目 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
