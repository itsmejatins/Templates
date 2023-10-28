package arrays;

import java.util.Arrays;

import templates.testing.StressTesting;

public class StressTestor
{
	public static void main(String[] args)
	{
		while (true)
		{
			int nums[] = StressTesting.generateArray(5, -10, 10);
			int k = StressTesting.generateInteger(1, nums.length);
			int mine = new Mine().constrainedSubsetSum(Arrays.copyOf(nums, nums.length), k);
			int correct = new Official().constrainedSubsetSum(Arrays.copyOf(nums, nums.length), k);
			if (mine != correct)
			{
				Arrays.stream(nums).forEach(i -> System.out.print(i + ", "));
				System.out.println("\nk = " + k);
				System.out.println();
				System.out.println("Mine " + mine);
				System.out.println("Official " + correct);
				break;

			}

		}
	}
}
