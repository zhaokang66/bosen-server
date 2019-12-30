package com.sun.bosen.service;

import java.util.List;

import com.sun.bosen.pojo.PP_ProductPO;

public interface PP_ProductPOService {

	List<PP_ProductPO> list(boolean bFinished, int endId);

}
