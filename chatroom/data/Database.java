/* ***************************************************
	^> File Name: Database.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 08:53:54
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.*;
public abstract class Database
{
	String host=null;
	int port;
	String username=null;
	String password=null;
	String database=null;
	String url=null;
	static Database instance=null;
	static Connection connection=null;
	public static void setInstance(Database database)
	{
		instance=database;
	}
	public static Database getInstance()
	{
		if(instance==null)
		{
			instance=new Mysql();
		}
		return instance;
	}
	public Connection getConnection()
	{
		if(connection==null)
		{
			try
			{
				connection=DriverManager.getConnection(url);
			}
			catch(SQLException e)
			{
				Logger.exception(e);
			}
		}
		return connection;
	}
	public Statement getStatement()
	{
		Statement statement=null;
		Connection connection=null;
		try
		{
			connection=getConnection();
			if(connection!=null)
			{
				statement=connection.createStatement();
			}
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return statement;
	}
	public Statement getPreparedStatement(String sql)
	{
		PreparedStatement preparedStatement=null;
		Connection connection=null;
		try
		{
			connection=getConnection();
			if(connection!=null)
			{
				preparedStatement=connection.prepareStatement(sql);
			}
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return preparedStatement;
	}
	public static String getEncoding()
	{
		return "utf8";
	}
	public int executeUpdate(String sql)
	{
		Statement statement=getStatement();
		int num=0;
		try
		{
			num=statement.executeUpdate(sql);
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
		return num;
	}
}
