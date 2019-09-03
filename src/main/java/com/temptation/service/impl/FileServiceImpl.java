package com.temptation.service.impl;

import java.util.List;

import com.temptation.pojo.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.temptation.dao.FileMapper;
import com.temptation.service.FileService;
@Service
public class FileServiceImpl implements FileService{
	@Autowired
	private FileMapper fileMapper;
	@Override
	public List<FileUpload> queryFiles(Integer filetype) {
		// TODO Auto-generated method stub
		return fileMapper.queryFiles(filetype);
	}

	@Override
	public boolean addFile(FileUpload file) {
		// TODO Auto-generated method stub
		return fileMapper.addFile(file)> 0 ? true : false;
	}

	@Override
	public Integer delFile(Integer fid) {
		// TODO Auto-generated method stub
		return fileMapper.delFile(fid);
	}
}
