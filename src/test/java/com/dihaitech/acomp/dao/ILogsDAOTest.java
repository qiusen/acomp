package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Logs;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;

/**
 * 日志 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-22
 */
public class ILogsDAOTest extends BaseTestCase {
	@Resource
	private ILogsDAO logsDAO;
	private Logs buildTestModel(){
	
		Logs logs = new Logs();
		logs.setUsername("测试");
		logs.setNickname("测试");
		logs.setIp("测试");
		logs.setAct("测试");
        logs.setOpttime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		logs.setContent("测试");
	    return logs;
	}
	private Logs addTestLogs(){
		Logs logs = buildTestModel();
	    logsDAO.addSaveLogs(logs);
	    return logs;
	}
	@Test
	public void getLogsCount() {
		addTestLogs();
		Logs total_obj = new Logs();
		Long total = logsDAO.getLogsCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectLogsLike() {
		addTestLogs();
		Logs tmp = new Logs();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Logs> l = logsDAO.selectLogsLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectLogsById() {
		Logs obj = addTestLogs();
		Logs tmp = new Logs();
		tmp.setId(obj.getId());
		Logs res = logsDAO.selectLogsById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveLogs(){
		Logs obj = buildTestModel();
		int res = logsDAO.addSaveLogs(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		logsDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveLogs(){
		Logs add_obj = addTestLogs();
		add_obj.setUsername("测试2");
		add_obj.setNickname("测试2");
		add_obj.setIp("测试2");
		add_obj.setAct("测试2");
        add_obj.setOpttime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setContent("测试2");
		
		int edit_res = logsDAO.editSaveLogs(add_obj);
		Assert.assertTrue(edit_res>0);
		
		logsDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Logs obj = addTestLogs();
		
		int res = logsDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(logsDAO.selectLogsById(obj));
	}
}