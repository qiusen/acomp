package com.dihaitech.acomp.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.dihaitech.acomp.BaseTestCase;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.util.DateUtil;

/**
 * 频道 DAO 接口测试
 * 
 * @author cg
 * @since 2014-08-27
 */
public class IChannelDAOTest extends BaseTestCase {
	@Resource
	private IChannelDAO channelDAO;
	private Channel buildTestModel(){
	
		Channel channel = new Channel();
		channel.setCode("测试");
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
	    channelDAO.addSaveChannel(channel);
	    return channel;
	}
	@Test
	public void getChannelCount() {
		addTestChannel();
		Channel total_obj = new Channel();
		Long total = channelDAO.getChannelCount(total_obj);
		Assert.assertTrue(total>=1);
		System.out.println("total :" +total);
	}
	@Test
	public void selectChannelLike() {
		addTestChannel();
		Channel tmp = new Channel();
		Page p = new Page(10, 10);
		tmp.setStart(p.getFirstItemPos());
		tmp.setPageSize(p.getPageSize());
		List<Channel> l = channelDAO.selectChannelLike(tmp);
		Assert.assertNotNull(l);
		Assert.assertTrue("ERROR:results is empty!!", l.size() >= 1);
	}

	@Test
	public void selectChannelById() {
		Channel obj = addTestChannel();
		Channel tmp = new Channel();
		tmp.setId(obj.getId());
		Channel res = channelDAO.selectChannelById(tmp);
		Assert.assertNotNull(res);
	}
	
	@Test
	public void addSaveChannel(){
		Channel obj = buildTestModel();
		int res = channelDAO.addSaveChannel(obj);
		Assert.assertTrue(res>0);
		Assert.assertNotNull(obj.getId());
		System.out.println("res :" +res);
		channelDAO.deleteByIds("where id = "+obj.getId());
	}
	
	@Test
	public void editSaveChannel(){
		Channel add_obj = addTestChannel();
		add_obj.setCode("测试2");
		add_obj.setName("测试2");
		add_obj.setBrief("测试2");
		add_obj.setTempleteId(21);
		add_obj.setStatus(21);
		add_obj.setOrdernum(21);
		add_obj.setCreateuser("测试2");
        add_obj.setCreatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		add_obj.setUpdateuser("测试2");
        add_obj.setUpdatetime(DateUtil.stringToDate(DateUtil
				.getNowDateString(DateUtil.TIME_CONTINUOUS_FORM)));
		
		int edit_res = channelDAO.editSaveChannel(add_obj);
		Assert.assertTrue(edit_res>0);
		
		channelDAO.deleteByIds("where id = "+add_obj.getId());
	}
	
	@Test
	public void deleteByIds(){
		Channel obj = addTestChannel();
		
		int res = channelDAO.deleteByIds("where id = "+obj.getId());
		Assert.assertTrue(res>0);
		Assert.assertNull(channelDAO.selectChannelById(obj));
	}
}