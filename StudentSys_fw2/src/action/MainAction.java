package action;

import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import service.StudentService;
import service.StudentServiceImp;
import bean.Clasz;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 主页面
 * @author wangchunli
 *
 */
public class MainAction extends ActionSupport {
	private StudentService service=new StudentServiceImp();
	private HttpServletRequest request;
	
	private Integer clsId;

	@Override
	public String execute() throws Exception {
		//获取request
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
	
	
	

}
