package com.sun.bosen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Inventory;

public interface InventoryMapper {

	List<Inventory> list(@Param(value="cInvCCode") String cInvCCode);

	Inventory getInventory(@Param(value="cInvCode") String cInvCode);

}
