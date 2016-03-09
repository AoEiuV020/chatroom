/* ***************************************************
	^> File Name: MD5.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 10:28:35
*************************************************** */
package chatroom.translate;
import java.security.*;
public class MD5
{
	public static String getMD5(String source)
	{
		return getMD5(source.getBytes());
	}
	public static String getMD5(byte[] source)
	{
		StringBuffer destination=new StringBuffer();
		try
		{
			MessageDigest md=MessageDigest.getInstance("MD5");
			md.update(source);
			byte[] arr=md.digest();
			for(byte b:arr)
			{
				int i=b;
				i=i<0?i+256:i;
				if(0<=i&&i<16)
				{
					destination.append(0);
				}
				destination.append(Integer.toHexString(i&0xFF));
			}
		}
		catch(Exception e)
		{
			return "";
		}
		return destination.toString();
	}
}
