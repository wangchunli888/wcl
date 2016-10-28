package util;

/**
 *��ҳ��������
 * @author yangxuefeng 2016-09-09
 *
 */
public class Pagination {
	
	private  int count;//��������
	
	private int pageSize;//ÿҳ��������
	
	private int currPage;//��ǰҳ
	

	public Pagination(int count, int pageSize) {
		super();
		this.count = count;
		this.pageSize = pageSize;
	}
	/**
	 * ��ȡ��ҳ��
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
	 * ���õ�ǰҳ
	 * @param page ��ǰ��ҳ��
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
	 * ��ȡ��ʼ���ݵ��±�
	 * @return
	 */
	public int getStartIndex(){
		return (currPage-1)*pageSize+1;
	}
	/**
	 * ��ȡ���ݵĽ����±�
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
