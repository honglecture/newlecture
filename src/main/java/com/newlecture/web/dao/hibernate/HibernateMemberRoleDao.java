package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.entity.MemberRole;

@Repository
public class HibernateMemberRoleDao implements MemberRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(MemberRole memberRole) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.save(memberRole);
		return object != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int update(MemberRole memberRole) {
		Session session = sessionFactory.getCurrentSession();
		session.update(memberRole);
		return 1;
	}

	@Override
	@Transactional
	public int delete(MemberRole memberRole) {
		Session session = sessionFactory.getCurrentSession();
		session.remove(memberRole);
		return 1;
	}

	@Override
	@Transactional
	public MemberRole get(MemberRole memberRole) {
		Session session = sessionFactory.getCurrentSession();
		MemberRole role = session.get(MemberRole.class, memberRole.getMemberId()); // where 절의 들어갈 id
		return role;
	}

	@Override
	@Transactional
	public List<MemberRole> getList() {
		return null;
	}

	@Override
	@Transactional
	public List<MemberRole> getList(String memberId) {
		return null;
	}

}
