package com.sun.bosen.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.sun.bosen.mapper.PP_PodetailsMapper;
import com.sun.bosen.mapper.UserMapper;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.User;
import com.sun.bosen.pojo.User_Task;
import com.sun.bosen.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	UserService userService;
	@Autowired
	PP_PodetailsMapper pp_PodetailsMapper;
	/**
	 * 
	 * @param data
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public List<User> login(@RequestBody User data) {
		System.out.println(data);
		List<User> user = userService.login(data);
		return user;
	}
	
	@ResponseBody
	@RequestMapping("/addUser")
	public void addUser(@RequestBody User data) {
		System.out.println(data);
		userService.addUser(data);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public void delete(@RequestBody User data) {
		System.out.println(data);
		userService.delete(data);
	}
	
	/**	
	 * 获取工组和生产编号，把数据进行分发，根据库管员字段，
	 * @param department productionCode
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sendMessage")
	public String sendMessage(@RequestBody PP_Podetails[] data) {
		String res = userService.sendMessage(data);
		return res;
	}
	
	@ResponseBody
	@RequestMapping("/getTask")
	public Map<Integer, List<User_Task>> getTask(Integer  userId) {
		Map<Integer, List<User_Task>> data = userService.getTask(userId);
		System.out.println(data);
		return data;
	}
	@ResponseBody
	@RequestMapping("/getCountTask")
	public int getCountTask(Integer  userId) {
		int data = userService.getCountTask(userId);
		return data;
	}
	
	@ResponseBody
	@RequestMapping("/selectData")
	public List<PP_Podetails> selectData(
			String cDepCode,
			String productionCode,
			boolean detailsFinshed 
			) {
		Map<String, Object> param= new HashMap<String, Object>();
		param.put("cDepCode", cDepCode);
		param.put("productionCode", productionCode);
		param.put("detailsFinshed", detailsFinshed);
		List<PP_Podetails> data = pp_PodetailsMapper.list(param);
		return data;
	}
}
