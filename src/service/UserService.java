package service;

import java.util.List;

import model.User;
/**
 * �û���¼ʱ����û���
 */


public interface UserService {
	public User login(String username,String password);
	
	public List<User> findUsers(int userId);
	
	public User findUser(int userId);
}
