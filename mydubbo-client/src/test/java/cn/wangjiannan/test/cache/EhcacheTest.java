package cn.wangjiannan.test.cache;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import cn.wangjiannan.model.User;
import cn.wangjiannan.test.base.BaseTest;

public class EhcacheTest extends BaseTest {
	@Autowired
	private EhCacheCacheManager cacheManager;

	@Test
	public void testEhcache() {
		Long id = 1L;
		User user = new User();
		user.setId(id);
		user.setName("dsfsda");

		Cache cache = cacheManager.getCache("halfHour");
		cache.put(id, user);

		System.out.println("-----------" + cache.get(id, User.class));
		Assert.assertNotNull(cache.get(id, User.class));
	}

}
