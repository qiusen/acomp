package com.dihaiboyun.cms.controller.action.articleCategory;

import java.util.Date;
import java.util.List;

import com.dihaiboyun.cms.common.Property;
import com.dihaiboyun.cms.controller.action.BaseAction;
import com.dihaiboyun.cms.model.ArticleCategory;
import com.dihaiboyun.cms.model.ArticleColumn;
import com.dihaiboyun.cms.model.Channel;
import com.dihaiboyun.cms.service.IArticleCategoryService;
import com.dihaiboyun.cms.service.IArticleColumnService;
import com.dihaiboyun.cms.service.IChannelService;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.common.util.TypeUtil;
import com.dihaiboyun.common.util.json.JSONUtil;
import com.dihaiboyun.tserver.managercenter.Manager;

/**
 * 文章类别Action
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
 @SuppressWarnings("serial")
public class ArticleCategoryAction extends BaseAction {
	private ArticleCategory articleCategory = new ArticleCategory();
	private IArticleCategoryService articleCategoryService;
	
	private IChannelService channelService;
	
	private IArticleColumnService articleColumnService;
	
	public ArticleCategory getArticleCategory() {
		return articleCategory;
	}

	public void setArticleCategory(ArticleCategory articleCategory) {
		this.articleCategory = articleCategory;
	}
	public IArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(IArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
	}
	
	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	public IArticleColumnService getArticleColumnService() {
		return articleColumnService;
	}

	public void setArticleColumnService(IArticleColumnService articleColumnService) {
		this.articleColumnService = articleColumnService;
	}

	/* 
	 * 文章类别查询
	 * @see com.opensymphony.xwork2.ActionSupport#execute()
	 */
	public String execute(){
		try {
			String pageSizeStr = this.getRequest().getParameter("pageSize");
			String pageNoStr = this.getRequest().getParameter("pageNo");
			int pageSize = 0;
			int pageNo = 0;
			
			pageSize = TypeUtil.stringToInt(pageSizeStr);
			if (pageSize <= 0) {
				pageSize = Property.PAGESIZE;
			}

			pageNo = TypeUtil.stringToInt(pageNoStr);
			if (pageSize > 0) {
				this.setManagerPageSize(pageSize);
			}else{
				this.setManagerPageSize(Property.PAGESIZE);
			}

			Page pageInfo = articleCategoryService.selectArticleCategory(articleCategory,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<ArticleCategory> resultList = this.articleCategoryService.selectArticleCategory(articleCategory,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","articleCategoryAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("ArticleCategory json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			
			if(resultList!=null && resultList.size()>0){
				ArticleCategory articleCategory = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					articleCategory = resultList.get(i);
					if(i==0){
						strbuf.append(articleCategory.getColumnId());
					}else{
						strbuf.append(", " + articleCategory.getColumnId());
					}
				}
				
				ArticleColumn articleColumn = new ArticleColumn();
				articleColumn.setIdStr(strbuf.toString());
				List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumnByIds(articleColumn);
				this.getRequest().setAttribute("articleColumnList", articleColumnList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 文章类别
	 * @return
	 */
	public String add(){
		
		Channel channel = new Channel();
		channel.setStatus(1);
		
		List<Channel> channelList = channelService.selectChannelByStatus(channel);
		this.getRequest().setAttribute("channelList", channelList);
		
//		if(channelList!=null && channelList.size()>0){
//			int channelId = channelList.get(0).getId();
//			
//			ArticleColumn articleColumn = new ArticleColumn();
//			articleColumn.setChannelId(channelId);
//			List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumnByChannelId(articleColumn);
//			this.getRequest().setAttribute("articleColumnList", articleColumnList);
//		}
		return "add";
	}
	
	/**
	 * 保存添加 文章类别
	 * @return
	 */
	public String addSave(){
		
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		articleCategory.setCreateuser(managerVO.getNickname());
		articleCategory.setCreatetime(new Date());
		articleCategory.setUpdateuser(managerVO.getNickname());
		articleCategory.setUpdatetime(new Date());
		
		articleCategoryService.addSave(articleCategory);
		return "addSave";
	}
	
	/**
	 * 修改 文章类别
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			articleCategory.setId(id);
		}else{
			return null;
		}
		
		ArticleCategory articleCategoryVO = articleCategoryService.selectArticleCategoryById(articleCategory);
		this.getRequest().setAttribute("articleCategory", articleCategoryVO);
		
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setId(articleCategoryVO.getColumnId());
		ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnById(articleColumn);
		this.getRequest().setAttribute("articleColumn", articleColumnVO);
		
		
		Channel channel = new Channel();
		channel.setStatus(1);
		
		List<Channel> channelList = channelService.selectChannelByStatus(channel);
		this.getRequest().setAttribute("channelList", channelList);
		
		return "edit";
	}
	
	/**
	 * 保存修改 文章类别
	 * @return
	 */
	public String editSave(){
		
		Manager managerVO = (Manager) this.getSession().getAttribute("manager");
		articleCategory.setUpdateuser(managerVO.getNickname());
		articleCategory.setUpdatetime(new Date());
		
		articleCategoryService.editSave(articleCategory);
		return "editSave";
	}
	
	/**
	 * 删除 文章类别
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		articleCategoryService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 文章类别
	 * @return
	 */
	public String deleteByIds(){
		String[] ids = this.getRequest().getParameterValues("id");
		StringBuffer strbuf = new StringBuffer(" where id in(");
		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				if (i != 0) {
					strbuf.append("," + ids[i]);
				} else {
					strbuf.append(ids[i]);
				}
			}
			strbuf.append(")");
			articleCategoryService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}