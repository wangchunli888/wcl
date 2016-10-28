package service;

import java.util.ArrayList;
import java.util.List;

import dao.UserDAO;
import bean.Black;
import bean.User;

public class UserServiceImp implements UserService {
	private UserDAO userDao=new UserDAO();
	
	/**
	 * 1.查找所有用户信息
	 */
	@Override
	public List findAll() {
		// TODO Auto-generated method stub
		return userDao.findAll();
	}

	/**
	 *2. 查找列入黑名单的用户
	 */
	@Override
	public List findBlackList() {
		// TODO Auto-generated method stub
//		List list=userDao.findByStatus(1);
//		return list;
		return userDao.findBlackList();
	}

	/**
	 * 3.根据id查找用户
	 */
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return userDao.findById(id);
	}

	/**
	 * 4.修改用户的黑名单的状态
	 */
	@Override
	public void updateStatus(User user) {
		// TODO Auto-generated method stub
		userDao.attachDirty(user);

	}

	/**
	 * 5.通过名字查找用户
	 */
	@Override
	public User findByName(String name) {
		// TODO Auto-generated method stub
		return userDao.findByNmae2(name);
	}

	/**
	 * 6.修改用户信息
	 */
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		userDao.attachDirty(user);

	}

	/**
	 * 7.添加用户
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.save(user);

	}

	/**
	 * 8.通过id删除用户
	 */
	@Override
	public void deleteUser(int id) {
		// TODO Auto-generated method stub
		userDao.delete(id);
	}
	
	public static void main(String[] args) {
		UserService userService=new UserServiceImp();
		User user=new User();
		UserDAO userDao=new UserDAO();
		
		//1.查找所有用户信息
		//System.out.println(userService.findAll());
		
		//2.查找列入黑名单的用户
		//System.out.println(userService.findBlackList());
		
		//3.根据id查找用户
		//System.out.println(userService.findById(2));
		
		//4.修改用户的黑名单状态
//		user=userDao.findById(1);
//		user.setStatus(1);
//		userService.updateStatus(user);
		
		//5.通过名字查找用户信息
		//System.out.println(userService.findByName("1"));
		
		//6.修改用户信息
//		user.setId(2);
//		user.setName("2");
//		user.setPassword("2");
//		user.setEmail("2");
//		user.setStatus(1);
//		userService.updateUser(user);
		
		//7.添加用户
//		user.setName("3");
//		user.setPassword("3");
//		user.setEmail("3");
//		user.setStatus(1);
//		userService.addUser(user);
		
		//8.通过id删除用户
		userService.deleteUser(3);
		
		
		
	
		
		
		
	}

}
