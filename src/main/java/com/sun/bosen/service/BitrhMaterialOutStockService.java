package com.sun.bosen.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.sun.bosen.pojo.PP_Podetails;

public interface BitrhMaterialOutStockService {

	String add(HttpSession session,PP_Podetails[] data) throws IOException;

}
