package com.temptation.controller;

import javax.servlet.http.HttpServletRequest;

import com.temptation.util.ConstantUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temptation.pojo.User;
import com.temptation.service.UserService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test
 * @author yanghui
 *
 */
@Controller
@RequestMapping(value = "/login")
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/tologin")
	public ModelAndView toLogin() {
		return new ModelAndView("/html/login.html");
	}

	@RequestMapping("/toindex")
	public ModelAndView toindex() {
		return new ModelAndView("/html/index.html");
	}

	@RequestMapping("/toaccountmanagement")
	public ModelAndView toaccountmanagement() {
		return new ModelAndView("/html/accountmanagement.html");
	}

	@RequestMapping("/user")
	@ResponseBody
	public Object getUser(String selectname,Integer page,Integer limit) {
		List<User> users =userService.queryAllUser(selectname,page,limit);
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("data", users);
		resultMap.put("code", "0");
		resultMap.put("msg", "");
		resultMap.put("count", userService.queryAllUserCount(selectname));
		return resultMap;
	}


	/**
	 * 用户登录接口
	 * @param request
	 * @return
	 */
	@RequestMapping("admin")
	@ResponseBody
	public Object login(User user,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<>();
		User sysUser = userService.queryPwdByName(user.getUsername());
		if (null == sysUser) {
			map.put("msg", "用户不存在！");
			map.put("code", ConstantUtil.NUM_1);
		} else {
			if (user.getPassword().equals(sysUser.getPassword())) {
				//request.getSession().setAttribute("user", sysUser);
				request.getSession().setAttribute("userName", user.getUsername());
				request.getSession().setAttribute("passWord", user.getPassword());
				map.put("msg", "登录成功！");
				map.put("code", ConstantUtil.NUM_0);
			} else {
				map.put("msg", "密码错误！");
				map.put("code", ConstantUtil.NUM_2);
			}
		}
		return map;
	}



	/**
	 * 新增用户接口
	 * @return
	 */
	@RequestMapping("addUser")
	@ResponseBody
	public Object addUser(User user) {
		return userService.addUser(user);
	}

    /**
     * 新增用户权限接口
     * @return
     */
    @RequestMapping("addAuthority")
    @ResponseBody
    public Object addAuthority(Integer[] ids,Integer userId) {
        return userService.addAuthority(ids,userId);
    }

	/**
	 * 根据id查询用户
	 * @return
	 */
	@RequestMapping("queryUserById")
	@ResponseBody
	public Object queryUserById(Integer id) {
		return userService.queryUserById(id);
	}

	/**
	 * 修改用户
	 * @return
	 */
	@RequestMapping("updateUser")
	@ResponseBody
	public Object updateUser(User user) {
		return userService.updateUser(user);
	}

    /**
     * 修改用户权限
     * @return
     */
    @RequestMapping("updateAuthority")
    @ResponseBody
    public Object updateAuthority(Integer[] ids,Integer userId) {
        return userService.updateAuthority(ids,userId);
    }

	/**
	 * 删除用户
	 * @return
	 */
	@RequestMapping("deleteUserById")
	@ResponseBody
	public Object deleteUserById(String[] ids) {
		try {
			for(int i=0;i<ids.length;i++){
				userService.deleteUserById(Integer.valueOf(ids[i]));
			}
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}

	/**
	 * 根据用户id查询用户责任教室
	 * @return
	 */
	@RequestMapping("queryClassByUserId")
	@ResponseBody
	public Object queryClassByUserId(Integer id) {
		return userService.queryClassByUserId(id);
	}

	/**
	 * 修改用户责任教室
	 * @return
	 */
	@RequestMapping("updateClassByUserId")
	@ResponseBody
	public Object updateClassByUserId(Integer userId, Integer[] listIds) {
		return userService.updateClassByUserId(userId,listIds);
	}

}
