package com.sun.bosen.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.bosen.service.RdStyleService;
import com.sun.bosen.pojo.RdStyle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")


public class RdStyleController {

	@Autowired
	RdStyleService rdStyleService;
	
	@ResponseBody
	@RequestMapping("/getRdStyleList")
	public List<RdStyle> getRdStyleList() {
		List<RdStyle> list = rdStyleService.list();
		return list;
	}
}
