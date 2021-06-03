package edu.wtbu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.CityService;

//通过注解的方式，告诉扫描器这个类是个controller类
@Controller
public class CityController {

		// 通过注解的方法，引入service的CityService,这样做就不用newService的对象了
		@Resource
		private CityService cityService;
		
		@RequestMapping("/getCityNames")//访问接口的名字，如果没有或者写错会报404错误
		@ResponseBody //将Object转换为json，是jacksonjar包起作用
		public Object getCityNames() {
			Result result = cityService.getCityNames();
			return result;
		}	
}
