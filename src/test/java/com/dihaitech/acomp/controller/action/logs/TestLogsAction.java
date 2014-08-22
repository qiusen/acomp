package com.dihaitech.acomp.controller.action.logs;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.model.Logs;
import com.dihaitech.acomp.service.ILogsService;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 日志 Action 测试
 * 
 * @author cg
 * @since 2014-08-22
 */
public class TestLogsAction extends CommonTestAction {
	private LogsAction test;
	private ILogsService logsService;
	
	@Override
	public String getNameSpace() {
		return "/admin/logs/logsAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (LogsAction) proxy.getAction();
		if(null == logsService)
			logsService =(ILogsService) applicationContext.getBean("logsService");
	}
	
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
	public void testExecute() throws Exception {
		String result = null;
		if (null != proxy)
			result = proxy.execute();

		Assert.assertEquals("success", result);

	}
	
	@Test
	public void testAdd(){
		String res = test.add();

		Assert.assertEquals("add", res);
	}

	@Test
	public void testAddSave() {
		Logs logs = buildTestModel();
	    test.setLogs(logs);
		String res = test.addSave();
		logsService.deleteByIds(" where id = "+logs.getId());
		Assert.assertNotNull(test.getLogs());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Logs logs = addTestLogs();
		request.setParameter("id", ""+logs.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Logs a = (Logs) request.getAttribute("logs");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(logs.getId(), a.getId());
		logsService.deleteByIds(" where id = "+logs.getId());
	}
	@Test
	public void testEditSave(){
		
		Logs logs1 = addTestLogs();
	    Logs logs2 = buildTestModel();
	    logs2.setId(logs1.getId());
		test.setLogs(logs2);
		String edit_save_res = test.editSave();
		logsService.selectLogsById(logs1);
		Assert.assertEquals("editSave", edit_save_res);
		
		logsService.deleteByIds(" where id = "+logs1.getId());
	}
	@Test
	public void testDelete(){
		Logs logs = addTestLogs();
		request.setParameter("id", logs.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(logsService.selectLogsById(logs));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Logs logs0 = addTestLogs();
		ids[0]=logs0.getId()+"";
		Logs logs1 = addTestLogs();
		ids[1]=logs1.getId()+"";
		Logs logs2 = addTestLogs();
		ids[2]=logs2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(logsService.selectLogsById(logs0));
	}
}