package service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import util.Pagination;
import dao.ClaszDAO;
import dao.StudentDAO;
import bean.Clasz;
import bean.Student;

public class StudentServiceImp implements StudentService {
	//设置每页学生的个数
	public  static final int PAGE_SIZE=3;
	
	private StudentDAO studentDao=new StudentDAO();
	private ClaszDAO claszDao=new ClaszDAO();

	/**
	 * 1.获取所有班级的id和名字
	 */
	@Override
	public Map<Integer, String> getClaszNameWithId() {
		// 先定义一个Map
		Map<Integer,String> map=new LinkedHashMap<Integer, String>();
		//获取所有班级的数据
		List<Clasz> cList=claszDao.findAll();
		//for循环  将班级的id和name放入map中
		for(Clasz cls:cList)
		{
			map.put(cls.getId(), cls.getName());
		}
        return map;
	}

	/**
	 * 2.统计班级的学生人数
	 */
	@Override
	public int[] countBySex(int claszId) {
		// TODO Auto-generated method stub
		return studentDao.countBysex(claszId);
	}

	/**
	 * 3.通过班级id获取某个班级的对象
	 */
	@Override
	public Clasz getClasz(int claszId) {
		// TODO Auto-generated method stub
		return claszDao.findById(claszId);
	}

	/**
	 * 4.添加一個學生
	 */
	@Override
	public void add(Student s) {
		studentDao.save(s);

	}

	/**
	 *5. 通过班级id删除学生
	 */
	@Override
	public void deleteStudent(int studId) {
		studentDao.delete(studId);

	}

	/**
	 * 6.修改学生信息
	 */
	@Override
	public void updateStudent(Student stud) {
		studentDao.attachDirty(stud);
		}

	/**
	 *7. 通过学生姓名查询学生信息 模糊查询
	 */
	@Override
	public List<Student> findByName(int clsId, String name) {
		// TODO Auto-generated method stub
		return studentDao.findByName2(name);
	}

	/**
	 * 8.分页
	 */
	@Override
	public List<Student> findByPage(int clsId, int count, int currPage) {
		Pagination pagination=new Pagination(count,PAGE_SIZE);
		pagination.setCurrPage(currPage);
		int startIndex=pagination.getStartIndex();
		int stopIndex=pagination.getStopIndex();
		return studentDao.findByPage(clsId, startIndex, stopIndex);
	}
	
	public static void main(String[] args) {
		StudentService studentService=new StudentServiceImp();
		
		Student student=new Student();
		StudentDAO studentDao=new StudentDAO();
		ClaszDAO claszDao=new ClaszDAO();
		Clasz clasz=new Clasz();
		
		clasz.setId(1);
	//	student.setName("aaa");
	//	student.setSex(0);
	//	student.setCode("1");
	//	student.setClasz(clasz);
		//studentService.add(student);
		
		student.setId(96);
		student.setName("666");
		student.setCode("666");
		student.setClasz(clasz);
		studentService.updateStudent(student);
		
		
		//1.获取所有的id和班级的名字
		//System.out.println(studentService.getClaszNameWithId());
		
		//2.统计班级的学生人数  两个方法输出的结果一样
		//System.out.println(Arrays.toString(studentDao.countBysex(1)));
		//System.out.println(Arrays.toString(studentService.countBySex(1)));
		
		//3.通过班级id获取某个班级的对象(Clasz和Student中只能toString一个   如果两个都toString会报异常（栈溢出）)
		//System.out.println(studentService.getClasz(1));
		
		//4.添加一个学生  
//		student.setName("AA");
//		student.setCode("00001");
//		Timestamp stramp=new Timestamp((new Date()).getTime());
//		student.setBirth(stramp);
//		student.setSex(0);
//		student.setClasz(claszDao.findById(1));
//		studentService.add(student);
		
		//5. 通过班级id删除学生
		//studentService.deleteStudent(93);
		
		//6.修改学生信息  
//		student.setId(4);
//		student.setName("大花花");
//		student.setCode("111");
//		student.setSex(0);
//		student.setClasz(claszDao.findById(1));
//		 SimpleDateFormat sf=new SimpleDateFormat("yyyy-mm-dd");
//		 String ddte="1999-09-19";
//		 Date date=null;
//		 try {
//			date = sf.parse(ddte);
//			student.setBirth(date);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		 studentService.updateStudent(student);
		
		//7. 通过学生姓名查询学生信息 模糊查询
		//System.out.println(studentService.findByName(1, "大"));
		
		
	}

}
