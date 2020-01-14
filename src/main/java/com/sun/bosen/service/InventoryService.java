package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.Inventory;

public interface InventoryService {

	List<Inventory> list(String cInvCCode, Integer limit);

	Inventory getInventory(String cInvCode);

}
