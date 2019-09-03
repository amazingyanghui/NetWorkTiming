package com.temptation.service;

import java.util.List;

import com.temptation.pojo.Parameter;

public interface ParameterService {
	List<Parameter> queryParameters(Long limitStart, Long pageSize);
	Parameter queryParameterById(Long id);
	void addParameter(String keyName, String pattern, String parameterValue, String defaultValue, String describel);
	void updateParameter(Long id, String keyName, String pattern, String parameterValue, String defaultValue, String describel);
	Long queryParameterNums();
}
