package edu.wtbu.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.wtbu.dao.UsersDao;
import edu.wtbu.pojo.Page;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	@Resource
	private UsersDao usersDao;

	@Override
	public Result findEmailAndPassword(HashMap<String, Object> map) {
		Result result = new Result("fail", null,null);

		List<HashMap<String, Object>> list = usersDao.findEmailAndPassword(map);
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
		} else {
			String email = map.get("email").toString();
			if (findEmail(email)) {
				result.setData("�������");
			} else {
				result.setData("���䲻����");
			}
		}

		return result;
	}

	@Override
	public boolean findEmail(String email) {
		boolean flag = false;
		List<HashMap<String, Object>> list = usersDao.findEmail(email);
		if (list != null && list.size() > 0) {
			flag = true;
		}
		return flag;
	}

	@Override
	public Result addUser(HashMap<String, Object> map) {
		Result result = new Result("fail", null,null);

		String email = map.get("email").toString();
		if (findEmail(email)) {
			result.setData("�����ظ�");
		} else {
			int count = usersDao.addUser(map);
			if (count >= 1) {// ��ӳɹ�
				result.setFlag("success");
			}
		}
		return result;
	}

	@Override
	public Result findUserListByPage(HashMap<String, Object> map) {
		Result result = new Result("success", null,null);
		List<HashMap<String, Object>> list = null;
	    int total = 0;
		
		int roleId = Integer.parseInt(map.get("roleId").toString());		
		int startPage = Integer.parseInt(map.get("startPage").toString());
		int pageSize = Integer.parseInt(map.get("pageSize").toString());
		if(roleId != 0) {
			total = usersDao.findUserListCountByPageAndRoleId(map);
			list = usersDao.findUserListByPageAndRoleId(map);
		}else{
			total = usersDao.findUserListCountByPage(map);
			list = usersDao.findUserListByPage(map);
		}
		Page page = new Page(total,startPage,pageSize);		
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
			result.setPage(page);
		} 		
		return result;
	}

	@Override
	public Result findByUserId(Integer userId) {
		Result result = new Result("fail",null,null);
		List<HashMap<String, Object>> list = usersDao.findByUserId(userId);
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
		}
		return result;
	}

	@Override
	public Result updateUser(HashMap<String, Object> map) {
		Result result = new Result("fail",null,null);
		List<HashMap<String, Object>> list = usersDao.findByEmailAndUserId(map);
		if (list != null && list.size() > 0) {			
			result.setData("�����ظ�");		
			return result;
		}
		int updateResult = usersDao.updateUser(map);
		if (updateResult > 0) {
			result.setFlag("success");
		}
		return result;
	}
}
