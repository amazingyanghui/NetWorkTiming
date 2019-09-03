package com.temptation.service;

import com.temptation.pojo.User;

import java.util.List;

import com.temptation.pojo.UserAsAuthority;
import com.temptation.pojo.UserAsClass;
import org.apache.ibatis.annotations.Param;

public interface UserService {
	User queryByName(String name);
	
	/**
	 * 根据用户名查找密码
	 */
	User queryPwdByName(String userName);
	//查询所有用户信息
	List<User> queryAllUser(String name,Integer page,Integer limit);
	//查询所有用户总数
	Integer queryAllUserCount(String name);
	//修改密码
	boolean updatePwd(@Param(value="userName") String userName,@Param(value="passWord") String passWord);

	//新增用户
	Integer addUser(User user);

	//新增用户权限
	Integer addAuthority(Integer[] ids,Integer userId);

	//根据id查询用户
	User queryUserById(Integer id);

	//修改用户
	Integer updateUser(User user);

	//修改用户权限
	Integer updateAuthority(Integer[] ids,Integer userId);

	//根据id删除用户
	Integer deleteUserById(Integer id);

	//根据用户id查询用户的责任教室
	List<UserAsClass> queryClassByUserId(Integer userId);

	//根据用户id修改用户责任教室
	Integer updateClassByUserId(Integer userId,Integer[] listIds);
	
	String queryRoleByName(String name);
}
