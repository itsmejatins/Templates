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
		spaceAndBracket( "[[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]");
	}
}
