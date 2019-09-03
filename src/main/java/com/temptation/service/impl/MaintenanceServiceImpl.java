package com.temptation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.MaintenanceMapper;
import com.temptation.pojo.Maintenance;
import com.temptation.service.MaintenanceService;
@Service
public class MaintenanceServiceImpl implements MaintenanceService {
	@Autowired
	private MaintenanceMapper maintenanceMapper; 
	@Override
	public List<Maintenance> queryMaintens() {
		// TODO Auto-generated method stub
		return maintenanceMapper.queryMaintens();
	}

	@Override
	public int addMaintens(Maintenance maintenance) {
		return maintenanceMapper.addMaintens(maintenance);
	}

	@Override
	public Maintenance queryMaintensById(Integer mid) {
		return maintenanceMapper.queryMaintensById(mid);
	}

	@Override
	public int updateMaintensById(Maintenance maintenance) {
		return maintenanceMapper.updateMaintensById(maintenance);
	}

	@Override
	public int deleteMaintensById(Integer mid) {
		return maintenanceMapper.deleteMaintensById(mid);
	}

}
