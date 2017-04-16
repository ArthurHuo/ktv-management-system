package dao.impl;

import java.util.List;

import model.User;
import dao.UserDao;
/**
 * 用户的增删改查
 */



public class UserDaoImpl extends BaseDao implements UserDao {

	public void save(User user) {
		getSession().save(user);

	}

	public User findUserById(int id) {
		return (User)getSession().load(User.class, id);
	}

	public User finduserByUsername(String username) {
		return (User)getSession().createQuery("select u from User u where u.username = ?").setParameter(0, username).uniqueResult();
	}
	public void updateUser(User user) {
		getSession().update(user);

	}

	public void delUser(int id) {
		getSession().delete(getSession().load(User.class, id));

	}

	public List<User> findUsers(int userId) {
		return getSession().createQuery("select u from u where u.id != ?").setParameter(0, userId).list();
	}
	

}
