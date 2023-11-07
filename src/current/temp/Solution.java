package current.temp;

import java.util.Arrays;

class Solution
{
	public boolean isIsomorphic(String s, String t)
	{

		char[] map = new char[26];
		char[] inv = new char[26];

		Arrays.fill(map, ' ');
		Arrays.fill(inv, ' ');

		for (int i = 0; i < s.length(); i++)
		{
			char key = s.charAt(i);
			char val = t.charAt(i);

			if (map[key - 'a'] == ' ' && inv[val - 'a'] == ' ')
			{
				map[key - 'a'] = val;
				inv[val - 'a'] = key;
			}
			else if (map[key - 'a'] == ' ' && inv[val - 'a'] != ' ')
				return false;

			else if (map[key - 'a'] != val)
				return false;

		}

		return true;

	}
	
	public static void main(String[] args)
	{
		var x = new Solution().isIsomorphic("egg", "add");
	}
}