package current.temp;

import java.util.HashMap;
import java.util.Map;

class Solution
{
	public int lengthOfLongestSubstring(String s)
	{
		if (s.length() == 0)
			return 0;

		int i = 0, j = 0; // window is from i to j - 1
		int ans = 1;
		Map<Character, Integer> freq = new HashMap<>();

		while (j < s.length())
		{
			if (freq.getOrDefault(s.charAt(j), 0) > 0)
			{
				freq.compute(s.charAt(i), (k, v) -> v - 1);
				i++;
			}
			else
			{
				freq.put(s.charAt(j), 1);
				j++;
				ans = Math.max(ans, j - i);
			}
			// if(i == j)
			// {

			// j++;
			// }
		}
		return ans;
	}
}