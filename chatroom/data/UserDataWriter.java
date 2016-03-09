/* ***************************************************
	^> File Name: UserDataWriter.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/08 - 09:15:22
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.*;
public abstract class UserDataWriter
{
	public static void updateUserData(UserData userData)
	{
		int id=userData.getId();
		String nickname=userData.getNickname();
		String sex=getSex(userData);
		String age=getAge(userData);
		String country=userData.getCountry();
		country=Country.fullToShort(country);
		/*
		String sql="update userdata set nickname="+quote(nickname)+",sex="+quote(sex)+",age="+age+",country="+quote(country)+" where id="+id+"";
		Database.getInstance().executeUpdate(sql);
		*/
		String sql="update userdata set nickname=?,sex="+sex+",age="+age+",country=? where id="+id+"";
		Logger.log(sql);
		try
		{
			PreparedStatement preparedStatement=Database.getInstance().getConnection().prepareStatement(sql);
			preparedStatement.setString(1,nickname);
			preparedStatement.setString(2,country);
			preparedStatement.executeUpdate();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
	}
	private static String quote(String str)
	{
		if(str==null)
		{
			return "null";
		}
		else
		{
			return "'"+str+"'";
		}
	}
	public static void newUserData(String username)
	{
		String sql="insert into userdata(id) select id from user where username='"+username+"'";
		Database.getInstance().executeUpdate(sql);
	}
	private static String getAge(UserData userData)
	{
		int age=userData.getAge();
		if(age==0)
		{
			return "null";
		}
		return ""+age;
	}
	private static String getSex(UserData userData)
	{
		String sex=userData.getSex();
		if(sex==null)
		{
			return "null";
		}
		else if(sex.equals("man")||sex.equals("男"))
		{
			return "'man'";
		}
		else if(sex.equals("woman")||sex.equals("女"))
		{
			return "'woman'";
		}
		return "null";
	}
}
