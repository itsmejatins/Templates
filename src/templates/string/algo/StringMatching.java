package templates.string.algo;

public class StringMatching
{
	public static int [] genPrefixArray(StringBuilder s)
	{
		int prefix[] = new int[s.length()];
		for (int i = 1; i < prefix.length; i++)
		{
			int b = prefix[i - 1];
			if (s.charAt(b) == s.charAt(i))
				prefix[i] = b + 1;
			else
			{
				while (b > 0)
				{
					b = prefix[b - 1];
					if (s.charAt(b) == s.charAt(i))
					{
						prefix[i] = b + 1;
						break;
					}

				}
				if (prefix[i] == 0)
					prefix[i] = s.charAt(0) == s.charAt(i) ? 1 : 0;
			}
		}
		return prefix;
	} 

}
