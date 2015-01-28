package com.dihaitech.acomp.controller.action.article;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dihaitech.acomp.common.Property;
import com.dihaitech.acomp.controller.action.BaseAction;
import com.dihaitech.acomp.model.Article;
import com.dihaitech.acomp.model.ArticleCategory;
import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.model.Templete;
import com.dihaitech.acomp.service.IArticleCategoryService;
import com.dihaitech.acomp.service.IArticleColumnService;
import com.dihaitech.acomp.service.IArticleService;
import com.dihaitech.acomp.service.IChannelService;
import com.dihaitech.acomp.service.ITempleteService;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.TypeUtil;
import com.dihaitech.acomp.util.json.JSONUtil;
import com.dihaitech.tserver.managercenter.Manager;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;

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
	
	private ITempleteService templeteService;
	
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
	
	public ITempleteService getTempleteService() {
		return templeteService;
	}

	public void setTempleteService(ITempleteService templeteService) {
		this.templeteService = templeteService;
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
		
		Templete templete = new Templete();
		templete.setType(3);	//文章模板
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
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
		
		//上一篇
		List<Article> articleList = articleService.selectPreviousArticleList(article);
		if(articleList!=null && articleList.size()>0){
			Article prevArticle = articleList.get(0);
			
			if(articleList.size()==2){
				prevArticle.setPrevId(articleList.get(1).getId());
			}
			prevArticle.setNextId(article.getId());
			publishArticle(prevArticle);
			
			article.setPrevId(prevArticle.getId());
			
		}
		publishArticle(article);
		
		
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
		
		
		Templete templete = new Templete();
		templete.setType(3);	//文章模板
		List<Templete> templeteList = templeteService.selectTempleteByType(templete);
		this.getRequest().setAttribute("templeteList", templeteList);
		
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
		
		Article prevArticle = articleService.selectPreviousArticle(article);
		if(prevArticle!=null){
			article.setPrevId(prevArticle.getId());
		}
		
		Article nextArticle = articleService.selectNextArticle(article);
		if(nextArticle!=null){
			article.setNextId(nextArticle.getId());
		}
		
		publishArticle(article);
		return "editSave";
	}
	
	/**
	 * 发布文章
	 * @param article
	 * @return
	 */
	private boolean publishArticle(Article article){
		boolean success = false;

		//重新获取文章所有内容
		Article articleVO = articleService.selectArticleById(article);
		articleVO.setPrevId(article.getPrevId());
		articleVO.setNextId(article.getNextId());
		
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setCode(articleVO.getColumnCode());
		ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnByCode(articleColumn);
		if(articleColumnVO!=null && articleColumnVO.getChannelId()!=null){
			Channel channel = new Channel();
			channel.setId(articleColumnVO.getChannelId());
			Channel channelVO = channelService.selectChannelById(channel);
			String fileFolder = Property.FILE_PUBLISH_PATH + channelVO.getCode();
			File file = new File(fileFolder);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String filePath = fileFolder + "/" + articleVO.getId() + ".html";
			
			//模板
			Templete templete = new Templete();
			templete.setId(articleVO.getTempleteId());
			Templete templeteVO = templeteService.selectTempleteById(templete);
			String templeteContent = templeteVO.getContent();
			
			//数据
			Map<String, Object> rootMap=new HashMap<String, Object>();
			rootMap.put("article", articleVO);
			rootMap.put("articleColumn", articleColumnVO);
			rootMap.put("channel", channelVO);
			
			//写文件
			PrintWriter printWriter = null;
			
			try{
				printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(filePath),"utf-8")));
				
				Configuration cfg = new Configuration();
				StringTemplateLoader stringLoader = new StringTemplateLoader();

				stringLoader.putTemplate("templete", templeteContent);
				cfg.setTemplateLoader(stringLoader);
				freemarker.template.Template t = cfg.getTemplate("templete","utf-8");
				t.process(rootMap, printWriter);
				printWriter.flush();
				
				success = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(printWriter!=null){
					printWriter.close();
					printWriter = null;
				}
			}
			
			publishChannel(channelVO);
		}
		
		return success;
	}
	
	/**
	 * 发布频道首页
	 * @param channel
	 * @return
	 */
	private boolean publishChannel(Channel channel){
		boolean success = false;
//		Channel channelVO = channelService.selectChannelById(channel);
		Channel channelVO = channel;
		if(channelVO!=null){
			//模板
			Templete templete = new Templete();
			templete.setId(channelVO.getTempleteId());
			Templete templeteVO = templeteService.selectTempleteById(templete);
			String templeteContent = templeteVO.getContent();
			
			//数据
			Map<String, Object> rootMap=new HashMap<String, Object>();
			rootMap.put("channel", channelVO);
			
			ArticleColumn articleColumn = new ArticleColumn();
			articleColumn.setChannelId(channelVO.getId());
			List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumnByChannelId(articleColumn);
			rootMap.put("articleColumnList", articleColumnList);
			
			if(articleColumnList!=null && articleColumnList.size()>0){
				ArticleColumn articleColumnTemp = null;
				StringBuffer strbuf = new StringBuffer();
				for(int i=0;i<articleColumnList.size();i++){
					articleColumnTemp = articleColumnList.get(i);
					if(i==0){
						strbuf.append("'" + articleColumnTemp.getCode() + "'");
					}else{
						strbuf.append(",'" + articleColumnTemp.getCode() + "'");
					}
				}
				Article article = new Article();
				article.setIdStr(strbuf.toString());
				List<Article> articleList = articleService.selectArticleByColumnCodes(article);
				rootMap.put("articleList", articleList);
			}
			
			//路径
			String fileFolder = Property.FILE_PUBLISH_PATH + channelVO.getCode();
			File file = new File(fileFolder);
			if(!file.exists()){
				file.mkdirs();
			}
			String filePath = fileFolder + "/index.html";

			//写文件
			PrintWriter printWriter = null;
			
			try{
				printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									new FileOutputStream(filePath),"utf-8")));
				
				Configuration cfg = new Configuration();
				StringTemplateLoader stringLoader = new StringTemplateLoader();

				stringLoader.putTemplate("templete", templeteContent);
				cfg.setTemplateLoader(stringLoader);
				freemarker.template.Template t = cfg.getTemplate("templete","utf-8");
				t.process(rootMap, printWriter);
				printWriter.flush();
				
				success = true;
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(printWriter!=null){
					printWriter.close();
					printWriter = null;
				}
			}
		}
		
		return success;
	}
	
	/**
	 * 删除 文章
	 * @return
	 */
	public String delete(){
		int id = TypeUtil.stringToInt(this.getRequest().getParameter("id"));
		
		if(id>0){
			article.setId(id);
			Article articleVO = articleService.selectArticleById(article);
			
			ArticleColumn articleColumn = new ArticleColumn();
			articleColumn.setCode(articleVO.getColumnCode());
			ArticleColumn articleColumnVO = articleColumnService.selectArticleColumnByCode(articleColumn);
			if(articleColumnVO!=null && articleColumnVO.getChannelId()!=null){
				int channelId = articleColumnVO.getChannelId();
				Channel channel = new Channel();
				channel.setId(channelId);
				Channel channelVO = channelService.selectChannelById(channel);
				
				StringBuffer strbuf = new StringBuffer(" where id =");
				strbuf.append(id);
				articleService.deleteByIds(strbuf.toString());
				
				
				publishChannel(channelVO);
			}
			
			
		}
		
		
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