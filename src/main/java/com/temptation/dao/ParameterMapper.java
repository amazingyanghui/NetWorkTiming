package com.temptation.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.Parameter;

@Repository
public interface ParameterMapper {
	List<Parameter> queryParameters(@Param("limitStart") Long limitStart, @Param("pageSize") Long pageSize);
	Parameter queryParameterById(Long id);
	void addParameter(@Param("keyName") String keyName, @Param("pattern") String pattern, @Param("parameterValue") String value, @Param("defaultValue") String defaultValue, @Param("describel") String describe);
	void updateParameter(@Param("id") Long id, @Param("keyName") String keyName, @Param("pattern") String pattern, @Param("parameterValue") String value, @Param("defaultValue") String defaultValue, @Param("describel") String describe);
	Long queryParameterNums();
}
