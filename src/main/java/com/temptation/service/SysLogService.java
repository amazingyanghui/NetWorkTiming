package com.temptation.service;

import java.util.List;
import java.util.Map;

import com.temptation.pojo.SysLog;

public interface SysLogService {
	//根据用户名查找日志
		List<SysLog> queryByName(String userName, Map<String, Object> map);
		//添加日志
		boolean addSysLog(SysLog sysLog);
}
