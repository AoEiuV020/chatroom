/* ***************************************************
	^> File Name: UserData.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 20:12:22
*************************************************** */
package chatroom.data;
import chatroom.log.Logger;
public class UserData
{
	private int id;
	private int age;
	private String nickname=null;
	private String username=null;
	private String sex=null;
	private String country=null;
	public UserData()
	{

	}
	public void setAge(int age)
	{
		this.age=age;
	}
	public int getAge()
	{
		return age;
	}
	public void setId(int id)
	{
		this.id=id;
	}
	public int getId()
	{
		return id;
	}
	public void setSex(String sex)
	{
		if(sex!=null)
		{
			sex=(sex.equals("man")?"男":sex.equals("woman")?"女":"null");
		}
		this.sex=sex;
	}
	public String getSex()
	{
		return sex;
	}
	public void setCountry(String country)
	{
		if(country!=null)
		{
			country=Country.shortToFull(country);
		}
		this.country=country;
	}
	public String getCountry()
	{
		return country;
	}
	public void setNickname(String string)
	{
		if(string!=null&&string.length()>20)
		{
			string=string.substring(0,20);
		}
		nickname=string;
	}
	public String getNickname()
	{
		return nickname;
	}
	public void setUsername(String string)
	{
		username=string;
	}
	public String getUsername()
	{
		return username;
	}
	public static UserData getUserDataFromId(int id)
	{
		return UserDataReader.getUserDataFromId(id);
	}
	public static UserData getUserDataFromUsername(String username)
	{
		return UserDataReader.getUserDataFromUsername(username);
	}
	private String showAllData()
	{
		StringBuffer dataBuf=new StringBuffer();
		dataBuf.append("id:"+id);
		dataBuf.append("\nusername:"+username);
		dataBuf.append("\nnickname:"+nickname);
		dataBuf.append("\nage:"+(age==0?"null":age));
		dataBuf.append("\nsex:"+sex);
		dataBuf.append("\ncountry:"+country);
		return dataBuf.toString();
	}
	@Override
	public String toString()
	{
		return showAllData();
	}
	public void update()
	{
		UserDataWriter.updateUserData(this);
	}
	public static UserData searchFromUsername(String username)
	{
		return UserDataReader.getUserDataFromUsername(username);
	}
	public static UserData searchFromId(int id)
	{
		return UserDataReader.getUserDataFromId(id);
	}
	public static int getIdByUsername(String username)
	{
		return UserDataReader.getIdByUsername(username);
	}
}
