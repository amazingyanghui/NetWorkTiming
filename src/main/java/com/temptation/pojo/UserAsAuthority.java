package com.temptation.pojo;

public class UserAsAuthority {
	private Integer id;
	private Integer userId;
	private Integer authorityId;

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

	public Integer getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}

	@Override
	public String toString() {
		return "UserAsAuthority{" +
				"id=" + id +
				", userId=" + userId +
				", authorityId=" + authorityId +
				'}';
	}
}
