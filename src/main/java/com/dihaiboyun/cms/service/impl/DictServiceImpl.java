package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IDictDAO;
import com.dihaiboyun.cms.model.Dict;
import com.dihaiboyun.cms.service.IDictService;

/**
 * 字典 业务接口 IDictService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
public class DictServiceImpl implements IDictService {

	@Resource
	private IDictDAO dictDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#addSave(com.dihaiboyun.cms.model.Dict)
	 */
	public int addSave(Dict dict) {
		return dictDAO.addSaveDict(dict);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return dictDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#editSave(com.dihaiboyun.cms.model.Dict)
	 */
	public int editSave(Dict dict) {
		return dictDAO.editSaveDict(dict);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IDictService#selectAll()
	 */
	public List<Dict> selectAll() {
		return dictDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#selectDict(com.dihaiboyun.cms.model.Dict, int)
	 */
	public Page selectDict(Dict dict, int pageSize) {
		return new Page(dictDAO.getDictCount(dict), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#selectDict(com.dihaiboyun.cms.model.Dict, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Dict> selectDict(Dict dict, Page page) {
		dict.setStart(page.getFirstItemPos());
		dict.setPageSize(page.getPageSize());
		return dictDAO.selectDictLike(dict);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IDictService#selectDictById(com.dihaiboyun.cms.model.Dict)
	 */
	public Dict selectDictById(Dict dict) {
		return dictDAO.selectDictById(dict);
	}
}
