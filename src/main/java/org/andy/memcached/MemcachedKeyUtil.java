package org.andy.memcached;  
  
public class MemcachedKeyUtil {  
    private static final String GOODS_KEY_PREFIX = "goods_";  
      
    public static String getGoodsKey(long goodsId) {  
        return GOODS_KEY_PREFIX + goodsId;  
    }  
} 