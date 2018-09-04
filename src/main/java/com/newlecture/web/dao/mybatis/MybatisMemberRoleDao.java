package com.newlecture.web.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.web.dao.MemberRoleDao;
@Repository
public class MybatisMemberRoleDao implements MemberRoleDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(MemberRoleDao memberRole) {
		MemberRoleDao memberRoleDao = sqlSession.getMapper(MemberRoleDao.class);
		return memberRoleDao.insert(memberRole);
	}

	@Override
	public int update(MemberRoleDao memberRole) {
		MemberRoleDao memberRoleDao = sqlSession.getMapper(MemberRoleDao.class);
		return memberRoleDao.update(memberRole);
	}

	@Override
	public int delete(String id) {
		MemberRoleDao memberRoleDao = sqlSession.getMapper(MemberRoleDao.class);
		return memberRoleDao.delete(id);
	}

	@Override
	public MemberRoleDao get(String id) {
		MemberRoleDao memberRoleDao = sqlSession.getMapper(MemberRoleDao.class);
		return memberRoleDao.get(id);
	}

	@Override
	public List<MemberRoleDao> getList() {
		MemberRoleDao memberRoleDao = sqlSession.getMapper(MemberRoleDao.class);
		return memberRoleDao.getList();
	}

}
