package com.temptation.pojo;

public class FileUpload {
	private Integer fid;
	private String filename;
	private String fileurl;
	private String loadtime;
	private String username;
	private Integer filetype;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getLoadtime() {
		return loadtime;
	}
	public void setLoadtime(String loadtime) {
		this.loadtime = loadtime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getFiletype() {
		return filetype;
	}
	public void setFiletype(Integer filetype) {
		this.filetype = filetype;
	}
	public FileUpload(Integer fid, String filename, String fileurl, String loadtime, String username, Integer filetype) {
		this.fid = fid;
		this.filename = filename;
		this.fileurl = fileurl;
		this.loadtime = loadtime;
		this.username = username;
		this.filetype = filetype;
	}
	public FileUpload() {
		
	}
	@Override
	public String toString() {
		return "File [fid=" + fid + ", filename=" + filename + ", fileurl=" + fileurl + ", loadtime=" + loadtime
				+ ", username=" + username + ", filetype=" + filetype + "]";
	}
	
	
}
