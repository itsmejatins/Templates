package current.temp;

import java.util.Arrays;

class Solution
{
	private int mod = 1_000_000_007;
	public int countPalindromicSubsequences(String s)
	{
		int dp[][] = new int[s.length()][s.length()];

		for (int i = 0; i < s.length(); i++)
		{
			dp[i][i] = 1;
			if (i + 1 < s.length())
			{
				dp[i][i + 1] = 2;
			}
		}

		int next[] = new int[s.length()], prev[] = new int[s.length()], map[] = new int[26];
		Arrays.fill(next, s.length());
		Arrays.fill(prev, -1);
		Arrays.fill(map, -1);

		for (int i = 0; i < s.length(); i++)
		{
			if (map[s.charAt(i) - 'a'] != -1)
				prev[i] = map[s.charAt(i) - 'a'];

			map[s.charAt(i) - 'a'] = i;
		}
		Arrays.fill(map, -1);
		for (int i = s.length() - 1; i >= 0; i--)
		{
			if (map[s.charAt(i) - 'a'] != -1)
				next[i] = map[s.charAt(i) - 'a'];

			map[s.charAt(i) - 'a'] = i;
		}

		for (int diag = 2; diag < s.length(); diag++)
		{
			for (int r = 0; r < s.length() - diag; r++)
			{
				int c = r + diag;
				if (s.charAt(c) != s.charAt(r))
				{
					dp[r][c] = (int)(((long)dp[r][c - 1] + dp[r + 1][c] - dp[r + 1][c - 1] + mod) % mod);
				}

				else
				{
					// case1 a _____ a
					if (next[r] >= c && prev[c] <= r)
					{
						dp[r][c] = (int)((2l * dp[r + 1][c - 1] + 2) % mod);
					}

					else if (next[r] < c && prev[c] > r && next[r] == prev[c])
					{
						dp[r][c] = (int)((2l * dp[r + 1][c - 1] + 1) % mod);
					}

					else
					{
						dp[r][c] = (int)((2l * dp[r + 1][c - 1] + mod - dp[next[r] + 1][prev[c] - 1]) % mod);
					}

				}
			}
		}

		return dp[0][s.length() - 1];

	}
}