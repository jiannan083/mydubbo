package cn.wangjiannan.common.shiro.cache;

import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

/**
 * 缓存管理器:使用spring-cache作为shiro缓存
 * 
 * @author wangjiannan
 * @date 2017年12月26日 下午1:44:45
 */
public class ShiroCacheManager implements org.apache.shiro.cache.CacheManager, org.apache.shiro.util.Destroyable {
	private static final Logger logger = LoggerFactory.getLogger(ShiroCacheManager.class);
	private CacheManager cacheManager;

	public CacheManager getCacheManager() {
		return cacheManager;
	}

	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Override
	public void destroy() throws Exception {
		cacheManager = null;

	}

	@Override
	public <K, V> org.apache.shiro.cache.Cache<K, V> getCache(String name) throws CacheException {
		if (logger.isTraceEnabled()) {
			logger.trace("Acquiring ShiroSpringCache instance named [" + name + "]");
		}
		Cache cache = cacheManager.getCache(name);
		return new ShiroCache<K, V>(cache);
	}

}
