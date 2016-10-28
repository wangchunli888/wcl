package service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import dao.ClaszDAO;
import bean.Clasz;

public class ClaszServiceImp implements ClaszService {
	private ClaszDAO claszDao=new ClaszDAO();
	

	/**
	 * 1.添加班级
	 */
	@Override
	public void add(Clasz cls) {
		claszDao.save(cls);

	}

	/**
	 * 2.通过班级id删除学生  isDelStud是否可以关联删除学生
	 */
	@Override
	public boolean delete(int claszId, boolean isDelStud) {
		// TODO Auto-generated method stub
		return claszDao.delete(claszId, isDelStud);
	}

	/**
	 * 3.更新班级
	 */
	@Override
	public void updateClasz(Clasz cls) {
		// TODO Auto-generated method stub
		claszDao.attachDirty(cls);

	}

	/**
	 * 4.获取所有班级的id和名字
	 */
	@Override
	public Map<Integer, String> getClaszNameWithId() {
		// 先定义一个Map
		Map<Integer,String> map=new LinkedHashMap<Integer, String>();
		//获取所有班级的数据
		List<Clasz> cList=claszDao.findAll();
		//for循环  将每个班级的id和name存入map中
		for(Clasz cls:cList)
		{
			map.put(cls.getId(), cls.getName());
		}
		return map;
	}
	public static void main(String[] args) {
		ClaszService claszService=new ClaszServiceImp();
		Clasz clasz=new Clasz();
		
		//1.添加班级
//		clasz.setName("1");
//		claszService.add(clasz);
		
		//2.通过班级id删除学生  isDelStud是否可以关联删除学生
		//claszService.delete(5, true);
		
		//3.更新班级
//		clasz.setId(1);
//		clasz.setName("一年一年");
//		clasz.setTName("小花");
//		claszService.updateClasz(clasz);
		
		//4.获取所有班级的id和名字
		System.out.println(claszService.getClaszNameWithId());
	}

}
