package action;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.util.ServletContextAware;

import service.StudentService;
import service.StudentServiceImp;
import bean.Clasz;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MainAction3 extends ActionSupport implements ServletContextAware,
		ServletRequestAware {
	
	private StudentService service=new StudentServiceImp();
	private HttpServletRequest request;
	private Integer clsId;
	
	private ActionContext context;


	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request=request;

	}

	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		this.context=context;

	}

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
		System.out.println("测试"+clsId);
		this.clsId = clsId;
	}
	
	

}
