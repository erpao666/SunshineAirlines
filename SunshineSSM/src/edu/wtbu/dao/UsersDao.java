package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

public interface UsersDao {
	//�ӿ�֮���巽�����ͷ����ķ���ֵ����ʵ�־���ķ����壬Ҳ���ǲ�Ҫ������
	
	//��¼
	public List<HashMap<String,Object>> findEmailAndPassword(HashMap<String,Object> map);
	
	public List<HashMap<String,Object>> findEmail(String email);
	
	//�û����
	public int addUser(HashMap<String,Object> map);
	
	//�û���ѯ
	public List<HashMap<String,Object>> findUserListByPage(HashMap<String,Object> map);
	
	public int findUserListCountByPage(HashMap<String,Object> map);
	
	//�û���ѯ(����roleId)
	public List<HashMap<String,Object>> findUserListByPageAndRoleId(HashMap<String,Object> map);
	
	public int findUserListCountByPageAndRoleId(HashMap<String,Object> map);	
	
	//�û�����
	public int updateUser(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> findByEmailAndUserId(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> findByUserId(Integer userId);		
}
