package com.temptation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.temptation.pojo.Parameter;
import com.temptation.service.ParameterService;

@Controller
@RequestMapping("/parameter")
public class ParameterController {
	@Autowired
	ParameterService parameterService;
	
	@RequestMapping("/toparameter")
	public ModelAndView toparameter() {
		return new ModelAndView("/html/parameter.html");
	}
	
	@RequestMapping("/getParameters")
	@ResponseBody
	public Object getParameters(Long page,Long limit) {
		Map<String, Object> parametersMap=new HashMap<String, Object>();
		long limitStart=(page-1)*limit;
		long nums=parameterService.queryParameterNums();
		List<Parameter> parameters=parameterService.queryParameters(limitStart, limit);
		parametersMap.put("data", parameters);
		parametersMap.put("msg", "");
		parametersMap.put("count", nums);
		parametersMap.put("code", 0);
		return parametersMap;
	}
	
	@RequestMapping(value="/addParameter",method=RequestMethod.POST)
	@ResponseBody
	public Object addParameter(String keyName,String pattern,String parameterValue,String defaultValue,String describel) {
		
		Map<String, Object> map=new HashMap<>();
		if(keyName==null||keyName.isEmpty()) {
			map.put("msg", "键名不能为空");
			return map;
		}
		if(defaultValue==null||defaultValue.isEmpty()) {
			map.put("msg", "默认取值不能为空");
			return map;
		}
		if(describel==null||describel.isEmpty()) {
			map.put("msg", "描述不能为空");
			return map;
		}
		parameterService.addParameter(keyName,pattern,parameterValue,defaultValue,describel);
		return 1;
	}
	
	@RequestMapping(value="/queryParameterById",method=RequestMethod.POST)
	@ResponseBody
	public Object queryParameterById(Long id) {
		Parameter parameter=parameterService.queryParameterById(id);
		return parameter;
	}
	
	@RequestMapping(value="/updateParameterById",method=RequestMethod.POST)
	@ResponseBody
	public Object updateParameterById(Long id,String keyName,String pattern,String parameterValue,String defaultValue,String describel) {
		
		Map<String, Object> map=new HashMap<>();
		if(keyName==null||keyName.isEmpty()) {
			map.put("msg", "键名不能为空");
			return map;
		}
		if(defaultValue==null||defaultValue.isEmpty()) {
			map.put("msg", "默认取值不能为空");
			return map;
		}
		if(describel==null||describel.isEmpty()) {
			map.put("msg", "描述不能为空");
			return map;
		}
		parameterService.updateParameter(id, keyName, pattern, parameterValue, defaultValue, describel);
		return 1;
	}
}
