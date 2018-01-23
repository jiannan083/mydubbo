package cn.wangjiannan.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;

import cn.wangjiannan.base.BaseTest;

public class ShiroTest extends BaseTest {
	@Autowired
	private CacheManager cacheManager;

	// @Test
	// public void testShiro() {
	// Cache<String, AtomicInteger> cache = cacheManager.getCache("retryLimitCache");
	// cache.put("bfay", new AtomicInteger(1));
	//
	// System.out.println("-----------" + cache.get("bfay"));
	// }

}
