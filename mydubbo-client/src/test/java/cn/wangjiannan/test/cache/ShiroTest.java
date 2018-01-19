package cn.wangjiannan.test.cache;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.test.base.BaseTest;

public class ShiroTest extends BaseTest {
	@Autowired
	private CacheManager cacheManager;

	@Test
	public void testShiro() {
		Cache<String, AtomicInteger> cache = cacheManager.getCache("retryLimitCache");
		cache.put("bfay", new AtomicInteger(1));

		System.out.println("-----------" + cache.get("bfay"));
	}

}
