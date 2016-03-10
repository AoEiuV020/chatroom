/* ***************************************************
	^> File Name: Room.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:50:15
*************************************************** */
package chatroom.room;
import chatroom.user.User;
import chatroom.history.UserChatHistory;
import chatroom.history.ChatMessage;
import java.util.Set;
import java.util.LinkedHashSet;
public class Room
{
	Set<User> users;
	String name;
	public Room(String name)
	{
		this.name=name;
		users=new LinkedHashSet<User>();
	}
	public boolean add(User user)
	{
		return users.add(user);
	}
	public boolean leave(User user)
	{
		return users.remove(user);
	}
	public String getName()
	{
		return name;
	}
	public void broadcast(User user,String string)
	{
		String rname=name;
		if(name!=null)
		{
			if(name.equals("hall"))
			{
				return;
			}
			if(name.length()>20)
			{
				rname=rname.substring(0,20);
			}
		}
		int messageId=ChatMessage.add(user.getId(),rname,string);
		for(User otherUser:users)
		{
			UserChatHistory.add(otherUser.getId(),messageId);
			if(otherUser==user)
			{
				continue;
			}
			otherUser.receive(user,string);
		}
	}
}
