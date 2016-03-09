/* ***************************************************
	^> File Name: Check.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 12:23:19
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.*;
public class Check
{
	public static boolean existsUsername(String username)
	{
		Statement statement=Database.getInstance().getStatement();
		try
		{
			ResultSet resultSet=statement.executeQuery("select username from user");
			while(resultSet.next())
			{
				if(resultSet.getString("username").equals(username))
				{
					return true;
				}
			}
			resultSet.close();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
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
		return false;
	}
	public static boolean checkPassword(String username,String password)
	{
		password=encode(password);
		Statement statement=Database.getInstance().getStatement();
		try
		{
			ResultSet resultSet=statement.executeQuery("select password from user where username='"+username+"'");
			while(resultSet.next())
			{
				if(resultSet.getString("password").equals(password))
				{
					return true;
				}
			}
			resultSet.close();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
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
		return false;
	}
	private static String encode(String plaint)
	{
		String cipher=plaint;
		Statement statement=Database.getInstance().getStatement();
		try
		{
			ResultSet resultSet=statement.executeQuery("select password('"+plaint+"') as cipher");
			while(resultSet.next())
			{
				cipher=resultSet.getString("cipher");
			}
			resultSet.close();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
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
		return cipher;
	}
}
