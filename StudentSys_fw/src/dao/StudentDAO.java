package dao;






import static org.hibernate.criterion.Example.create;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import util.BaseHibernateDAO;
import bean.Clasz;
import bean.Student;

import org.hibernate.Session;



/**
 * A data access object (DAO) providing persistence and search support for
 * Student entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.jzfactory.jd.bean.Student
 * @author MyEclipse Persistence Tools
 */
public class StudentDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(StudentDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String CODE = "code";
	public static final String SEX = "sex";

	public void save(Student transientInstance) {
		log.debug("saving Student instance");
		try {
			Transaction trans = getSession().beginTransaction();
			getSession().save(transientInstance);
			trans.commit();
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Student persistentInstance) {
		log.debug("deleting Student instance");
		try {
			Transaction trans = getSession().beginTransaction();
			getSession().delete(persistentInstance);
			trans.commit();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Student findById(java.lang.Integer id) {
		log.debug("getting Student instance with id: " + id);
		try {
			Student instance = (Student) getSession().get(
					"com.jzfactory.jd.bean.Student", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Student> findByExample(Student instance) {
		log.debug("finding Student instance by example");
		try {
			List<Student> results = (List<Student>) getSession()
					.createCriteria("com.jzfactory.jd.bean.Student")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Student instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Student as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Student> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Student> findByCode(Object code) {
		return findByProperty(CODE, code);
	}

	public List<Student> findBySex(Object sex) {
		return findByProperty(SEX, sex);
	}

	public List findAll() {
		log.debug("finding all Student instances");
		try {
			String queryString = "from Student";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Student merge(Student detachedInstance) {
		log.debug("merging Student instance");
		try {
			Student result = (Student) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Student instance) {
		log.debug("attaching dirty Student instance");
		try {
			Transaction trans=getSession().beginTransaction();
			getSession().saveOrUpdate(instance);
			trans.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Student instance) {
		log.debug("attaching clean Student instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 为某个班级添加多个学生
	 * @param cls
	 * @param studList
	 */
	public void save(Clasz cls, List<Student> studList) {
		Transaction trans = getSession().beginTransaction();
		for (Student std : studList) {
			std.setClasz(cls);
			getSession().save(std);
		}
		trans.commit();
	}

	/**
	 * 通过id删除学生
	 * 
	 * @param id
	 */
	public void delete(int id) {
		Transaction trans = getSession().beginTransaction();
		String hql = "delete from Student  stud where stud.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, id);
		query.executeUpdate();
		trans.commit();
	}

	/**
	 *删除某个班级的学生
	 * @param clsId
	 */
	public void deleteByCls(int clsId) {
		Transaction trans = getSession().beginTransaction();
		String hql = "delete from Student s where s.clasz.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, clsId);
		query.executeUpdate();
		trans.commit();
	}
	/**
	 * 通过班级名字删除这个班级的所有学生
	 * * @param claszName
	 * @return
	 * */

	public int deleteByClsName(String claszName) {
		Transaction trans = getSession().beginTransaction();
		String hql = "from Clasz cls where cls.name=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, claszName);
		Clasz cls = (Clasz) query.uniqueResult();
		hql = "delete from Student s where s.clasz.id=?";
		query = getSession().createQuery(hql);
		query.setInteger(0, cls.getId());
		int num = query.executeUpdate();
		trans.commit();
		return num;
	}

	/**
	 * 将一个班的学生更改为另一个班
	 * @param oldClaszId以前班级的id
	 * @param newClsId  新的班级的id
	 */
	public void changeClasz(int oldClaszId, int newClsId) {
		String hql = "from Student s where s.clasz.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, oldClaszId);
		List<Student> sList = query.list();
		Clasz newCls = (Clasz) getSession().get(Clasz.class, newClsId);
		Transaction trans = getSession().beginTransaction();
		for (Student s : sList) {
			s.setClasz(newCls);
			getSession().save(s);
		}
		trans.commit();
	}

	/**
	 *通过名字查询学生信息(模糊查询)
	 * 
	 * @return name
	 */
	public List<Student> findByName2(String name) {
		String hql = "from Student s where s.name like ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, "%" + name + "%");
		return query.list();

	}

	public Student findByCode2(String code) {
		String hql = "from Student s where s.code=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, code);
		return (Student) query.uniqueResult();
	}

	/**
	 * 通过班级的id茶道学生
	 * 
	 * @param claszId
	 * @return
	 */
	public List<Student> findByClsId(int claszId) {
		String hql = "from Student s where s.clasz.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, claszId);
		return query.list();
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public List<Student> findByClsName(String name) {
		String hql = "from Student s where s.clasz.name=?";
		Query query = getSession().createQuery(hql);
		query.setString(0, name);
		return query.list();
	}
	/**
	 * 鏌ヨ鏌愪釜瀛︾敓鎵€鍦ㄧ殑鐝骇
	 * @param studId瀛︾敓id
	 * @return
	 */
	public Clasz findClsByStudId(int studId) {
		Student s = (Student) getSession().get(Student.class, studId);
		if (s != null)
			return s.getClasz();
		return null;
	}
	/**
	 * 统计男女人数    前面是性别  后面是人数
	 * @param claszId
	 * @return
	 */
	public int[] countBysex(int claszId) {
		String hql = "select s.sex,count(s.id) from Student s  where s.clasz.id=? group by s.sex";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, claszId);
		List<Object[]> obList = query.list();
		int[] nums = new int[2];
		if (obList!= null && obList.size() > 0) {

			for (Object[] obs : obList) {
				int sex = (Integer) obs[0];
				long count = (Long) obs[1];
				if (sex == 0)
					nums[0] = (int)count;
				else
					nums[1] = (int) count;
			}
		}
		return nums;
	}

	/**
	 * 查询某跟班级的学生编号和姓名
	 * 
	 * @param clsId 班级的id
	 * @return key学生的编号   value-学生的姓名
	 * 	 */
	public Map<String, String> getStudentName(int clsId) {
		String hql = "select s.code,s.name from Student s  where s.clasz.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, clsId);
		List<Object[]> obList = query.list();

		Map<String, String> map = new HashMap<String, String>();
		for (Object[] obs : obList) {
			String code = (String) obs[0];
			String name = (String) obs[1];
			map.put(code, name);
		}
		return map;
	}

	/**
	 * 通过查询某一个班级下某一个学生（名字）的信息
	 * @param claszId
	 * @param name
	 * @return
	 */
	public List<Student> findByclName(int claszId, String name) {
		String hql = "from Student s where  s.clasz.id=? and s.name like ?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, claszId);
		query.setString(1, "%" + name + "%");
		return query.list();
	}

	/**
	 * 分页
	 * @param clsId
	 * @param startIndex 起事下标  
	 * @param stopIndex  结=结束下标
	 * @return
	 */
	public List<Student> findByPage(int clsId, int startIndex, int stopIndex) {
		String hql = "from Student s where s.clasz.id=?";
		Query query = getSession().createQuery(hql);
		query.setInteger(0, clsId);
		query.setFirstResult(startIndex);
		//位移
		query.setMaxResults(stopIndex - startIndex + 1);
		return query.list();
	}

	public static void main(String[] args) {
		StudentDAO sDao = new StudentDAO();
		// Student st=new Student();
		// Student s1 = sDao.findById(5);
		// System.out.println(s1.getClasz().getName());
//		ClaszDAO clsDao = new ClaszDAO();

		// //娣诲姞澶氫釜
		// Clasz cls=clsDao.findById(1);
		// Student student=new Student();
		// List<Student> list=new ArrayList<Student>();
		// student.setBirth(new Date());
		// student.setClasz(cls);
		// student.setCode("999");
		// student.setName("灏忎竴");
		// student.setSex(1);
		// list.add(student);
		// sDao.save(cls, list);
		// Student student1=new Student();
		// student1.setBirth(new Date());
		// student1.setClasz(cls);
		// student1.setCode("9888");
		// student1.setName("灏忎簩");
		// student1.setSex(0);
		// list.add(student1);
		// sDao.save(cls, list);
		//

		// sDao.delete(8);
		// sDao.deleteByCls(2);
		// System.out.println(sDao.deleteByClsName("涓€骞翠竴鐝?));
		// sDao.changeClasz(1, 11);
		// List<Student> list = sDao.findByName2("6");
		// System.out.println(list);

		// System.out.println(sDao.findByCode2("1"));
		// System.out.println(sDao.findByClsId(6));
		// System.out.println(sDao.findByClsName("涓夊勾浜岀彮"));
		// System.out.println(sDao.findClsByStudId(25));
		System.out.println(Arrays.toString(sDao.countBysex(1)));
		// System.out.println(sDao.getStudentName(6));
		// List<Student> list = sDao.findByclName(2, "22");
		// System.out.println(list);
		 List<Student> list=sDao.findByPage(2, 1,3 );
		 System.out.println(list);
		
	
		
	}
}