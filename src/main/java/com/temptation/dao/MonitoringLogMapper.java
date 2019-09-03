package com.temptation.dao;

import com.temptation.pojo.MonitoringLog;
import com.temptation.pojo.SysLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Repository
public interface MonitoringLogMapper {
	//查询所有监控日志
	List<MonitoringLog> queryAllMonitoringLog(@Param("startTime")String startTime,@Param("endTime")String endTime,
	@Param("page")Integer page,@Param("limit")Integer limit);
	//查询所有监控日志总数
	Integer queryAllMonitoringLogCount(@Param("startTime")String startTime,@Param("endTime")String endTime);
}
