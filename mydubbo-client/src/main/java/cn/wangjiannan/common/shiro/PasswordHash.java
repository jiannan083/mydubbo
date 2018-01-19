package cn.wangjiannan.common.shiro;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

import cn.wangjiannan.common.util.DigestUtils;

/**
 * shiro密码加密配置
 * 
 * @author wangjiannan
 * @date 2017年12月26日 下午2:54:47
 */
public class PasswordHash implements InitializingBean {
	private String algorithmName;
	private int hashIterations;

	public String getAlgorithmName() {
		return algorithmName;
	}

	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}

	public int getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(int hashIterations) {
		this.hashIterations = hashIterations;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.hasLength(algorithmName, "algorithmName mast be MD5、SHA-1、SHA-256、SHA-384、SHA-512");
	}

	public String toHex(Object source, Object salt) {
		return DigestUtils.hashByShiro(algorithmName, source, salt, hashIterations);
	}
}
