package action;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import service.StudentService;
import service.StudentServiceImp;
import bean.Clasz;

import com.opensymphony.xwork2.ActionSupport;

public class MainAction1 extends ActionSupport implements RequestAware {
	private StudentService service=new StudentServiceImp();
	private Map request;
	private Integer clsId;

	@Override
	public void setRequest(Map<String, Object> arg0) {
		this.request=arg0;

	}


	
	@Override
	public String execute() throws Exception {
		//request = ServletActionContext.getRequest();
		
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
		request.put("nameMap", nameMap);
		request.put("count", count);
		request.put("cls", cls);
//		request.setAttribute("nameMap", nameMap);
//		request.setAttribute("count", count);
//		request.setAttribute("cls", cls);
        return super.execute();
		
	}



	public Integer getClsId() {
		return clsId;
	}



	public void setClsId(Integer clsId) {
		this.clsId = clsId;
	}
		



	
	

}
