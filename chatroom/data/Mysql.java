/* ***************************************************
	^> File Name: Mysql.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 08:56:48
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
public class Mysql extends Database
{
	static
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(Exception e)
		{
			Logger.exception(e);
		}
	}
	public Mysql()
	{
		String password=null;
		try
		{
			BufferedReader reader=new BufferedReader(new FileReader("password"));
			password=reader.readLine();
			reader.close();
		}
		catch(Exception e)
		{
			Logger.exception(e);
		}
		init("mysql.aoeiuv020.top",3306,"chatroom","chatroom",password);
	}
	private void init(String host,int port,String username,String password,String database)
	{
		this.host=host;
		this.port=port;
		this.username=username;
		this.password=password;
		this.database=database;
		String urlFormat="jdbc:mysql://%s:%d/%s?user=%s&password=%s&useUnicode=true&characterEncoding="+Database.getEncoding();
		this.url=String.format(urlFormat,host,port,database,username,password);
	}
	public Mysql(String host,int port,String username,String password,String database)
	{
		init(host,port,username,password,database);
	}
}
