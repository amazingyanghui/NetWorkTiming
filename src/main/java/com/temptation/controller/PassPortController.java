package com.temptation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import com.temptation.service.SysLogService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temptation.pojo.SysLog;
import com.temptation.pojo.User;
import com.temptation.service.UserService;
import com.temptation.util.ConstantUtil;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录
 * @author yanghui
 *
 */
@Controller
@RequestMapping(value="/sign")
public class PassPortController {
	
	@Autowired
	private UserService userService;
	@Autowired
	private SysLogService syslogService;

	@RequestMapping("/tolog")
	public ModelAndView tolog() {
		return new ModelAndView("/html/log.html");
	}
	/**
	 * 用户登录接口
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public  Object login(HttpServletRequest request)
	{	
		ConcurrentHashMap<String, Object> map=new ConcurrentHashMap<String, Object>();
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		//判断用户名是否为空
		if(StringUtils.isNotEmpty(userName)) {
			userName = userName.trim();
		}
		//根据用户名查询用户想信息
		User sysUser=userService.queryPwdByName(userName);
		//判断用户是否为空
		if(null==sysUser){
			//为空则用户不存在			
			//model.addAttribute("msg", "用户不存在！");
			//model.addAttribute("code",ConstantUtil.NUM_1);
			map.put("msg", "用户不存在！");
			map.put("code",ConstantUtil.NUM_1);
		}else {
			//判断密码是否正确			
			if(passWord.equals(sysUser.getPassword())) {
				request.getSession().setAttribute("userName", userName);
				request.getSession().setAttribute("passWord", passWord);
				request.getSession().setAttribute("relName", sysUser.getName());
				//model.addAttribute("msg", "登录成功！");
				//model.addAttribute("code", ConstantUtil.NUM_0);
				map.put("msg", "登录成功！");
				map.put("code",ConstantUtil.NUM_0);
			}else{
				//model.addAttribute("msg", "密码错误！");
				//model.addAttribute("code", ConstantUtil.NUM_2);
				map.put("msg", "密码错误！");
				map.put("code",ConstantUtil.NUM_2);
			}
		}
		return map;
	}
	/**
	 * 日志列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/syslog")
	@ResponseBody
	public Object syslog(HttpServletRequest request) {
		Map<String, Object> logsMap=new HashMap<String, Object>();
		Map<String, Object> pageMap=new HashMap<String, Object>();
//当前页
		Integer page=Integer.parseInt(request.getParameter("page"));
//显示页数
		Integer limit=Integer.parseInt(request.getParameter("limit"));
		String userName=(String)request.getSession().getAttribute("userName");
		List<SysLog> tallogs = syslogService.queryByName(userName,pageMap);
		Integer pageNo=(page-1)*limit;
		pageMap.put("pageNo", pageNo);
		pageMap.put("limit", limit);
//从session获取username
//根据用户名查找日志列表
		List<SysLog> logs = syslogService.queryByName(userName,pageMap);
		logsMap.put("data", logs);
		logsMap.put("msg", "");
		logsMap.put("code", 0);
		logsMap.put("count",tallogs.size() );
		return logsMap;

	}

	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public Object logout(HttpServletRequest request) {
		ConcurrentHashMap<String, Object> map=new ConcurrentHashMap<String, Object>();
		//清楚session信息
		request.getSession().removeAttribute("userName");
		request.getSession().removeAttribute("passWord");
		request.getSession().removeAttribute("relName");
		map.put("msg", "退出成功！");
		map.put("code",ConstantUtil.NUM_0);
		return map;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/changePwd")
	@ResponseBody
	public Object changePwd(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		//从session中获取用户名
		String userName=(String)request.getSession().getAttribute("userName");
		//获取密码
		String passWord=request.getParameter("passWord");
		String newPwd=request.getParameter("newPwd");
		//根据用户名查询用户想信息
		User sysUser=userService.queryPwdByName(userName);
		//判断密码是否相同，相同进入修改密码
		if(sysUser.getPassword().equals(passWord)) {
		boolean flag=userService.updatePwd(userName, newPwd);
			if(flag) {
				map.put("msg", "修改成功!");
				map.put("code",ConstantUtil.NUM_0);
			}else {
				map.put("msg", "修改失败！");
				map.put("code",ConstantUtil.NUM_2);
			}
		}else {
			map.put("msg", "密码不正确！");
			map.put("code",ConstantUtil.NUM_1);
		}
		return map;
	}
	
	/**
	 * 获取当前用户角色名和身份
	 * @param request
	 * @return
	 */
	@RequestMapping("/getIdentity")
	@ResponseBody
	public Object getIdentity(HttpServletRequest request) {
		Map<String, Object>map=new HashMap<>();
		String name=(String) request.getSession().getAttribute("relName");
		String role=userService.queryRoleByName(name);
		String nameAndRole=name+" "+role;
		map.put("identity", nameAndRole);
		System.out.println(nameAndRole);
		return map;
	}
}
