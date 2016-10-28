package interceptor;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import bean.User;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AotuloadInterceptor extends AbstractInterceptor {

	//private Map request;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		// TODO Auto-generated method stub
		// 获取request对象
		HttpServletRequest request = ServletActionContext.getRequest();

		// HttpServletResponse response=ServletActionContext.getResponse();

		Cookie[] cookies = request.getCookies();

		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user")) {
					String value = cookie.getValue();
					String[] nv = value.split(",");
					User user = new User();
					user.setName(nv[0]);
					user.setPassword(nv[1]);

					HttpSession session = request.getSession();
					session.setAttribute("user", user);
					
				}
			}
		}
		// 不拦截
		return arg0.invoke();
	}

}
