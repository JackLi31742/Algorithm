package array; 

public class YangHui {
	public static void main(String args[]) {
		int x = 5;// 输入几层是几层
		int num[][] = new int[x][x];// 这个数组有几层
		for (int m = 0; m < x; m++)// 主要是对数组进行赋值  m是层数
		{
			for (int n = 0; n <= m; n++)// 每一层的个数都是小于等于层数的，m代表层数，n代表着第几个数
			{
				if (n == 0 || m == n)// 每一层的开头都是1，m==n的时候也是1,必须要这个，凡事都得有个开头
				{
					num[m][n] = 1;
				} else
					num[m][n] = num[m - 1][n - 1] + num[m - 1][n];// 这个就是递推的方法了，例如3=1+2，3的坐标就是3[3,1]=1[2,0]+2[2,1];
			}
		}
		for (int i = 0; i < x; i++)// 主要是输出数组
		{
			for (int l = i; l < x; l++)// 这个主要是打空格，好看一点，去掉就是直角三角形了
			{
				System.out.print(" ");
			}
			for (int j = x - i; j <= x; j++)// 这个就是打印数组了，每层循环几次就几个
			{
				System.out.print(num[i][x - j] + " ");// 不懂的可以把x替换成10，更加清楚点
			}
			System.out.println();// 每层换行
		}
	}
}
