/* ***************************************************
	^> File Name: FriendReader.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/08 - 15:34:38
*************************************************** */
package chatroom.friend;
import chatroom.data.Database;
import chatroom.log.Logger;
import java.sql.*;
import java.util.Set;
import java.util.LinkedHashSet;
public abstract class FriendReader
{
	public static Set<Integer> getTwoWayFriendSet(int id)
	{
		String sql="select a.friendid as friendid from friend as a join friend as b where a.friendid=b.id&&b.friendid=a.id"+"&&a.id="+id+"";
		return getFriendSet(sql);
	}
	public static Set<Integer> getOneWayFriendSet(int id)
	{
		String sql="select friendid from friend where id="+id+"";
		return getFriendSet(sql);
	}
	private static Set<Integer> getFriendSet(String sql)
	{
		Set<Integer> list=null;
		try
		{
			list=new LinkedHashSet<Integer>();
			Statement statement=Database.getInstance().getStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			while(resultSet.next())
			{
				list.add(resultSet.getInt("friendid"));
			}
			resultSet.close();
			statement.close();
		}
		catch(Exception e)
		{
			Logger.log(""+e);
		}
		return list;
	}
	public static boolean isFriend(int id,int friendId)
	{
		Set<Integer> set=getTwoWayFriendSet(id);
		if(set.contains(friendId))
		{
			return true;
		}
		return false;
	}
}
