/* ***************************************************
	^> File Name: Translate.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 09:52:36
*************************************************** */
package chatroom.translate;
public abstract class Translate
{
	private static Translate instance=null;
	public static void setInstance(Translate translate)
	{
		instance=translate;
	}
	public static Translate getInstance()
	{
		if(instance==null)
		{
			instance=new BaiduTranslate();
		}
		return instance;
	}
	public abstract String translate(String query,String from,String to);
	public abstract String translateToEn(String query);
	public abstract String translateToZh(String query);
}
