/* ***************************************************
	^> File Name: Main.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 10:06:58
*************************************************** */
package chatroom.translate;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Scanner;
public class Main
{
	public static void main(String[] args)throws Exception
	{
		Scanner stdin=new Scanner(System.in);
		Translate trans=Translate.getInstance();
		String query=stdin.nextLine();
		System.err.println(""+trans.translateToZh(query));
	}
}
