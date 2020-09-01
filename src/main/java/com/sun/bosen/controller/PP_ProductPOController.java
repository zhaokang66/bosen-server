package com.sun.bosen.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.PP_ProductPO;
import com.sun.bosen.pojo.Rdrecords;
import com.sun.bosen.service.BirthPurchaseWarehousingService;
import com.sun.bosen.service.CurrentStockService;
import com.sun.bosen.service.BirthProductionWarehousingService;
import com.sun.bosen.service.PP_PodetailsService;
import com.sun.bosen.service.PP_PomainService;
import com.sun.bosen.service.PP_ProductPOService;
import com.sun.bosen.util.ImageUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")
public class PP_ProductPOController {
	
	@Autowired
	PP_ProductPOService pp_ProductPOService;
	@Autowired
	PP_PomainService pp_PomainService;
	@Autowired
	BirthProductionWarehousingService birthproductionWarehousingService;
	@Autowired
	PP_PodetailsService pp_PodetailsService;
	@Autowired
	CurrentStockService currentStockService;
	
	@ResponseBody
	@RequestMapping("/listPP_ProductPO")
	public List<PP_ProductPO> listPP_ProductPO(boolean bFinished, int endId, boolean detailsFinshed, 
			String cDepCode, String productionCode,String cWhCode,
			HttpServletResponse req, HttpServletRequest res)
			throws UnsupportedEncodingException {
		List<PP_ProductPO> list = pp_ProductPOService.list(bFinished, endId, detailsFinshed, cDepCode, productionCode, cWhCode);
		System.out.println(JSONObject.toJSONString(list));
		
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/listPP_Pomain")
	public List<PP_Pomain> listPP_Pomain(Integer ID, boolean bFinished, boolean detailsFinshed,String productionCode, String cdepcode ) throws UnsupportedEncodingException {
		System.out.println(ID);
		List<PP_Pomain> list = pp_PomainService.list(ID, bFinished, detailsFinshed,cdepcode,productionCode);
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	@ResponseBody
	@RequestMapping("/listPP_Podetails")
	public List<PP_Podetails> listPP_Podetails(Integer mainId, boolean detailsFinshed) {
		System.out.println(mainId);
		List<PP_Podetails> list = pp_PodetailsService.list(mainId, detailsFinshed);
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/submitpp_Pomain")
	public String submitpp_Pomain(@RequestBody PP_Pomain[] data) {
		System.out.println(JSONObject.toJSONString(data));
		return birthproductionWarehousingService.add(data);
//		return "123";
	}
	
	@ResponseBody
	@RequestMapping("/getInStock")
	public BigDecimal getInStock(String cWhCode, String cInvCode) {
		return currentStockService.getInStock(cWhCode, cInvCode);
	}
	

	
	

	

	
}
