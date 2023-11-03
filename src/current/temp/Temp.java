package current.temp;

import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Temp extends RegexDemo
{
	public static void main(String[] args)
	{
		String input = "21474836460";

		// Remove leading zeroes using regex
		String output = input.replaceFirst("^0+", "");

		System.out.println("Original string: " + input);
		System.out.println("String without leading zeroes: " + output);

	}

}
