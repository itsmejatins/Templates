package templates.tries;

public class Util
{
	public static int getMaxXor(int arr1[], int arr2[])
	{
		NumberTrie trie = new NumberTrie();
		for (int i : arr1)
			trie.insert(i, 32);

		int ans = -1;

		for (int number : arr2)
		{
			NumberTrie.Node traverse = trie.head;
			int currAns = 0;

			for (int i = 31; i >= 0; i--)
			{
				char d = ((1 << i) & number) != 0 ? '1' : '0';
				char opp = d == '1' ? '0' : '1';

				if (traverse != null && traverse.children[opp - '0'] != null)
				{
					traverse = traverse.children[opp - '0'];
					currAns |= (1 << i);
				}
				else
					traverse = traverse.children[d - '0'];

			}

			ans = Math.max(ans, currAns);
		}

		return ans;
	}
}
