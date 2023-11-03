package current.temp;

import java.util.Arrays;

public class MyClass
{

	// Function for adding number in the fenwick tree
	static void add(int BIT[], int idx, int val)
	{
		idx += 1;
		while (idx < BIT.length)
		{
			BIT[idx] += val;
			idx += idx & -idx;
		}
	}

	// Query for numbers < = idx
	static int query(int BIT[], int idx)
	{
		idx += 1;
		int sum = 0;
		while (idx > 0)
		{
			sum += BIT[idx];
			idx &= idx - 1;
		}
		return sum;
	}

	/*
	 * Function to calculate number of Inversions in a given array using the fenwick
	 * tree technique
	 */
	static int countInv(int pref[], int n)
	{

		int[] BIT = getBIT(n);

		int b[] = Arrays.copyOf(pref, pref.length);
		Arrays.sort(b);
		helper1(pref, n, b);

		// Now for all i, 0 <= a[i] < n
		int invCount = 0;
		for (int i = 0; i < n; i++)
		{

			// Increasing index a[i] by 1
			add(BIT, pref[i], 1);

			/*
			 * Count of numbers greater than a[i] before it is Being added to the inverse
			 * count
			 */
			invCount += query(BIT, pref[i] - 1);
		}
		return invCount;
	}

	private static int[] getBIT(int n)
	{
		int BIT[];
		BIT = new int[n + 1];
		for (int i = 0; i <= n; i++)
		{
			BIT[i] = 0;
		}
		return BIT;
	}

	private static void helper1(int[] pref, int n, int[] b)
	{
		for (int i = 0; i < n; i++)
		{
			int left = 0, right = n - 1, idx = n;
			while (left <= right)
			{

				int mid = (left + right) / 2;

				if (b[mid] >= pref[i])
				{
					idx = mid;
					right = mid - 1;
				}
				else
				{
					left = mid + 1;
				}
			}
			pref[i] = idx;
		}
	}

	/*
	 * Function to count the number of Substrings that contains more 1s than 0s
	 */
	static int getAnswer(String s, int n)
	{

		// Initializing prefix array
		int pref[];
		pref = new int[n];
		for (int i = 0; i < n; i++)
		{
			pref[i] = 0;
		}

		// Putting 1 for '1' and -1 for '0' as explained in the approach
		for (int i = 0; i < n; i++)
		{
			if (s.charAt(i) == '0')
			{
				pref[i] = -1;
			}
			else
			{
				pref[i] = 1;
			}
		}

		// Converting into prefix sum array
		for (int i = 1; i < n; i++)
		{
			pref[i] += pref[i - 1];
		}

		// Stores the count of valid substrings
		int total = 0;
		for (int i = 0; i < n; i++)
		{

			// If pref[i] > 0 means string from 0 to i is a valid one
			if (pref[i] > 0)
			{
				++total;
			}
		}

		total += countInv(pref, n);
		return total;
	}

	// Driver Function
	public static void main(String args[])
	{

		// Length of the given string
		int n = 5;

		// Given string
		String s = "11010";

		// Function Call
		int ans = getAnswer(s, n);

		System.out.print(ans);

	}
}