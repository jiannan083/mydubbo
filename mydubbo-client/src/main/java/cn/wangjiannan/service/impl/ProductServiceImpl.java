package cn.wangjiannan.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.wangjiannan.mapper.ProductMapper;
import cn.wangjiannan.model.Product;
import cn.wangjiannan.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductMapper productMapper;

	@Override
	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	public void inserProduct() {
		Product product = new Product();

		product.setName("烧鸡");
		product.setPrice(100.0);
		product.setVersion(0);
		product.setCreateTime(new Date());
		product.setUpdateTime(new Date());
		productMapper.insert(product);
	}
}
