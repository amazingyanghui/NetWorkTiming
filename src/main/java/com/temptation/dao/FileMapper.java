package com.temptation.dao;

import java.util.List;

import com.temptation.pojo.FileUpload;
import org.springframework.stereotype.Repository;

/**
 * 文件上传
 * @author yanghui
 *
 */
@Repository
public interface FileMapper {
	/**
	 * 根据文件类型获取列表
	 * @param filetype
	 * @return
	 */
	List<FileUpload> queryFiles(Integer filetype);

	/**
	 - 文件上传
	 */
	Integer addFile(FileUpload file);
	
	/**
	 * 根据id删除文件
	 */
	Integer delFile(Integer fid);

}
