package cn.wangjiannan.test.cache;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;

import cn.wangjiannan.model.User;
import cn.wangjiannan.test.base.BaseTest;

public class RedisTest extends BaseTest {
	@Autowired
	private RedisCacheManager cacheManager;

	@Test
	public void testRedis() {
		Long id = 2L;
		User user = new User();
		user.setId(id);
		user.setName("bb");

		Cache cache = cacheManager.getCache("halfHour");

		cache.put(id, user);

		System.out.println("-----------" + cache.get(id, User.class));
		Assert.assertNotNull(cache.get(id, User.class));
	}

}
