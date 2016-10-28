package service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.BlackDAO;
import bean.Black;

public class BlackImp implements BlackService {
	private BlackDAO blackDao=new BlackDAO();

     /**
      * 1.获取所有黑名单用户的信息
      */
	@Override
	public List<Black> getAll() {
		// TODO Auto-generated method stub
		return blackDao.findAll();
	}

	/**2.通过名字查找该用户
	 */
	@Override
	public Black findByName(String name) {
		// TODO Auto-generated method stub
		return blackDao.findByName(name);
	}

	/**
	 * 3.保存新用户
	 */
	@Override
	public void saveBlack(Black black) {
		blackDao.save(black);

	}

	/**
	 * 4.更新用户
	 */
	@Override
	public void updateBlack(Black black) {
		blackDao.attachDirty(black);

	}

	/**
	 * 5.查询所有列入黑名单的信息
	 */
	@Override
	public List<Black> getInTable() {
		// TODO Auto-generated method stub
		return blackDao.findInTable();
	}

	/**
	 * 6.判断用户是否在黑名单中
	 */
	@Override
	public boolean isInBlack(String name) {
		// TODO Auto-generated method stub
		Black black=new Black();
	   black=blackDao.findByName(name);
	   if(black!=null)
		return true;
	   else
		   return false;
	}
	public static void main(String[] args) {
		BlackService blackService=new BlackImp();
		Black black=new Black();
		//1.获取所有黑名单的用户信息
	    //System.out.println(blackService.getAll());
		
		//2.通过名字查找该用户
		//System.out.println(blackService.findByName("1"));
		
		//3.保存新用户
//		black.setUsername("2");
//		black.setRemoved(0);
//		Timestamp stramp=new Timestamp((new Date()).getTime());
//		black.setIncludeDate(stramp);
//		blackService.saveBlack(black);
		
		//4.更新用户
//		black.setId(1);
//		black.setUsername("222222");
//		black.setRemoved(0);
//		Timestamp stramp=new Timestamp((new Date()).getTime());
//		black.setIncludeDate(stramp);
//		blackService.updateBlack(black);
		
		
		//5.查询所有列入黑名单的信息
//		List<Black> bList=blackService.getInTable();
//		System.out.println(bList);
		
		//6.判断用户是否在黑名单中
		System.out.println(blackService.isInBlack("9"));
		
		
		
		
		
		
	
	}

}
