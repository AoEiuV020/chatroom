/* ***************************************************
	^> File Name: UserControllar.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 16:35:31
*************************************************** */
package chatroom.user;
import chatroom.data.UserData;
import chatroom.data.Country;
import chatroom.login.Login;
import chatroom.login.LoginException;
import chatroom.log.Logger;
import chatroom.online.OnlineSet;
import chatroom.history.UserChatHistory;
import chatroom.history.Message;
import chatroom.history.LocalDateTime;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.NoSuchElementException;
public class UserControllar extends Thread
{
	User user;
	Socket socket;
	Scanner sin;
	PrintWriter sout;
	boolean isRun;
	public UserControllar(User user)
	{
		this.user=user;
		this.socket=this.user.getSocket();
		this.sin=this.user.getScanner();
		this.sout=this.user.getPrintWriter();
		isRun=true;
	}
	@Override
	public void run()
	{
		try
		{
			Login login=new Login(user);
			if(!login.login())
			{
				throw new LoginException();
			}
			OnlineSet.add(user);
			sout.println("输入/help看帮助，");
			while(isRun)
			{
				String line=sin.nextLine();
				if(isCommand(line))
				{
					if(!Command.execute(line,this))
					{
						sout.println("### 未知命令 ###");
					}
				}
				else
				{
					send(line);
				}
			}
		}
		catch(LoginException e)
		{
			Logger.loginException(e);
			if(user!=null)
			{
				user.exit();
				user=null;
			}
		}
		catch(Exception e)
		{
			if(e instanceof NoSuchElementException)
			{
				Logger.log("force close "+user);
			}
			else
			{
				Logger.exception(e);
			}
			//OnlineSet.remove(user.getUserData().getId());
			if(user!=null)
			{
				user.exit();
				user=null;
			}
		}
	}
	public void exit()
	{
		//OnlineSet.remove(user.getUserData().getId());
		isRun=false;
		User t=user;
		user=null;
		if(t!=null)
		{
			t.exit();
		}
		try
		{
			socket.close();
		}
		catch(Exception e)
		{
			Logger.exception(e);
		}
	}
	public void receive(LocalDateTime time,String username,String string)
	{
		sout.println(String.format("(%s)[%s]%s",time.toString(),username,string));
	}
	public void receive(LocalDateTime time,User remoteUser,String string)
	{
		receive(time,remoteUser.toString(),string);
	}
	public void receive(User remoteUser,String string)
	{
		receive(LocalDateTime.now(),remoteUser,string);
	}
	private boolean isCommand(String string)
	{
		if(string.isEmpty())
		{
			return false;
		}
		return string.charAt(0)=='/';
	}
	private void send(String string)
	{
		user.broadcast(string);
	}
	public void help(String help)
	{
		sout.print(help);
		sout.flush();
	}
	public void listRooms()
	{
		/*
		int i=0;
		for(String string:user.getRoomsSet())
		{
			sout.print(""+string+",");
			if(++i%5==0)
			{
				sout.println("");
			}
		}
		sout.println("");
		*/
		unsupported();
	}
	public void leave()
	{
		user.leave();
	}
	public void setNickname(String nickname)
	{
		user.setNickname(nickname);
	}
	public void setSex(String sex)
	{
		if(sex.equals("man")||sex.equals("woman"))
		{
			user.setSex(sex);
		}
		else if(sex.equals("null"))
		{
			user.setSex(null);
		}
		else
		{
			sout.println("### 性别只能是man或woman ###");
		}
	}
	public void setAge(String age)
	{
		try
		{
			int a=Integer.parseInt(age);
			if(a<=0)
			{
				throw new NumberFormatException();
			}
			user.setAge(a);
		}
		catch(NumberFormatException e)
		{
			sout.println("### 数字格式不对 ###");
		}
	}
	public void setCountry(String country)
	{
		user.setCountry(country);
	}
	public void createRoom(String room)
	{
		for(char ch:room.toCharArray())
		{
			if(ch<'0'||(ch>'9'&&ch<'A')||(ch>'Z'&&ch<'a'&&ch!='_')||(ch>'z'&&ch<128))
			{
				sout.println("### 字符"+ch+"不支持 ###");
				return ;
			}
		}
		user.createRoom(room);
	}
	public void enterRoom(String room)
	{
		user.enterRoom(room);
	}
	private void unsupported()
	{
		throw new RuntimeException("### 已经不支持了的命令 ###");
	}
	public User getUser()
	{
		return user;
	}
	public void ownData()
	{
		sout.println(""+user.getUserData());
	}
	public void setCountry()
	{
		sout.println("请选择国家，");
		int i=0;
		for(Map.Entry<String,String> country:Country.getCountriesMap().entrySet())
		{
			sout.print(""+country.getKey()+":"+country.getValue()+",");
			if(++i%5==0)
			{
				sout.println("");
			}
		}
		sout.println("");
		String country=sin.nextLine();
		setCountry(Country.shortToFull(country));
	}
	public void setAllData()
	{
		sout.println("请输入昵称，");
		String nickname=sin.nextLine();
		setNickname(nickname);
		sout.println("请输入年龄，");
		String age=sin.nextLine();
		setAge(age);
		sout.println("请选择性别，");
		sout.println("man:男");
		sout.println("woman:女");
		sout.println("null:保密");
		String sex=sin.nextLine();
		setSex(sex);
		setCountry();
		ownData();
	}
	public void searchFromUsername(String username)
	{
		sout.println(""+UserData.searchFromUsername(username));
	}
	public void searchFromId(String id)
	{
		try
		{
			int a=Integer.parseInt(id);
			if(a<=0)
			{
				throw new NumberFormatException();
			}
			sout.println(""+UserData.searchFromId(a));
		}
		catch(NumberFormatException e)
		{
			sout.println("### ID格式不对 ###");
		}
	}
	public void makeFriendByUsername(String username)
	{
		if(user.getUsername().equals(username))
		{
			sout.println("### 不能添加自己为好友 ###");
		}
		else
		{
			user.makeFriendByUsername(username);
		}
	}
	public void TwoWayFriend()
	{
		user.TwoWayFriend();
	}
	public void OneWayFriend()
	{
		user.OneWayFriend();
	}
	public void onlineFriend()
	{
		user.onlineFriend();
	}
	public void talkTo(String[] arr)
	{
		String token=null;
		if(arr.length>2)
		{
			token=arr[1];
			if(user.createRoom(token))
			{
				for(int i=2;i<arr.length;++i)
				{
					user.talkTo(arr[i],token);
				}
			}
			else
			{
				sout.println("### 房间"+token+"已经存在 ###");
			}
		}
	}
	public void history()
	{
		Set<String> set=UserChatHistory.listRoom(user.getId());
		int i=0;
		for(String roomName:set)
		{
			sout.print(roomName+",");
			if(++i%5==0)
			{
				sout.println("");
			}
		}
		sout.println("");
	}
	public void history(String roomName)
	{
		Set<Message> set=UserChatHistory.get(user.getId(),roomName);
		for(Message message:set)
		{

			LocalDateTime time=message.getTime();
			String username=UserData.getUsernameById(message.getUserId());
			String string=message.getMessage();
			//sout.println(String.format("(%s)[%s]%s",""+time,username,string));
			receive(time,username,string);
		}
	}
	public void changePassword()
	{
		sout.println("# 先输入旧密码验证身份再输入新密码 #");
		Login.changePassword(user);
	}
}
