package edu.wtbu.service;

import java.util.HashMap;

import edu.wtbu.pojo.Result;

public interface UsersService {
	// �ӿ�֮���巽�����ͷ����ķ���ֵ����ʵ�־���ķ����壬Ҳ���ǲ�Ҫ������
	public Result findEmailAndPassword(HashMap<String, Object> map);

	public boolean findEmail(String email);

	// �û����
	public Result addUser(HashMap<String, Object> map);

	// �û���ѯ
	public Result findUserListByPage(HashMap<String, Object> map);

	// ��ȡ�û���Ϣ�������û�id���ӿ�
	public Result findByUserId(Integer userId);

	// �����û������
	public Result updateUser(HashMap<String, Object> paramMap);

}
