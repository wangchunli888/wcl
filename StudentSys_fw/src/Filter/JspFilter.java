package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;

public class JspFilter implements Filter {
	
	private String redirectUrl;//重定向Url地址
	private String passUrl;//直接通过的Url地址

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		redirectUrl=filterConfig.getInitParameter("redirectUrl");
		passUrl=filterConfig.getInitParameter("passUrl");

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		

		// TODO Auto-generated method stub
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String path = req.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		
		
		String servletPath=req.getServletPath();
		if(servletPath.equals(redirectUrl))
		{
			Cookie[] cookies = req.getCookies();

			if (cookies != null && cookies.length > 0) {
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("user")) {
						String value = cookie.getValue();
						String[] nv = value.split(",");
						User user = new User();
						user.setName(nv[0]);
						user.setPassword(nv[1]);

						HttpSession session = req.getSession();
						session.setAttribute("user", user);
					   
				
						
						resp.sendRedirect(basePath+"main/mainAction.action");
						
						return;
						

						
					}
				}
			}
			
			chain.doFilter(request, response);
			return;
		}
		String[] passes=passUrl.split(",");
		for(String pass:passes)
		{
			if(servletPath.equals(pass))
			{
				chain.doFilter(request, response);
				return;
			}
		}
		
      resp.sendRedirect(basePath+redirectUrl);
	

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
