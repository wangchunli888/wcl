package action;

import service.LoginService;
import service.LoginServiceImpl;
import bean.User;

import com.opensymphony.xwork2.ActionSupport;

public class RegisterAction extends ActionSupport {
	//业务逻辑
		private LoginService service=new LoginServiceImpl();
		
		//根据jsp生成相应的属性  必须和jsp中的name相同  一定要有set get方法
		private String email;//邮箱地址
		private String username;//用户名 
		private String password;//密码
		
		private int reg_res;//注册结果
		
		private int log_res;//登录结果
		

		@Override
		public String execute() throws Exception {
			// 调用父类的execute方法
			System.out.println("email="+email+"username="+username+"password="+password);
			//return "registerSuccess";
			User user=new User();
			user.setEmail(email);
			user.setName(username);
			user.setPassword(password);
		
			reg_res=service.register(user);
			log_res=service.login(user);
//			
//			return super.execute();
			
			if(log_res==0 && password.equals(user.getPassword()))
			{
				return "log1";
			}
			else if(log_res==1 && username.equals(user.getName()))
			{
				return "log2";
			}
			else
				return "log3";
		}


		public String getEmail() {
			return email;
		}


		public void setEmail(String email) {
			this.email = email;
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


		public int getReg_res() {
			return reg_res;
		}


		public void setReg_res(int reg_res) {
			this.reg_res = reg_res;
		}
		

}
