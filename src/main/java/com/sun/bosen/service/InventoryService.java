package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.Inventory;

public interface InventoryService {

	List<Inventory> list(String cInvCCode);

	Inventory getInventory(String cInvCode);

}
