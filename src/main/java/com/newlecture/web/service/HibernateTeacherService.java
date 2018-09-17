package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.QuestionLevelDao;
import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.QuestionLevel;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

//@Repository, @Controller, @Service  의미가 있는 컴포넌트
@Service
public class HibernateTeacherService implements TeacherService {

	@Autowired
	private SubjectDao subjectDao;

	@Autowired
	private QuestionLevelDao levelDao;

	@Autowired
	private QuestionDao questionDao;

	@Override
	public List<Subject> getSubjectList() {
		return subjectDao.getList();
	}

	@Override
	public List<QuestionLevel> getLevelList() {
		return levelDao.getList();
	}

	@Override
	public List<Question> getQuestionList() {
		return getQuestionList("", null, "regDate", 1);
	}

	@Override
	public List<Question> getQuestionList(String query) {
		return getQuestionList(query, null, "regDate", 1);
	}

	@Override
	public List<Question> getQuestionList(String query, int page) {
		return getQuestionList(query, null, "regDate", page);
	}

	@Override
	public List<Question> getQuestionList(String query, String ownerId, int page) {
		return getQuestionList(query, null, "regDate", page);
	}

	@Override
	public List<Question> getQuestionList(String query, String ownerId, String sortField, int page) {
		return questionDao.getList(query, ownerId, sortField, page);
	}

}