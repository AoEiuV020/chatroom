/* ***************************************************
	^> File Name: UserChatHistory.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 09:30:46
*************************************************** */
package chatroom.history;
import chatroom.data.Database;
import chatroom.log.Logger;
import java.sql.*;
import java.util.Set;
import java.util.LinkedHashSet;
public abstract class UserChatHistory
{
	private static PreparedStatement addPreparedStatement=null;
	private static PreparedStatement getPreparedStatement=null;
	private static PreparedStatement roomPreparedStatement=null;
	public static Set<String> listRoom(int userId)
	{
		Set<String> set=new LinkedHashSet<String>();
		try
		{
			if(addPreparedStatement==null)
			{
				String sql="select m.room from chatmessage as m join userchathistory as h on m.id=h.messageid where h.id=?";
				getPreparedStatement=Database.getInstance().getConnection().prepareStatement(sql);
			}
			getPreparedStatement.setInt(1,userId);
			ResultSet result=getPreparedStatement.executeQuery();
			while(result.next())
			{
				String roomName=result.getString("room");
				set.add(roomName);
			}
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return set;
	}
	public static Set<Message> get(int userId,String roomName)
	{
		Set<Message> set=new LinkedHashSet<Message>();
		try
		{
			if(addPreparedStatement==null)
			{
				String sql="select m.userid,m.time,m.message from chatmessage as m join userchathistory as h on m.id=h.messageid where h.id=?&&m.room=?";
				getPreparedStatement=Database.getInstance().getConnection().prepareStatement(sql);
			}
			getPreparedStatement.setInt(1,userId);
			getPreparedStatement.setString(2,roomName);
			ResultSet result=getPreparedStatement.executeQuery();
			while(result.next())
			{
				Message m=new Message();
				m.setUserId(result.getInt("userid"));
				m.setTimestamp(result.getTimestamp("time"));
				m.setMessage(result.getString("message"));
				set.add(m);
			}
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return set;
	}
	public static void add(int userId,int messageId)
	{
		if(messageId==0)
		{
			return;
		}
		try
		{
			if(addPreparedStatement==null)
			{
				String sql="insert into userchathistory(id,messageid) value (?,?)";
				addPreparedStatement=Database.getInstance().getConnection().prepareStatement(sql);
			}
			addPreparedStatement.setInt(1,userId);
			addPreparedStatement.setInt(2,messageId);
			addPreparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
	}
}
