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
		spaceAndBracket("[[7,0],[3,1],[6,2],[4,3],[4,5],[4,6],[4,7]]");
	}
}
