package edu.wtbu.service;

import java.util.HashMap;

import edu.wtbu.pojo.Result;

public interface UsersService {
	// 接口之定义方法名和方法的返回值，不实现具体的方法体，也就是不要大括号
	public Result findEmailAndPassword(HashMap<String, Object> map);

	public boolean findEmail(String email);

	// 用户添加
	public Result addUser(HashMap<String, Object> map);

	// 用户查询
	public Result findUserListByPage(HashMap<String, Object> map);

	// 获取用户信息（根据用户id）接口
	public Result findByUserId(Integer userId);

	// 更新用户结果集
	public Result updateUser(HashMap<String, Object> paramMap);

}
