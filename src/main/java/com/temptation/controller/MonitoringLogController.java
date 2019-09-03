package com.temptation.controller;

import com.temptation.pojo.MonitoringLog;
import com.temptation.service.MonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Controller
@RequestMapping(value = "/monitoring")
public class MonitoringLogController {
	@Autowired
	private MonitoringLogService monitoringLogService;

	@RequestMapping("/toMonitoringLog")
	public ModelAndView toMonitoringLog() {
		return new ModelAndView("/html/MonitoringLog.html");
	}
	
	@RequestMapping("/queryAllMonitoringLog")
	@ResponseBody
	public Object queryAllMonitoringLog(String startTime,String endTime,Integer page,Integer limit) {
		System.out.println(startTime+"====="+endTime);

		List<MonitoringLog> monitoringLog = monitoringLogService.queryAllMonitoringLog(startTime,endTime,page,limit);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("data", monitoringLog);
		resultMap.put("code","0");
		resultMap.put("msg", "");
		resultMap.put("count", monitoringLogService.queryAllMonitoringLogCount(startTime, endTime));
		return resultMap;
	}

	
}
