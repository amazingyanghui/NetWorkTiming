package com.temptation.service.impl;

import java.util.List;

import com.temptation.pojo.Bellstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.RingingTaskMapper;
import com.temptation.pojo.RingingTask;
import com.temptation.pojo.Terminal;
import com.temptation.service.RingingTaskService;

@Service
public class RingingTaskServiceImpl implements RingingTaskService {
	@Autowired
	private RingingTaskMapper taskMapper;
	@Override
	public List<RingingTask> queryRingingTasks(Integer page,Integer limit) {
		// TODO Auto-generated method stub
		Integer pagenum=(page-1)*limit;
		return taskMapper.queryRingingTasks(pagenum,limit);
	}

	@Override
	public Integer queryRingingTasksCount() {
		return taskMapper.queryRingingTasksCount();
	}


	@Override
	public Terminal queryTerByID(Integer terminalId) {
		// TODO Auto-generated method stub
		return taskMapper.queryTerByID(terminalId);
	}

	@Override
	public Boolean addRingingTask(RingingTask task) {
		// TODO Auto-generated method stub
		return taskMapper.addRingingTask(task)> 0 ? true : false;
	}

	@Override
	public Boolean delTaskById(Integer taskId) {
		// TODO Auto-generated method stub
		return taskMapper.delTaskById(taskId)> 0 ? true : false;
	}

	@Override
	public Boolean openTask(Integer taskId) {
		// TODO Auto-generated method stub
		return taskMapper.openTask(taskId)>0 ? true : false;
	}

	@Override
	public Boolean closeTask(Integer taskId) {
		// TODO Auto-generated method stub
		return taskMapper.closeTask(taskId)>0 ? true : false;
	}


	@Override
	public Boolean updateVolume(Integer taskId, Integer volume) {
		// TODO Auto-generated method stub
		return taskMapper.updateVolume(taskId, volume)>0 ? true : false;
	}

	@Override
	public List<Bellstate> queryAllBellstate(Integer page,Integer limit,Integer listManagementid) {
		Integer pagenum=(page-1)*limit;
		return taskMapper.queryAllBellstate(pagenum, limit, listManagementid);
	}

	@Override
	public Integer queryAllBellstateCount(Integer listManagementid) {
		return taskMapper.queryAllBellstateCount(listManagementid);
	}

}
