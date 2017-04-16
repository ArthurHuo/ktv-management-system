package service;

import java.util.List;

import model.User;
/**
 * 用户登录时检测用户名
 */


public interface UserService {
	public User login(String username,String password);
	
	public List<User> findUsers(int userId);
	
	public User findUser(int userId);
}
