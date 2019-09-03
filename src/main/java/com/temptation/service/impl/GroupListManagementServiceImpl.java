package com.temptation.service.impl;

import com.temptation.dao.GroupListManagementMapper;

import com.temptation.pojo.GroupManagement;

import com.temptation.pojo.ListManagement;
import com.temptation.service.GroupListManagementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupListManagementServiceImpl implements GroupListManagementService{
	@Autowired
	private GroupListManagementMapper groupListManagementMapper;

	@Override
	public List<GroupManagement> queryAllGroupManagement() {
		return groupListManagementMapper.queryAllGroupManagement();
	}

	@Override
	public List<ListManagement> queryAllListManagement() {
		return groupListManagementMapper.queryAllListManagement();
	}

	@Override
	public Integer addGroupManagement(GroupManagement groupManagement) {
		return groupListManagementMapper.addGroupManagement(groupManagement);
	}

	@Override
	public GroupManagement queryGroupManagementById(Integer id) {
		return groupListManagementMapper.queryGroupManagementById(id);
	}

	@Override
	public Integer updateGroupManagement(GroupManagement groupManagement) {
		return groupListManagementMapper.updateGroupManagement(groupManagement);
	}

	@Override
	public List<GroupManagement> queryGroupManagementByParentId(Integer parentId) {
		return groupListManagementMapper.queryGroupManagementByParentId(parentId);
	}

	@Override
	public Integer deleteGroupManagement(Integer id) {
		return groupListManagementMapper.deleteGroupManagement(id);
	}

	@Override
	public Integer addListManagement(ListManagement listManagement) {
		return groupListManagementMapper.addListManagement(listManagement);
	}

	@Override
	public ListManagement queryListManagementById(Integer id) {
		return groupListManagementMapper.queryListManagementById(id);
	}

	@Override
	public Integer updateListManagement(ListManagement listManagement) {
		return groupListManagementMapper.updateListManagement(listManagement);
	}

	@Override
	public List<ListManagement> queryListManagementByParentId(Integer parentId) {
		return groupListManagementMapper.queryListManagementByParentId(parentId);
	}

	@Override
	public Integer deleteListManagement(Integer id) {
		return groupListManagementMapper.deleteListManagement(id);
	}

	@Override
	public List<ListManagement> queryManagementByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return groupListManagementMapper.queryManagementByParentId(parentId);
	}

	@Override
	public List<GroupManagement> queryGroupByParentId(Long parentId) {
		// TODO Auto-generated method stub
		return groupListManagementMapper.queryGroupByParentId(parentId);
	}

	@Override
	public List<ListManagement> queryManagementsL4() {
		// TODO Auto-generated method stub
		return groupListManagementMapper.queryManagementsL4();
	}

	@Override
	public List<GroupManagement> queryGroupsL4() {
		// TODO Auto-generated method stub
		return groupListManagementMapper.queryGroupsL4();
	}

	

}
