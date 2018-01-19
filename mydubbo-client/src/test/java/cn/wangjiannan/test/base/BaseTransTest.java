package cn.wangjiannan.test.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * AbstractTransactionalJUnit4SpringContextTests默认会回滚
 * 
 * 详解：@TransactionConfiguration使用在类级别上，里面有两个属性，transactionManager 指定事物管理器的名字，
 * 
 * 默认的名称为：transactionManager；defaultRollback 配置全局默认的事务回滚，默认为：true。
 * 
 * 取消事务回滚
 * 
 * 第一种：配置全局默认的事务回滚
 * 
 * 在测试类加上@TransactionConfiguration(defaultRollback=false)
 * 
 * 第二种：配置具体测试方法的事务回滚
 * 
 * 在测试类的具体方法加上@Rollback(false)
 * 
 * @author wangjiannan
 * @date 2017年12月18日 下午10:21:03
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
public abstract class BaseTransTest extends AbstractTransactionalJUnit4SpringContextTests {

}
