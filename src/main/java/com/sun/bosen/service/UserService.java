package com.sun.bosen.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.User;
import com.sun.bosen.pojo.User_Task;

public interface UserService {

	List<User> login(User data);

	String sendMessage(PP_Podetails[] data) ;

	Map<Integer, List<User_Task>> getTask(int userId);

	int getCountTask(Integer userId);

	void addUser(User data);

	void delete(User data);

}
