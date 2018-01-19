/**
 * The MIT License (MIT)
 * Copyright (c) 2016 Dreamlu (596392912@qq.com).
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package cn.wangjiannan.common.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * 输错5次密码锁定半小时
 * 
 * @author wangjiannan
 * @date 2017年12月26日 下午3:01:08
 */
public class RetryLimitCredentialsMatcher extends HashedCredentialsMatcher implements InitializingBean {
	private static final Logger logger = LoggerFactory.getLogger(RetryLimitCredentialsMatcher.class);
	private final static String DEFAULT_CHACHE_NAME = "retryLimitCache";

	private final CacheManager cacheManager;
	private String retryLimitCacheName;
	private Cache<String, AtomicInteger> passwordRetryCache;
	private PasswordHash passwordHash;

	public RetryLimitCredentialsMatcher(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
		this.retryLimitCacheName = DEFAULT_CHACHE_NAME;
	}

	public String getRetryLimitCacheName() {
		return retryLimitCacheName;
	}

	public void setRetryLimitCacheName(String retryLimitCacheName) {
		this.retryLimitCacheName = retryLimitCacheName;
	}

	public void setPasswordHash(PasswordHash passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken authcToken, AuthenticationInfo info) {
		String username = (String) authcToken.getPrincipal();
		// retry count + 1
		AtomicInteger retryCount = passwordRetryCache.get(username);
		if (retryCount == null) {
			retryCount = new AtomicInteger(0);
			passwordRetryCache.put(username, retryCount);
		}
		if (retryCount.incrementAndGet() > 5) {
			// if retry count > 5 throw
			logger.warn("username: " + username + " tried to login more than 5 times in period");
			throw new ExcessiveAttemptsException("用户名: " + username + " 密码连续输入错误超过5次，锁定半小时！");
		} else {
			passwordRetryCache.put(username, retryCount);
		}

		boolean matches = super.doCredentialsMatch(authcToken, info);
		if (matches) {
			// clear retry data
			passwordRetryCache.remove(username);
		}
		return matches;
	}

	/**
	 * InitializingBean接口为bean提供了初始化方法的方式,它只包括afterPropertiesSet方法,
	 * 
	 * 凡是继承该接口的类,在初始化bean的时候会执行该方法
	 * 
	 * @author wangjiannan
	 * @date 2017年12月26日 下午2:58:08
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(passwordHash, "you must set passwordHash!");
		super.setHashAlgorithmName(passwordHash.getAlgorithmName());
		super.setHashIterations(passwordHash.getHashIterations());
		this.passwordRetryCache = cacheManager.getCache(retryLimitCacheName);
	}
}
