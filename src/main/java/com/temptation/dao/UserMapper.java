package com.temptation.dao;

import com.temptation.pojo.Authority;

import com.temptation.pojo.UserAsAuthority;
import com.temptation.pojo.UserAsClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.User;

import java.util.List;

@Repository
public interface UserMapper {
	User queryByName(String name);
	/**
	 * 根据用户名查找密码
	 */
	User queryPwdByName(String userName);
	//查询所有用户信息
	List<User> queryAllUser(@Param("name")String name,@Param("page")Integer page,@Param("limit")Integer limit);
	//查询所有用户总数
	Integer queryAllUserCount(String name);

	//根据用户id查询用户权限
	List<Authority> queryAuthorityByUserId(Integer userId);
	//修改密码
	Integer updatePwd(@Param(value="userName") String userName,@Param(value="passWord") String passWord);

	//新增用户
	Integer addUser(User user);
	//新增用户时新增用户的权限
	Integer addUserAsAuthority(@Param("userId") Integer userId,@Param("authorityId")Integer authorityId);

	//根据id查询用户
	User queryUserById(Integer id);

	//修改用户
	Integer updateUser(User user);

	//根据id删除用户
	Integer deleteUserById(Integer id);
	//删除用户时删除用户的权限
	Integer deleteUserAsAuthority(Integer userId);

	//根据用户id查询用户的责任教室
	List<UserAsClass> queryClassByUserId(Integer userId);

	//根据用户id删除责任教室
	Integer deleteClassByUserId(Integer userId);
	//新增用户责任教室
	Integer addUserAsClass(@Param("userId")Integer userId,@Param("listId") Integer listId);
	
	String queryRoleByName(String name);
}
