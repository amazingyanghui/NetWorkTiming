package com.temptation.service.impl;

import com.temptation.pojo.Authority;
import com.temptation.pojo.UserAsClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.UserMapper;
import com.temptation.pojo.User;
import com.temptation.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userdao;


	@Override
	public List<User> queryAllUser(String name,Integer page,Integer limit) {
		Integer pagenum=(page-1)*limit;
		List<User> users = userdao.queryAllUser(name,pagenum,limit);
		for(User user:users){
			List<Authority> authorities = userdao.queryAuthorityByUserId(user.getId());
			user.setAuthorityList(authorities);
		}
		return users;
	}

	@Override
	public Integer queryAllUserCount(String name) {
		return userdao.queryAllUserCount(name);
	}

	@Override
	public User queryPwdByName(String userName) {
		// TODO Auto-generated method stub
		return userdao.queryPwdByName(userName);
	}
	@Override
	public User queryByName(String name) {
		// TODO Auto-generated method stub
		return userdao.queryByName(name);
	}
	@Override
	public boolean updatePwd(String userName, String passWord) {
		// TODO Auto-generated method stub
		return userdao.updatePwd(userName, passWord)> 0 ? true : false;
	}

	@Override
	public Integer addUser(User user) {
		userdao.addUser(user);
		return user.getId();
	}

	@Override
	public Integer addAuthority(Integer[] ids,Integer userId) {
		if(ids!=null && ids.length!=0){
			for(int m=0;m<ids.length;m++){
				userdao.addUserAsAuthority(userId, ids[m]);
			}
		}
		return 1;
	}

	@Override
	public User queryUserById(Integer id) {
		User user = userdao.queryUserById(id);
		List<Authority> authorities = userdao.queryAuthorityByUserId(id);
		user.setAuthorityList(authorities);
		return user;
	}

	@Override
	public Integer updateUser(User user) {
		userdao.updateUser(user);
		return 1;
	}

	@Override
	public Integer updateAuthority(Integer[] ids, Integer userId) {
		userdao.deleteUserAsAuthority(userId);
		if(ids!=null && ids.length!=0){
			for(int m=0;m<ids.length;m++){
				userdao.addUserAsAuthority(userId,ids[m]);
			}
		}
		return 1;
	}

	@Override
	public Integer deleteUserById(Integer id) {
		userdao.deleteUserAsAuthority(id);
		return userdao.deleteUserById(id);
	}

	@Override
	public List<UserAsClass> queryClassByUserId(Integer userId) {
		return userdao.queryClassByUserId(userId);
	}

	@Override
	public Integer updateClassByUserId(Integer userId, Integer[] listIds) {
		userdao.deleteClassByUserId(userId);
		if(listIds!=null && listIds.length!=0){
			for(int m=0;m<listIds.length;m++){
				userdao.addUserAsClass(userId,listIds[m]);
			}
		}
		return 1;
	}

	@Override
	public String queryRoleByName(String name) {
		// TODO Auto-generated method stub
		return userdao.queryRoleByName(name);
	}


}
