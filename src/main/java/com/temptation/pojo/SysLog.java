package com.temptation.pojo;

public class SysLog {
	private  Integer Sid;	//id
	private String accname;//账户名
	private String content;//操作内容
	private String contime;//操作时间
	public Integer getSid() {
		return Sid;
	}
	public void setSid(Integer sid) {
		Sid = sid;
	}
	public String getAccname() {
		return accname;
	}
	public void setAccname(String accname) {
		this.accname = accname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContime() {
		return contime;
	}
	public void setContime(String contime) {
		this.contime = contime;
	}
	public SysLog(Integer sid, String accname, String content, String contime) {
		Sid = sid;
		this.accname = accname;
		this.content = content;
		this.contime = contime;
	}
	public SysLog() {
		
	}
	@Override
	public String toString() {
		return "SysLog [Sid=" + Sid + ", accname=" + accname + ", content=" + content + ", contime=" + contime + "]";
	}
	
}
