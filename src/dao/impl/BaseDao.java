package dao.impl;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import test.HibernateUtils;
/**
 * ����ʵ�ֵĸ��࣬�õ�session
 */


public class BaseDao {
	
	@Resource
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		Session session = (Session) HibernateUtils.getSession();
		return session;
	}
}
