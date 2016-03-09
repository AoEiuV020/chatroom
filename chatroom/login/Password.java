/* ***************************************************
	^> File Name: Password.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 18:17:03
*************************************************** */
package chatroom.login;
import java.util.Scanner;
import java.io.PrintWriter;
public class Password extends UnsupportedCharFilter
{
	private Password()
	{
	}
	public static String getPassword(Scanner sin,PrintWriter sout)
	{
		sout.println("请输入密码，");
		String password=null;
		boolean flag=true;
		while(flag)
		{
			password=sin.nextLine();
			if(password.isEmpty())
			{
				sout.println("不能为空，请重新输入，");
				continue;
			}
			flag=false;
			for(char ch:password.toCharArray())
			{
				if(filter(ch))
				{
					sout.println("字符"+ch+"不允许，请重新输入，");
					flag=true;
					break;
				}
			}
		}
		return password;
	}
}
