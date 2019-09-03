package com.temptation.pojo;

import java.util.List;

/**
 * Created by Administrator on 2019/4/3.
 */
public class GroupManagement {
    private Integer id;
    private String name;
    private Integer parentId;
    private Integer sortnum;
    private Integer level;
    private List<GroupManagement> children;
    private Long isHaveTerminal;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public Integer getSortnum() {
		return sortnum;
	}
	public void setSortnum(Integer sortnum) {
		this.sortnum = sortnum;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public List<GroupManagement> getChildren() {
		return children;
	}
	public void setChildren(List<GroupManagement> children) {
		this.children = children;
	}
	public Long getIsHaveTerminal() {
		return isHaveTerminal;
	}
	public void setIsHaveTerminal(Long isHaveTerminal) {
		this.isHaveTerminal = isHaveTerminal;
	}
	@Override
	public String toString() {
		return "GroupManagement [id=" + id + ", name=" + name + ", parentId=" + parentId + ", sortnum=" + sortnum
				+ ", level=" + level + ", children=" + children + ", isHaveTerminal=" + isHaveTerminal + "]";
	}
    
    
}
