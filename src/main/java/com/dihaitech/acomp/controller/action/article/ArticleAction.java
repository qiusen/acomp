package com.dihaitech.acomp.controller.action.article;

import java.util.Date;
import java.util.List;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.model.Manager;
import com.dihaitech.acomp.service.IArticleCategoryService;
import com.dihaitech.acomp.service.IArticleColumnService;
import com.dihaitech.acomp.service.IArticleService;
import com.dihaitech.acomp.service.IChannelService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;

/**
 * 文章Action
 * 
 * @author cg
 *
 * @date 2014-08-28
 */
 @SuppressWarnings("serial")
public class ArticleAction extends BaseAction {
	private Article article = new Article();
	private IArticleService articleService;
	
	private IChannelService channelService;
	
	private IArticleCategoryService articleCategoryService;
	
	private IArticleColumnService articleColumnService;
	
	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	public IArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(IArticleService articleService) {
		this.articleService = articleService;
	}
	
	public IChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}

	

	public IArticleCategoryService getArticleCategoryService() {
		return articleCategoryService;
	}

	public void setArticleCategoryService(
			IArticleCategoryService articleCategoryService) {
		this.articleCategoryService = articleCategoryService;
	}

	public IArticleColumnService getArticleColumnService() {
		return articleColumnService;
	}

	public void setArticleColumnService(IArticleColumnService articleColumnService) {
		this.articleColumnService = articleColumnService;
	}

	/* 
	 * 文章查询
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

			Page pageInfo = articleService.selectArticle(article,this.getManagerPageSize());
			
			if (pageNo > 0) {
				pageInfo.setPage(pageNo);
			} else {
				pageInfo.setPage(0);
			}
			
			List<Article> resultList = this.articleService.selectArticle(article,pageInfo);
			
			this.getRequest().setAttribute("pageInfo", pageInfo);
			this.getRequest().setAttribute("resultList", resultList);
			this.getRequest().setAttribute("actionName","articleAction");

			String json = "\"Rows\":" + JSONUtil.objectArrayToJson(resultList)+", \"Total\":" + pageInfo.getResultCount();
			System.out.println("Article json:::::::::::::::::::" + json);
			this.getRequest().setAttribute("json", json);
			
			if(resultList!=null && resultList.size()>0){
				Article articleTemp = null;
				StringBuffer columnCodeStrBuf = new StringBuffer();
				StringBuffer categoryCodeStrBuf = new StringBuffer();
				for(int i=0;i<resultList.size();i++){
					articleTemp = resultList.get(i);
					if(i==0){
						columnCodeStrBuf.append("'" + articleTemp.getColumnCode() + "'");
						categoryCodeStrBuf.append("'" + articleTemp.getCategoryCode() + "'");
					}else{
						columnCodeStrBuf.append(",'" + articleTemp.getColumnCode() + "'");
						categoryCodeStrBuf.append(",'" + articleTemp.getCategoryCode() + "'");
					}
					
				}
				
				ArticleColumn articleColumn = new ArticleColumn();
				articleColumn.setIdStr(columnCodeStrBuf.toString());	//借助idStr属性
				List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumnByCodes(articleColumn);
				this.getRequest().setAttribute("articleColumnList", articleColumnList);
				
				ArticleCategory articleCategory = new ArticleCategory();
				articleCategory.setIdStr(categoryCodeStrBuf.toString());	//借助idStr属性
				List<ArticleCategory> articleCategoryList = articleCategoryService.selectArticleCategoryByCodes(articleCategory);
				this.getRequest().setAttribute("articleCategoryList", articleCategoryList);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 添加 文章
	 * @return
	 */
	public String add(){
		Channel channel = new Channel();
		channel.setStatus(1);
		List<Channel> channelList = channelService.selectChannelByStatus(channel);
		this.getRequest().setAttribute("channelList", channelList);
		
		return "add";
	}
	
	/**
	 * 保存添加 文章
	 * @return
	 */
	public String addSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		article.setCreateuser(manager.getNickname());
		article.setCreatetime(new Date());
		article.setUpdateuser(manager.getNickname());
		article.setUpdatetime(new Date());
		article.setStatus(1);	//状态为有效
		
		articleService.addSave(article);
		return "addSave";
	}
	
	/**
	 * 修改 文章
	 * @return
	 */
	public String edit(){
		String idStr = this.getRequest().getParameter("id");
		int id = 0;
		id = TypeUtil.stringToInt(idStr);
		if(id>0){
			article.setId(id);
		}else{
			return null;
		}
		
		Article articleVO = articleService.selectArticleById(article);
		this.getRequest().setAttribute("article", articleVO);
		
		String columnCode = articleVO.getColumnCode();
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setCode(columnCode);
		ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnByCode(articleColumn);
		this.getRequest().setAttribute("articleColumn", articleColumnVO);
		
		if(articleColumnVO!=null && articleColumnVO.getChannelId()!=null){
			int channelId = articleColumnVO.getChannelId();
			Channel channel = new Channel();
			channel.setId(channelId);
			Channel channelVO = channelService.selectChannelById(channel);
			this.getRequest().setAttribute("channel", channelVO);
		}
		
		String categoryCode = articleVO.getCategoryCode();
		ArticleCategory articleCategory = new ArticleCategory();
		articleCategory.setCode(categoryCode);
		ArticleCategory articleCategoryVO = articleCategoryService.selectArticleCategoryByCode(articleCategory);
		this.getRequest().setAttribute("articleCategory", articleCategoryVO);
		
		return "edit";
	}
	
	/**
	 * 保存修改 文章
	 * @return
	 */
	public String editSave(){
		Manager manager = (Manager)this.getSession().getAttribute("manager");
		article.setUpdateuser(manager.getNickname());
		article.setUpdatetime(new Date());
		article.setStatus(1);	//状态为有效
		
		articleService.editSave(article);
		return "editSave";
	}
	
	/**
	 * 删除 文章
	 * @return
	 */
	public String delete(){
		String id = this.getRequest().getParameter("id");
		StringBuffer strbuf = new StringBuffer(" where id =");
		strbuf.append(id);
		articleService.deleteByIds(strbuf.toString());
		return "deleteSuccess";
	}

	/**
	 * 删除 文章
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
			articleService.deleteByIds(strbuf.toString());
			return "deleteSuccess";
		}
		return "deleteFailure";
	}
}