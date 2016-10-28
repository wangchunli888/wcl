package dao;



import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Clasz;
import util.BaseHibernateDAO;

/**
 * A data access object (DAO) providing persistence and search support for Clasz
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.jzfactory.jd.bean.Clasz
 * @author MyEclipse Persistence Tools
 */
public class ClaszDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(ClaszDAO.class);
	// property constants
	public static final String NAME = "name";
	public static final String _TNAME = "TName";

	public void save(Clasz transientInstance) {
		log.debug("saving Clasz instance");
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

	public void delete(Clasz persistentInstance) {
		log.debug("deleting Clasz instance");
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

	public Clasz findById(java.lang.Integer id) {
		log.debug("getting Clasz instance with id: " + id);
		try {
			Clasz instance = (Clasz) getSession().get(
					"bean.Clasz", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Clasz> findByExample(Clasz instance) {
		log.debug("finding Clasz instance by example");
		try {
			List<Clasz> results = (List<Clasz>) getSession()
					.createCriteria("bean.Clasz")
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
		log.debug("finding Clasz instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Clasz as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Clasz> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Clasz> findByTName(Object TName) {
		return findByProperty(_TNAME, TName);
	}

	public List findAll() {
		log.debug("finding all Clasz instances");
		try {
			String queryString = "from Clasz";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Clasz merge(Clasz detachedInstance) {
		log.debug("merging Clasz instance");
		try {
			Clasz result = (Clasz) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Clasz instance) {
		log.debug("attaching dirty Clasz instance");
		try {
			Transaction trans = getSession().beginTransaction();
			getSession().saveOrUpdate(instance);
			trans.commit();
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Clasz instance) {
		log.debug("attaching clean Clasz instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 通过班级的id删除班级
	 * @param claszId
	 * @param isDelStud  是否可以删除关联学生
	 * @return
	 */
	public boolean delete(int claszId, boolean isDelStud) {
		Clasz cls = this.findById(claszId);
		if (!isDelStud && cls.getStudents().size() > 0)
			return false;
		this.delete(cls);
		return true;
	}

	public static void main(String[] args) {
		ClaszDAO clsDao = new ClaszDAO();
		// Clasz cls=new Clasz();
		// cls.setName("鍏勾鍏彮");
		// cls.setTName("鐜嬭€佸笀");
		// clsDao.save(cls);

		// Clasz cls2=clsDao.findById(9);
		// Student s1=new Student();
		// s1.setBirth(new Date());
		// s1.setCode("1111");
		// s1.setName("灏忛");
		// s1.setSex(1);
		//
		// cls.getStudents().add(s1);

		// cls2.getStudents().add(s1);
		// clsDao.attachDirty(cls2);

		// clsDao.attachDirty(cls);

		// Clasz cls1=clsDao.findById(4);
		// clsDao.delete(cls1);
		clsDao.delete(6, true);

	}
}
