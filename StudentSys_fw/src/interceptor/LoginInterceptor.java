package interceptor;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import bean.User;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * 自定义过滤器进行登录验证
 * @author wangchunli
 *
 */
public class LoginInterceptor extends AbstractInterceptor {
	


	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		
		ActionContext context=arg0.getInvocationContext();
		// 获取session对象
//		Map session=context.getSession();
//		//获取user对象
//		User user=(User) session.get("user");
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User)session.getAttribute("user");
		if(user!=null)
		{
			//不拦截，直接过去
			return arg0.invoke();
		}
		//否则拦截，直接到登录页面
		return "login";
	}

}
