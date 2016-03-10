/* ***************************************************
	^> File Name: BaiduTranslate.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/04 - 09:52:03
*************************************************** */
package chatroom.translate;
import chatroom.log.Logger;
import java.util.*;
import java.io.*;
import java.net.*;
import net.sf.json.*;
public class BaiduTranslate extends Translate
{
	private static final String url = "http://api.fanyi.baidu.com/api/trans/vip/translate";
	private static final String UTF8="utf-8";
	private static final String encode=UTF8;
	private static final Random random = new Random();
	private String appid;
	private String token;
	public BaiduTranslate()
	{
		String file="token";
		String token=null;
		try
		{
			BufferedReader reader=new BufferedReader(new FileReader(file));
			token=reader.readLine();
			reader.close();
		}
		catch(Exception e)
		{
			Logger.exception(e);
		}
		this.token=token;
		this.appid="20160304000014491";
	}
	public BaiduTranslate(String token)
	{
		this.token=token;
		this.appid="20160304000014491";
	}
	public BaiduTranslate(String token,String appid)
	{
		this.token=token;
		this.appid=appid;
	}
	private int getSalt()
	{
		return BaiduTranslate.random.nextInt(10000);
	}
	private String URLEncode(String str)
	{
		StringBuffer stringBuffer=new StringBuffer();
		try
		{
			str=URLEncoder.encode(str,BaiduTranslate.encode);
			return str;
		}
		catch(Exception e)
		{
		}
		return stringBuffer.toString();
	}
	private JSONObject getJson(String get)
	{
		StringBuffer stringBuffer=new StringBuffer();
		try
		{
			URL url=new URL(get);
			InputStreamReader input=new InputStreamReader(url.openStream());
			char[] buf=new char[100];
			int len=0;
			while((len=input.read(buf))>0)
			{
				stringBuffer.append(buf,0,len);
			}
		}
		catch(Exception e)
		{
			Logger.exception(e);
			return new JSONObject();
		}
		return JSONObject.fromObject(stringBuffer.toString());
	}
	private String getDestination(JSONObject json)
	{
		return json.getJSONArray("trans_result").getJSONObject(0).getString("dst");
	}
	public String translate(String query,String from,String to)
	{
		if(query==null||query.isEmpty())
		{
			return "";
		}
		int salt=getSalt();
		String sign=""+appid+query+salt+token;
		sign=MD5.getMD5(sign);
		query=URLEncode(query);
		String get=BaiduTranslate.url+"?q="+query+"&from="+from+"&to="+to+"&appid="+appid+"&salt="+salt+"&sign="+sign;
		String destination=getDestination(getJson(get));
		return destination;
	}
	public String translateToEn(String query)
	{
		return translate(query,"auto","en");
	}
	public String translateToZh(String query)
	{
		return translate(query,"auto","zh");
	}
}
