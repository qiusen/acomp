package com.dihaiboyun.cms.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaiboyun.common.util.Page;
import com.dihaiboyun.cms.dao.IChannelDAO;
import com.dihaiboyun.cms.model.Channel;
import com.dihaiboyun.cms.service.IChannelService;

/**
 * 频道 业务接口 IChannelService 实现类
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
public class ChannelServiceImpl implements IChannelService {

	@Resource
	private IChannelDAO channelDAO;

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#addSave(com.dihaiboyun.cms.model.Channel)
	 */
	public int addSave(Channel channel) {
		return channelDAO.addSaveChannel(channel);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return channelDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#editSave(com.dihaiboyun.cms.model.Channel)
	 */
	public int editSave(Channel channel) {
		return channelDAO.editSaveChannel(channel);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.IChannelService#selectAll()
	 */
	public List<Channel> selectAll() {
		return channelDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#selectChannel(com.dihaiboyun.cms.model.Channel, int)
	 */
	public Page selectChannel(Channel channel, int pageSize) {
		return new Page(channelDAO.getChannelCount(channel), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#selectChannel(com.dihaiboyun.cms.model.Channel, com.dihaiboyun.cms.controller.helper.Page)
	 */
	public List<Channel> selectChannel(Channel channel, Page page) {
		channel.setStart(page.getFirstItemPos());
		channel.setPageSize(page.getPageSize());
		return channelDAO.selectChannelLike(channel);
	}

	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#selectChannelById(com.dihaiboyun.cms.model.Channel)
	 */
	public Channel selectChannelById(Channel channel) {
		return channelDAO.selectChannelById(channel);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#selectChannelByStatus(com.dihaiboyun.cms.model.Channel)
	 */
	@Override
	public List<Channel> selectChannelByStatus(Channel channel) {
		// TODO Auto-generated method stub
		return channelDAO.selectChannelByStatus(channel);
	}


	/* (non-Javadoc)
	 * @see com.dihaiboyun.cms.service.IChannelService#selectChannelByIds(com.dihaiboyun.cms.model.Channel)
	 */
	@Override
	public List<Channel> selectChannelByIds(Channel channel) {
		// TODO Auto-generated method stub
		return channelDAO.selectChannelByIds(channel);
	}
}
