/* ***************************************************
	^> File Name: Message.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 10:20:57
*************************************************** */
package chatroom.history;
import java.sql.Timestamp;
import java.time.LocalDateTime;
public class Message
{
	int userId=0;
	Timestamp time=null;
	String message=null;
	public Message()
	{
	}
	public void setUserId(int i)
	{
		userId=i;
	}
	public int getUserId()
	{
		return userId;
	}
	public void setTimestamp(Timestamp t)
	{
		time=t;
	}
	public Timestamp getTimestamp()
	{
		return time;
	}
	public LocalDateTime getTime()
	{
		return time.toLocalDateTime();
	}
	public void setMessage(String mess)
	{
		message=mess;
	}
	public String getMessage()
	{
		return message;
	}
}
