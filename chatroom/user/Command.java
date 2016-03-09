/* ***************************************************
	^> File Name: Command.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 16:19:03
*************************************************** */
package chatroom.user;
import chatroom.log.Logger;
import java.util.Arrays;
import java.io.CharArrayWriter;
import java.io.PrintWriter;
public abstract class Command
{
	public static boolean execute(String line,UserControllar userControllar)
	{
		boolean flag=true;
		line=line.substring(1);
		String[] arr=line.split(" ");
		if(arr.length>2)
		{
			String command=arr[0].toLowerCase();
			String arg1=arr[1];
			String arg2=arr[2];
			if(command.equals("talkto"))
			{
				userControllar.talkTo(arg1,arg2);
			}
			else
			{
				flag=false;
			}
		}
		else if(arr.length>1)
		{
			String command=arr[0].toLowerCase();
			String arg=arr[1];
			if(command.equals("setnickname"))
			{
				userControllar.setNickname(arg);
			}
			else if(command.equals("makefriend"))
			{
				userControllar.makeFriendByUsername(arg);
			}
			else if(command.equals("setsex"))
			{
				userControllar.setSex(arg);
			}
			else if(command.equals("setage"))
			{
				userControllar.setAge(arg);
			}
			else if(command.equals("searchfromusername"))
			{
				userControllar.searchFromUsername(arg);
			}
			else if(command.equals("searchfromid"))
			{
				userControllar.searchFromId(arg);
			}
			else if(command.equals("setcountry"))
			{
				userControllar.setCountry(arg);
			}
			else if(command.equals("enter"))
			{
				userControllar.enterRoom(arg);
			}
			else
			{
				flag=false;
			}
		}
		else if(arr.length>0)
		{
			String command=arr[0].toLowerCase();
			if(command.equals("help"))
			{
				userControllar.help(help());
			}
			else if(command.equals("owndata"))
			{
				userControllar.ownData();
			}
			else if(command.equals("setalldata"))
			{
				userControllar.setAllData();
			}
			else if(command.equals("friend"))
			{
				userControllar.TwoWayFriend();
			}
			else if(command.equals("twowayfriend"))
			{
				userControllar.TwoWayFriend();
			}
			else if(command.equals("onlinefriend"))
			{
				userControllar.onlineFriend();
			}
			else if(command.equals("onewayfriend"))
			{
				userControllar.OneWayFriend();
			}
			else if(Arrays.asList(new String[]{"exit","quit"}).contains(command))
			{
				userControllar.exit();
			}
			else
			{
				flag=false;
			}
		}
		else
		{
			flag=false;
		}
		return flag;
	}
	private static String help()
	{
		//java不支持raw字符串，
		//为了一行一行的效果，就绕了下，
		CharArrayWriter charArrayWriter=new CharArrayWriter();
		PrintWriter out=new PrintWriter(charArrayWriter);
		out.println("/help 帮助");
		out.println("/owndata 看自己的信息");
		out.println("/setalldat 所有设置");
		out.println("/friend 列出所有好友");
		out.println("/onewayfriend 列出所有包括单向好友");
		out.println("/onlineFriend 列出在线好友");
		out.println("/exit or quit 退出");
		out.println("/setnickname <name> 设置昵称");
		out.println("/makefriend <friendname> 添加好友");
		out.println("/enter <name> 进入房间");
		//out.println("/list");
		//out.println("/leave");
		//out.println("/create <room>");
		//out.println("/enter <room>");
		return charArrayWriter.toString();
	}
}
