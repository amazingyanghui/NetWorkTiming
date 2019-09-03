package com.temptation.pojo;
/**
 * 终端
 * @author yanghui
 *
 */
public class Terminal {
	private Integer tid;
	private Integer equipment;
	private String timemode;
	private String Ipaddress;
	private String Macaddress;
	private Integer devicestauts;
	private Integer timingcycle;
	private String centreno;
	private String location;
	private Integer display;
	private Integer mid;
	private Integer gid;
	
	private ListManagement listManagement;
	private GroupManagement groupManagement;
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public Integer getEquipment() {
		return equipment;
	}
	public void setEquipment(Integer equipment) {
		this.equipment = equipment;
	}
	public String getTimemode() {
		return timemode;
	}
	public void setTimemode(String timemode) {
		this.timemode = timemode;
	}
	public String getIpaddress() {
		return Ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		Ipaddress = ipaddress;
	}
	public String getMacaddress() {
		return Macaddress;
	}
	public void setMacaddress(String macaddress) {
		Macaddress = macaddress;
	}
	public Integer getDevicestauts() {
		return devicestauts;
	}
	public void setDevicestauts(Integer devicestauts) {
		this.devicestauts = devicestauts;
	}
	public Integer getTimingcycle() {
		return timingcycle;
	}
	public void setTimingcycle(Integer timingcycle) {
		this.timingcycle = timingcycle;
	}
	public String getCentreno() {
		return centreno;
	}
	public void setCentreno(String centreno) {
		this.centreno = centreno;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getDisplay() {
		return display;
	}
	public void setDisplay(Integer display) {
		this.display = display;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	
	public ListManagement getListManagement() {
		return listManagement;
	}
	public void setListManagement(ListManagement listManagement) {
		this.listManagement = listManagement;
	}
	
	public GroupManagement getGroupManagement() {
		return groupManagement;
	}
	public void setGroupManagement(GroupManagement groupManagement) {
		this.groupManagement = groupManagement;
	}
	public Terminal(Integer tid, Integer equipment, String timemode, String ipaddress, String macaddress,
			Integer devicestauts, Integer timingcycle, String centreno, String location, Integer display, Integer mid,
			Integer gid) {
		this.tid = tid;
		this.equipment = equipment;
		this.timemode = timemode;
		Ipaddress = ipaddress;
		Macaddress = macaddress;
		this.devicestauts = devicestauts;
		this.timingcycle = timingcycle;
		this.centreno = centreno;
		this.location = location;
		this.display = display;
		this.mid = mid;
		this.gid = gid;
	}
	public Terminal() {
		
	}
	@Override
	public String toString() {
		return "Terminal [tid=" + tid + ", equipment=" + equipment + ", timemode=" + timemode + ", Ipaddress="
				+ Ipaddress + ", Macaddress=" + Macaddress + ", devicestauts=" + devicestauts + ", timingcycle="
				+ timingcycle + ", centreno=" + centreno + ", location=" + location + ", display=" + display + ", mid="
				+ mid + ", gid=" + gid + "]";
	}
	
	
	
}
