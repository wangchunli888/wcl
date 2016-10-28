package dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bean.Black;
import util.BaseHibernateDAO;










/**
 * A data access object (DAO) providing persistence and search support for Black
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.jzfactory.jd.bean.Black
 * @author MyEclipse Persistence Tools
 */
public class BlackDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(BlackDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String REMOVE = "remove";

	public void save(Black transientInstance) {
		log.debug("saving Black instance");
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

	public void delete(Black persistentInstance) {
		log.debug("deleting Black instance");
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

	public Black findById(java.lang.Integer id) {
		log.debug("getting Black instance with id: " + id);
		try {
			Black instance = (Black) getSession().get(
					"com.jzfactory.jd.bean.Black", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Black> findByExample(Black instance) {
		log.debug("finding Black instance by example");
		try {
			List<Black> results = (List<Black>) getSession()
					.createCriteria("com.jzfactory.jd.bean.Black")
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
		log.debug("finding Black instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Black as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Black> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<Black> findByRemove(Object remove) {
		return findByProperty(REMOVE, remove);
	}

	public List findAll() {
		log.debug("finding all Black instances");
		try {
			String queryString = "from Black";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Black merge(Black detachedInstance) {
		log.debug("merging Black instance");
		try {
			Black result = (Black) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Black instance) {
		log.debug("attaching dirty Black instance");
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

	public void attachClean(Black instance) {
		log.debug("attaching clean Black instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	/**
	 * 通过名字修改用户信息
	 */
	public void updateByName(Black black) {
//				
		Transaction trans = getSession().beginTransaction();
		String hql = "update Black black set black.includeDate=?,black.remove=? where black.username=?";
//		璁剧疆鍙傛暟
		Query query = getSession().createQuery(hql);
		query.setTimestamp(0, black.getIncludeDate());
		query.setInteger(1, black.getRemoved());
		query.setString(2, black.getUsername());
		query.executeUpdate();
		trans.commit();
	}

	/**
	 * 查询所有列入黑名单的信息
	 * @return
	 */
	public List<Black> findInTable() {
		String hql = "from Black black where black.removed=0";
		Query query = getSession().createQuery(hql);
		return query.list();
	}

	/**
	 * 
	 * @param name
	 * @return
	 */
	public Black findByName(String name) {
		String hql = "from Black black where black.username=?";
		Session session = getSession();
		Query query = session.createQuery(hql);
		//璁剧疆鍙傛暟
		query.setString(0, name);

		Black black = (Black) query.uniqueResult();
		
		// List<Black> blackList=query.list();
		// if(blackList==null ||blackList.size()==0)
		// return null;
		// else
		// return blackList.get(0);
		return black;
		//
	}

	public static void main(String[] args) {
		Black black = new Black();
		// black.setIncludeDate(new Date());
		// black.setRemove(0);
		// black.setUsername("qqq");
		BlackDAO blackDao = new BlackDAO();
		// blackDao.save(black);
		// Black black2=blackDao.findById(1);
		// blackDao.delete(black2);

		// Black black3=blackDao.findById(5);
		// black3.setUsername("777");
		// blackDao.attachDirty(black3);
		// List<Black> blackList=blackDao.findAll();
		// System.out.println(blackList);

		// List<Black> blackInList=blackDao.findByRemove(0);
		// System.out.println(blackInList);
		// List<Black> nameList=blackDao.findByUsername("ss");
		// System.out.println(nameList);

		// black.setUsername("dd");
		// black.setRemove(0);
		// //Timestamp stramp=new Timestamp((new Date()).getTime());
		// black.setIncludeDate(new Date());
		// blackDao.updateByName(black);

		 List<Black> list = blackDao.findInTable();
		 System.out.println(list);
		
	
	}
}
