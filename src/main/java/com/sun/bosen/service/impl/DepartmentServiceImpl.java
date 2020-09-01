package com.sun.bosen.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.bosen.mapper.DepartmentMapper;
import com.sun.bosen.pojo.Department;
import com.sun.bosen.service.DepartmentService;

@Service
public class DepartmentServiceImpl  implements DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;
	
	@Override
	public List<Department> getDepartment(int type) {
		
		return departmentMapper.getDepartment(type);
	}

}
