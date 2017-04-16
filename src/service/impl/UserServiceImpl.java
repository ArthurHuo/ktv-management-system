package service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.swing.JOptionPane;

import model.User;
import service.UserService;
import dao.impl.UserDaoImpl;

/**
 * ����ʵ��
 */

//@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDaoImpl userDaoImpl;

	public User login(String username, String password) {
		userDaoImpl = new UserDaoImpl();
		User user = userDaoImpl.finduserByUsername(username);
		
		if(user == null){
//			throw new RuntimeException("�û���"+username+"��������");
			return null;
		}
		if(!user.getPassword().equals(password)){
			return null;
		}
		return user;
	}

	public List<User> findUsers(int userId) {
		return userDaoImpl.findUsers(userId);
	}

	public User findUser(int userId) {
		return userDaoImpl.findUserById(userId);
	}

}
