package com.temptation.pojo;


/**
 * 打铃状态表
 * Created by Administrator on 2019/4/12.
 */
public class Bellstate {
    private Integer id;
    //private Integer maintenanceId;//教室id
    private ListManagement listManagement;//教室
    private Integer networkTatus;//网络状态
    private Integer bellSwitch;//打铃开关
    //private Integer ringingtaskId;//打铃任务id
    private RingingTask ringingTask;//打铃任务

    @Override
    public String toString() {
        return "Bellstate{" +
                "id=" + id +
                ", listManagement=" + listManagement +
                ", networkTatus=" + networkTatus +
                ", bellSwitch=" + bellSwitch +
                ", ringingTask=" + ringingTask +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getNetworkTatus() {
        return networkTatus;
    }

    public void setNetworkTatus(Integer networkTatus) {
        this.networkTatus = networkTatus;
    }

    public Integer getBellSwitch() {
        return bellSwitch;
    }

    public void setBellSwitch(Integer bellSwitch) {
        this.bellSwitch = bellSwitch;
    }

    public RingingTask getRingingTask() {
        return ringingTask;
    }

    public void setRingingTask(RingingTask ringingTask) {
        this.ringingTask = ringingTask;
    }

    public ListManagement getListManagement() {
        return listManagement;
    }

    public void setListManagement(ListManagement listManagement) {
        this.listManagement = listManagement;
    }

    public Bellstate(Integer id, ListManagement listManagement, Integer networkTatus, Integer bellSwitch, RingingTask ringingTask) {

        this.id = id;
        this.listManagement = listManagement;
        this.networkTatus = networkTatus;
        this.bellSwitch = bellSwitch;
        this.ringingTask = ringingTask;
    }

    public Bellstate() {

    }
}
