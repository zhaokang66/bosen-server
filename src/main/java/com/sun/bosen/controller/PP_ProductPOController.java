package com.sun.bosen.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.sun.bosen.pojo.PP_Pomain;
import com.sun.bosen.pojo.PP_ProductPO;
import com.sun.bosen.service.BirthPurchaseWarehousingService;
import com.sun.bosen.service.BirthproductionWarehousingService;
import com.sun.bosen.service.PP_PomainService;
import com.sun.bosen.service.PP_ProductPOService;

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
	BirthproductionWarehousingService birthproductionWarehousingService;
	
	@ResponseBody
	@RequestMapping("/listPP_ProductPO")
	public List<PP_ProductPO> listPP_ProductPO(boolean bFinished, int endId, HttpServletResponse req, HttpServletRequest res)
			throws UnsupportedEncodingException {
		List<PP_ProductPO> list = pp_ProductPOService.list(bFinished, endId);
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/listPP_Pomain")
	public List<PP_Pomain> listPP_Pomain(Integer ID) {
		System.out.println(ID);
		List<PP_Pomain> list = pp_PomainService.list(ID);
		System.out.println(JSONObject.toJSONString(list));
		return list;
	}
	
	@ResponseBody
	@RequestMapping("/submitpp_Pomain")
	public String submitpp_Pomain(@RequestBody PP_Pomain[] data) {
		System.out.println(JSONObject.toJSONString(data));
		return birthproductionWarehousingService.add(data);
	}
	
	
}
