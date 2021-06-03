package edu.wtbu.serviceImpl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import edu.wtbu.dao.CityDao;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.CityService;

//ͨ��ע��ķ�ʽ������ɨ����������Ǹ�service��
@Service
public class CityServiceImpl implements CityService {

	// ͨ��ע��ķ���������dao��CityDao,�������Ͳ���newDao�Ķ�����
	@Resource
	private CityDao cityDao;

	@Override
	public Result getCityNames() {
		Result result = new Result("fail",null, null);

		List<HashMap<String, Object>> list = cityDao.getCityNames();
		if (list != null && list.size() > 0) {
			result.setFlag("success");
			result.setData(list);
		}

		return result;
	}

}
