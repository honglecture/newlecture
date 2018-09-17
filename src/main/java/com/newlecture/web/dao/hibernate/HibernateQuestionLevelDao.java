package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.dao.QuestionLevelDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;
import com.newlecture.web.entity.QuestionLevel;

@Repository
public class HibernateQuestionLevelDao implements QuestionLevelDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(QuestionLevel level) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.save(level);
		return object != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		QuestionLevel questionLevel = new QuestionLevel();
		questionLevel.setId(id);
		session.remove(questionLevel);
		return 1;
	}

	@Override
	@Transactional
	public int update(QuestionLevel level) {
		Session session = sessionFactory.getCurrentSession();
		session.update(level);
		return 1;
	}

	@Override
	@Transactional
	public QuestionLevel get(long id) {
		Session session = sessionFactory.getCurrentSession();
		QuestionLevel questionLevel = session.get(QuestionLevel.class, id); // where 절의 들어갈 id
		return questionLevel;
	}

	@Override
	@Transactional
	public List<QuestionLevel> getList() {
		// TODO Auto-generated method stub
		return null;
	}


}
