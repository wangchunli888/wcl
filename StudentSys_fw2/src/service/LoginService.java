package service;

import bean.User;



/**
 * 登录服务接口
 * @author 王春力
 *
 */
public interface LoginService {
	/**
	 * 注册用户
	 * @param user 注册的用户信息
	 * @return 0-成功 1-用户存在
	 */
	int register(User user);
	/**
	 * 用户登录
	 * @param user 登录的用户信息
	 * @return 0-登录成功 1-用户不存在  2-用户密码不正确
	 */
	int login(User user);
	/**
	 * 用户登录
	 * @param username 传入的用户名
	 * @param password 密码
	 * @return 登录成功返回登录对象
	 */
	User login(String username,String password);

}
