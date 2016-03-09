/* ***************************************************
	^> File Name: ConnectionListener.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:09:42
*************************************************** */
package chatroom.server;
import chatroom.room.RoomMedium;
import java.net.*;
public class ConnectionListener extends Thread
{
	String host;
	int port;
	boolean isRun;
	ServerSocket serverSocket;
	RoomMedium roomMedium;
	public ConnectionListener(String host,int port)
	{
		this.host=host;
		this.port=port;
		this.isRun=true;
	}
	@Override
	public void run()
	{
		try
		{
			serverSocket=new ServerSocket(port,100,InetAddress.getByName(host));
			serverSocket.setReuseAddress(true);
			roomMedium=new RoomMedium();
			Socket socket=null;
			while(isRun)
			{
				socket=serverSocket.accept();
				roomMedium.addUser(socket);
			}
		}
		catch(SocketException e)
		{
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void exit()
	{
		isRun=false;
		this.interrupt();
		try
		{
			serverSocket.close();
		}
		catch(Exception e)
		{
		}
		roomMedium.exit();
	}
}
