package dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import test.HibernateUtils;
/**
 * 所有实现的父类，拿到session
 */


public class BaseDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		Session session = (Session) HibernateUtils.getSession();
		return session;
	}
}
