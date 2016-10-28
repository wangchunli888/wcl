package service;

import java.util.List;

import bean.User;

/**
 * @author 王春力
 *
 */
public interface UserService {
	List findAll();
	
	List findBlackList();
	
	User findById(int id);
	
	void updateStatus(User user);
	
	/**
	 * ͨ通过用户姓名差找用户
	 * @param name
	 * @return
	 */
	
	User findByName(String name);
	
	
	void updateUser(User user);
	
	void addUser(User user);
	
	void deleteUser(int id);

}
