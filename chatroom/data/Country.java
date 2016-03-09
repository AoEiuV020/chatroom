/* ***************************************************
	^> File Name: Country.java
	^> Author: AoEiuV020
	^> Mail: 490674483@qq.com
	^> Created Time: 2016/03/07 - 15:03:20
*************************************************** */
package chatroom.data;
import java.util.Map;
import java.util.HashMap;
public abstract class Country
{
	static Map<String,String> languages;
	static Map<String,String> countries;
	static
	{
		countries=new HashMap<String,String>();
		countries.put("zh","中国");
		countries.put("en","英国");
		countries.put("jp","日国");
		countries.put("kor","韩国");
		countries.put("fra","法国");
		countries.put("spa","西班牙");
		countries.put("th","泰国");
		countries.put("ara","阿拉伯");
		countries.put("ru","俄国");
		countries.put("pt","葡萄牙");
		countries.put("de","德国");
		countries.put("it","意大利");
		countries.put("el","希腊");
		countries.put("nl","荷兰");
		countries.put("pl","波兰");
		countries.put("bul","保加利亚");
		countries.put("est","爱沙尼亚");
		countries.put("dan","丹麦");
		countries.put("fin","芬兰");
		countries.put("cs","捷克");
		countries.put("rom","罗马尼亚");
		countries.put("slo","斯洛文尼亚");
		countries.put("swe","瑞典");
		countries.put("hu","匈牙利");
		languages=new HashMap<String,String>();
		for(Map.Entry<String,String> entry:countries.entrySet())
		{
			languages.put(entry.getValue(),entry.getKey());
		}
	}
	public static Map<String,String> getCountriesMap()
	{
		return countries;
	}
	public static String getCountries()
	{
		StringBuffer buf=new StringBuffer();
		for(String country:countries.keySet())
		{
			buf.append(country+",");
		}
		buf.append("oth");
		return buf.toString();
	}
	public static String shortToFull(String s)
	{
		//如果在缩写全称中，
		if(languages.containsKey(s))
		{
			return s;
		}
		if(countries.containsKey(s))
		{
			return countries.get(s);
		}
		else
		{
			return "其他";
		}
	}
	public static String fullToShort(String s)
	{
		//如果在缩写列表中，
		if(countries.containsKey(s))
		{
			return s;
		}
		if(languages.containsKey(s))
		{
			return languages.get(s);
		}
		else
		{
			return "oth";
		}
	}
}
