package dp;

import java.util.Arrays;
import java.util.List;

public class Solution {

	/**
	 * 139. Word Break
	 * possible[i] = true      if  S[0,i]在dictionary里面

                = true      if   possible[k] == true 并且 S[k+1,i]在dictionary里面， 0<k<i

               = false      if    no such k exist.

	 * LANG
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public boolean wordBreak(String s, List<String> wordDict) {
        int len=s.length();
        boolean[] dp=new boolean[len+1];
        dp[0]=true;
        for(int i=1;i<=len;i++){
            for(int j=0;j<i;j++){
                if(dp[j]&&wordDict.contains(s.substring(j,i))){
                    dp[i]=true;
                    break;
                }
            }
        }
        
        return dp[len];
    }
	/**
	 * leetcode 787. Cheapest Flights Within K Stops
	 * 1029. Cheapest Flights Within K Stops
		https://blog.csdn.net/ak_lady/article/details/70147204
		时间复杂度是O(V*E)
		第一，初始化所有点。每一个点保存一个值，表示从原点到达这个点的距离，将原点的值设为0，其它的点的值设为无穷大（表示不可达）。
第二，进行循环，循环下标为从1到n－1（n等于图中点的个数）。在循环内部，遍历所有的边，进行松弛计算。
第三，遍历途中所有的边（edge（u，v）），判断是否存在这样情况：d（v）> d (u) + w(u,v)则返回false，表示途中存在从源点可达的权为负的回路。

 ！！！之所以需要第三部分的原因，是因为，如果存在从源点可达的权为负的回路。则 应为无法收敛而导致不能求出最短路径

	 * Bellman-Ford
	 * LANG
	 * @param n
	 * @param flights
	 * @param src
	 * @param dst
	 * @param K
	 * @return
	 */
	 public static int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		 
		 //初始化距离 从起点src出发，到其余各个节点，经过最多K次跳转的最短距离
		 int inf = 0x4f4f4f4f;
		 int []dis=new int[n];
		 //不能使用Integer.MAX_VALUE，再加会超过int的值
		 Arrays.fill(dis, inf);
		 //源设为0
		 dis[src]=0;
		 int distance=dis[dst];
		 //到了k是为了减少比较次数  // 最多K次中继
		 for (int i = 0; i <=K; i++) {
			 int []temp=new int[n];
			 Arrays.fill(temp, inf);
			for (int[] e : flights) {
				temp[e[1]]=Math.min(temp[e[1]], dis[e[0]]+e[2]);
			}
			dis=temp;
			distance=Math.min(distance, dis[dst]);
		}
		 return distance==inf?-1:distance;
	 }
	 
	 /**
	  *  Bellman-Ford 动态规划 没做出来
	  * LANG
	  * @param n
	  * @param flights
	  * @param src
	  * @param dst
	  * @param K
	  * @return
	  */
	 public static int findCheapestPrice2(int n, int[][] flights, int src, int dst, int K) {
		 int value = 0x4f4f4f4f;
		 //任意一个元素[i][j]表示从起点在j步之内到达结点i的最短路径长度
		 int[][]dis=new int[n][K+1];
		 for (int i = 0; i < dis.length; i++) {
			for (int j = 0; j <=K; j++) {
				dis[i][j]=value;
			}
		}
		dis[src][0]=0;
		for (int i = 1; i <=K; i++) {
			
			for (int[] e : flights) {
				dis[e[1]][i]=Math.min(dis[e[1]][i], dis[e[1]][i-1]+e[2]);
			}
		}
		 
		 return dis[dst][K]==value?-1:dis[dst][K];
	 }
	 
	 public static void main(String[] args) {
		 int [][]flights={{0,1,100},{1,2,100},{0,2,500}};
			
			
		System.out.println(findCheapestPrice2(3, flights, 0, 2, 0));;
	}
}
