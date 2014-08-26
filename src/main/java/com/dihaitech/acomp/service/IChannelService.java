package com.dihaitech.acomp.service;

import java.util.List;


import com.dihaitech.acomp.model.Channel;
import com.dihaitech.acomp.util.Page;

/**
 * 频道 业务接口
 * 
 * @author cg
 *
 * @date 2014-08-25
 */
public interface IChannelService {
	/**
	 * 查询 Channel Page 对象
	 * @param channel
	 * @param pageSize
	 * @return
	 */
	public Page selectChannel(Channel channel, int pageSize);

	/**
	 * 分页查找 频道
	 * @param channel
	 * @param page
	 * @return
	 */
	public List<Channel> selectChannel(Channel channel, Page page);
	
	/**
	 * 查询所有 频道
	 * @return
	 */
	public List<Channel> selectAll();
	
	/**
	 * 根据 ID 查找 频道 
	 * @param channel
	 * @return
	 */
	public Channel selectChannelById(Channel channel);
	
	/**
	 * 添加 频道 
	 * @param channel
	 * @return
	 */
	public int addSave(Channel channel);
	
	/**
	 * 修改 频道 
	 * @param channel
	 * @return
	 */
	public int editSave(Channel channel);
	
	/**
	 * 根据 ID 删除 频道 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);
}
