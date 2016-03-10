/* ***************************************************
	^> File Name: Register.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 21:47:44
*************************************************** */
package chatroom.login;
import chatroom.user.User;
import chatroom.data.Data;
import java.util.Scanner;
import java.io.PrintWriter;
public class Register
{
	User user;
	Scanner sin=null;
	PrintWriter sout=null;
	public Register(User user)
	{
		this.user=user;
		sin=user.getScanner();
		sout=user.getPrintWriter();
	}
	private String getUsername()
	{
		String username=null;
		try
		{
			while(true)
			{
				username=Username.getUsername(sin,sout);
				if(Data.existsUsername(username))
				{
					sout.println("用户名存在或错误，请重输，");
				}
				else
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			username=null;
		}
		return username;
	}
	private boolean weakPassword(String password)
	{
		boolean weak=false;
		if(password.isEmpty())
		{
			weak=true;
		}
		return weak;
	}
	public String getPassword()
	{
		String password=null;
		try
		{
			while(true)
			{
				password=Password.getPassword(sin,sout);
				if(weakPassword(password))
				{
					sout.println("密码强度太低，请重输，");
					continue;
				}
				sout.println("请再输一遍，");
				String reenter=Password.getPassword(sin,sout);
				if(!reenter.equals(password))
				{
					sout.println("密码不一致，请重输，");
				}
				else
				{
					break;
				}
			}
		}
		catch(Exception e)
		{
			password=null;
		}
		return password;
	}
	public boolean register()
	{
		boolean registered=false;
		String username,password;
		username=getUsername();
		if(username==null)
		{
			return false;
		}
		password=getPassword();
		if(password==null)
		{
			return false;
		}
		registered=Data.register(username,password);
		user.setUserData(username);
		return registered;
	}
}
