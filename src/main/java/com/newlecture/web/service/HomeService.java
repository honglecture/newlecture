package com.newlecture.web.service;

import com.newlecture.web.entity.Member;

public interface HomeService {

	String getDefaultRoleName(String memberId);

	boolean isEmailDuplicated(String email);

	boolean isIdDuplicated(String id);

	int insertMember(Member member);

	Member getMember(String id);

}
