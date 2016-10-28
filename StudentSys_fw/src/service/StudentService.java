package service;

import java.util.List;
import java.util.Map;

import bean.Clasz;
import bean.Student;

/**
 * 学生业务逻辑处理接口
 * @author 王春力
 *
 */
public interface StudentService {
	/**
	 * 获取班级的名称和id
	 * @return 
	 */
	Map<Integer,String> getClaszNameWithId();
	/**
	 * 统计每个班级的学生人数
	 * @param claszId 班级的id
	 * @return   数组下标 	0-女生人数  1-男生人数
	 * 
	 * 	 */
	int[] countBySex(int claszId);
	/**
	 * ͨ通过班级id获取某个班级的对象
	 * @param claszId 班级的id
	 * @return  班级的对象
	 */
	Clasz getClasz(int claszId);
	/**
	 * 添加学生
	 * @param s
	 */
	void add(Student s);
	/**
	 * 删除学生
	 * @param studId ѧ��id
	 */
	void deleteStudent(int studId);
	/**
	 * 更新学生信息
	 * @param stud
	 */
	void updateStudent(Student stud);
	/**
	 *通过班级姓名进行模糊查询
	 * @param name 学生姓名
	 * @return  查询到的学生
	 */
    List<Student> findByName(int clsId,String name);
    
    /**
     * 获取当前页的学生信息
      * @param count 总数
     * @param currPage 当前页
     * @return
     */
    List<Student> findByPage(int clsId,int count,int currPage);
    
    
    
	

}
