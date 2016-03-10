/* ***************************************************
	^> File Name: OnlineSet.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/08 - 14:19:12
*************************************************** */
package chatroom.online;
import chatroom.log.Logger;
import chatroom.user.User;
import java.util.Set;
import java.util.LinkedHashSet;
public abstract class OnlineSet
{
	static Set<User> list=new LinkedHashSet<User>();
	public static void add(User user)
	{
		Logger.online(user);
		offline(user.getId());
		list.add(user);
	}
	public static void remove(User user)
	{
		if(list.contains(user))
		{
			Logger.offline(user);
			list.remove(user);
		}
	}
	public static void offline(int id)
	{
		for(User user:list)
		{
			if(user.getId()==id)
			{
				remove(user);
				user.warning("#### 你在别处登陆，这边强制下线 ####");
				user.exit();
				break;
			}
		}
	}
	public static boolean isOnline(int id)
	{
		for(User user:list)
		{
			if(user.getId()==id)
			{
				return true;
			}
		}
		return false;
	}
	public static boolean isOnline(User user)
	{
		return list.contains(user);
	}
	public static User getUser(int id)
	{
		for(User user:list)
		{
			if(user.getId()==id)
			{
				return user;
			}
		}
		return null;
	}
	public static User getUser(String username)
	{
		for(User user:list)
		{
			if(user.getUsername().equals(username))
			{
				return user;
			}
		}
		return null;
	}
}
