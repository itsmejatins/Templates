package templates.formatting;

public class FormatToArray
{
	public static String spaceAndBracket(String s)
	{
		String conv = s.replace(' ', ',').replace('[', '{').replace(']', '}');
		System.out.println(conv);
		return conv;
	}

	public static void main(String[] args)
	{
		spaceAndBracket( "[15 6 2 1 16 4 2 29 9 12 8 5 14 21 8 12 17 16 6 26 3 " + "]");
	}
}
