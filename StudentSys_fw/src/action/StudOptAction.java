package action;

import java.util.Iterator;
import java.util.List;

import service.StudentService;
import service.StudentServiceImp;
import bean.Clasz;
import bean.Student;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 学生操作  增删改
 * @author 61933
 *
 */
public class StudOptAction extends ActionSupport {
	
	private Student stud=new Student();
	
	private Integer claszId;
	
	private StudentService service=new StudentServiceImp();

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	/**
	 * 添加学生访问方法
	 * @return
	 */
	public String add()
	{
		Clasz clasz=new Clasz();
		clasz.setId(claszId);
		stud.setClasz(clasz);
		service.add(stud);
	    return "success";
	}
	
	/**
	 * 删除学生访问方法
	 * @return
	 */
	public String del()
	{
		//getClasz 通过班级id获取某个班级的对象
		Clasz cls=service.getClasz(claszId);
		//迭代器（循环）  通过迭代器获取班级下的学生
		Iterator<Student> is=cls.getStudents().iterator();
		while(is.hasNext())
		{
			Student s=is.next();
			//equals比较的是地址
			if(s.getId().equals(stud.getId()))
			{
				is.remove();
				service.deleteStudent(s.getId());
			}
		}
	return "success";

	}
	
	/**
	 * 修改学生访问方法
	 * @return
	 */
	public String edit()
	{
		Clasz cls=new Clasz();
		cls.setId(claszId);
		stud.setClasz(cls);
		service.updateStudent(stud);
        return "success";
	}

	public Student getStud() {
		return stud;
	}

	public void setStud(Student stud) {
		this.stud = stud;
	}

	public Integer getClaszId() {
		return claszId;
	}

	public void setClaszId(Integer claszId) {
		this.claszId = claszId;
	}
	
	
	

}
