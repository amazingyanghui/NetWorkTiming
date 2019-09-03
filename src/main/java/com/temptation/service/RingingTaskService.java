package com.temptation.service;

import java.util.List;

import com.temptation.pojo.Bellstate;
import org.apache.ibatis.annotations.Param;

import com.temptation.pojo.RingingTask;
import com.temptation.pojo.Terminal;

public interface RingingTaskService {
	/**
	 * 任务列表
	 */
	List<RingingTask> queryRingingTasks(Integer page,Integer limit);

	/**
	 * 查询任务总数
	 */
	Integer queryRingingTasksCount();

	/**
	 * 新增任务
	 */
	Boolean addRingingTask(RingingTask task);
	
	/**
	 * 根据查看终端列表
	 */
	Terminal queryTerByID(Integer terminalId);
	/**
	 * 删除任务
	 */
	Boolean delTaskById(Integer taskId);
	
	/**
	 * 开启任务
	 */
	Boolean openTask(Integer taskId);
	
	/**
	 * 关闭任务
	 */
	Boolean closeTask(Integer taskId);
	
	/**
	 * 调节音量
	 */
	Boolean updateVolume(@Param(value = "taskId") Integer taskId, @Param(value = "volume") Integer volume);

	//查询所有打铃状态
	List<Bellstate> queryAllBellstate(Integer page,Integer limit,Integer listManagementid);

	//查询所有打铃状态总数
	Integer queryAllBellstateCount(Integer listManagementid);
}
