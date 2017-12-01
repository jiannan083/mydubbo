package cn.wangjiannan.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.mapper.UserOrderMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.UserOrder;
import cn.wangjiannan.service.ProductService;
import cn.wangjiannan.service.TransService;

@Service
public class TransServiceImpl implements TransService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserOrderMapper userOrderMapper;
	@Autowired
	private ProductService productService;

	@Override
	// @Transactional(propagation = Propagation.REQUIRED)
	public String testTrans() {
		inserUser();
		// productService.inserProduct();
		// insertUserOrder();
		// inserProduct();
		// try {
		// Thread.sleep(1000 * 20);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// int i = 2 / 0;

		return "ok";
	}

	private void inserUser() {
		User user = new User();
		user.setMobile("18515918722");
		user.setName("aa");
		user.setPid("345678909876543212");
		user.setVersion(0);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userMapper.insert(user);
	}

	@Override
	public void insertUserOrder() {
		UserOrder userOrder = new UserOrder();
		userOrder.setUserId(1L);
		userOrder.setStatus(0);
		userOrder.setVersion(0);
		userOrder.setCreateTime(new Date());
		userOrder.setUpdateTime(new Date());
		userOrderMapper.insert(userOrder);
	}

}
