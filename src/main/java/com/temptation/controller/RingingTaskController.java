package com.temptation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.temptation.pojo.Bellstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temptation.pojo.RingingTask;
import com.temptation.pojo.Terminal;
import com.temptation.service.RingingTaskService;
import com.temptation.util.ConstantUtil;
import org.springframework.web.servlet.ModelAndView;

/**
 * 任务controller
 * @return
 */
@Controller
@RequestMapping(value="/ringingTask")
public class RingingTaskController {
	@Autowired
	private RingingTaskService taskService;

	@RequestMapping("/totask")
	public ModelAndView totask() {
		return new ModelAndView("/html/task.html");
	}

	@RequestMapping("/totimestate")
	public ModelAndView totimestate() {
		return new ModelAndView("/html/timestate.html");
	}
	/**
	 * 任务列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/queryTask")
	@ResponseBody
	public Object queryTask(HttpServletRequest request,Integer page,Integer limit) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<RingingTask> tasks=taskService.queryRingingTasks(page, limit);
		map.put("data", tasks);
		map.put("count", taskService.queryRingingTasksCount());
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

	/**
	 * 新建任务
	 * @param request
	 * @param task
	 * @return
	 */
	@RequestMapping("addTask")
	@ResponseBody
	public Object addTask(HttpServletRequest request,RingingTask task) {
		//String terminals=task.getTerminals();
		//String[] ary = terminals.split("[,]");
		Map<String, Object> map=new HashMap<String, Object>();
		//String userName=(String) request.getSession().getAttribute("userName");
		String userName="admin";
		task.setCreator(userName);
		boolean flag=taskService.addRingingTask(task);
		//判断是否新建成功
		if(flag) {
			map.put("code", ConstantUtil.NUM_0);
			map.put("msg", "新建任务成功！");
		}else {
			map.put("code", ConstantUtil.NUM_1);
			map.put("msg", "新建任务失败！");
		}
		return map;
	}
	
	/**
	 * 查看终端列表
	 * @param request
	 * @return
	 */
	@RequestMapping("lookTerList")
	@ResponseBody
	public Object lookTerList(HttpServletRequest request) {
		Map<String, Object> terMap=new HashMap<String, Object>();
		//根据传递的终端字符串截取终端id进行查询
		String terminals=request.getParameter("terminals");
		String[] ary = terminals.split("[,]");
		List<Terminal> terLists=new ArrayList<Terminal>();
		for (int i = 0; i < ary.length; i++) {
			Terminal terminal=taskService.queryTerByID(Integer.parseInt(ary[i]));
			terLists.add(terminal);
		}
		terMap.put("data", terLists);
		terMap.put("count", terLists.size());
		terMap.put("code", 0);
		terMap.put("msg", "");
		return terMap;
	}
	
	/**
	 * 删除任务
	 */
	@RequestMapping("delTask")
	@ResponseBody
	public Object delTask(HttpServletRequest request,Integer[] tid) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		//Integer taskId=Integer.parseInt(request.getParameter("tid"));
		if(tid!=null && tid.length!=0){
			for(int i=0;i<tid.length;i++){
				Boolean flag=taskService.delTaskById(tid[i]);
				if(flag) {
					statusMap.put("code", ConstantUtil.NUM_0);
					statusMap.put("msg", "删除任务成功！");
				}else {
					statusMap.put("code", ConstantUtil.NUM_1);
					statusMap.put("msg", "删除任务失败！");
				}
			}
		}
		return statusMap;
	}
	
	/**
	 * 开启任务
	 */
	@RequestMapping("openTask")
	@ResponseBody
	public Object openTask(HttpServletRequest request,Integer[] tid) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		//Integer taskId=Integer.parseInt(request.getParameter("tid"));
		if(tid!=null && tid.length!=0){
			for(int i=0;i<tid.length;i++){
				Boolean flag=taskService.openTask(tid[i]);
				if(flag) {
					statusMap.put("code", ConstantUtil.NUM_0);
					statusMap.put("msg", "开启任务成功！");
				}else {
					statusMap.put("code", ConstantUtil.NUM_1);
					statusMap.put("msg", "开启任务失败！");
				}
			}
		}
		return statusMap;
	}
	
	
	/**
	 * 关闭任务
	 */
	@RequestMapping("closeTask")
	@ResponseBody
	public Object closeTask(HttpServletRequest request,Integer[] tid) {
		Map<String, Object> statusMap=new HashMap<String, Object>();
		//Integer taskId=Integer.parseInt(request.getParameter("tid"));
		if(tid!=null && tid.length!=0){
			for(int i=0;i<tid.length;i++){
				Boolean flag=taskService.closeTask(tid[i]);
				if(flag) {
					statusMap.put("code", ConstantUtil.NUM_0);
					statusMap.put("msg", "关闭任务成功！");
				}else {
					statusMap.put("code", ConstantUtil.NUM_1);
					statusMap.put("msg", "关闭任务失败！");
				}
			}
		}
		return statusMap;
	}
	
	/**
	 * 调节音量
	 */
	@RequestMapping("/updataVolume")
	@ResponseBody
	public Object updataVolume(HttpServletRequest request) {
		Integer taskId=Integer.parseInt(request.getParameter("tid"));
		Integer volume=Integer.parseInt(request.getParameter("volume"));
		Boolean flag=taskService.updateVolume(taskId, volume);
		Map<String, Object> voMap=new HashMap<String, Object>();
		if(flag) {
			voMap.put("code", ConstantUtil.NUM_0);
			voMap.put("msg", "调节音量成功！");
		}else {
			voMap.put("code", ConstantUtil.NUM_1);
			voMap.put("msg", "调节音量失败！");
		}
		return voMap;
	}

	/**
	 * 打铃状态
	 * @return
	 */
	@RequestMapping("/queryAllBellstate")
	@ResponseBody
	public Object queryAllBellstate(Integer page,Integer limit,Integer listManagementid) {
		Map<String, Object> map=new HashMap<String, Object>();
		List<Bellstate> bellstates = taskService.queryAllBellstate(page,limit,listManagementid);
		map.put("data", bellstates);
		map.put("count", taskService.queryAllBellstateCount(listManagementid));
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}

}
