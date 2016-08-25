package org.andy.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedUtil {  
    private int MEMCACHED_SERVER_NUM = 1;   //memcached 服务器数量
    private SockIOPool[] pools = new SockIOPool[MEMCACHED_SERVER_NUM];  
    private MemCachedClient[] mcs = new MemCachedClient[MEMCACHED_SERVER_NUM];  
    private final String[] poolNames = new String[] { "GOODSDETAIL_POOL", "", "", "" };  
    private static MemcachedUtil instance;  
    private MemcachedUtil() {  
        this.init();  
    }  
    // 单例  
    public static MemcachedUtil getInstance() {  
        if (MemcachedUtil.instance == null) {  
            synchronized (MemcachedUtil.class) {  
                if (MemcachedUtil.instance == null) {  
                    MemcachedUtil.instance = new MemcachedUtil();  
                }  
            }  
        }  
        return MemcachedUtil.instance;  
    }  
      
    public Object get(int index, String key) {  
        return this.mcs[index].get(key);  
    }  
      
    public boolean set(int index, String key, Object value) {  
        return this.mcs[index].set(key, value);  
    }  
      
    public boolean delete(int index,String key) {  
		return this.mcs[index].delete(key);  
    }  
    public MemCachedClient getMemCachedClient(int index) {  
        return this.mcs[index];  
    }  
      
    public void init() {  
    	String[] servers={"127.0.0.1:11211"};//服务器列表
    	Integer[] weights;//
    	int initConn;
    	int minConn;
    	int maxConn;
    	int maxIdle;
    	long maxBusyTime;
    	long maintSleep;
    	boolean ifFailOver;
    	int socketConnectTO;
    	int socketTO;
    	boolean ifNagle;
    	boolean ifFailback;
    	boolean ifAliveCheck;
        for (int i = 0; i < MEMCACHED_SERVER_NUM; ++i) {  
            this.pools[i] = SockIOPool.getInstance(poolNames[i]);  
			this.pools[i].setServers(servers);  
//			this.pools[i].setWeights(weights);  
//			this.pools[i].setInitConn(initConn);  
//			this.pools[i].setMinConn(minConn);  
//			this.pools[i].setMaxConn(maxConn);  
//			this.pools[i].setMaxIdle(maxIdle);  
//			this.pools[i].setMaxBusyTime(maxBusyTime);  
//			this.pools[i].setMaintSleep(maintSleep);  
//			this.pools[i].setNagle(ifNagle);  
//			this.pools[i].setSocketTO(socketTO);  
//			this.pools[i].setSocketConnectTO(socketConnectTO);  
//			this.pools[i].setFailover(ifFailOver);  
//			this.pools[i].setFailback(ifFailback);  
//			this.pools[i].setAliveCheck(ifAliveCheck);  
            this.pools[i].initialize();  
            this.mcs[i] = new MemCachedClient(poolNames[i]);  
        }  
    }  
}