package com.sun.bosen.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.Department;

public interface DepartmentMapper {

	List<Department> getDepartment(@Param(value="type")int type);

}
