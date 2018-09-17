package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.Question;

@Repository
public class HibernateQuestionDao implements QuestionDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(Question question) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.save(question);
		return object != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = new Question();
		question.setId(id);
		session.remove(question);
		return 1;
	}

	@Override
	@Transactional
	public int update(Question question) {
		Session session = sessionFactory.getCurrentSession();
		session.update(question);
		return 1;
	}

	@Override
	@Transactional
	public Question get(long id) {
		Session session = sessionFactory.getCurrentSession();
		Question question = session.get(Question.class, id); // where 절의 들어갈 id
		return question;
	}

	@Override
	@Transactional
	public List<Question> getList(String query, String ownerId, String sortField, int page) {
		// TODO Auto-generated method stub
		return null;
	}



}
