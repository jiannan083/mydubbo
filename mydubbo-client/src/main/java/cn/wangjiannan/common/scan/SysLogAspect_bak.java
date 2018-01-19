package cn.wangjiannan.common.scan;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

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
// @Aspect // 意思是这个类为切面类
// @Component // 因为作为切面类需要 Spring 管理起来，所以在初始化时就需要将这个类初始化加入 Spring 的管理
public class SysLogAspect_bak {
	// private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

	// @Autowired
	// private SysLogService sysLogService;

	/**
	 * 定义切入点
	 * 
	 * @author wangjiannan
	 * @date 2018年1月3日 下午1:18:02
	 */
	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void cutController() {
	}

	// @Before("cutController()")
	// public void before() {
	// System.out.println("I am before");
	// }
	//
	// @After("cutController()")
	// public void after() {
	// System.out.println("I am after");
	// }
	//
	// @AfterReturning("cutController()")
	// public void afterReturning() {
	// System.out.println("I am afterReturning");
	// }
	//
	// @AfterThrowing("cutController()")
	// public void afterThrowing() {
	// System.out.println("I am afterThrowing");
	// }

	@Around("cutController()")
	public void recordSysLog(ProceedingJoinPoint pj) throws Throwable {
		System.out.println("I am around start");
		long start = new Date().getTime();
		pj.proceed();// 上面是前置before执行；本句是方法内容执行；下面是after执行
		System.out.println("I am around end");
		long end = new Date().getTime();
		System.out.println("I am used time " + (end - start));
	}

}
