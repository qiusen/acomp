package com.dihaitech.acomp.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

import static org.hamcrest.CoreMatchers.equalTo;	

/**
 * 频道 Service 接口测试
 * 
 * @author cg
 * @since 2014-08-26
 */
public class IChannelServiceTest extends BaseTestCase{
	@Autowired
	private IChannelService channelService;
	private Channel buildTestModel(){
	
		Channel channel = new Channel();
		channel.setName("测试");
		channel.setBrief("测试");
		channel.setTempleteId(123);
		channel.setStatus(123);
		channel.setOrdernum(123);
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
	public void selectChannelPage(){
		addTestChannel();
	    Page p = channelService.selectChannel(new Channel(), 10);
	    Assert.assertNotNull(p);
	    Assert.assertTrue(p.getTotalPage()>=1);
	}
	
	@Test
	public void selectChannelPageList(){
		
		addTestChannel();
		
		Channel obj = new Channel();
		Page page = new Page(10, 10);
		obj.setStart(page.getFirstItemPos());
		obj.setPageSize(page.getPageSize());
		List<Channel> channelList = channelService.selectChannel(obj, page);
		Assert.assertTrue(channelList.size() >=1);
	}
	
	@Test
	public void selectAll(){
		
		addTestChannel();
		
		Assert.assertTrue("fail: channel`s total is bad res!", channelService.selectAll().size() >0);
	}
	@Test
	public void addSave(){

		Channel channel = buildTestModel();
		int pr = channelService.selectAll().size();
		int res = channelService.addSave(channel);
		int s = channelService.selectAll().size();
		Assert.assertTrue(res>0);
		Assert.assertNotNull(channelService.selectChannelById(channel));
		Assert.assertTrue("Fail:call channelService insert fail!", s==pr+1);
	}

	@Test
	public void selectChannelById(){
		
		Channel channel = addTestChannel();
		
		Channel tmp = new Channel();
		tmp.setId(channel.getId());
		Assert.assertNotNull(channelService.selectChannelById(tmp));
	}

	@Test
	public void editSave(){
		
		Channel channel1 = addTestChannel();
		
	    Channel channel2 = channelService.selectChannelById(channel1);
		channel2.setName("测试2");
		channel2.setBrief("测试2");
		channel2.setTempleteId(21);
		channel2.setStatus(21);
		channel2.setOrdernum(21);
		channel2.setCreateuser("测试2");
        channel2.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		channel2.setUpdateuser("测试2");
        channel2.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		channelService.editSave(channel2);
		
		Channel tmp = channelService.selectChannelById(channel2);
		Assert.assertNotEquals(channel1.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals(channel2.getUpdateuser(), tmp.getUpdateuser());
		Assert.assertEquals("测试2", tmp.getUpdateuser());
		Assert.assertThat(channel2.getOrdernum(),equalTo(tmp.getOrdernum()));
	}
	
	@Test
	public void deleteByIds(){
		
		Channel channel = addTestChannel();
		
		Channel obj = new Channel();
		obj.setId(channel.getId());
		Assert.assertNotNull(channelService.selectChannelById(obj));
		int res = channelService.deleteByIds("where id = "+channel.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(channelService.selectChannelById(obj));
	}
}