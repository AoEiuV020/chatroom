/* ***************************************************
	^> File Name: Username.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 10:55:36
*************************************************** */
package chatroom.login;
import java.util.Scanner;
import java.io.PrintWriter;
public class Username extends UnsupportedCharFilter
{
	private Username()
	{
	}
	public static String getUsername(Scanner sin,PrintWriter sout)
	{
		sout.println("请输入用户名，");
		String username=null;
		boolean flag=true;
		while(flag)
		{
			username=sin.nextLine();
			if(username.isEmpty())
			{
				sout.println("不能为空，请重新输入，");
				continue;
			}
			flag=false;
			for(char ch:username.toCharArray())
			{
				if(filter(ch))
				{
					sout.println("字符"+ch+"不允许，请重新输入，");
					flag=true;
					break;
				}
			}
		}
		return username;
	}
}
