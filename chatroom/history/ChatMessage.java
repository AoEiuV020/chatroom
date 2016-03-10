/* ***************************************************
	^> File Name: ChatMessage.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 09:32:31
*************************************************** */
package chatroom.history;
import chatroom.data.Database;
import chatroom.log.Logger;
import java.sql.*;
public abstract class ChatMessage
{
	private static PreparedStatement preparedStatement=null;
	public static int add(int userId,String roomName,String message)
	{
		int messageId=0;
		try
		{
			if(preparedStatement==null)
			{
				String sql="insert into chatmessage(userid,room,message) value(?,?,?)";
				preparedStatement=Database.getInstance().getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			}
			preparedStatement.setInt(1,userId);
			preparedStatement.setString(2,roomName);
			preparedStatement.setString(3,message);
			preparedStatement.executeUpdate();
			ResultSet resultSet=preparedStatement.getGeneratedKeys();
			if(resultSet.next())
			{
				messageId=resultSet.getInt(1);
			}
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return messageId;
	}
}
