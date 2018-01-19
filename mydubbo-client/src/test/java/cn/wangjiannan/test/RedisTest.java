package cn.wangjiannan.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.model.User;
import cn.wangjiannan.service.TestRedisService;
import cn.wangjiannan.test.base.BaseTest;

public class RedisTest extends BaseTest {
	@Autowired
	private TestRedisService testRedisService;

	@Test
	public void testRedis() {
		User user = testRedisService.selectByIdWithCache(1L);
		logger.info("-----" + user);
	}
}
