/* ***************************************************
	^> File Name: Room.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:50:15
*************************************************** */
package chatroom.room;
import chatroom.user.User;
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
		if(name.equals("hall"))
		{
			return;
		}
		for(User otherUser:users)
		{
			if(otherUser==user)
			{
				continue;
			}
			otherUser.receive(user,string);
		}
	}
}
