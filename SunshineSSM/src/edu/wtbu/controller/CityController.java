package edu.wtbu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.CityService;

//ͨ��ע��ķ�ʽ������ɨ����������Ǹ�controller��
@Controller
public class CityController {

		// ͨ��ע��ķ���������service��CityService,�������Ͳ���newService�Ķ�����
		@Resource
		private CityService cityService;
		
		@RequestMapping("/getCityNames")//���ʽӿڵ����֣����û�л���д��ᱨ404����
		@ResponseBody //��Objectת��Ϊjson����jacksonjar��������
		public Object getCityNames() {
			Result result = cityService.getCityNames();
			return result;
		}	
}
