/* ***************************************************
	^> File Name: LocalDateTime.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/10 - 15:50:33
*************************************************** */
package chatroom.history;
import java.util.Date;
import java.text.SimpleDateFormat;
public class LocalDateTime extends Date
{
	static String dateFormat="yyyy.MM.dd/HH:mm:ss";
	public LocalDateTime(long l)
	{
		super(l);
	}
	public LocalDateTime(Date date)
	{
		this(date.getTime());
	}
	public static LocalDateTime now()
	{
		LocalDateTime time=new LocalDateTime(System.currentTimeMillis());
		return time;
	}
	@Override
	public String toString()
	{
		return toString(LocalDateTime.dateFormat);
	}
	public String toString(String format)
	{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.format(this);
	}
}
