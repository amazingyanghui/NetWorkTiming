package com.temptation.service;

import com.temptation.pojo.MonitoringLog;
import com.temptation.pojo.SysLog;

import java.util.Date;
import java.util.List;

public interface MonitoringLogService {
	//查询所有监控日志
	List<MonitoringLog> queryAllMonitoringLog(String startTime, String endTime,Integer page,Integer limit);
	//查询所有监控日志总数
	Integer queryAllMonitoringLogCount(String startTime,String endTime);
}
