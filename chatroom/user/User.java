/* ***************************************************
	^> File Name: User.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:34:39
*************************************************** */
package chatroom.user;
import chatroom.data.UserData;
import chatroom.room.RoomMedium;
import chatroom.log.Logger;
import chatroom.friend.Friend;
import chatroom.online.OnlineSet;
import java.net.Socket;
import java.util.Set;
import java.util.HashSet;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
public class User
{
	Socket socket;
	UserControllar userControllar;
	UserData userData;
	RoomMedium roomMedium;
	Scanner sin=null;
	PrintWriter sout=null;
	public User(Socket socket)
	{
		this.socket=socket;
		try
		{
			String chatset="UTF-8";
			sin=new Scanner(socket.getInputStream(),chatset);
			sout=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),chatset),true);
		}
		catch(IOException e)
		{
			Logger.exception(e);
			exit();
		}
		userControllar=new UserControllar(this);
		userControllar.start();
	}
	public Scanner getScanner()
	{
		return sin;
	}
	public PrintWriter getPrintWriter()
	{
		return sout;
	}
	public void setRoomMedium(RoomMedium roomMedium)
	{
		this.roomMedium=roomMedium;
	}
	public Socket getSocket()
	{
		return socket;
	}
	public void exit()
	{
		OnlineSet.remove(this);
		//正常不会等于null，但是有可能User初始化就失败了，
		UserControllar t=userControllar;
		userControllar=null;
		if(t!=null)
		{
			t.exit();
		}
		if(roomMedium!=null)
		{
			roomMedium.leave(this);
		}
		if(socket!=null)
		{
			try
			{
				socket.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void broadcast(String string)
	{
		roomMedium.broadcast(this,string);
	}
	public void receive(User remoteUser,String string)
	{
		userControllar.receive(remoteUser,string);
	}
	@Override
	public String toString()
	{
		String name=null;
		try
		{
			if(userData!=null)
			{
				name=userData.getNickname();
				if(name==null)
				{
					name=userData.getUsername();
				}
			}
			if(name==null)
			{
				if(socket!=null)
				{
					String host=socket.getInetAddress().getHostAddress();
					int port=socket.getPort();
					name=String.format("[%s:%d]",host,port);
				}
				else
				{
					name="null";
				}
			}
		}
		catch(NullPointerException e)
		{
			return "null";
		}
		return name;
	}
	public void setNickname(String nickname)
	{
		userData.setNickname(nickname);
		userData.update();
	}
	public void setSex(String sex)
	{
		userData.setSex(sex);
		userData.update();
	}
	public void setAge(int age)
	{
		userData.setAge(age);
		userData.update();
	}
	public void setCountry(String country)
	{
		userData.setCountry(country);
		userData.update();
	}
	public void setUsername(String string)
	{
		userData=UserData.getUserDataFromUsername(string);
	}
	public boolean createRoom(String string)
	{
		boolean flag=roomMedium.createRoom(this,string);
		if(!flag)
		{
			sout.println("### 房间"+string+" 已存在 ###");
		}
		return flag;
	}
	public void enterRoom(String string)
	{
		boolean flag=roomMedium.enterRoom(this,string);
		if(!flag)
		{
			sout.println("### 房间"+string+" 不存在 ###");
		}
	}
	public Set<String> getRoomsSet()
	{
		return roomMedium.getRoomsSet();
	}
	public void leave()
	{
		String username=userData.getUsername();
		if(username!=null)
		{
			enterRoom(username);
		}
		enterRoom("hall");
	}
	public UserData getUserData()
	{
		return userData;
	}
	public void setUserData(UserData userData)
	{
		this.userData=userData;
	}
	public void setUserData(String username)
	{
		this.userData=UserData.getUserDataFromUsername(username);
	}
	public void setUserData(int id)
	{
		this.userData=UserData.getUserDataFromId(id);
	}
	public void makeFriendByUsername(String username)
	{
		int friendId=UserData.getIdByUsername(username);
		if(friendId!=0)
		{
			Friend.makeFriend(userData.getId(),friendId);
		}
		else
		{
			Logger.log(""+"make friend error,"+username+" not exists,");
		}
	}
	public void TwoWayFriend()
	{
		Set<Integer> list=Friend.getTwoWayFriendSet(userData.getId());
		listFriend(list);
	}
	private void listFriend(Set<Integer> list)
	{
		for(int friendId:list)
		{
			sout.println(UserData.getUserDataFromId(friendId));
		}
	}
	public void OneWayFriend()
	{
		Set<Integer> list=Friend.getOneWayFriendSet(userData.getId());
		listFriend(list);
	}
	public void onlineFriend()
	{
		Set<Integer> list=Friend.getTwoWayFriendSet(userData.getId());
		Set<Integer> onlineSet=new HashSet<Integer>();
		for(int friendId:list)
		{
			if(OnlineSet.isOnline(friendId))
			{
				onlineSet.add(friendId);
			}
		}
		listFriend(onlineSet);
	}
	public int getId()
	{
		return userData.getId();
	}
	public String getUsername()
	{
		return userData.getUsername();
	}
	public void talkTo(String username,String token)
	{
		int friendId=UserData.getIdByUsername(username);
		if(!Friend.isFriend(getId(),friendId))
		{
			sout.println("### "+username+" 不是你的好友 ###");
		}
		else if(!OnlineSet.isOnline(friendId))
		{
			sout.println("### "+username+" 不在线 ###");
		}
		else if(createRoom(token))
		{
			User friend=OnlineSet.getUser(friendId);
			friend.talkRequest(this,token);
		}
	}
	public void talkRequest(User user,String token)
	{
		warning(""+user+"请求与你聊天，房间名:"+token);
	}
	public void warning(String string)
	{
		sout.println(string);
	}
}
