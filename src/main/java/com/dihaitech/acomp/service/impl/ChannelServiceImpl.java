package com.dihaitech.acomp.service.impl;

import java.util.List;
import javax.annotation.Resource;
import com.dihaitech.acomp.util.Page;
import com.dihaitech.acomp.dao.IChannelDAO;
import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.service.IChannelService;

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
	 * @see com.dihaitech.acomp.service.IChannelService#addSave(com.dihaitech.acomp.model.Channel)
	 */
	public int addSave(Channel channel) {
		return channelDAO.addSaveChannel(channel);
	}
	
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IChannelService#deleteByIds(java.lang.String)
	 */
	public int deleteByIds(String str) {
		return channelDAO.deleteByIds(str);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IChannelService#editSave(com.dihaitech.acomp.model.Channel)
	 */
	public int editSave(Channel channel) {
		return channelDAO.editSaveChannel(channel);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.IChannelService#selectAll()
	 */
	public List<Channel> selectAll() {
		return channelDAO.selectAll();
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IChannelService#selectChannel(com.dihaitech.acomp.model.Channel, int)
	 */
	public Page selectChannel(Channel channel, int pageSize) {
		return new Page(channelDAO.getChannelCount(channel), pageSize);
	}
	
	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IChannelService#selectChannel(com.dihaitech.acomp.model.Channel, com.dihaitech.acomp.controller.helper.Page)
	 */
	public List<Channel> selectChannel(Channel channel, Page page) {
		channel.setStart(page.getFirstItemPos());
		channel.setPageSize(page.getPageSize());
		return channelDAO.selectChannelLike(channel);
	}

	/* (non-Javadoc)
	 * @see com.dihaitech.acomp.service.IChannelService#selectChannelById(com.dihaitech.acomp.model.Channel)
	 */
	public Channel selectChannelById(Channel channel) {
		return channelDAO.selectChannelById(channel);
	}
}
