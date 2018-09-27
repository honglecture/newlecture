package com.newlecture.web.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class MemberRoleId implements Serializable { // 줄 세우기 위한 작업 where ~ and ~

	private String memberId;
	private String roleName;

	public MemberRoleId() {
	}

	public MemberRoleId(String memberId, String roleName) {
		this.memberId = memberId;
		this.roleName = roleName;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}