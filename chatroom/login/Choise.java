/* ***************************************************
	^> File Name: Choise.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/06 - 18:12:52
*************************************************** */
package chatroom.login;
import java.util.Scanner;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
public class Choise
{
	private Choise()
	{
	}
	public static int getChoise(int min,int max,Scanner sin,PrintWriter sout)
	{
		int choise;
		while(true)
		{
			try
			{
				choise=sin.nextInt();
				if(choise<min||choise>max)
				{
					throw new NumberFormatException();
				}
				break;
			}
			catch(NumberFormatException e)
			{
				sout.println("请输入范围内的数字，多谢配合，");
			}
			catch(InputMismatchException e)
			{
				sout.println("请输入正确数字，多谢配合，");
				sin.next();
			}
			catch(NoSuchElementException e)
			{
				//客户端输入结束了，
				throw e;
				//抛出去让UserContrullor处理，
			}
		}
		//java的输入的换行符好纠结，
		sin.nextLine();
		return choise;
	}
}
