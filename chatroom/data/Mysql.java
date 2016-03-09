/* ***************************************************
	^> File Name: Mysql.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 08:56:48
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
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
		this("mysql.aoeiuv020.top",3306,"chatroom","chatroom","chatroom");
	}
	public Mysql(String host,int port,String username,String password,String database)
	{
		this.host=host;
		this.port=port;
		this.username=username;
		this.password=password;
		this.database=database;
		String urlFormat="jdbc:mysql://%s:%d/%s?user=%s&password=%s&useUnicode=true&characterEncoding="+Database.getEncoding();
		this.url=String.format(urlFormat,host,port,database,username,password);
	}
}
