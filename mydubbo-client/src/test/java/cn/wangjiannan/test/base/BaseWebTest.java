package cn.wangjiannan.test.base;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
// 注解在类上,用来声明加载的ApplicationContex是一个WebApplicationContext,它的属性指定的是Web资源的位置,默认为 src/main/webapp
@WebAppConfiguration(value = "src/main/webapp") // Spring Web测试框架@since Spring 3.2
@ContextHierarchy({ @ContextConfiguration(name = "parent", locations = "classpath:applicationContext.xml"),
		@ContextConfiguration(name = "child", locations = "classpath:spring-mvc.xml") })
public abstract class BaseWebTest {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	// 过@Autowired WebApplicationContext wac：注入web环境的ApplicationContext容器；
	@Autowired
	protected WebApplicationContext wac;
	protected MockMvc mockMvc;

	@Before
	public void setUp() {
		// 然后通过MockMvcBuilders.webAppContextSetup(wac).build()创建一个MockMvc进行测试；
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
}
