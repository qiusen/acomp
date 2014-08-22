package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Logs;
import com.dihaitech.acomp.util.DateUtil;
import com.dihaitech.acomp.util.Page;

/**
 * 日志 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-22
 */
public class ILogsServiceTest extends BaseTestCase{
	@Autowired
	private ILogsService logsService;
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
	    logsService.addSave(logs);
	    return logs;
	}
	
	@Test
	public void selectLogsPage(){
		addTestLogs();
	    Page p = logsService.selectLogs(new Logs(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectLogsPageList(){
		
		addTestLogs();
		
		Logs obj = new Logs();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Logs> logsList = logsService.selectLogs(obj, page);
		Assert.assertTrue(logsList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestLogs();
		
		Assert.assertTrue("fail: logs`s total is bad res!", logsService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Logs logs = buildTestModel();
		int pr = logsService.selectAll().size();
		int res = logsService.addSave(logs);
		int s = logsService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(logsService.selectLogsById(logs));
		Assert.assertTrue("Fail:call logsService insert fail!", s==pr+1);
	}

	@Test
	public void selectLogsById(){
		
		Logs logs = addTestLogs();
		
		Logs tmp = new Logs();
		tmp.setId(logs.getId());
		Assert.assertNotNull(logsService.selectLogsById(tmp));
	}

	@Test
	public void editSave(){
		
		Logs logs1 = addTestLogs();
		
	    Logs logs2 = logsService.selectLogsById(logs1);
		logs2.setUsername("测试2");
		logs2.setNickname("测试2");
		logs2.setIp("测试2");
		logs2.setAct("测试2");
        logs2.setOpttime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		logs2.setContent("测试2");
		logsService.editSave(logs2);
		
		Logs tmp = logsService.selectLogsById(logs2);
		Assert.assertNotEquals(logs1.getContent(), tmp.getContent());
		Assert.assertEquals(logs2.getContent(), tmp.getContent());
		Assert.assertEquals("测试2", tmp.getContent());
	}
	
	@Test
	public void deleteByIds(){
		
		Logs logs = addTestLogs();
		
		Logs obj = new Logs();
		obj.setId(logs.getId());
		Assert.assertNotNull(logsService.selectLogsById(obj));
		int res = logsService.deleteByIds("where id = "+logs.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(logsService.selectLogsById(obj));
	}
}