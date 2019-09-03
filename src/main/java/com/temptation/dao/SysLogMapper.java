package com.temptation.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.SysLog;
/**
 * SysLogMapper
 * @author yanghui
 *
 */
@Repository
public interface SysLogMapper {
	//根据用户名查找日志
	List<SysLog> queryByName(@Param(value = "userName") String userName, @Param(value = "map") Map<String, Object> map);
	//添加日志
	Integer addSysLog(SysLog sysLog);
}
