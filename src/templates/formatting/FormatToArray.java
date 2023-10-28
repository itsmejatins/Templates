package templates.formatting;

public class FormatToArray
{
	public static String spaceAndBracket(String s)
	{
		String conv = s.replace(' ', ',').replace('[','{').replace(']','}');
		System.out.println(conv);
		return conv;
	}
	public static void main(String[] args)
	{
		spaceAndBracket("[[0,1,1000000000],[0,3,1000000000],[1,3,1000000000],[1,2,1000000000],[1,5,1000000000],[3,4,1000000000],[4,5,1000000000],[2,5,1000000000]]");
	}
}
