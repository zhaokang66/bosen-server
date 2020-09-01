package com.sun.bosen.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sun.bosen.pojo.User;
import com.sun.bosen.pojo.User_Task;

public interface UserMapper {

	List<User> login(User data);

	void sendTask(Map<String, Object> param);

	List<User_Task> getTask(@Param(value="userId") int userId);

	int getDistributionId();

	int getCountTask(@Param(value="userId") Integer userId);

	List<String> getNew(@Param(value="distributionId") int distributionId);

	void updateStatus(Map<String, Object> param);

	void addUser(User data);

	void delete(User data);

	void updateUser(User data);
}
 