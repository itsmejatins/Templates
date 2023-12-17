package templates.io;

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
		spaceAndBracket("[[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"],[\"John\",\"johnsmith@mail.com\",\"john00@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]");
	}
}
