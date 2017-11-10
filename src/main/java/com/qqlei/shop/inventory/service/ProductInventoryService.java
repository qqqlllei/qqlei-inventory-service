package com.qqlei.shop.inventory.service;

import com.qqlei.shop.inventory.model.ProductInventory;

public interface ProductInventoryService {
	
	 void add(ProductInventory productInventory);
	
	 void update(ProductInventory productInventory);
	
	 void delete(Long id);
	
	 ProductInventory findById(Long id);
	
}
