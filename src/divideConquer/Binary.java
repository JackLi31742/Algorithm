package divideConquer;

import sun.reflect.generics.visitor.Reifier;
import sun.util.logging.resources.logging;

/*分治法，典型应用：归并排序，二分查找，幂函数，通过分支，形成树结构，每一层的叶子节点的和是n，树的高度是logn，所以O(n)=nlogn
 * 由于二分查找会使得另一半失效，所以是O(n)=logn
 */
public class Binary {

	/**
	 * 50. Pow(x, n)
	 * power function 幂函数
	 * 类似于二分查找，
	 * 
	 * x^n=x^(n/2)*x^(n/2) x为even 偶数
	 * x^n=x^[(n-1)/2]*x^[(n-1)/2] x为odd 奇数
	 * 
	 * O(logn)
	 * LANG
	 * @return
	 */
	public double myPow(double x, int n){
		double result=0;
		if (n==0) {
			return 1;
		}
		if (n==1) {
			return x;
		}
		if (n==-1) {
			return 1/x;
		}
		if (n%2==0) {
			double temp=myPow(x, n/2);
			result=temp*temp;
		}else {
			double temp=myPow(x, (n-1)/2);
			result=temp*temp*x;
		}
		return result;
	}
	
	/**
	 * 69. Sqrt(x)
	 * 返回开方的整数部分
	 * Sqrt(x)=Sqrt(x/2)*Sqrt(2) 这个公式是不行的，因为sqrt(2)算不出来
	 * 本题的一个重点是，只需要得到开方后的整数部分，小数是不用管的，所以可以用下面的公式
	 * 
	 * result*result<=x<=(result+1)*(result+1)
	 * sqrt(x)<(x/2+1)
	 * 所以每次就可以减半，类似于二分查找
	 * LANG
	 * @param x
	 * @return
	 */
	public int mySqrt(int x) {
		if (x<1) {
			return 0;
		}
		if (x==1) {
			return 1;
		}
		long right=x/2+1;
		long left=1;
		while(left<=right){
			//必须用long，否则可能加起来超过int的最大值，精度会丢失
			long mid=left+(right-left)/2;
			long square=mid*mid;
			if (square<x) {
				left=mid+1;
			}else if (square>x) {
				right=mid-1;
			}else {
				return (int)mid;
			}
		}
		//出循环的时候，left的值会超过right，所以left=righ+1，应该返回的值，平方后要小于x，所以返回right
        return (int)right;
    }
	
	
	public static void main(String[] args) {
		Binary binary=new Binary();
//		System.out.println(binary.myPow(8, -2));
//		System.out.println(1/2);
		System.out.println(Integer.MAX_VALUE+Integer.MAX_VALUE);;
	}
}
