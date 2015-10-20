package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ITempleteTagDAO;
import com.dihaiboyun.cms.model.TempleteTag;
import com.dihaiboyun.cms.service.ITempleteTagService;

/**
 * 模板标签 业务接口 ITempleteTagService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-26
 */
public class TempleteTagServiceImpl implements ITempleteTagService {

	@Resource
	private ITempleteTagDAO templeteTagDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#addSave(com.dihaiboyun.cms.model.TempleteTag)
	 */
	public int addSave(TempleteTag templeteTag) {
		return templeteTagDAO.addSaveTempleteTag(templeteTag);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return templeteTagDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#editSave(com.dihaiboyun.cms.model.TempleteTag)
	 */
	public int editSave(TempleteTag templeteTag) {
		return templeteTagDAO.editSaveTempleteTag(templeteTag);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ITempleteTagService#selectAll()
	 */
	public List<TempleteTag> selectAll() {
		return templeteTagDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#selectTempleteTag(com.dihaiboyun.cms.model.TempleteTag, int)
	 */
	public Page selectTempleteTag(TempleteTag templeteTag, int pageSize) {
		return new Page(templeteTagDAO.getTempleteTagCount(templeteTag), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#selectTempleteTag(com.dihaiboyun.cms.model.TempleteTag, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<TempleteTag> selectTempleteTag(TempleteTag templeteTag, Page page) {
		templeteTag.setStart(page.getFirstItemPos());
		templeteTag.setPageSize(page.getPageSize());
		return templeteTagDAO.selectTempleteTagLike(templeteTag);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ITempleteTagService#selectTempleteTagById(com.dihaiboyun.cms.model.TempleteTag)
	 */
	public TempleteTag selectTempleteTagById(TempleteTag templeteTag) {
		return templeteTagDAO.selectTempleteTagById(templeteTag);
	}
}
