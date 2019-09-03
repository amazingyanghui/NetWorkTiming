package com.temptation.dao;

import com.temptation.pojo.GroupManagement;
import com.temptation.pojo.ListManagement;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupListManagementMapper {
	//查询所有分组
	List<GroupManagement> queryAllGroupManagement();

	//查询所有列表
	List<ListManagement> queryAllListManagement();

	//增加分组
	Integer addGroupManagement(GroupManagement groupManagement);

	//根据id查询分组
	GroupManagement queryGroupManagementById(Integer id);

	//根据id修改分组
	Integer updateGroupManagement(GroupManagement groupManagement);

	//根据id查询下面有没有子分组
	List<GroupManagement> queryGroupManagementByParentId(Integer parentId);

	//根据id删除分组
	Integer deleteGroupManagement(Integer id);

	//增加列表
	Integer addListManagement(ListManagement listManagement);

	//根据id查询列表
	ListManagement queryListManagementById(Integer id);

	//根据id修改列表
	Integer updateListManagement(ListManagement listManagement);

	//根据id查询下面有没有子列表
	List<ListManagement> queryListManagementByParentId(Integer parentId);

	//根据id删除列表
	Integer deleteListManagement(Integer id);
	
	List<ListManagement> queryManagementByParentId(Long parentId);
	
	List<GroupManagement> queryGroupByParentId(Long parentId);
	
	//获得所有等级为4的management
	List<ListManagement> queryManagementsL4();
	
	//获得所有等级为4的Group
	List<GroupManagement> queryGroupsL4();
}
