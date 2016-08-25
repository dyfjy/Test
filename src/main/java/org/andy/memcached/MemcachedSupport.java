package org.andy.memcached;  
  
public class MemcachedSupport {  
    public boolean setDetailData(int memIndex ,String key, Object value) {  
        return MemcachedUtil.getInstance().set(memIndex, key, value);  
    }  
      
    public Object getDetailData(int memIndex ,String key) {  
        return MemcachedUtil.getInstance().get(memIndex, key);  
    }  
      
    public boolean deleteDetailData(int memIndex ,String key) {  
        return MemcachedUtil.getInstance().delete(memIndex,key);  
    }  
} 