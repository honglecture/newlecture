package com.newlecture.web.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
//@IdClass(Member.class)
public class MemberRole {
	@EmbeddedId
	private MemberRoleId id; // 2개의

	/*
	 * private String memberId; private String roleName;
	 */
	private boolean defaultRole;

	public MemberRole() {

	}

	public MemberRole(String memberId, String roleName, boolean defaultRole) {
		this.id = new MemberRoleId(memberId, roleName);
		/*
		 * this.memberId = memberId; this.roleName = roleName;
		 */
		this.defaultRole = defaultRole;
	}

	public String getMemberId() {
		return id.getMemberId();
	}

	public void setMemberId(String memberId) {
		this.id.setMemberId(memberId);
	}

	public String getRoleName() {
		return id.getRoleName();
	}

	public void setRoleName(String roleName) {
		this.id.setRoleName(roleName);
	}

	public boolean getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(boolean defaultRole) {
		this.defaultRole = defaultRole;
	}

	@Override
	public String toString() {
		return "MemberRole [memberId=" + id.getMemberId() + ", roleName=" + id.getRoleName() + ", defaultRole="
				+ defaultRole + "]";
	}

}