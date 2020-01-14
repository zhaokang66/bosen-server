package com.sun.bosen.controller;

import java.util.List;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sun.bosen.pojo.Department;
import com.sun.bosen.service.DepartmentService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
@Controller
@RequestMapping("")

public class DepartmentController {
	
	@Autowired
	DepartmentService departmentSerice;
	
	
	@ResponseBody
	@RequestMapping("/getDepartmentList")
	public List<Department> getDepartmentList() {
		List<Department> list = departmentSerice.getDepartment();
		return list;
		
	}
	
}
