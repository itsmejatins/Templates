package templates.array;

public class Util
{
	public static int upperBoundWithDuplicate(int arr[], boolean sorted)
	{
		return -1;
	}

	public static int upperBoundWithoutDuplicate(int arr[], boolean sorted)
	{
		return -1;
	}
	// same for lower bound

	public static void swap(int[] arr, int i, int j)
	{
		int x = arr[i];
		arr[i] = arr[j];
		arr[j] = x;
	}
	
	// countInv1 modifies the original array
	public static int countInv1(int nums[], int low, int high)
	{
		if (low == high)
			return 0;
		int mid = (low + high) / 2;
		int count = 0;
		count += countInv1(nums, low, mid);
		count += countInv1(nums, mid + 1, high);

		int merged[] = new int[high - low + 1];
		int x = 0;
		int i = low, j = mid + 1;
		while (i <= mid && j <= high)
		{
			if (2 * nums[j] < nums[i])
			{
				count += mid - i + 1;
				merged[x++] = nums[j++];
			}
			else
				merged[x++] = nums[i++];
		}
		while (i <= mid)
			merged[x++] = nums[i++];
		while (j <= high)
			merged[x++] = nums[j++];

		for (x = low; x <= high; x++)
			nums[x] = merged[x - low];

		return count;
	}

}
