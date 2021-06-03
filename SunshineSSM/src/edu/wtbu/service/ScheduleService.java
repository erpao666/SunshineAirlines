package edu.wtbu.service;

import java.util.HashMap;
import java.util.List;

import edu.wtbu.pojo.Result;

public interface ScheduleService {
	public List<HashMap<String, Object>> findScheduleByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findTicketsByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findSeatByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findSeatByAircraftId(Integer aircraftId);

	public List<HashMap<String, Object>> findResidueTickesByScheduleIdAndCabinType(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findFlightNumber(HashMap<String, Object> map);

	public Result findScheduleByCityAndDate(HashMap<String, Object> map);

	public List<HashMap<String, Object>> getNonstop(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> getOnestop(HashMap<String, Object> map);
	
	public Result getFlightStatus(HashMap<String,Object> paramMap);
	
	public Result updateSchedule(HashMap<String,Object> paramMap);
	
	public Result getScheduleDetail(Integer scheduleId);
	
	public Result getSearchFlight(HashMap<String,Object> paramMap);
}
