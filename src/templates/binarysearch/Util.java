package templates.binarysearch;

public class Util
{

	public static int lowerBound(int[] arr, int n, int x)
	{
		int low = 0, high = n - 1;
		while (low <= high)
		{
			int mid = (low + high) >> 1;
			if (arr[mid] < x)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return low;
	}

	public static int upperBound(int[] arr, int x, int n)
	{

		int low = 0, high = n - 1;
		while (low <= high)
		{
			int mid = (low + high) >> 1;
			if (arr[mid] <= x)
				low = mid + 1;
			else
				high = mid - 1;
		}
		return high + 1;
	}

	public static int searchInsert(int[] arr, int target)
	{
		int low = 0, high = arr.length - 1;

		while (low <= high)
		{
			int mid = (low + high) >> 1;
			if (arr[mid] == target)
				return mid;
			if (arr[mid] < target)
				low = mid + 1;
			else
				high = mid - 1;
		}

		return low;
	}

}
