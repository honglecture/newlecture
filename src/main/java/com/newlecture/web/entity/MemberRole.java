package com.newlecture.web.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemberRole {

	@Id
	private String memberId;
	
	@Id
	private String roleName;
	private boolean defaultRole;

	public MemberRole() {
	}

	public MemberRole(String memberId, String roleName, boolean defaultRole) {
		this.memberId = memberId;
		this.roleName = roleName;
		this.defaultRole = defaultRole;
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

	public boolean getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	@Override
	public String toString() {
		return "MemberRole [memberId=" + memberId + ", roleName=" + roleName + ", defaultRole=" + defaultRole + "]";
	}

}
