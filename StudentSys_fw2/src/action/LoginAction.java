package action;

import java.util.Map;

import service.LoginService;
import service.LoginServiceImpl;
import bean.User;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 登录
 * @author 61933
 *
 */
public class LoginAction extends ActionSupport {
	//业务逻辑
	private LoginService service=new LoginServiceImpl();
	
	  private String username;//用户名 
	  private String password;//密码
	  private User user=new User();
	  private int log_res;//登录结果
      private Map session;
	@Override
	public String execute() throws Exception {
		log_res=service.login(user);
		
		if(log_res==0)
		{
			session.put("user", user);
			return "log1";
		}
		else
		{
			return "log2";
		}
	
		
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getLog_res() {
		return log_res;
	}
	public void setLog_res(int log_res) {
		this.log_res = log_res;
	}
      
      

}
