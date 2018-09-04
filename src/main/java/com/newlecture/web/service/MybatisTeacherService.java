package com.newlecture.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.web.dao.LevelDao;
import com.newlecture.web.dao.QuestionDao;
import com.newlecture.web.dao.SubjectDao;
import com.newlecture.web.entity.Level;
import com.newlecture.web.entity.Question;
import com.newlecture.web.entity.Subject;

//@Repository, @Controller, @Service  의미가 있는 컴포넌트
@Service
public class MybatisTeacherService implements TeacherService {
	
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private LevelDao levelDao;
	
	@Autowired
	private QuestionDao questionDao;
	

	@Override
	public List<Subject> getSubjectList() {
		return subjectDao.getList();
	}

	@Override
	public List<Level> getLevelList() {
		return levelDao.getList();
	}

	@Override
	public List<Question> getQuestionList() {
		return questionDao.getList();
	}

	@Override
	public List<Question> getQuestionList(String query) {
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, int page) {
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, int page) {
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, String sortField, int page) {
		return null;
	}

}