package com.dihaitech.acomp.controller.action.channel;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.service.IChannelService;
import com.dihaitech.acomp.controller.action.CommonTestAction;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 频道 Action 测试
 * 
 * @author cg
 * @since 2014-08-27
 */
public class TestChannelAction extends CommonTestAction {
	private ChannelAction test;
	private IChannelService channelService;
	
	@Override
	public String getNameSpace() {
		return "/admin/channel/channelAction";
	}
	@Override
	protected void setUp()throws Exception {
		super.setUp();
		if (null == test)
			test = (ChannelAction) proxy.getAction();
		if(null == channelService)
			channelService =(IChannelService) applicationContext.getBean("channelService");
	}
	
	private Channel buildTestModel(){
	
		Channel channel = new Channel();
		channel.setCode("测试");
		channel.setName("测试");
		channel.setBrief("测试");
		channel.setTempleteId(1);
		channel.setStatus(1);
		channel.setOrdernum(1);
		channel.setCreateuser("测试");
        channel.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		channel.setUpdateuser("测试");
        channel.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
	    return channel;
	}
	private Channel addTestChannel(){
		Channel channel = buildTestModel();
	    channelService.addSave(channel);
	    return channel;
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
		Channel channel = buildTestModel();
	    test.setChannel(channel);
		String res = test.addSave();
		channelService.deleteByIds(" where id = "+channel.getId());
		Assert.assertNotNull(test.getChannel());
		Assert.assertEquals("addSave", res);
		
	}

	@Test
	public void testEdit() {
		Channel channel = addTestChannel();
		request.setParameter("id", ""+channel.getId());
		String res = test.edit();
		Assert.assertEquals("edit", res);
		Channel a = (Channel) request.getAttribute("channel");
		
		Assert.assertNotNull(a);
		Assert.assertEquals(channel.getId(), a.getId());
		channelService.deleteByIds(" where id = "+channel.getId());
	}
	@Test
	public void testEditSave(){
		
		Channel channel1 = addTestChannel();
	    Channel channel2 = buildTestModel();
	    channel2.setId(channel1.getId());
		test.setChannel(channel2);
		String edit_save_res = test.editSave();
		channelService.selectChannelById(channel1);
		Assert.assertEquals("editSave", edit_save_res);
		
		channelService.deleteByIds(" where id = "+channel1.getId());
	}
	@Test
	public void testDelete(){
		Channel channel = addTestChannel();
		request.setParameter("id", channel.getId().toString());
		String res = test.delete();
		Assert.assertEquals("deleteSuccess", res);
		Assert.assertNull(channelService.selectChannelById(channel));
		
	}
	@Test
	public void testDeleteByIds(){
		String[] ids = new String[3];
		Channel channel0 = addTestChannel();
		ids[0]=channel0.getId()+"";
		Channel channel1 = addTestChannel();
		ids[1]=channel1.getId()+"";
		Channel channel2 = addTestChannel();
		ids[2]=channel2.getId()+"";
		request.setParameter("id", ids);
		test.deleteByIds();
		Assert.assertNull(channelService.selectChannelById(channel0));
	}
}