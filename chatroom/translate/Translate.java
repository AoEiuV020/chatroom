/* ***************************************************
	^> File Name: Translate.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 09:52:36
*************************************************** */
package chatroom.translate;
public interface Translate
{
	public String translate(String query,String from,String to);
	public String translateToEn(String query);
	public String translateToZh(String query);
}
