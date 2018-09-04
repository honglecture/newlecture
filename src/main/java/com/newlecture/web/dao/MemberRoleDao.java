package com.newlecture.web.dao;

import java.util.List;

public interface MemberRoleDao {
	
	int insert(MemberRoleDao memberRole);

	int update(MemberRoleDao memberRole);

	int delete(String id);

	MemberRoleDao get(String id);

	List<MemberRoleDao> getList();

}
