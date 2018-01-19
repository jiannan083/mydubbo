package cn.wangjiannan.common.scan;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
public class InvokeLogAspect {
	private static final Logger logger = LoggerFactory.getLogger(InvokeLogAspect.class);

	/**
	 * 定义切入点
	 * 
	 * @author wangjiannan
	 * @date 2018年1月9日 下午4:18:07
	 */
	@Pointcut("execution(* cn.wangjiannan.manager..*.Invoke*.*(..))")
	private void cutInvokeMethod() {
	}

	@Around("cutInvokeMethod()")
	public void recordInvokeLog(ProceedingJoinPoint point) throws Throwable {
		System.out.println("-----------------recordInvokeLog-----------------------");
		String className = point.getTarget().getClass().getName();
		String methodName = point.getSignature().getName();
		if (isWriteLog(methodName)) {
			long start = System.currentTimeMillis();
			point.proceed();
			long end = System.currentTimeMillis();
			logger.info("className={},methodName={}耗时为{}", className, methodName, end - start);
		} else {
			System.out.println("------------not write----------------------");
		}
	}

	private boolean isWriteLog(String method) {
		String[] pattern = { "getPositionByLatLng", "getLnyLatByPosition" };
		for (String s : pattern) {
			if (method.indexOf(s) > -1) {
				return true;
			}
		}
		return false;
	}

}
