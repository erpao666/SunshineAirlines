package edu.wtbu.serviceImpl;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import edu.wtbu.dao.ScheduleDao;
import edu.wtbu.pojo.Page;
import edu.wtbu.pojo.Result;
import edu.wtbu.service.ScheduleService;

@Service("scheduleService")
public class ScheduleServiceImpl implements ScheduleService {

	@Resource
	private ScheduleDao scheduleDao;	

	public List<HashMap<String, Object>> findScheduleByScheduleId(Integer scheduleId) {
		if (scheduleId == null) {
			scheduleId = 0;
		}
		return scheduleDao.findScheduleByScheduleId(scheduleId);
	}

	public List<HashMap<String, Object>> findTicketsByScheduleId(Integer scheduleId) {
		if (scheduleId == null) {
			scheduleId = 0;
		}
		return scheduleDao.findTicketsByScheduleId(scheduleId);
	}

	public List<HashMap<String, Object>> findSeatByScheduleId(Integer scheduleId) {
		if (scheduleId == null) {
			scheduleId = 0;
		}
		return scheduleDao.findSeatByScheduleId(scheduleId);
	}

	public List<HashMap<String, Object>> findSeatByAircraftId(Integer aircraftId) {
		if (aircraftId == null) {
			aircraftId = 0;
		}
		return scheduleDao.findSeatByAircraftId(aircraftId);
	}

	public List<HashMap<String, Object>> findResidueTickesByScheduleIdAndCabinType(HashMap<String, Object> map) {		
		return scheduleDao.findResidueTickesByScheduleIdAndCabinType(map);
	}

	public List<HashMap<String, Object>> findFlightNumber(HashMap<String, Object> map) {		
		return scheduleDao.findFlightNumber(map);
	}

	public List<HashMap<String, Object>> findScheduleByDate(HashMap<String, Object> map) {		
		return scheduleDao.findScheduleByDate(map);
	}

	public int scheduleByDateCount(HashMap<String, Object> map) {		
		return scheduleDao.scheduleByDateCount(map);
	}

	public Result findScheduleByCityAndDate(HashMap<String, Object> map) {	
		Result result = new Result("fail",null,null);
		List<HashMap<String, Object>> ListSchedule = scheduleDao.findScheduleByCityAndDate(map);
		if (ListSchedule != null && ListSchedule.size() > 0) {
			result.setFlag("success");
			result.setData(ListSchedule);
		}
		return result;
	}


	public List<HashMap<String, Object>> getNonstop(HashMap<String, Object> paramMap) {		
		List<HashMap<String, Object>> listSchedule = scheduleDao.findSchedulesByCityAndDate(paramMap);
		for (int i = 0; listSchedule != null && i < listSchedule.size(); i++) {
			HashMap<String, Object> resultMap = listSchedule.get(i);
			try {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("scheduleId", Integer.parseInt(resultMap.get("ScheduleId").toString()));
				map.put("cabinType", Integer.parseInt(paramMap.get("cabinType").toString()));
				List<HashMap<String, Object>> listResidueTickes = this.findResidueTickesByScheduleIdAndCabinType(map);

				int counts = Integer.parseInt(listResidueTickes.get(0).get("Counts").toString());
				if (Integer.parseInt(paramMap.get("cabinType").toString()) == 3) {
					int allTickes = Integer.parseInt(resultMap.get("FirstSeatsAmount").toString());
					resultMap.put("ResidueTickets", allTickes - counts);
				}
				if (Integer.parseInt(paramMap.get("cabinType").toString()) == 2) {
					int allTickes = Integer.parseInt(resultMap.get("BusinessSeatsAmount").toString());
					resultMap.put("ResidueTickets", allTickes - counts);
				}
				if (Integer.parseInt(paramMap.get("cabinType").toString()) == 1) {
					int allTickes = Integer.parseInt(resultMap.get("EconomySeatsAmount").toString());
					resultMap.put("ResidueTickets", allTickes - counts);
				}
				resultMap.put("FlightType", "Non-stop");
			} catch (Exception e) {
				resultMap.put("ResidueTickets", 0);
			}
		}
		return listSchedule;
	}

