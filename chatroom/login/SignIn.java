/* ***************************************************
	^> File Name: SignIn.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 17:52:04
*************************************************** */
package chatroom.login;
import chatroom.user.User;
import chatroom.data.Data;
import chatroom.online.OnlineSet;
import java.util.Scanner;
import java.io.PrintWriter;
public class SignIn
{
	User user;
	Scanner sin=null;
	PrintWriter sout=null;
	public SignIn(User user)
	{
		this.user=user;
		sin=user.getScanner();
		sout=user.getPrintWriter();
	}
	public boolean signIn()
	{
		boolean logged=false;
		String username,password;
		username=Username.getUsername(sin,sout);
		password=Password.getPassword(sin,sout);
		if(!Data.existsUsername(username))
		{
			logged=false;
			sout.println("用户不存在，");
		}
		else
		{
			if(!Data.checkPassword(username,password))
			{
				logged=false;
				sout.println("密码错误，");
			}
			else
			{
				logged=true;
			}
		}
		user.setUserData(username);
		/*
		if(OnlineSet.isOnline(user.getUserData().getId()))
		{
			sout.println("该用户已经登录，");
			logged=false;
		}
		*/
		return logged;
	}
}
