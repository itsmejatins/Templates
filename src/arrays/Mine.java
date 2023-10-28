package arrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.TreeSet;

class Mine
{

	static public class Pair<T, U>
	{
		public T first;
		public U second;

		Pair(T first, U second)
		{
			this.first = first;
			this.second = second;
		}

		@Override
		public String toString()
		{
			return "[" + first + "," + second + "]";
		}

		@Override
		public int hashCode()
		{
			return Objects.hash(first, second);
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (!(obj instanceof Pair))
				return false;
			Pair other = (Pair) obj;
			return Objects.equals(first, other.first) && Objects.equals(second, other.second);
		}

	}

	public int constrainedSubsetSum(int[] nums, int k)
	{
		int dp[] = Arrays.copyOf(nums, nums.length);
		// dp[i] -> maximum sum of a subsequence ending at i if nums[i] is in the
		// subsequence

		int ans = nums[0];
		TreeSet<Pair<Integer, Integer>> set = new TreeSet<>((p1, p2) -> p2.first - p1.first);
		set.add(new Pair(nums[0], 0));
		for (int i = 1; i < dp.length; i++)
		{
			try
			{
				while (i - set.first().second > k)
					set.remove(set.first());
			}
			catch (Exception e)
			{
				System.out.println(set.size());
				return -1111112322;
			}
			dp[i] = Math.max(set.first().first + nums[i], dp[i]);
			set.add(new Pair(dp[i], i));
			ans = Math.max(dp[i], ans);
		}
		return ans;
	}
}