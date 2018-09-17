package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberDao;
import com.newlecture.web.entity.Member;

@Repository
public class HibernateMemberDao implements MemberDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(Member member) {
		Session session = sessionFactory.getCurrentSession();
		Object id = session.save(member);
		return id != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int update(Member member) {
		Session session = sessionFactory.getCurrentSession();
		session.update(member);
		return 1;
	}

	@Override
	@Transactional
	public int delete(String id) {
		Session session = sessionFactory.getCurrentSession();
		/*Member member = get(id);*/
		Member member = new Member();
		member.setId(id);
		session.remove(member);
		return 1;
	}

	@Override
	@Transactional
	public Member get(String id) {
		Session session = sessionFactory.getCurrentSession();
		Member member = session.get(Member.class, id); // where 절의 들어갈 id
		return member;
	}

	@Override
	@Transactional
	public List<Member> getList() {
		return null;
	}

	@Override
	@Transactional
	public List<Member> getList(int page) {
		return null;
	}

	@Override
	@Transactional
	public List<Member> getList(String field, String query) {
		return null;
	}

	@Override
	@Transactional
	public List<Member> getList(String field, String query, int page) {
		return null;
	}

	@Override
	@Transactional
	public Member getByEmail(String email) {
		return null;
	}

}
