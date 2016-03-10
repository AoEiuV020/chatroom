/* ***************************************************
	^> File Name: Client.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 16:29:13
*************************************************** */
package client;
import java.net.Socket;
import java.util.Scanner;
import java.io.PrintWriter;
public class Client
{
	static String host;
	static int port;
	public static void main(String[] args)
	{
		host="localhost";
		port=45678;
		getopt(args);
		try
		{
			Socket socket=new Socket(host,port);
			PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
			Scanner scanner=new Scanner(socket.getInputStream());
			Scanner in=new Scanner(System.in);
			Thread listenThread=new Thread()
			{
				@Override
				public void run()
				{
					while(in.hasNextLine())
					{
						printWriter.println(in.nextLine());
						printWriter.flush();
					}
				}
			};
			listenThread.setDaemon(true);
			listenThread.start();
			while(scanner.hasNextLine())
			{
				System.out.println(scanner.nextLine());
			}
			printWriter.close();
			scanner.close();
			socket.close();
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
