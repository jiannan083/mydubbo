package cn.wangjiannan.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangjiannan.mapper.ProductMapper;
import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.mapper.UserOrderMapper;
import cn.wangjiannan.model.Product;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.UserOrder;
import cn.wangjiannan.service.TransService;

@Service
public class TransServiceImpl implements TransService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private ProductMapper productMapper;

	@Override
	// @Transactional(propagation = Propagation.REQUIRED)
	public String testTrans() {
		User user = new User();
		user.setMobile("18515918722");
		user.setName("aa");
		user.setPid("345678909876543212");
		user.setVersion(0);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userMapper.insert(user);

		Product product = new Product();
		product.setName("烧鸡");
		product.setPrice(100.0);
		product.setVersion(0);
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		productMapper.insert(product);

		UserOrder userOrder = new UserOrder();
		userOrder.setUserId(1L);
		userOrder.setStatus(0);
		userOrder.setVersion(0);
		userOrder.setCreateTime(new Date());
		userOrder.setUpdateTime(new Date());
		userOrderMapper.insert(userOrder);
		// int i = 2 / 0;
		return "ok";
	}

}
