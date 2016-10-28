package action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import service.LoginService;
import service.LoginServiceImpl;
import bean.User;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 登录
 * @author 61933
 *
 */
public class LoginAction extends ActionSupport implements SessionAware{
	//业务逻辑
		private LoginService service=new LoginServiceImpl();
		
		//根据jsp生成相应的属性  必须和jsp中的name相同  一定要有set get方法
        private String username;//用户名 
		private String password;//密码
        private User user=new User();
        
		private Integer auto_load;//自动登录参数
		
		private int log_res;//登录结果
        private Map session;

		@Override
		public String execute() throws Exception {
			// 调用父类的execute方法
			//System.out.println("username="+username+"password="+password);
			//return "registerSuccess";
			//User user=new User();
			//user.setName(username);
			//user.setPassword(password);
			
			
			if(auto_load!=null && auto_load==1)
			{
				//设置cookie
				Cookie cookie=new Cookie("user", user.getName()+","+user.getPassword());
				cookie.setPath("/");
				cookie.setMaxAge(60*60);
				//action获取response
				HttpServletResponse response = ServletActionContext.getResponse();
				//cookie要放在response中  因为cookie是要返回的
				response.addCookie(cookie);
			}

			
			
			log_res=service.login(user);
			
			if(log_res==0)
			{
				session.put("user", user);
				return "log1";
			}
			else
				return "log2";
		
			
		}
		
		
		

		public User getUser() {
			return user;
		}




		public void setUser(User user) {
			this.user = user;
		}




		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getLog_res() {
			return log_res;
		}

		public void setLog_res(int log_res) {
			this.log_res = log_res;
		}
		
		
		




		public Integer getAuto_load() {
			return auto_load;
		}




		public void setAuto_load(Integer auto_load) {
			this.auto_load = auto_load;
		}




		@Override
		public void setSession(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
			this.session=arg0;
		}
		
		

}
