package com.sun.bosen.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sun.bosen.mapper.UserMapper;
import com.sun.bosen.pojo.PP_Podetails;
import com.sun.bosen.pojo.User;
import com.sun.bosen.pojo.User_Task;
import com.sun.bosen.service.UserService;
import com.sun.bosen.webSocket.ServerManager;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserMapper userMapper;

	@Override
	public List<User> login(User data) {
		return userMapper.login(data);
	}

	@Override
	public String sendMessage(PP_Podetails[] data) {
		int distributionId = userMapper.getDistributionId() + 1;
		for (int i = 0; i < data.length; i++) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("librarian", data[i].getLibrarian());
			param.put("task", JSON.toJSONString(data[i]));
			param.put("distributionId", distributionId);
			param.put("cdepname", data[i].getCdepname());
			param.put("productionCode", data[i].getcDefine22());
			userMapper.sendTask(param);
		}
		this.getNew(distributionId);
		
		return "分发成功";
	}

	@Override
	public Map<Integer, List<User_Task>> getTask(int userId) {
		List<User_Task> data = userMapper.getTask(userId);
		Map<Integer, List<User_Task>> collect = data.stream()
				.collect(Collectors.groupingBy(User_Task::getDistributionId));
		System.out.println(collect);
		return collect;
	}

	@Override
	public int getCountTask(Integer userId) {
		return userMapper.getCountTask(userId);
	}

	private void getNew(int distributionId) {
		List<String> data = userMapper.getNew(distributionId);
		Map<String, String> res = new HashMap<String, String>();
		res.put("type", "1");
		res.put("data", String.join(",", data));
		ServerManager.broadCast(JSON.toJSONString(res, SerializerFeature.WriteMapNullValue));
	}

	@Override
	public void addUser(User data) {
		if (data.getId() == 0) {
			userMapper.addUser(data);
		}else {
			userMapper.updateUser(data);
		}
	}

	@Override
	public void delete(User data) {
		userMapper.delete(data);
		
	}
}
