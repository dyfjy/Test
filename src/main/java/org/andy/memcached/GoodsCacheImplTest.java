package org.andy.memcached;

import javax.annotation.Resource;

import org.andy.shop.dao.UserInfoDao;
import org.andy.shop.entity.UserInfo;
import org.springframework.stereotype.Service;
@Service("goodsCacheImplTest")
public class GoodsCacheImplTest extends MemcachedSupport{  
    @Resource(name = "userInfoDao")  
    private UserInfoDao userInfoDao;  
    private int memIndex = MemcachedConstant.MEMCACHED_GOODSDETAIL;
    public UserInfo selectGoodsById(long goodsId) {  
    	UserInfo goods = null;  
        String goodsKey = MemcachedKeyUtil.getGoodsKey(goodsId);  
        goods =  (UserInfo) getDetailData(memIndex,goodsKey);  
        if (goods == null) {  
            goods = userInfoDao.get(Long.valueOf(goodsId).intValue());  
            if (goods != null) {  
                setDetailData(memIndex,goodsKey, goods);  
            }  
        }  
        return goods;  
    }  
}  