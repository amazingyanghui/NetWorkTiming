package com.temptation.service.impl;

import com.temptation.dao.MonitoringLogMapper;
import com.temptation.pojo.MonitoringLog;
import com.temptation.service.MonitoringLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MonitoringLogServiceImpl implements MonitoringLogService{
	@Autowired
	private MonitoringLogMapper monitoringLogMapper;

	@Override
	public List<MonitoringLog> queryAllMonitoringLog(String startTime, String endTime,Integer page,Integer limit) {
			Integer pagenum=(page-1)*limit;
		return monitoringLogMapper.queryAllMonitoringLog(startTime, endTime,pagenum,limit);
	}

	@Override
	public Integer queryAllMonitoringLogCount(String startTime, String endTime) {
		return monitoringLogMapper.queryAllMonitoringLogCount(startTime, endTime);
	}

}
