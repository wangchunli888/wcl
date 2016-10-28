package service;

import java.util.Map;

import bean.Clasz;

/**
 * 班级业务逻辑处理接口
 * @author 王春力
 *
 */
public interface ClaszService {
	/**
	 * 添加班级
	 * @param cls
	 */
	void add(Clasz cls);

	/**
	 * ͨ通过班级id删除班级
	 * @param claszId 班级的id
	 * @param isDelStud 是否可以关联删除学生
	 * @return 是否删除成功 true-成功
	 */
	boolean delete(int claszId,boolean isDelStud);
	/**
	 * 更新班级信息
	 * @param cls
	 */
	void updateClasz(Clasz cls);
	
	/**
	 * 获取所有班级的名称和id
	 * @return
	 */
	Map<Integer, String> getClaszNameWithId();

}
