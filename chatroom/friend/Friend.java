/* ***************************************************
	^> File Name: Friend.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/08 - 14:50:50
*************************************************** */
package chatroom.friend;
import java.util.Set;
public abstract class Friend
{
	public static void makeFriend(int id,int friendId)
	{
		FriendWriter.makeFriend(id,friendId);
	}
	public static Set<Integer> getTwoWayFriendSet(int id)
	{
		return FriendReader.getTwoWayFriendSet(id);
	}
	public static Set<Integer> getOneWayFriendSet(int id)
	{
		return FriendReader.getOneWayFriendSet(id);
	}
	public static boolean isFriend(int id,int friendId)
	{
		return FriendReader.isFriend(id,friendId);
	}
}
