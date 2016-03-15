/* ***************************************************
	^> File Name: Server.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:04:17
*************************************************** */
package chatroom.server;
import chatroom.log.Logger;
import chatroom.data.Data;
import chatroom.data.Database;
import chatroom.data.Mysql;
import java.util.Scanner;
public class Server
{
	static String host;
	static int port;
	static ConnectionListener listener;
	public static void main(String[] args) throws Exception
	{
		host="localhost";
		port=45678;
		getopt(args);
		Database.setInstance(new Mysql("localhost",3307,"root","usbw","chatroom"));
		//Database.setInstance(new Mysql());
		listener=new ConnectionListener(host,port);
		listener.setDaemon(true);
		listener.start();
		try
		{
			Data.createTables();
		}
		catch(Exception e)
		{
			Logger.exception(e);
		}
		try
		{
			Scanner stdin=new Scanner(System.in);
			while(true)
			{
				String line=stdin.nextLine();
				if(line.equals("exit"))
				{
					listener.exit();
					break;
				}
			}
		}
		catch(Exception e)
		{
		}
	}
	private static void getopt(String[] args)
	{
		int len=args.length;
		if(len>1)
		{
			try
			{
				port=Integer.parseInt(args[1]);
			}
			catch(Exception e)
			{
			}
		}
		if(len>0)
		{
			host=args[0];
		}
	}
}
