package com.temptation.service;

import com.temptation.pojo.FileUpload;

import java.util.List;

public interface FileService {
	/**
	 * 根据文件类型获取列表
	 * @param filetype
	 * @return
	 */
	List<FileUpload> queryFiles(Integer filetype);

	/**
	 * 文件上传
	 */
	boolean addFile(FileUpload file);
	
	
	/**
	 * 根据id删除文件
	 */
	Integer delFile(Integer fid);
}
