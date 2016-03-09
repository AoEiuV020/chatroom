/* ***************************************************
	^> File Name: UnsupportedCharFilter.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 11:11:31
*************************************************** */
package chatroom.login;
public abstract class UnsupportedCharFilter
{
	public static boolean filter(char ch)
	{
		if(ch<'0'||(ch>'9'&&ch<'A')||(ch>'Z'&&ch<'a'&&ch!='_')||(ch>'z'&&ch<128))
		{
			return true;
		}
		return false;
	}
}
