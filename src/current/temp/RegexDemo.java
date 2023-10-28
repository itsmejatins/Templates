package current.temp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo
{
	public static void main(String[] args)
	{
		String regex = ".";
		String literalVersion = Pattern.quote(regex);
		String target = "a.b.c";
		Pattern p = Pattern.compile(literalVersion);
		Matcher m = p.matcher(target);
		while (m.find())
			System.out.println(m.start() + "~~" + m.group());
	}
}