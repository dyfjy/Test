package org.andy.shop.service.impl;

import org.andy.shop.common.BaseServiceImpl;
import org.andy.shop.common.PageList;
import org.andy.shop.dao.UserInfoDao;
import org.andy.shop.entity.UserInfo;
import org.andy.shop.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 创建时间：2015-2-13 下午1:03:47
 * 
 * @author andy
 * @version 2.2 描述：
 */
@Service("userInfoService")
public class UserInfoServiceImpl extends BaseServiceImpl<UserInfo> implements UserInfoService {
	@Autowired
	private UserInfoDao userInfoDao;
	
	public PageList<UserInfo> queryByPage() {
		String hql ="from UserInfo";
		int page =0;
		int rows=5;
		return userInfoDao.findPageList(hql, page, rows);
	}
}
