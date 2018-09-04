package com.newlecture.web.dao;

import java.util.List;

import com.newlecture.web.entity.Level;

public interface LevelDao {
	
	int insert(Level level);

	int delete(long id);

	int update(Level level);

	Level get(long id);

	List<Level> getList();


}
