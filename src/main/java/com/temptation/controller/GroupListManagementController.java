package com.temptation.controller;

import com.temptation.pojo.GroupManagement;
import com.temptation.pojo.ListManagement;
import com.temptation.pojo.User;
import com.temptation.service.GroupListManagementService;
import com.temptation.service.UserService;
import com.temptation.util.ConstantUtil;
import com.temptation.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * test
 * @author yanghui
 *
 */
@Controller
@RequestMapping(value = "/grouplist")
public class GroupListManagementController {
	@Autowired
	private GroupListManagementService groupListManagementService;

	@RequestMapping("/tolistmanagement")
	public ModelAndView tolistmanagement() {
		return new ModelAndView("/html/listmanagement.html");
	}

	@RequestMapping("/togroupmanagement")
	public ModelAndView togroupmanagement() {
		return new ModelAndView("/html/groupmanagement.html");
	}

	@RequestMapping("/queryGroupManagement")
	@ResponseBody
	public Object queryGroupManagement() {
		List<GroupManagement> groupManagements = groupListManagementService.queryAllGroupManagement();
		return TreeUtil.RecursiveAddress(groupManagements);
	}

	@RequestMapping("/queryGroupClass")
	@ResponseBody
	public Object queryGroupClass() {
		List<GroupManagement> groupManagements = groupListManagementService.queryAllGroupManagement();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("data", groupManagements);
		resultMap.put("code", "0");
		resultMap.put("msg", "ok");
		resultMap.put("count", "1");
		return resultMap;
	}

	@RequestMapping("/queryListClass")
	@ResponseBody
	public Object queryListClass() {
		List<ListManagement> listManagements = groupListManagementService.queryAllListManagement();
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("data", listManagements);
		resultMap.put("code", "0");
		resultMap.put("msg", "ok");
		resultMap.put("count", "1");
		return resultMap;
	}


	@RequestMapping("/queryListManagement")
	@ResponseBody
	public Object queryListManagement() {
		List<ListManagement> listManagements = groupListManagementService.queryAllListManagement();
		List<ListManagement> listManagements1 = TreeUtil.queryListManagement(listManagements);
		return listManagements1;
	}

	/**
	 * 增加分组
	 */
	@RequestMapping("/addGroupManagement")
	@ResponseBody
	public Object addGroupManagement(GroupManagement groupManagement) {
		groupManagement.setLevel(groupManagement.getLevel()+1);
		int i = groupListManagementService.addGroupManagement(groupManagement);
		return i;
	}

	/**
	 * 根据id查询分组
	 */
	@RequestMapping("/queryGroupManagementById")
	@ResponseBody
	public Object queryGroupManagementById(Integer id) {
		return	groupListManagementService.queryGroupManagementById(id);
	}

	/**
	 * 修改分组
	 */
	@RequestMapping("/updateGroupManagement")
	@ResponseBody
	public Object updateGroupManagement(GroupManagement groupManagement) {
		int i = groupListManagementService.updateGroupManagement(groupManagement);
		return i;
	}

	/**
	 * 根据id查询分组下面是否有子分组
	 */
	@RequestMapping("/queryGroupManagementByParentId")
	@ResponseBody
	public Object queryGroupManagementByParentId(Integer id) {
		List<GroupManagement> groupManagements = groupListManagementService.queryGroupManagementByParentId(id);
		if(groupManagements==null || groupManagements.size()==0){
			return 1;
		}
		return 0;
	}

	/**
	 * 删除分组
	 */
	@RequestMapping("/deleteGroupManagement")
	@ResponseBody
	public Object deleteGroupManagement(Integer id) {
		int i = groupListManagementService.deleteGroupManagement(id);
		return i;
	}


	/**
	 * 增加列表
	 */
	@RequestMapping("/addListManagement")
	@ResponseBody
	public Object addListManagement(ListManagement listManagement) {
		listManagement.setLevel(listManagement.getLevel()+1);
		int i = groupListManagementService.addListManagement(listManagement);
		return i;
	}

	/**
	 * 根据id查询列表
	 */
	@RequestMapping("/queryListManagementById")
	@ResponseBody
	public Object queryListManagementById(Integer id) {
		return	groupListManagementService.queryListManagementById(id);
	}

	/**
	 * 修改列表
	 */
	@RequestMapping("/updateListManagement")
	@ResponseBody
	public Object updateListManagement(ListManagement listManagement) {
		int i = groupListManagementService.updateListManagement(listManagement);
		return i;
	}

	/**
	 * 根据id查询列表下面是否有子列表
	 */
	@RequestMapping("/queryListManagementByParentId")
	@ResponseBody
	public Object queryListManagementByParentId(Integer id) {
		List<ListManagement> listManagements = groupListManagementService.queryListManagementByParentId(id);
		if(listManagements==null || listManagements.size()==0){
			return 1;
		}
		return 0;
	}

	/**
	 * 删除分组
	 */
	@RequestMapping("/deleteListManagement")
	@ResponseBody
	public Object deleteListManagement(Integer id) {
		int i = groupListManagementService.deleteListManagement(id);
		return i;
	}
	
	@RequestMapping("/getManagements")
	@ResponseBody
	public Object getManagements() {
		Map<String, Object> map = new HashMap<>();
		List<ListManagement> list = groupListManagementService.queryManagementsL4();
		map.put("data", list);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("/queryManagementByParentId")
	@ResponseBody
	public Object queryManagementByParentId(Long parentId) {
	Map<String, Object> map = new HashMap<>();
	List<ListManagement> list=	groupListManagementService.queryManagementByParentId(parentId);
	map.put("data", list);
	map.put("msg", "");
	map.put("count", list.size());
	map.put("code", 0);
	return map;
	}
	
	@RequestMapping("/getGroups")
	@ResponseBody
	public Object getGroups() {
		Map<String, Object> map = new HashMap<>();
		List<GroupManagement> list = groupListManagementService.queryGroupsL4();
		map.put("data", list);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("code", 0);
		return map;
	}
	
	@RequestMapping("/queryGroupByParentId")
	@ResponseBody
	public Object queryGroupByParentId(Long parentId) {
	Map<String, Object> map = new HashMap<>();
	List<GroupManagement> list=	groupListManagementService.queryGroupByParentId(parentId);
	map.put("data", list);
	map.put("msg", "");
	map.put("count", list.size());
	map.put("code", 0);
	return map;
	}

}
