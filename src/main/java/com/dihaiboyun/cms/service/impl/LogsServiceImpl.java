package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.ILogsDAO;
import com.dihaiboyun.cms.model.Logs;
import com.dihaiboyun.cms.service.ILogsService;

/**
 * 日志 业务接口 ILogsService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-22
 */
public class LogsServiceImpl implements ILogsService {

	@Resource
	private ILogsDAO logsDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#addSave(com.dihaiboyun.cms.model.Logs)
	 */
	public int addSave(Logs logs) {
		return logsDAO.addSaveLogs(logs);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return logsDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#editSave(com.dihaiboyun.cms.model.Logs)
	 */
	public int editSave(Logs logs) {
		return logsDAO.editSaveLogs(logs);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.ILogsService#selectAll()
	 */
	public List<Logs> selectAll() {
		return logsDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#selectLogs(com.dihaiboyun.cms.model.Logs, int)
	 */
	public Page selectLogs(Logs logs, int pageSize) {
		return new Page(logsDAO.getLogsCount(logs), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#selectLogs(com.dihaiboyun.cms.model.Logs, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Logs> selectLogs(Logs logs, Page page) {
		logs.setStart(page.getFirstItemPos());
		logs.setPageSize(page.getPageSize());
		return logsDAO.selectLogsLike(logs);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.ILogsService#selectLogsById(com.dihaiboyun.cms.model.Logs)
	 */
	public Logs selectLogsById(Logs logs) {
		return logsDAO.selectLogsById(logs);
	}
}
