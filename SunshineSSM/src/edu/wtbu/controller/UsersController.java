package edu.wtbu.controller;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.UsersService;

@Controller
public class UsersController {

	// 1.通过注解引入service方法
	@Resource
	private UsersService usersService;

	@RequestMapping("/login")
	@ResponseBody
	public Object findEmailAndPassword(String email, String password) {
		// 2.调用service的方法做登录
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("email", email);
		map.put("password", password);
		Result result = usersService.findEmailAndPassword(map);
		return result;
	}

	@RequestMapping("/addUser")
	@ResponseBody
	public Object addUser(int roleId, String email, String firstName, String lastName, String gender,
			String dateOfBirth, String phone, String photo, String address) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("email", email);
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("gender", gender);
		map.put("dateOfBirth", dateOfBirth);
		map.put("phone", phone);
		map.put("photo", photo);
		map.put("address", address);
		String password = email.split("@")[0];
		password = password.length() > 6 ? password.substring(0, 6) : password;
		map.put("password", password);

		Result result = usersService.addUser(map);
		return result;
	}

	@RequestMapping("/userList")
	@ResponseBody
	public Object userList(int roleId, String name, int startPage, int pageSize) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("roleId", roleId);
		map.put("name", name);
		map.put("startPage", startPage * pageSize);
		map.put("pageSize", pageSize);

		Result result = usersService.findUserListByPage(map);
		return result;
	}

	// 用户更新接口
	@RequestMapping(value = "/updateUser")
	@ResponseBody
	public Object updateUser(Integer userId, String email, String password, String firstName, String lastName,
			String dateOfBirth, String address, String phone, String photo, String gender, Integer roleId) {
		Result result = new Result("fail", null, null);
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("email", email);		
		map.put("firstName", firstName);
		map.put("lastName", lastName);
		map.put("dateOfBirth", dateOfBirth);
		map.put("address", address);
		map.put("phone", phone);
		map.put("photo", photo);
		map.put("gender", gender);
		map.put("roleId", roleId);
		if (roleId == null) {
			roleId = 1;
		}
		map.put("userId", userId);
		if (userId == null) {
			return result;
		}				
		String passwd = email.split("@")[0];
		passwd = passwd.length() > 6 ? passwd.substring(0, 6) : passwd;
		map.put("password", passwd);
		result = usersService.updateUser(map);
		return result;
	}

	// 获取用户信息（根据用户id）接口
	@RequestMapping(value = "/getUserInfo")
	@ResponseBody
	public Object getUserInfo(Integer userId) {
		Result result = new Result("fail", null, null);
		if (userId == null) {
			return result;
		}
		result = usersService.findByUserId(userId);
		return result;
	}
}
