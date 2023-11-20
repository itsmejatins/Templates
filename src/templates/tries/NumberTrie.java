package templates.tries;

public class NumberTrie
{
	static class Node
	{
		char c = 'r';
		Node children[] = new Node[2];
		int prefixCount, endCount;

		public Node(char c)
		{
			this.c = c;
		}

		public Node()
		{
		}

		@Override
		public String toString()
		{
			return c + "";
		}
	}

	Node head = new Node();

	/**
	 * Takes a binary representation of a number and inserts it into the trie.
	 * 
	 * @param number - The binary representation of the number in string format.
	 * @return true if number was absent from the trie, ie, a new number is being
	 *         inserted into the trie, false if number already existed in the trie.
	 */

	public boolean insert(String number)
	{
		boolean newNumber = false;
		Node traverse = head;
		for (int i = 0; i < number.length(); i++)
		{
			char c = number.charAt(i);
			if (traverse.children[c - '0'] != null)
			{
				traverse = traverse.children[c - '0'];
			}
			else
			{
				newNumber = true;
				Node node = new Node(c);
				traverse.children[c - '0'] = node;
				traverse = node;
			}
			traverse.prefixCount++;
		}
		traverse.endCount++;

		return newNumber;
	}

	/**
	 * Converts the input number 'number' into its binary representation and inserts
	 * it into the trie.
	 * 
	 * @param number   - the number to be inserted. It should be in base 10.
	 * @param bitCount - how many bits are used to represent that number in binary
	 * @return true if number was absent from the trie, false if number already
	 *         existed in the trie.
	 */
	public boolean insert(int number, int bitCount)
	{
		boolean newNumber = false;
		Node traverse = head;
		for (int i = bitCount - 1; i >= 0; i--)
		{
			char d = ((1 << i) & number) != 0 ? '1' : '0';
			if (traverse.children[d - '0'] != null)
			{
				traverse = traverse.children[d - '0'];
			}
			else
			{
				Node node = new Node(d);
				traverse.children[d - '0'] = node;
				newNumber = true;
				traverse = node;
			}
			traverse.prefixCount++;
		}
		traverse.endCount++;
		return newNumber;
	}

	/**
	 * Takes in a number and returns the maximum value of its XOR with candidates
	 * being all the elements present in the trie.
	 * 
	 * @param n:        the input number
	 * @param bitCount: in how many bits the input number is represented. Ideally it
	 *                  should be equal to the number of bits in which the numbers
	 *                  inside the trie are represented. For ex - if numbers inside
	 *                  the trie are of 32 bits, the bitCount should be equal to 32.
	 * @return maximum value of XOR.
	 */

	public int maxXor(int n, int bitCount)
	{
		int ans = 0;
		Node traverse = this.head;

		for (int i = bitCount - 1; i >= 0; i--)
		{
			int d = (n & (1 << i)) != 0 ? 1 : 0;
			int dc = d == 1 ? 0 : 1; // d complement

			if (traverse.children[dc] != null)
			{
				ans = 2 * ans + 1;
				traverse = traverse.children[dc];
			}
			else
			{
				ans *= 2;
				traverse = traverse.children[d];
			}

		}

		return ans;
	}

}