/* ***************************************************
	^> File Name: FriendWriter.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/08 - 14:41:07
*************************************************** */
package chatroom.friend;
import chatroom.data.Database;
import java.sql.*;
public abstract class FriendWriter
{
	public static void makeFriend(int id,int friendid)
	{
		String sql="insert into friend(id,friendid) value ("+id+","+friendid+")";
		Database.getInstance().executeUpdate(sql);
	}
}
