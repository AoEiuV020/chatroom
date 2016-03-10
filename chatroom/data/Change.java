/* ***************************************************
	^> File Name: Change.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 14:18:12
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
import java.sql.*;
public abstract class Change
{
	private static PreparedStatement chpass=null;
	public static void changePassword(int id,String password)
	{
		try
		{
			if(chpass==null)
			{
				String sql="update user set password=password(?) where id=?";
				chpass=Database.getInstance().getConnection().prepareStatement(sql);
			}
			chpass.setString(1,password);
			chpass.setInt(2,id);
			chpass.executeUpdate();
		}
		catch(SQLException e)
		{
			Logger.exception(e);
		}
	}
}
