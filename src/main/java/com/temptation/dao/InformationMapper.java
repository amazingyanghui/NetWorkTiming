package com.temptation.dao;



import java.sql.Date;
import java.util.List;


import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.temptation.pojo.Information;

@Repository
public interface InformationMapper {
	List<Information> queryInformations(@Param("limitStart") Long limitStart, @Param("pageSize") Long pageSize);
	void deleteInformation(Long id);
	Information queryInformationById(Long id);
	void updateInformationById(@Param("id") Long id, @Param("title") String title, @Param("content") String content);
	void addInformation(@Param("title") String title, @Param("content") String content, @Param("publisher") String publisher, @Param("releaseTime") Date releaseTime, @Param("releaseMethod") Long releaseMethod, @Param("terminal") String terminals);
	Long queryInformationsNums();
}
