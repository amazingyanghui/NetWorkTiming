package com.temptation.pojo;

import java.util.Date;

public class MonitoringLog {
	private  Integer id;	//id
	private Integer equipmentId; //设备编号
	private String faultTime; //故障时间
	private String equipmentIp; //设备ip
	private String host;//本地端口
	private Integer maintenanceId;//实际位置
	private String abnormalReason;//异常原因
	private Maintenance maintenance;

	@Override
	public String toString() {
		return "MonitoringLog{" +
				"id=" + id +
				", equipmentId=" + equipmentId +
				", faultTime='" + faultTime + '\'' +
				", equipmentIp='" + equipmentIp + '\'' +
				", host='" + host + '\'' +
				", maintenanceId=" + maintenanceId +
				", abnormalReason='" + abnormalReason + '\'' +
				", maintenance=" + maintenance +
				'}';
	}

	public Maintenance getMaintenance() {
		return maintenance;
	}

	public void setMaintenance(Maintenance maintenance) {
		this.maintenance = maintenance;
	}

	public MonitoringLog() {
	}

	public MonitoringLog(Integer id, Integer equipmentId, String faultTime, String equipmentIp, String host, Integer maintenanceId, String abnormalReason, Maintenance maintenance) {
		this.id = id;
		this.equipmentId = equipmentId;
		this.faultTime = faultTime;
		this.equipmentIp = equipmentIp;
		this.host = host;
		this.maintenanceId = maintenanceId;
		this.abnormalReason = abnormalReason;
		this.maintenance = maintenance;
	}

	public MonitoringLog(Integer id, Integer equipmentId, String faultTime, String equipmentIp, String host, Integer maintenanceId, String abnormalReason) {
		this.id = id;
		this.equipmentId = equipmentId;
		this.faultTime = faultTime;
		this.equipmentIp = equipmentIp;
		this.host = host;
		this.maintenanceId = maintenanceId;
		this.abnormalReason = abnormalReason;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Integer equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getFaultTime() {
		return faultTime;
	}

	public void setFaultTime(String faultTime) {
		this.faultTime = faultTime;
	}

	public String getEquipmentIp() {
		return equipmentIp;
	}

	public void setEquipmentIp(String equipmentIp) {
		this.equipmentIp = equipmentIp;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getMaintenanceId() {
		return maintenanceId;
	}

	public void setMaintenanceId(Integer maintenanceId) {
		this.maintenanceId = maintenanceId;
	}

	public String getAbnormalReason() {
		return abnormalReason;
	}

	public void setAbnormalReason(String abnormalReason) {
		this.abnormalReason = abnormalReason;
	}

}
