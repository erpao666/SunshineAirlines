package edu.wtbu.dao;

import java.util.HashMap;
import java.util.List;

public interface ScheduleDao {
	public List<HashMap<String, Object>> findScheduleByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findTicketsByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findSeatByScheduleId(Integer scheduleId);

	public List<HashMap<String, Object>> findSeatByAircraftId(Integer aircraftId);

	public List<HashMap<String, Object>> findSchedulesByCityAndDate(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findResidueTickesByScheduleIdAndCabinType(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findOnestopSchedulesByCityAndDate(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findFlightNumber(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findScheduleByDate(HashMap<String, Object> map);

	public int scheduleByDateCount(HashMap<String, Object> map);

	public List<HashMap<String, Object>> findScheduleByCityAndDate(HashMap<String, Object> map);

	public int updateSchedule(HashMap<String, Object> map);
	
    public List<HashMap<String, Object>> getNonstop(HashMap<String, Object> map);
	
	public List<HashMap<String, Object>> getOnestop(HashMap<String, Object> map);
}
