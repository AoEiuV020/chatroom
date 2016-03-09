/* ***************************************************
	^> File Name: UserDataReader.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 15:49:51
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.*;
public abstract class UserDataReader
{
	public static UserData getUserDataFromUsername(String username)
	{
		String sql="select * from user,userdata where user.id=userdata.id&&username='"+username+"'";
		return getUserData(sql);
	}
	public static UserData getUserDataFromId(int id)
	{
		String sql="select * from user,userdata where user.id=userdata.id&&user.id="+id+"";
		return getUserData(sql);
	}
	public static int getIdByUsername(String username)
	{
		try
		{
			String sql="select id from user where username='"+username+"'";
			Statement statement=Database.getInstance().getStatement();
			ResultSet resultSet=statement.executeQuery(sql);
			int id=0;
			if(resultSet.next())
			{
				id=resultSet.getInt("id");
			}
			return id;
		}
		catch(Exception e)
		{
			throw new RuntimeException(e);
		}
	}
	private static UserData getUserData(String sql)
	{
		UserData userData=new UserData();
		Statement statement=Database.getInstance().getStatement();
		try
		{
			ResultSet resultSet=statement.executeQuery(sql);
			if(resultSet.next())
			{
				userData.setId(resultSet.getInt("id"));
				userData.setUsername(resultSet.getString("username"));
				userData.setNickname(resultSet.getString("nickname"));
				String sex=resultSet.getString("sex");
				userData.setSex(sex);
				userData.setAge(resultSet.getInt("age"));
				userData.setCountry(resultSet.getString("country"));
			}
			resultSet.close();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
			throw new RuntimeException("sqlexception"+e);
		}
		finally
		{
			try
			{
				if(statement!=null)
				{
					statement.close();
				}
			}
			catch(SQLException e)
			{
				Logger.exception(e);
			}
		}
		return userData;
	}
}
