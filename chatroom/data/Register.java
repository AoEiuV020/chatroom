/* ***************************************************
	^> File Name: Register.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 09:48:50
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.Statement;
import java.sql.SQLException;
public class Register
{
	public static boolean register(String username,String password)
	{
		Statement statement=Database.getInstance().getStatement();
		boolean registered=true;
		try
		{
			String sqlFormat="insert into user (username,password) value ('%s',password('%s'))";
			String sql=String.format(sqlFormat,username,password);
			statement.executeUpdate(sql);
			UserDataWriter.newUserData(username);
		}
		catch(SQLException e)
		{
			Logger.exception(e);
			registered=false;
		}
		finally
		{
			try
			{
				if(statement==null)
				{
					statement.close();
				}
			}
			catch(SQLException e)
			{
				Logger.exception(e);
			}
		}
		return registered;
	}
}
