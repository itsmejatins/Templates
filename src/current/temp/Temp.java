package current.temp;

public class Temp extends RegexDemo
{
	public static void main(String[] args)
	{
		int a = Integer.MAX_VALUE, b = Integer.MAX_VALUE, c = Integer.MAX_VALUE;
		System.out.println((long) a + b + c);
		System.out.println((long) a + (long) b + (long) c);
		System.out.println(a + b + (long) c);
	}
}
