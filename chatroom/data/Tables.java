/* ***************************************************
	^> File Name: Tables.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 11:30:19
*************************************************** */
package chatroom.data;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;
public abstract class Tables
{
	static Map<String,String> tables;
	public static void create()throws Exception
	{
		Statement statement=Database.getInstance().getStatement();
		mapInit();
		String encoding=Database.getEncoding();
		for(Map.Entry<String,String> table:tables.entrySet())
		{
			String tableName=table.getKey();
			String tableColumns=table.getValue();
			String sql="create table if not exists "+tableName+"("+tableColumns+")charset="+encoding;
			statement.executeUpdate(sql);
		}
	}
	private static void mapInit()
	{
		tables=new HashMap<String,String>();
		tables.put("user",
				"id int unsigned not null auto_increment,"+
				"username char(22) binary not null ,"+
				"password char(45) not null,"+
				"primary key(id),"+
				"unique (username)");
		tables.put("userdata",
				"id int unsigned not null,"+//本该是外键，指向user中的id，
				"nickname char(22),"+
				"country char(6),"+
				"sex enum('man','woman'),"+
				"age int,"+
				"primary key(id)");
		tables.put("friend",
				"id int unsigned not null,"+//本该是外键，指向user中的id，
				"friendid int unsigned not null,"+//本该是外键，指向user中的id，
				"primary key(id,friendid)");
		tables.put("userchathistory",
				"id int unsigned not null,"+//本该是外键，指向user中的id，
				"messageid int unsigned not null,"+//本该是外键，指向chatmessage中的id，
				"primary key(id,messageid)");
		tables.put("chatmessage",
				"id int unsigned not null auto_increment,"+
				"userid int unsigned not null,"+//本该是外键，指向user中的id，
				"time timestamp ,"+
				"room varchar(22) not null,"+
				"message varchar(1000) not null,"+
				"primary key(id)");
	}
}
