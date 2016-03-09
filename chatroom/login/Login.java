/* ***************************************************
	^> File Name: Login.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 15:54:32
*************************************************** */
package chatroom.login;
import chatroom.user.User;
import chatroom.online.OnlineSet;
import chatroom.log.Logger;
import java.util.Scanner;
import java.io.PrintWriter;
public class Login
{
	User user=null;
	Scanner sin=null;
	PrintWriter sout=null;
	boolean logged=false;
	SignIn signIn;
	Register register;
	public Login(User user)
	{
		this.user=user;
		sin=user.getScanner();
		sout=user.getPrintWriter();
		signIn=new SignIn(user);
		register=new Register(user);
	}
	private void signIn()
	{
		logged=signIn.signIn();
		if(logged)
		{
			sout.println("登录成功");
			Logger.login(user);
		}
		else
		{
			sout.println("登录失败");
		}
	}
	private void register()
	{
		logged=register.register();
		if(logged)
		{
			sout.println("注册成功");
			Logger.register(user);
		}
		else
		{
			sout.println("注册失败");
		}
	}
	private void forget()
	{
		sout.println("忘了密码找管理员，");
	}
	private void mainMenu()
	{
		sout.println("1,登录");
		sout.println("2,注册");
		sout.println("3,忘记密码");
		int choise=Choise.getChoise(1,3,sin,sout);
		switch(choise)
		{
			case 1:
				signIn();
				break;
			case 2:
				register();
				break;
			case 3:
				forget();
				break;
		}
	}
	public boolean login()
	{
		mainMenu();
		return logged;
	}
}
