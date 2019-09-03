package com.temptation.pojo;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Information {
	private Long id;
	private String title;
	private String content;
	private String publisher;
	private Date releaseTime;
	private Long releaseMethod;
	private String terminal;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	public Date getReleaseTime() {
		return releaseTime;
	}
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	public Long getReleaseMethod() {
		return releaseMethod;
	}
	public void setReleaseMethod(Long releaseMethod) {
		this.releaseMethod = releaseMethod;
	}
	public String getTerminal() {
		return terminal;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	@Override
	public String toString() {
		return "Information [id=" + id + ", title=" + title + ", content=" + content + ", publisher=" + publisher
				+ ", releaseTime=" + releaseTime + ", releaseMethod=" + releaseMethod + ", terminal=" + terminal + "]";
	}
	
	
	

}
