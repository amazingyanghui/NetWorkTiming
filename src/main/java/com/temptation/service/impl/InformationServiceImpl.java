package com.temptation.service.impl;



import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.InformationMapper;
import com.temptation.pojo.Information;
import com.temptation.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationMapper InformationMapper;
	@Override
	public List<Information> queryInformations(Long limitStart,Long pageSize) {
		// TODO Auto-generated method stub
		return InformationMapper.queryInformations(limitStart,pageSize);
	}
	
	@Override
	public void deleteInformation(Long id) {
		// TODO Auto-generated method stub
		InformationMapper.deleteInformation(id);
		
	}

	@Override
	public Information queryInformationById(Long id) {
		// TODO Auto-generated method stub
		return InformationMapper.queryInformationById(id);
	}

	@Override
	public void updateInformationById(Long id, String title, String content) {
		// TODO Auto-generated method stub
		InformationMapper.updateInformationById(id, title, content);
	}

	@Override
	public void addInformation(String title,String content,String publisher,Date releaseTime,Long releaseMethod,String terminals) {
		// TODO Auto-generated method stub
		InformationMapper.addInformation(title,content,publisher,releaseTime,releaseMethod,terminals);
	}

	@Override
	public Long queryInformationsNums() {
		// TODO Auto-generated method stub
		return InformationMapper.queryInformationsNums();
	}

	

}
