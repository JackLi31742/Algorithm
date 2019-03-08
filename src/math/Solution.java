package math;

public class Solution {
	
	/**
	 * 实现一个函数，对一个正整数n，算得到1需要的最少操作次数。操作规则为：
	 * 如果n为偶数，将其除以2；如果n为奇数，可以加1或减1；一直处理下去
	 * 
	 * 
	 * 奇数的时候加1或减1，完全取决于二进制的后两位，如果后两位是10、00那么肯定是偶数，
	 * 选择除以2，如果后两位是01、11，那么选择结果会不一样的，如果是*****01，那么选择减1，
	 * 如果是*****11，那么选择加1，特殊情况是就是n是3的时候，选择减1操作。
	 * LANG
	 * @param n
	 * @return
	 */
	public static int func(int n) {
		if (n == 1)
			return 0;
		if (n % 2 == 0)
			return 1 + func(n / 2);
		int x = func(n + 1);
		int y = func(n - 1);
		if (x > y)
			return y + 1;
		else
			return x + 1;
	}
	
	public static int func2(int n) {
		int count = 0;
		while (n > 1) {
			if (n % 2 == 0) {
				n = n >> 1;
			} else if (n == 3) {
				n--;
			} else {
				int temp = n & 2;
				if (temp > 0) { // 二进制是******11时
					n++;
				} else { // 二进制是******01时
					n--;
				}
			}
			count++;
		}
		return count;
	}
	
	
	/**
     * 数组的最大公约数
     * LANG
     * @param ints
     * @return
     */
	public int generalizedGCD(int num, int[] arr) {
		for (int i : arr) {
			if (i <= 0) {
				return -1;
			}
		}
		if (num == 0) {
			return -1;
		}
		if (num == 1) {
			return arr[0];
		}
		int index = 1;
		int re = arr[0];
		while (index < num) {
			re = gcd(re, arr[index]);
			index++;
		}
		return re;
	}

	public int gcd(int a, int b) {
		int t;
		if (a < b) {
			t = b;
			b = a;
			a = t;
		}
		int c = a % b;
		while (c > 0) {
			a = b;
			b = c;
			c = a % b;
		}
		return b;
	}
	public static void main(String[] args) {
		int a=Integer.parseInt("1101111111", 2);
		System.out.println(Integer.toBinaryString(a));
		System.out.println(a);
		System.out.println(func(a));
		System.out.println(func2(a));
	}

}
