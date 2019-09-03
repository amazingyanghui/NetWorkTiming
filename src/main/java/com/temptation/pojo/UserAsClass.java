package com.temptation.pojo;

/**
 * Created by Administrator on 2019/4/10.
 */
public class UserAsClass {
    private Integer id;
    private Integer userId;
    private Integer listId;
    private Integer groupId;

    @Override
    public String toString() {
        return "UserAsClass{" +
                "id=" + id +
                ", userId=" + userId +
                ", listId=" + listId +
                ", groupId=" + groupId +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getListId() {
        return listId;
    }

    public void setListId(Integer listId) {
        this.listId = listId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public UserAsClass(Integer id, Integer userId, Integer listId, Integer groupId) {

        this.id = id;
        this.userId = userId;
        this.listId = listId;
        this.groupId = groupId;
    }

    public UserAsClass() {

    }
}
