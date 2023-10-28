package templates.testing;

import java.util.Random;

public class StressTesting
{

	/**
	 * 
	 * @param n length of array
	 * @param a min value of array inclusive
	 * @param b max value of the array inclusive
	 * @return
	 */
	public static int[] generateArray(int n, int a, int b)
	{
		int[] array = new int[n];
		Random random = new Random();

		for (int i = 0; i < n; i++)
		{
			array[i] = random.nextInt(b - a + 1) + a;

		}

		return array;
	}

	/**
	 * 
	 * @param p min value in the array
	 * @param q max value in the array
	 * @return
	 */
	public static int generateInteger(int p, int q)
	{
		Random random = new Random();
		return random.nextInt(q - p + 1) + p;
	}
}