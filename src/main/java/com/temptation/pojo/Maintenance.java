package com.temptation.pojo;

public class Maintenance {
	private Integer mid;
	private String regname;
	private Integer regarea;
	private Integer seatnum;
	private String region;
	private Integer sortnum;
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getRegname() {
		return regname;
	}
	public void setRegname(String regname) {
		this.regname = regname;
	}
	public Integer getRegarea() {
		return regarea;
	}
	public void setRegarea(Integer regarea) {
		this.regarea = regarea;
	}
	public Integer getSeatnum() {
		return seatnum;
	}
	public void setSeatnum(Integer seatnum) {
		this.seatnum = seatnum;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getSortnum() {
		return sortnum;
	}
	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
	}
	public Maintenance(Integer mid, String regname, Integer regarea, Integer seatnum, String region, Integer sortnum) {
		this.mid = mid;
		this.regname = regname;
		this.regarea = regarea;
		this.seatnum = seatnum;
		this.region = region;
		this.sortnum = sortnum;
	}
	public Maintenance() {
		
	}
	@Override
	public String toString() {
		return "Maintenance [mid=" + mid + ", regname=" + regname + ", regarea=" + regarea + ", seatnum=" + seatnum
				+ ", region=" + region + ", sortnum=" + sortnum + "]";
	}
	
	
}
