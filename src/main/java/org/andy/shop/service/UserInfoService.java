package org.andy.shop.service;

import org.andy.shop.common.BaseService;
import org.andy.shop.common.PageList;
import org.andy.shop.entity.UserInfo;

/**  
 * 创建时间：2015-2-13 下午1:00:51  
 * @author andy  
 * @version 2.2  
 * 描述： 
 */

public interface UserInfoService  extends BaseService<UserInfo>{
	 PageList<UserInfo> queryByPage();
}
