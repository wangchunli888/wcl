package service;

import java.util.List;

import bean.Black;




/**
 * 黑名单业务逻辑处理类
 * @author 王春力
 *
 */
public interface BlackService {
	
	/**
	 * 获取所有的黑名单用户信息
	 * @return
	 */
	List<Black> getAll();
	/**
	 * ͨ通过名字查找到该用户
	 * @param name
	 * @return
	 */
	Black findByName(String name);
	/**
	 * 保存新用户
	 * @param black
	 */
	void saveBlack(Black black);
     /**
      * 更新用户
      * @param black
      */
	void updateBlack(Black black);
	/**
	 * 获取黑名单中的用户信息
	 * @return
	 */
	List<Black> getInTable();
	/**
	 * 判断用户是否在黑名单中
	 * @param name
	 * @return true-存在 false-不存在
	 */
	boolean isInBlack(String name);
}
