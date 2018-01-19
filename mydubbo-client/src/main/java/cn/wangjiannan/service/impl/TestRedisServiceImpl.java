package cn.wangjiannan.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.service.TestRedisService;

@Service
public class TestRedisServiceImpl extends ServiceImpl<UserMapper, User> implements TestRedisService {
	private static final Logger logger = LoggerFactory.getLogger(TestRedisServiceImpl.class);

	/**
	 * 
	 * "@Cacheable"可以注解在方法上也可以注解在类上。当标记在方法上面时，表示该方法是可以缓存的；
	 * 
	 * 如果标记在类上面，则表示该类的所有方法都是可以缓存的。使用这个注解的方法在执行后会缓存其返回结果;
	 * 
	 * 可以指定三个属性：value、key、condition;
	 * 
	 * eg:@Cacheable(value = { "users" }, key = "#user.id", condition = "#user.id%2==0");
	 *
	 * value:指定Cache的名称 //value值是必须指定的，其表示该方法缓存的返回结果是被缓存在哪个Cache上的， 对应Cache的名称。其可以是一个Cache也可以使多个Cache（数组）
	 * 
	 * key:自定义key //key属性是用来指定Spring缓存方法返回结果时所对应的key值的。该属性支持EL表达式。当我们没有指定key时，Spring会使用默认策略生成key
	 * 
	 * condition:指定发生条件 //通过condition可以设置一个条件，其条件值是使用SpringEL表达式来指定的。当为true时进行缓存处理；为false时不进行缓存处理，即每次调用该方法都会执行
	 * 
	 * @author wangjiannan
	 * @date 2018年1月12日 上午11:47:00
	 * @param id
	 * @return
	 */
	@Override
	@Cacheable(value = "halfHour", key = "#id")
	public User selectByIdWithCache(Long id) {
		logger.info("cache miss, invoke find by id, id={}", id);
		User user = this.selectById(id);
		return user;

	}
}
