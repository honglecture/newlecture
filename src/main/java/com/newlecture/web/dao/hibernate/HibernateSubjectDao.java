package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Subject;

@Repository
public class HibernateSubjectDao implements SubjectDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.save(subject);
		return object != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Subject subject = new Subject();
		subject.setId(id);
		session.remove(subject);
		return 1;
	}

	@Override
	@Transactional
	public int update(Subject subject) {
		Session session = sessionFactory.getCurrentSession();
		session.update(subject);
		return 1;
	}

	@Override
	@Transactional
	public Subject get(long id) {
		Session session = sessionFactory.getCurrentSession();
		Subject subject = session.get(Subject.class, id); // where 절의 들어갈 id
		return subject;
	}

	@Override
	@Transactional
	public List<Subject> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
