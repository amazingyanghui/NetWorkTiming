package com.temptation.pojo;

public class RingingTask {
	private Integer tid;
	private String taskname;
	private String creator;
	private String playedfile;
	private String starttime;
	private String endtime;
	private Integer taskstatus;
	private Integer tasktype;
	private String priority;
	private String terminals;
	private Integer volume;
	private Integer ringmode;

	public Integer getRingmode() {
		return ringmode;
	}

	public void setRingmode(Integer ringmode) {
		this.ringmode = ringmode;
	}

	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getTaskname() {
		return taskname;
	}
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getPlayedfile() {
		return playedfile;
	}
	public void setPlayedfile(String playedfile) {
		this.playedfile = playedfile;
	}
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public Integer getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(Integer taskstatus) {
		this.taskstatus = taskstatus;
	}
	
	public Integer getTasktype() {
		return tasktype;
	}
	public void setTasktype(Integer tasktype) {
		this.tasktype = tasktype;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getTerminals() {
		return terminals;
	}
	public void setTerminals(String terminals) {
		this.terminals = terminals;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	public RingingTask() {
	
	}

	@Override
	public String toString() {
		return "RingingTask{" +
				"tid=" + tid +
				", taskname='" + taskname + '\'' +
				", creator='" + creator + '\'' +
				", playedfile='" + playedfile + '\'' +
				", starttime='" + starttime + '\'' +
				", endtime='" + endtime + '\'' +
				", taskstatus=" + taskstatus +
				", tasktype=" + tasktype +
				", priority='" + priority + '\'' +
				", terminals='" + terminals + '\'' +
				", volume=" + volume +
				", ringmode=" + ringmode +
				'}';
	}

	public RingingTask(Integer tid, String taskname, String creator, String playedfile, String starttime, String endtime, Integer taskstatus, Integer tasktype, String priority, String terminals, Integer volume, Integer ringmode) {
		this.tid = tid;
		this.taskname = taskname;
		this.creator = creator;
		this.playedfile = playedfile;
		this.starttime = starttime;
		this.endtime = endtime;
		this.taskstatus = taskstatus;
		this.tasktype = tasktype;
		this.priority = priority;
		this.terminals = terminals;
		this.volume = volume;
		this.ringmode = ringmode;
	}
}
