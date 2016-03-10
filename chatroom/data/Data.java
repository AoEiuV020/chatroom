/* ***************************************************
	^> File Name: Data.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 19:28:59
*************************************************** */
package chatroom.data;
public class Data
{
	private Data()
	{

	}
	public static boolean existsUsername(String username)
	{
		return Check.existsUsername(username);
	}
	public static boolean checkPassword(String user,String password)
	{
		return Check.checkPassword(user,password);
	}
	public static boolean register(String username,String password)
	{
		return Register.register(username,password);
	}
	public static void createTables()throws Exception
	{
		Tables.create();
	}
	public static void changePassword(int id,String password)
	{
		Change.changePassword(id,password);
	}
}
