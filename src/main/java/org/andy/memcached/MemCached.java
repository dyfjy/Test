package org.andy.memcached;
import com.whalin.MemCached.MemCachedClient;

/**
 * 使用memcached的缓存实用类.
 * 
 * @author 铁木箱子
 * 
 */
public class MemCached {
	// 创建全局的唯一实例
	protected static MemCachedClient  mcc = new MemCachedClient();

}