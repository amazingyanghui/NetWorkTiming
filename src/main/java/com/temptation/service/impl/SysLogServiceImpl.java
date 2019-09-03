package com.temptation.service.impl;

import java.util.List;
import java.util.Map;

import com.temptation.dao.SysLogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.pojo.SysLog;
import com.temptation.service.SysLogService;
@Service
public class SysLogServiceImpl implements SysLogService{
	@Autowired
	private SysLogMapper sysLogMapper;
	@Override
	public List<SysLog> queryByName(String userName,Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sysLogMapper.queryByName(userName,map);
	}
	@Override
	public boolean addSysLog(SysLog sysLog) {
		// TODO Auto-generated method stub
		return sysLogMapper.addSysLog(sysLog)> 0 ? true : false;
	}

}
