package util;

/**
 *分页处理工具类
 * @author yangxuefeng 2016-09-09
 *
 */
public class Pagination {
	
	private  int count;//数据总数
	
	private int pageSize;//每页的数据数
	
	private int currPage;//当前页
	

	public Pagination(int count, int pageSize) {
		super();
		this.count = count;
		this.pageSize = pageSize;
	}
	/**
	 * 获取总页数
	 * @return
	 */
	public int getPageCount(){
		if(count<=pageSize)
			return 1;
		int size=count/pageSize;
		if(count%pageSize!=0)
			size++;
		return size;
	}
	/**
	 * 设置当前页
	 * @param page 当前的页数
	 */
	public void setCurrPage(int page){
		if(page<=0)
			this.currPage=1;
		if(page>this.getPageCount())
			this.currPage=this.getPageCount();
		else
			this.currPage=page;
	}
	/**
	 * 获取起始数据的下标
	 * @return
	 */
	public int getStartIndex(){
		return (currPage-1)*pageSize+1;
	}
	/**
	 * 获取数据的结束下标
	 * @return
	 */
	public int getStopIndex(){
		int cnt=currPage*pageSize;
		if(cnt>count)
			return count;
		else
			return cnt;
	}
	
	
}
