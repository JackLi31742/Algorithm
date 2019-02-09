package dp;

public class Backpack {

	/**
	 * 给出 n 个物品的体积 A[i] 和其价值 V[i] ，将他们装入一个大小为 m 的背包，最多能装入的总价值有多大？

		注意事项：A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的 m
		backpack[i][j] 代表在前 i件物品中选择若干件，这若干件物品的体积和不超过 j 的情况下，所能获得的最大物品价值和。
		
		backpack[i][j]=max(backpack[i−1][j],backpack[i−1][j−cap[i]]+val[i])
			如果不将第 i 件物品放入背包，那么 backpack[i][j] 的值为 backpack[i - 1][j]；如果将第 i 件物品放入背包，
			那么backpack[i][j]=backpack[i−1][j−cap[i]]+val[i]。
			我们根据 backpack[i - 1][j]backpack[i−1][j] 和 backpack[i - 1][j - cap[i]] + val[i]backpack[i−1][j−cap[i]]+val[i] 
			的大小选择放还是不放第 i 件物品。
		
		backpack[0][j]=0,j∈[0,V]
			在前 0 件物品中选择若干件，也就是一件都不选，此时无论物品的体积和为何值，物品价值和均为 0。
	 * LANG
	 * @param m
	 * @param A
	 * @param V
	 * @return
	 */
	
	public static int getMaxValueBy0_1(int m, int[] A, int[] V){
		//个数
		int n=A.length;
		
		int [][]bp=new int[n+1][m+1];
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				//只需要大于当前？不应该是大于之前所有的和？
				//每次存放物品都需要判断当前背包容量减去当前物品容量(k)的最大价值加上当前物品的价值( value )是否大于原价值。
				//因为bp是二维数组，减去当前的容量后的值在之前就已经保存了
				//A[i-1]是因为bp是下标比A和V的大一个
				if (j>A[i-1]) {
					bp[i][j]=Math.max(bp[i-1][j],bp[i-1][j-A[i-1]]+V[i-1]);
				}else {
					
					bp[i][j]=bp[i-1][j];
				}
				
			}
		}
		for (int i = 1; i <=n; i++) {
            for (int j = 1; j <=m; j++) {
                System.out.print(bp[i][j]+" ");
                if(j==m){
                    System.out.println();
                }
            }
        }
		return bp[n][m];
		
	}
	
	/**
	 * 在第 i 层循环初 f[j] 存的相当于 backpack[i−1][j] 的值。
	 * 在更新f[j] 时，我们用到了f[j−cap[i]]，
	 * 由于第二层循环倒序，所以 f[j−cap[i]] 未被更新，
	 * 此时它代表 backpack[i−1][j−cap[i]]。
	 * 所以 f[j] = Math.max(f[j], f[j - cap[i]] + val[i]) 
	 * 等价于 backpack[i][j] = Math.max(backpack[i - 1][j], backpack[i - 1][j - cap[i]] + val[i])
	 */
	
	public static int getMaxValueBy0_1_2(int m, int[] A, int[] V){
		int n = A.length;
	    int[] f = new int[m + 1];
	    for (int i = 1; i <= n; ++i) {
	        for (int j = m; j >= A[i-1]; --j) {
	            f[j] = Math.max(f[j], f[j - A[i-1]] + V[i-1]);
	        }
	    }
	    for (int i = 0; i < f.length; i++) {
			System.out.print(f[i]+" ");
		}
	    System.out.println();
	    return f[m];
    }
	public static void main(String[] args) {
		int m=10;
		int[] A={3, 4, 5};
		int[] V={4, 5, 6};
		
		System.out.println(getMaxValueBy0_1(m,A,V));;
		
//		int a=3;
//		System.out.println(a&1);
//		System.out.println(a^1);
	}
}
