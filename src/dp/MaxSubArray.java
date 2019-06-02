package dp;

public class MaxSubArray {

	/**
	 * 53. Maximum Subarray
	 * https://blog.csdn.net/lw_power/article/details/80892362
	 * dp[i]代表以nums[i]结尾的连续子数组的最大和
	 * dp[i]=max(nums[i],dp[i-1]+nums[i])
	 * -2,1,-3,4,-1,2,1,-5,4
	 *  dp[0]=-2,nums[1]=1,dp[1]=1
		dp[1]=1,nums[2]=-3,dp[2]=-2
		dp[2]=-2,nums[3]=4,dp[3]=4
		dp[3]=4,nums[4]=-1,dp[4]=3
		dp[4]=3,nums[5]=2,dp[5]=5
		dp[5]=5,nums[6]=1,dp[6]=6
		dp[6]=6,nums[7]=-5,dp[7]=1
		dp[7]=1,nums[8]=4,dp[8]=5

	 * LANG
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int maxSum=nums[0];
		int len=nums.length;
		int []dp=new int[len];
		dp[0]=nums[0];
		for (int i = 1; i < len; i++) {
			//System.out.print("dp["+(i-1)+"]="+dp[i-1]+",nums["+i+"]="+nums[i]+",");
			//dp数组把到nums[i]为止最大的子数组和全部记录了下来，
			//所以在计算子数组和的时候，窗口可以从-2滑到1，而到了4和-1的时候，4在dp[3]的时候记录了，4+（-1）就记录在了dp[4]
			dp[i]=Math.max(dp[i-1]+nums[i], nums[i]);
			//System.out.println("dp["+i+"]="+dp[i]);
		}
		for (int i = 0; i < dp.length; i++) {
			if (dp[i]>maxSum) {
				maxSum=dp[i];
			}
		}
		return maxSum;
	}
	
	//把dp数组变成当前变量，随时跟maxSum比较，随时赋值，就不用保存了
	public int maxSubArray2(int[] nums) {
		int maxSum=nums[0];
		int len=nums.length;
		int curSum=nums[0];
		for (int i = 1; i < len; i++) {
			
			curSum=Math.max(curSum+nums[i], nums[i]);
			if (curSum>maxSum) {
				maxSum=curSum;
			}
			
		}
		
		return maxSum;
	}
	
	

	public static void main(String[] args) {
		MaxSubArray maxSubArray=new MaxSubArray();
		int nums[]={-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(maxSubArray.maxSubArray2(nums));
	}
}
