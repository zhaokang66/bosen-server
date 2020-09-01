package com.sun.bosen.service;

import java.io.IOException;

import javax.servlet.http.HttpSession;

import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.Rdrecords;

public interface BitrhMaterialOutStockService {

	String add(HttpSession session,PP_Podetails[] data) throws IOException;

	String submitOtherMaterial(HttpSession session, Rdrecords[] data) throws IOException;

}
