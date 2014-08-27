package com.dihaitech.acomp.dao;

import java.util.List;


import com.dihaitech.acomp.model.Channel;

/**
 * 频道 DAO 接口
 * 
 * @author cg
 * 
 * @since 2014-08-25
 */
public interface IChannelDAO {

	/**
	 * 根据条件查询频道 条数
	 * 
	 * @param channel
	 * @return
	 */
	public Long getChannelCount(Channel channel);

	/**
	 * 分页查找频道
	 * 
	 * @param channel
	 * @param page
	 * @return
	 */
	public List<Channel> selectChannelLike(Channel channel);

	/**
	 * 添加频道
	 * 
	 * @param channel
	 * @return
	 */
	public int addSaveChannel(Channel channel);

	/**
	 * 根据ID获取指定的频道信息
	 * 
	 * @param channel
	 * @return
	 */
	public Channel selectChannelById(Channel channel);

	/**
	 * 修改频道
	 * 
	 * @param channel
	 * @return
	 */
	public int editSaveChannel(Channel channel);

	/**
	 * 根据ID删除指定的频道
	 * 
	 * @param str
	 * @return
	 */
	public int deleteByIds(String str);

	/**
	 * 查询所有频道
	 * 
	 * @return
	 */
	public List<Channel> selectAll();
	
	/**
	 * 根据状态获取所有频道
	 * @param channel
	 * @return
	 */
	public List<Channel> selectChannelByStatus(Channel channel);
	
	/**
	 * 根据IDS获取所有频道
	 * @param channel
	 * @return
	 */
	public List<Channel> selectChannelByIds(Channel channel);
}
