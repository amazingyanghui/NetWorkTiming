package com.temptation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.ParameterMapper;
import com.temptation.pojo.Parameter;
import com.temptation.service.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService {
	@Autowired
	ParameterMapper parameterMapper;

	@Override
	public List<Parameter> queryParameters(Long limitStart, Long pageSize) {
		// TODO Auto-generated method stub
		return parameterMapper.queryParameters(limitStart, pageSize);
	}

	@Override
	public Parameter queryParameterById(Long id) {
		// TODO Auto-generated method stub
		return parameterMapper.queryParameterById(id);
	}

	

	@Override
	public Long queryParameterNums() {
		// TODO Auto-generated method stub
		return parameterMapper.queryParameterNums();
	}

	@Override
	public void addParameter(String keyName, String pattern, String parameterValue, String defaultValue, String describel) {
		// TODO Auto-generated method stub
		parameterMapper.addParameter(keyName, pattern, parameterValue, defaultValue, describel);
	}

	@Override
	public void updateParameter(Long id, String keyName, String pattern, String parameterValue, String defaultValue,String describel) {
		parameterMapper.updateParameter(id, keyName, pattern, parameterValue, defaultValue, describel);
		
	}

}
