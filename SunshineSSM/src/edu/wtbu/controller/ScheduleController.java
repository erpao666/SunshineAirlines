package edu.wtbu.controller;

import java.util.HashMap;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;

@Controller
public class ScheduleController {
	@Resource
	private ScheduleService scheduleService;

	@RequestMapping(value = "/getScheduleDetail")
	@ResponseBody
	public Object getScheduleDetail(Integer scheduleId) {
		Result result = new Result("fail",null,null);
		if (scheduleId == null) {
			return result;
		}
		result = scheduleService.getScheduleDetail(scheduleId);
		return result;
	}

	@RequestMapping(value = "/getSearchFlight")
	@ResponseBody
	public Object getSearchFlight(String fromCity,String toCity,String departureDate,
			Integer cabinType,String flightType) {
		Result result = new Result("fail",null,null);
		String startDepartureDate = "";
		String endDepartureDate = "";
		if (departureDate == null) {
			return result;
		} else {
			startDepartureDate = departureDate + " 00:00:00";
			endDepartureDate = departureDate + " 23:59:59";
		}
		if(cabinType == null) {
			cabinType = 0;
		}
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("fromCity", fromCity);
		map.put("toCity", toCity);
		map.put("startDepartureDate", startDepartureDate);
		map.put("endDepartureDate", endDepartureDate);
		map.put("cabinType", cabinType);
		map.put("flightType", flightType);
		result = scheduleService.getSearchFlight(map);
		return result;
	}

	@RequestMapping(value = "/getFlightStatus")
	@ResponseBody
	public Object getFlightStatus(String departureDate, Integer startPage, Integer pageSize) {
		Result result = new Result("fail",null,null);
		if (startPage == null) {
			startPage = 0;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		String startDepartureDate = "";
		String endDepartureDate = "";
		if (departureDate == null) {
			return result;
		} else {
			startDepartureDate = departureDate + " 00:00:00";
			endDepartureDate = departureDate + " 23:59:59";
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startDepartureDate", startDepartureDate);
		map.put("endDepartureDate", endDepartureDate);
		map.put("startPage", startPage);
		map.put("pageSize", pageSize);
		result = scheduleService.getFlightStatus(map);
		return result;
	}
 
	@RequestMapping(value = "/getSchedule")
	@ResponseBody
	public Object getSchedule(String fromCity, String toCity, String startDate,String endDate) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("fromCity", fromCity);
		map.put("toCity", toCity);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		Result result = scheduleService.findScheduleByCityAndDate(map);
		return result;
	}

	@RequestMapping(value = "/updateSchedule")
	@ResponseBody
	public Object updateSchedule(Integer scheduleId, String status) {
		if (scheduleId == null) {
			scheduleId = 0;
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("scheduleId", scheduleId);
		map.put("status", status);
		Result result = scheduleService.updateSchedule(map);
		return result;
	}
}
