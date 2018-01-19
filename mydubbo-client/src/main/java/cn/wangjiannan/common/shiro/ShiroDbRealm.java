package cn.wangjiannan.common.shiro;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.RoleService;
import cn.wangjiannan.service.UserService;

/**
 * shiro认证、授权
 * 
 * 一般继承AuthorizingRealm（授权）即可；其继承了AuthenticatingRealm（即身份验证）， 而且也间接继承了CachingRealm（带有缓存实现）
 * 
 * @author wangjiannan
 * @date 2017年12月17日 下午1:19:24
 */
public class ShiroDbRealm extends AuthorizingRealm {
	private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
		super(cacheManager, matcher);
	}

	/**
	 * Shiro登录认证(Authenticator)
	 * 
	 * (原理：用户提交用户名和密码-shiro封装令牌-realm通过用户名将密码查询返回-shiro自动去比较查询出密码和用户输入密码是否一致-进行登陆控制 )
	 * 
	 * @author wangjiannan
	 * @date 2017年12月18日 下午1:28:33
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("Shiro开始登录认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserVo userVo = new UserVo();
		userVo.setLoginName(token.getUsername());
		List<User> list = userService.selectByLoginName(userVo);
		// 账号不存在
		if (list == null || list.isEmpty()) {
			return null;
		}
		User user = list.get(0);
		// 账号未启用
		if (user.getStatus() == 1) {
			return null;
		}
		// 读取用户的url和角色
		Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
		Set<String> urls = resourceMap.get("urls");
		Set<String> roles = resourceMap.get("roles");
		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), urls);
		shiroUser.setRoles(roles);
		// 认证缓存信息
		return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), ShiroByteSource.of(user.getSalt()), getName());
	}

	/**
	 * Shiro授权（访问控制）
	 * 
	 * 授权实现:则与认证实现非常相似，在我们自定义的Realm中，重载doGetAuthorizationInfo()方法，重写获取用户权限的方法即可
	 * 
	 * @author wangjiannan
	 * @date 2017年12月18日 下午1:28:37
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setRoles(shiroUser.getRoles());
		info.addStringPermissions(shiroUser.getUrlSet());
		return info;
	}

	@Override
	protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
		return shiroUser.toString();
	}

	@Override
	protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
		return shiroUser.toString();
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param shiroUser
	 */
	public void removeUserCache(ShiroUser shiroUser) {
		removeUserCache(shiroUser.getLoginName());
	}

	/**
	 * 清除用户缓存
	 * 
	 * @param loginName
	 */
	public void removeUserCache(String loginName) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection();
		principals.add(loginName, super.getName());
		super.clearCachedAuthenticationInfo(principals);
	}

}
