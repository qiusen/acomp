package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.ITempleteTagDAO;
import com.dihaitech.acomp.model.TempleteTag;
import com.dihaitech.acomp.service.ITempleteTagService;

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
	 * @see com.dihaitech.acomp.service.ITempleteTagService#addSave(com.dihaitech.acomp.model.TempleteTag)
	 */
	public int addSave(TempleteTag templeteTag) {
		return templeteTagDAO.addSaveTempleteTag(templeteTag);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteTagService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return templeteTagDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteTagService#editSave(com.dihaitech.acomp.model.TempleteTag)
	 */
	public int editSave(TempleteTag templeteTag) {
		return templeteTagDAO.editSaveTempleteTag(templeteTag);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.ITempleteTagService#selectAll()
	 */
	public List<TempleteTag> selectAll() {
		return templeteTagDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteTagService#selectTempleteTag(com.dihaitech.acomp.model.TempleteTag, int)
	 */
	public Page selectTempleteTag(TempleteTag templeteTag, int pageSize) {
		return new Page(templeteTagDAO.getTempleteTagCount(templeteTag), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteTagService#selectTempleteTag(com.dihaitech.acomp.model.TempleteTag, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<TempleteTag> selectTempleteTag(TempleteTag templeteTag, Page page) {
		templeteTag.setStart(page.getFirstItemPos());
		templeteTag.setPageSize(page.getPageSize());
		return templeteTagDAO.selectTempleteTagLike(templeteTag);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.ITempleteTagService#selectTempleteTagById(com.dihaitech.acomp.model.TempleteTag)
	 */
	public TempleteTag selectTempleteTagById(TempleteTag templeteTag) {
		return templeteTagDAO.selectTempleteTagById(templeteTag);
	}
}
