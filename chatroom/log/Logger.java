/* ***************************************************
	^> File Name: Logger.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 19:47:54
*************************************************** */
package chatroom.log;
import chatroom.user.User;
import chatroom.login.LoginException;
import java.io.PrintWriter;
public class Logger
{
	private static PrintWriter log=new PrintWriter(System.out,true);
	private static PrintWriter loginLog=log;
	private static PrintWriter createRoomLog=log;
	private static PrintWriter registerLog=log;
	private static PrintWriter exceptionLog=log;
	private Logger()
	{
	}
	public static void loginException(LoginException e)
	{
		log.println(""+e);
	}
	public static void register(User user)
	{
		registerLog.println("register "+user);
	}
	public static void login(User user)
	{
		loginLog.println("login "+user);
	}
	public static void exception(Exception e)
	{
		e.printStackTrace(exceptionLog);
	}
	public static void log(String ...strings)
	{
		for(String string:strings)
		{
			log.print(string+",");
		}
		log.println("");
	}
	public static void createRoom(User user,String string)
	{
		createRoomLog.println(""+user+" create room "+string);
	}
}
