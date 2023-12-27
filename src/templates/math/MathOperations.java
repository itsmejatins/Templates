package templates.math;

public class MathOperations {
	public static int binaryExponentiation(long a, long b, int mod) {
		long ans = 1;

		if (a == 1 || b == 0)
			return 1;
		if (a == 0)
			return 0;
		if (b == 1)
			return (int) a;

		while (b > 0) {
			if ((b & 1) != 0) {
				ans = (ans * a) % mod;
				b--;
			} else {
				a = (a * a) % mod;
				b = b >> 1;
			}
		}

		return (int) ans;
	}

	public static void main(String[] args) {
		int x = binaryExponentiation(2, 11, 23);
		System.out.println(2048 % 23);
		System.out.println(x);
	}
}
