package current.temp;

import java.util.Arrays;

class Solution
{
	public int minEatingSpeed(int[] piles, int h)
	{
		int low = 1, high = Arrays.stream(piles).max().getAsInt();
		while (low <= high)
		{
			int mid = (low + high) / 2;
			if (canEat(piles, mid, h))
				high = mid - 1;
			else
				low = mid + 1;
		}
		return high + 1;
	}

	private boolean canEat(int piles[], int speed, int timeAvailable)
	{
		long timeTaken = Arrays.stream(piles).mapToLong(i -> i).reduce(0,
				(a, b) -> a + (long) Math.ceil((double) b / speed));
		return timeTaken <= timeAvailable;
	}

	public static void main(String[] args)
	{
		int piles[] = { 805306368, 805306368, 805306368 };
		int h = 1000000000;

		System.out.println(new Solution().minEatingSpeed(piles, h));
	}
}
