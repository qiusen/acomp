package com.dihaitech.acomp.dwr;

import java.util.List;

import com.dihaitech.acomp.model.ArticleColumn;
import com.dihaitech.acomp.service.IArticleColumnService;

/**
 * 文章类别AJAX
 * @author qiusen
 *
 */
public class ArticleColumnAjax {
	
	private IArticleColumnService articleColumnService;

	public IArticleColumnService getArticleColumnService() {
		return articleColumnService;
	}

	public void setArticleColumnService(IArticleColumnService articleColumnService) {
		this.articleColumnService = articleColumnService;
	}
	
	/**
	 * 根据频道ID获取所有栏目
	 * @param channelId
	 * @return
	 */
	public List<ArticleColumn> selectArticleColumnByChannelId(int channelId){
		ArticleColumn articleColumn = new ArticleColumn();
		articleColumn.setChannelId(channelId);
		List<ArticleColumn> articleColumnList = articleColumnService.selectArticleColumnByChannelId(articleColumn);
		
		return articleColumnList;
	}

}