	public List<HashMap<String, Object>> getOnestop(HashMap<String, Object> paramMap) {		
		List<HashMap<String, Object>> listSchedule = scheduleDao.findOnestopSchedulesByCityAndDate(paramMap);
		List<HashMap<String, Object>> listFlightNumber = this.findFlightNumber(paramMap);
		for (int i = 0; listSchedule != null && i < listSchedule.size(); i++) {
			HashMap<String, Object> map = listSchedule.get(i);
			try {
				int s1scheduleId = Integer.parseInt(map.get("S1ScheduleId").toString());
				int s2scheduleId = Integer.parseInt(map.get("S2ScheduleId").toString());
				String s1flightnumber = map.get("S1FlightNumber").toString();
				String s2flightnumber = map.get("S2FlightNumber").toString();
				for (int j = 0; listFlightNumber != null && j < listFlightNumber.size(); j++) {
					String flightnumber = listFlightNumber.get(j).get("FlightNumber").toString();
					if (flightnumber.equals(s1flightnumber)) {
						map.put("S1AllCount", listFlightNumber.get(j).get("AllCount").toString());
						map.put("S1DelayCount", listFlightNumber.get(j).get("DelayCount").toString());
						map.put("S1NotDelay", listFlightNumber.get(j).get("NotDelay").toString());
					}
					if (flightnumber.equals(s2flightnumber)) {
						map.put("S2AllCount", listFlightNumber.get(j).get("AllCount").toString());
						map.put("S2DelayCount", listFlightNumber.get(j).get("DelayCount").toString());
						map.put("S2NotDelay", listFlightNumber.get(j).get("NotDelay").toString());
					}
				}
				HashMap<String, Object> paramMap2 = new HashMap<String, Object>();
				paramMap2.put("scheduleId", s1scheduleId);
				int cabinType = Integer.parseInt(paramMap.get("cabinType").toString());
				paramMap2.put("cabinType", cabinType);
				List<HashMap<String, Object>> listResidueTickes = this
						.findResidueTickesByScheduleIdAndCabinType(paramMap2);
				int counts = Integer.parseInt(listResidueTickes.get(0).get("Counts").toString());
				if (cabinType == 3) {
					int allTickes = Integer.parseInt(map.get("S1FirstSeatsAmount").toString());
					map.put("S1ResidueTickets", allTickes - counts);
				}
				if (cabinType == 2) {
					int allTickes = Integer.parseInt(map.get("S1BusinessSeatsAmount").toString());
					map.put("S1ResidueTickets", allTickes - counts);
				}
				if (cabinType == 1) {
					int allTickes = Integer.parseInt(map.get("S1EconomySeatsAmount").toString());
					map.put("S1ResidueTickets", allTickes - counts);
				}

				HashMap<String, Object> paramMap3 = new HashMap<String, Object>();
				paramMap3.put("scheduleId", s2scheduleId);
				paramMap3.put("cabinType", cabinType);
				listResidueTickes = this.findResidueTickesByScheduleIdAndCabinType(paramMap3);
				counts = Integer.parseInt(listResidueTickes.get(0).get("Counts").toString());
				if (cabinType == 3) {
					int allTickes = Integer.parseInt(map.get("S2FirstSeatsAmount").toString());
					map.put("S2ResidueTickets", allTickes - counts);
				}
				if (cabinType == 2) {
					int allTickes = Integer.parseInt(map.get("S2BusinessSeatsAmount").toString());
					map.put("S2ResidueTickets", allTickes - counts);
				}
				if (cabinType == 1) {
					int allTickes = Integer.parseInt(map.get("S2EconomySeatsAmount").toString());
					map.put("S2ResidueTickets", allTickes - counts);
				}
				map.put("FlightType", "1-stop");
			} catch (Exception e) {

			}
		}
		return listSchedule;
	}
 
	public Result getFlightStatus(HashMap<String, Object> paramMap) {
		Result result = new Result("fail",null,null);
		List<HashMap<String, Object>> listSchedule = this.findScheduleByDate(paramMap);
		int total = this.scheduleByDateCount(paramMap);
		if (listSchedule != null && listSchedule.size() > 0) {
			result.setFlag("success");
			result.setData(listSchedule);
			Page page = new Page(total, Integer.valueOf(paramMap.get("startPage").toString()),
					Integer.valueOf(paramMap.get("pageSize").toString()));
			result.setPage(page);
		}
		return result;
	}
 
	public Result updateSchedule(HashMap<String, Object> paramMap) {
		Result result = new Result("fail",null,null);
		int updateResult = scheduleDao.updateSchedule(paramMap);
		if (updateResult > 0) {
			result.setFlag("success");
			result.setData(updateResult);
		}
		return result;
	}
 
	public Result getScheduleDetail(Integer scheduleId) {
		Result result = new Result("fail", null, null);
		List<HashMap<String, Object>> listSchedule = this.findScheduleByScheduleId(scheduleId);
		if (listSchedule == null || listSchedule.size() == 0) {
			return result;
		}
		try {
			Integer aircraftId = Integer.parseInt(listSchedule.get(0).get("AircraftId").toString());
			List<HashMap<String, Object>> listTickets = this.findTicketsByScheduleId(scheduleId);
			List<HashMap<String, Object>> listSeat = this.findSeatByScheduleId(scheduleId);
			List<HashMap<String, Object>> listSeatLayout = this.findSeatByAircraftId(aircraftId);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("ListSchedule", listSchedule);
			map.put("ListTickets", listTickets);
			map.put("ListSeat", listSeat);
			map.put("ListSeatLayout", listSeatLayout);
			result.setData(map);
			result.setFlag("success");
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
 
	public Result getSearchFlight(HashMap<String, Object> paramMap) {
		Result result = new Result("fail",null,null);
		if (paramMap.get("flightType") != null && paramMap.get("flightType").toString().equals("Non-stop")) {
			List<HashMap<String, Object>> listSchedule = this.getNonstop(paramMap);
			result.setData(listSchedule);
			result.setFlag("success");
			return result;
		}
		if (paramMap.get("flightType") != null && paramMap.get("flightType").toString().equals("1-stop")) {
			List<HashMap<String, Object>> listOneStopSchedule = this.getOnestop(paramMap);
			result.setData(listOneStopSchedule);
			result.setFlag("success");
			return result;
		}
		if (paramMap.get("flightType") != null && paramMap.get("flightType").toString().equals("All")) {
			List<HashMap<String, Object>> listNoStopSchedule = this.getNonstop(paramMap);
			List<HashMap<String, Object>> listOneStopSchedule = this.getOnestop(paramMap);
			listNoStopSchedule.addAll(listOneStopSchedule);
			result.setData(listNoStopSchedule);
			result.setFlag("success");
			return result;
		}
		return result;
	}
}
