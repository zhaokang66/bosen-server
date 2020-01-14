package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Inventory;

public interface InventoryMapper {

	List<Inventory> list(Map<String,Object> param);

	Inventory getInventory(@Param(value="cInvCode") String cInvCode);

}
