package cn.wangjiannan.common.scan;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.wangjiannan.common.util.StringUtils;
import cn.wangjiannan.model.SysLog;
import cn.wangjiannan.service.SysLogService;

/**
 * 注解配置AOP（使用 AspectJ 类库实现的），大致分为三步
 * 
 * 1. 使用注解@Aspect来定义一个切面，在切面中定义切入点(@Pointcut),通知类型(@Before, @AfterReturning,@After,@AfterThrowing,@Around).
 * 
 * 2. 开发需要被拦截的类。
 * 
 * 3. 将切面配置到xml中，当然，我们也可以使用自动扫描Bean的方式。这样的话，那就交由Spring AoP容器管理（@Component）
 * 
 * @author wangjiannan
 * @date 2018年1月3日 下午1:14:43
 */
@Aspect // 意思是这个类为切面类
@Component // 因为作为切面类需要 Spring 管理起来，所以在初始化时就需要将这个类初始化加入 Spring 的管理
public class SysLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 定义切入点
	 * 
	 * @author wangjiannan
	 * @date 2018年1月3日 下午1:18:02
	 */
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void cutController() {
	}

	@Around("cutController()")
	public Object recordSysLog(ProceedingJoinPoint point) throws Throwable {
		String strClassName = point.getTarget().getClass().getName();
		String strMethodName = point.getSignature().getName();
		Object[] params = point.getArgs();
		StringBuffer bfParams = new StringBuffer();
		Enumeration<String> paraNames = null;
		HttpServletRequest request = null;
		if (params != null && params.length > 0) {
			request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			paraNames = request.getParameterNames();
			String key;
			String value;
			while (paraNames.hasMoreElements()) {
				key = paraNames.nextElement();
				value = request.getParameter(key);
				bfParams.append(key).append("=").append(value).append("&");
			}
			if (StringUtils.isBlank(bfParams)) {
				bfParams.append(request.getQueryString());
			}
		}
		String strMessage = String.format("[类名]:%s,[方法]:%s,[参数]:%s", strClassName, strMethodName, bfParams.toString());
		logger.info(strMessage);
		if (isWriteLog(strMethodName)) {
			try {
				Subject currentUser = SecurityUtils.getSubject();
				PrincipalCollection collection = currentUser.getPrincipals();
				if (null != collection) {
					String loginName = collection.getPrimaryPrincipal().toString();
					SysLog sysLog = new SysLog();
					sysLog.setLoginName(loginName);
					sysLog.setRoleName(loginName);
					sysLog.setOptContent(strMessage);
					sysLog.setCreateTime(new Date());
					if (request != null) {
						sysLog.setClientIp(request.getRemoteAddr());
					}
					logger.info(sysLog.toString());
					sysLogService.insert(sysLog);
				}
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return point.proceed();
	}

	private boolean isWriteLog(String method) {
		String[] pattern = { "login", "logout", "add", "edit", "delete", "grant" };
		for (String s : pattern) {
			if (method.indexOf(s) > -1) {
				return true;
			}
		}
		return false;
	}

}
