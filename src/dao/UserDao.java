package dao;

import java.util.List;

import model.User;
/**
 * �������ݿ⣬�����û����ӿ�
 */


public interface UserDao {
	public void save(User user);
	public User findUserById(int id);
	public User finduserByUsername(String username);
	public void updateUser(User user);
	public void delUser(int id);
	public List<User> findUsers(int userId);
}
