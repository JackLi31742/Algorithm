package array;

//滑动窗口
public class Window {

	/**
	 * 53. Maximum Subarray
	 * contiguous subarray (containing at least one number) 
	 * which has the largest sum and return its sum.
	 * 
	 * dp的解法在MaxSubArray
	 * LANG
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		//如果nums都是负数，所以不能初始化为0
        int maxSum=nums[0];
        int len=nums.length;
        for (int k = 1; k <= len; k++) {
//			int curWindowSum=window(nums, k ,len);
			int curWindowSum=slidWindow(nums, k ,len);
			maxSum=maxSum>curWindowSum?maxSum:curWindowSum;
		}
        return maxSum;
    }
	
	//长度k的窗口
	public int window(int[] nums,int k,int len){
		int curMaxSum=nums[0];
		for (int i = 0; i < len-k+1; i++) {
			//每次都重置
			int curWindowSum=0;
			for (int j = i; j < i+k; j++) {
				curWindowSum+=nums[j];
			}
			curMaxSum=curMaxSum>curWindowSum?curMaxSum:curWindowSum;
		}
		return curMaxSum;
	}
	
	//滑动窗口
	public int slidWindow(int[] nums,int k,int len){
		int curWindowSum=0;
		//先计算第一个窗口的
		for (int i = 0; i < k; i++) {
			curWindowSum+=nums[i];
		}
		int curMaxSum=curWindowSum;
		for (int i = 1; i < len-k+1; i++) {// 从下个元素开始，即窗口向右滑动
			curWindowSum=curWindowSum-nums[i-1]+nums[i+k-1];// 减去失效值，加上最新值
			curMaxSum=curMaxSum>curWindowSum?curMaxSum:curWindowSum;
		}
		return curMaxSum;
	}
	
	public static void main(String[] args) {
		Window window=new Window();
		int nums[]={-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(window.maxSubArray(nums));;
	}
}
