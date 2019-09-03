package com.temptation.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.temptation.pojo.Maintenance;

@Repository
public interface MaintenanceMapper {
	//区域列表
	List<Maintenance> queryMaintens();

	//新增区域
	int addMaintens(Maintenance maintenance);

	//根据id查询区域
	Maintenance queryMaintensById(Integer mid);

	//根据id修改区域
	int updateMaintensById(Maintenance maintenance);

	//根据id删除区域
	int deleteMaintensById(Integer mid);
}
