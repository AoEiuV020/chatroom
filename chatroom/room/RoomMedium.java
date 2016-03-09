/* ***************************************************
	^> File Name: RoomMedium.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/02/29 - 15:30:39
*************************************************** */
package chatroom.room;
import chatroom.user.User;
import chatroom.log.Logger;
import java.net.*;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
public class RoomMedium
{
	Map<User,Room> userInRoom;
	Map<String,Room> nameOfRoom;
	public RoomMedium()
	{
		userInRoom=new LinkedHashMap<User,Room>();
		nameOfRoom=new LinkedHashMap<String,Room>();
		createHall();
	}
	private void createHall()
	{
		String str="hall";
		Room hall=new Room(str);
		nameOfRoom.put(str,hall);
	}
	public void addUser(Socket socket)
	{
		User user=new User(socket);
		user.setRoomMedium(this);
		enterHall(user);
	}
	private void removeRoom(String name)
	{
		if(!name.equals("hall"))
		{
			nameOfRoom.remove(name);
		}
	}
	public void leave(User user)
	{
		Room room=userInRoom.get(user);
		if(room==null)
		{
			return;
		}
		room.leave(user);
		userInRoom.remove(user);
		if(!userInRoom.containsValue(room))
		{
			removeRoom(room.getName());
		}
	}
	public boolean enterRoom(User user,String string)
	{
		if(!nameOfRoom.containsKey(string))
		{
			return false;
		}
		leave(user);
		Room room=nameOfRoom.get(string);
		if(room!=null)
		{
			userInRoom.put(user,room);
			room.add(user);
		}
		else
		{
			createRoom(user,string);
		}
		return true;
	}
	private void enterHall(User user)
	{
		enterRoom(user,"hall");
	}
	public void exit()
	{
		Set<User> keys=new LinkedHashSet<User>(userInRoom.keySet());
		for(User user:keys)
		{
			user.exit();
		}
		nameOfRoom.clear();
	}
	public void broadcast(User user,String string)
	{
		Room room=userInRoom.get(user);
		if(room!=null)
		{
			room.broadcast(user,string);
		}
	}
	public boolean createRoom(User user,String string)
	{
		Logger.createRoom(user,string);
		if(nameOfRoom.containsKey(string))
		{
			return false;
		}
		Room room=new Room(string);
		nameOfRoom.put(string,room);
		enterRoom(user,string);
		return true;
	}
	public Set<String> getRoomsSet()
	{
		Set<String> set=new LinkedHashSet<String>(nameOfRoom.keySet());
		return set;
	}
}
