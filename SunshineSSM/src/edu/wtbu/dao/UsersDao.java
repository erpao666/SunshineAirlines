package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

public interface UsersDao {
	//接口之定义方法名和方法的返回值，不实现具体的方法体，也就是不要大括号
	
	//登录
	public List<HashMap<String,Object>> findEmailAndPassword(HashMap<String,Object> map);
	
	public List<HashMap<String,Object>> findEmail(String email);
	
	//用户添加
	public int addUser(HashMap<String,Object> map);
	
	//用户查询
	public List<HashMap<String,Object>> findUserListByPage(HashMap<String,Object> map);
	
	public int findUserListCountByPage(HashMap<String,Object> map);
	
	//用户查询(包含roleId)
	public List<HashMap<String,Object>> findUserListByPageAndRoleId(HashMap<String,Object> map);
	
	public int findUserListCountByPageAndRoleId(HashMap<String,Object> map);	
	
	//用户更新
	public int updateUser(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> findByEmailAndUserId(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> findByUserId(Integer userId);		
}
