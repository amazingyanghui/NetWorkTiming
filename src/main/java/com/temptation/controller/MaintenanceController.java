package com.temptation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.temptation.pojo.Maintenance;
import com.temptation.service.MaintenanceService;
/**
 * 系统设置
 * @author yanghui
 *
 */
@Controller
@RequestMapping(value = "/maintenance")
public class MaintenanceController {
	@Autowired
	private MaintenanceService  maintenanceService;
	
	@RequestMapping("/getMaintenance")
	@ResponseBody
	public Object getMaintenance(HttpServletRequest request) {
		//获取区域维护列表
		List<Maintenance> maintens=maintenanceService.queryMaintens();
		Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("data", maintens);
			resultMap.put("code", "0");
			resultMap.put("msg", "");
			resultMap.put("count", "1");
			return resultMap;
	}

	/**
	 * 增加区域
	 */
	@RequestMapping("/addMaintens")
	@ResponseBody
	public Object addMaintens(Maintenance maintenance) {
		int i = maintenanceService.addMaintens(maintenance);
		return i;
	}

	/**
	 * 根据id查询区域
	 */
	@RequestMapping("/queryMaintensById")
	@ResponseBody
	public Object queryMaintensById(Integer mid) {
		return	maintenanceService.queryMaintensById(mid);
	}

	/**
	 * 修改区域
	 */
	@RequestMapping("/updateMaintensById")
	@ResponseBody
	public Object updateMaintensById(Maintenance maintenance) {
		int i = maintenanceService.updateMaintensById(maintenance);
		return i;
	}

	/**
	 * 删除区域
	 */
	@RequestMapping("/deleteMaintensById")
	@ResponseBody
	public Object updateMaintensById(String[] ids) {
		try {
			for(int i=0;i<ids.length;i++){
                maintenanceService.deleteMaintensById(Integer.valueOf(ids[i]));
            }
		} catch (Exception e) {
			return 0;
		}
		return 1;
	}
}
