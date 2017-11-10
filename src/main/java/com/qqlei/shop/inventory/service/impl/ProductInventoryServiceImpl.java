package com.qqlei.shop.inventory.service.impl;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqlei.shop.inventory.mapper.ProductInventoryMapper;
import com.qqlei.shop.inventory.model.ProductInventory;
import com.qqlei.shop.inventory.service.ProductInventoryService;
import redis.clients.jedis.JedisCluster;

@Service
public class ProductInventoryServiceImpl implements ProductInventoryService {

	@Autowired
	private ProductInventoryMapper productInventoryMapper;

	@Autowired
	private JedisCluster jedisCluster;
	
	public void add(ProductInventory productInventory) {
		productInventoryMapper.add(productInventory);
		jedisCluster.set("product_inventory_" + productInventory.getProductId(), JSONObject.toJSONString(productInventory));
	}

	public void update(ProductInventory productInventory) {
		productInventoryMapper.update(productInventory);
		jedisCluster.set("product_inventory_" + productInventory.getProductId(), JSONObject.toJSONString(productInventory));
	}

	public void delete(Long id) {
		ProductInventory productInventory = findById(id);
		productInventoryMapper.delete(id);
		jedisCluster.del("product_inventory_" + productInventory.getProductId());
	}

	public ProductInventory findById(Long id) {
		return productInventoryMapper.findById(id);
	}

}
