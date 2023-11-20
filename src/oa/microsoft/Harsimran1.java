package oa.microsoft;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class Harsimran1
{

	public int solve(int N[], int k)
	{
		Arrays.sort(N);
		TreeMap<Integer, Integer> left = new TreeMap<>(Collections.reverseOrder()); // ordered map, descending order
		TreeMap<Integer, Integer> right = new TreeMap<>(); // ordered map, ascending order

		int leftIdx = N.length / 3 - 1;
		int rightIdx = N.length - N.length / 3;

		for (int i = 0; i <= leftIdx; i++)
			left.put(N[i], left.getOrDefault(N[i], 0) + 1);
		for (int i = rightIdx; i < N.length; i++)
			right.put(N[i], right.getOrDefault(N[i], 0) + 1);

		int diff = N[rightIdx] - N[leftIdx];

		while (k > 0)
		{
			int leftCost = left.firstEntry().getValue();
			int rightCost = right.firstEntry().getValue();
			if (k < leftCost && k < rightCost)
				return diff;

			if (left.size() == 1 && right.size() == 1) // there is now one option. Either increment all the right
														// elements, or decrement all the left elements
			{
				diff += k / (N.length / 3);
				return diff;
			}

			if (leftCost <= rightCost)
			{
				Map.Entry<Integer, Integer> currThirdSmallest = left.pollFirstEntry();

				int nextThirdSmallest = Math.max(left.firstKey(), currThirdSmallest.getKey() - k / leftCost);
				left.put(nextThirdSmallest, left.getOrDefault(nextThirdSmallest, 0) + currThirdSmallest.getValue());

				k -= leftCost * (currThirdSmallest.getKey() - nextThirdSmallest);
				diff += (currThirdSmallest.getKey() - nextThirdSmallest);
			}
			else
			{
				Map.Entry<Integer, Integer> currThirdLargest = right.pollFirstEntry();

				int nextThirdLargest = Math.min(right.firstKey(), currThirdLargest.getKey() + k / rightCost);
				right.put(nextThirdLargest, right.getOrDefault(nextThirdLargest, 0) + currThirdLargest.getValue());

				k -= rightCost * (nextThirdLargest - currThirdLargest.getKey());
				diff += (nextThirdLargest - currThirdLargest.getKey());
			}
		}
		return diff;
	}

	public static void main(String[] args)
	{
		int arr[] = { 1, 1, 1, 1, 1, 1, 1, 1, 1, }; // 9
		int k = 100;

		var x = new Harsimran1().solve(arr, k); // 33

		System.out.println(x);
	}

}
