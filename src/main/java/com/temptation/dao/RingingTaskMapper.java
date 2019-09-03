package com.temptation.dao;

import java.util.List;

import com.temptation.pojo.Bellstate;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.RingingTask;
import com.temptation.pojo.Terminal;
@Repository
public interface RingingTaskMapper {
	/**
	 * 任务列表
	 */
	List<RingingTask> queryRingingTasks(@Param("page")Integer page,@Param("limit")Integer limit);

	/**
	 * 查询任务总数
	 */
	Integer queryRingingTasksCount();

	/**
	 * 新增任务
	 */
	Integer addRingingTask(RingingTask task);
	
	/**
	 * 根据查看终端列表
	 */
	Terminal queryTerByID(Integer terminalId);
	
	/**
	 * 删除任务
	 */
	Integer delTaskById(Integer taskId);
	
	/**
	 * 开启任务
	 */
	Integer openTask(Integer taskId);
	
	/**
	 * 关闭任务
	 */
	Integer closeTask(Integer taskId);
	
	/**
	 * 调节音量
	 */
	Integer updateVolume(@Param(value = "taskId") Integer taskId, @Param(value = "volume") Integer volume);

	//查询所有打铃状态
	List<Bellstate> queryAllBellstate(@Param("page")Integer page,@Param("limit")Integer limit,@Param("listManagementid") Integer listManagementid);

	//查询所有打铃状态总数
	Integer queryAllBellstateCount(@Param("listManagementid") Integer listManagementid);
}
