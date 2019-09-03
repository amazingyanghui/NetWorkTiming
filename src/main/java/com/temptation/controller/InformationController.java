package com.temptation.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.temptation.pojo.Information;
import com.temptation.service.InformationService;
import com.temptation.service.TerminalService;

import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/information")
public class InformationController {
	@Autowired
	private InformationService informationService;
	@Autowired
	private TerminalService terminalService;

	@RequestMapping("/toinfomationList")
	public ModelAndView toinfomationList() {
		return new ModelAndView("/html/informationList.html");
	}

	@RequestMapping("/getInformation")
	@ResponseBody
	public Object getInformation(Long page,Long limit) {
	Map<String, Object> informationMap=new HashMap<String,Object>();
	Long limitStart=(page-1)*limit;
	List<Information> informations = informationService.queryInformations(limitStart,limit);
	Long nums = informationService.queryInformationsNums();
	informationMap.put("data", informations);
	informationMap.put("code", 0);
	informationMap.put("msg", "");
	informationMap.put("count", nums);
	return informationMap;
	}
	
	@RequestMapping(value="/deleteInformationById",method=RequestMethod.POST)
	@ResponseBody
	public Object deleteInformation(Long[] ids) {
		for(int i=0;i<ids.length;i++) {
			informationService.deleteInformation(ids[i]);
		}
		return 1;
	}
	
	@RequestMapping(value="/queryInformationById",method=RequestMethod.POST)
	@ResponseBody
	public Object queryInfomationById(Long id) {
		Information information = informationService.queryInformationById(id);
		return information;
	}
	
	@RequestMapping(value="/updateInformationById",method=RequestMethod.POST)
	@ResponseBody
	public Object updateInformationById(Long id,String title,String content) {
		Map<String, Object> map =new HashMap<String, Object>();
		if(title==null||title.isEmpty()) {
			map.put("msg", "标题不能为空");
			return map;
		}
		if(content==null||content.isEmpty()) {
			map.put("msg", "内容不能为空");
			return map;
		}
		informationService.updateInformationById(id, title, content);
		return 1;
	}
	
	@RequestMapping(value="/addInformation",method=RequestMethod.POST)
	@ResponseBody
	public Object addInformation(String title, String content, String releaseTime, Long releaseMethod, Long[] checkId, HttpServletRequest request) throws ParseException{
		Map<String, Object> map = new HashMap<String,Object>();
		if(title==null||title.isEmpty()) {
			map.put("msg", "请填写标题");
			return map;
		}
		if(content==null||content.isEmpty()) {
			map.put("msg", "请填写内容");
			return map;
		}
		if(checkId.length<1) {
			map.put("msg", "请选择终端");
			return map;
		}
		String publisher=(String)request.getSession().getAttribute("userName");
		Date date = Date.valueOf(releaseTime);
		StringBuffer terminal = new StringBuffer();
		for(int i=0;i<checkId.length;i++) {
			if(i==checkId.length-1) {
				Long terminalId=terminalService.queryTerminalIdByMid(checkId[i]);
				terminal = terminal.append(terminalId);
			}else {
				Long terminalId=terminalService.queryTerminalIdByMid(checkId[i]);
				terminal = terminal.append(terminalId).append(",");
			}
			
		}
		String terminals = terminal.toString();
		informationService.addInformation(title,content,publisher,date,releaseMethod,terminals);
		return 1;
	}
}
