package com.temptation.service;



import java.sql.Date;
import java.util.List;


import com.temptation.pojo.Information;

public interface InformationService {
	List<Information> queryInformations(Long limitStart, Long pageSize);
	void deleteInformation(Long id);
	Information queryInformationById(Long id);
	void updateInformationById(Long id, String title, String content);
	void addInformation(String title, String content, String publisher, Date releaseTime, Long releaseMethod, String terminals);
	Long queryInformationsNums();
}
