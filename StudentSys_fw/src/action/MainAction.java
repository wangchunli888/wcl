package action;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.StudentService;
import service.StudentServiceImp;
import bean.Clasz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
/**
 * 主页面
 * @author 61933
 *
 */
public class MainAction extends ActionSupport {
	/**
	 * 第三种方法
	 */
	private StudentService service=new StudentServiceImp();
	private HttpServletRequest request;
	
	private Integer clsId;
	

	@Override
	public String execute() throws Exception {
		request = ServletActionContext.getRequest();
		//获取班级的id和名字
		Map<Integer,String> nameMap=service.getClaszNameWithId();
		if(clsId==null)
		{
			for (Entry<Integer,String> entry:nameMap.entrySet()) {
				clsId=entry.getKey();
				break;
			}
		}
		int[] count=service.countBySex(clsId);
		//获取班级信息
		Clasz cls=service.getClasz(clsId);
		request.setAttribute("nameMap", nameMap);
		request.setAttribute("count", count);
		request.setAttribute("cls", cls);
        return super.execute();
	}


	public Integer getClsId() {
		return clsId;
	}


	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
	
	/**
	 * 第四种方法
	 */
//	private StudentService service=new StudentServiceImp();
//	private HttpServletRequest request;
//	private Integer clsId;
//	@Override
//	public String execute() throws Exception {
//		request = ServletActionContext.getRequest();
//		//获取班级的id和名字
//		Map<Integer,String> nameMap=service.getClaszNameWithId();
//		if(clsId==null)
//		{
//			for (Entry<Integer,String> entry:nameMap.entrySet()) {
//				clsId=entry.getKey();
//				break;
//			}
//		}
//		int[] count=service.countBySex(clsId);
//		//获取班级信息
//		Clasz cls=service.getClasz(clsId);
//		request.setAttribute("nameMap", nameMap);
//		request.setAttribute("count", count);
//		request.setAttribute("cls", cls);
//		return super.execute();
//	}
//	
//	public void setClsId(Integer clsId) {
//		System.out.println("测试："+clsId);
//		this.clsId = clsId;
//	}
	
	/**
	 * 第一种方法
	 */
//	private StudentService service=new StudentServiceImp();
//	private Map request;
//	private Integer clsId;
//	
//	private ActionContext context;
//	@Override
//	public String execute() throws Exception {
//		context=ActionContext.getContext();
//	
//		//request = ServletActionContext.getRequest();
//		//获取班级的id和名字
//		Map<Integer,String> nameMap=service.getClaszNameWithId();
//		if(clsId==null)
//		{
//			for (Entry<Integer,String> entry:nameMap.entrySet()) {
//				clsId=entry.getKey();
//				break;
//			}
//		}
//		int[] count=service.countBySex(clsId);
//		//获取班级信息
//		Clasz cls=service.getClasz(clsId);
//		request=(Map)context.get("request");
//		request.put("nameMap", nameMap);
//		request.put("count", count);
//		request.put("cls", cls);
////		request.setAttribute("nameMap", nameMap);
////		request.setAttribute("count", count);
////		request.setAttribute("cls", cls);
//		return super.execute();
//	}
//	public Integer getClsId() {
//		return clsId;
//	}
//	public void setClsId(Integer clsId) {
//		this.clsId = clsId;
//	}
	
	


}
