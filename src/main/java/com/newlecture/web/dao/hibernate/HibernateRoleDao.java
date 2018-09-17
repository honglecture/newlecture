package com.newlecture.web.dao.hibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.newlecture.web.dao.MemberRoleDao;
import com.newlecture.web.dao.RoleDao;
import com.newlecture.web.entity.Member;
import com.newlecture.web.entity.MemberRole;
import com.newlecture.web.entity.Role;

@Repository
public class HibernateRoleDao implements RoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public int insert(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Object object = session.save(role);
		return object != null ? 1 : 0;
	}

	@Override
	@Transactional
	public int update(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.update(role);
		return 1;
	}

	@Override
	@Transactional
	public int delete(String id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = new Role();
		role.setName(id);
		session.remove(role);
		return 1;
	}

	@Override
	@Transactional
	public Role get(String id) {
		Session session = sessionFactory.getCurrentSession();
		Role role = session.get(Role.class, id); // where 절의 들어갈 id
		return role;
	}

	@Override
	@Transactional
	public List<Role> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
