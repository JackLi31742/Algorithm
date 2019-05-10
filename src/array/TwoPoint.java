package array;

public class TwoPoint {

	/**
	 * 26. Remove Duplicates from Sorted Array
	 *  O(1) extra memory
	 *  使用快慢指针来记录遍历的坐标。

		开始时这两个指针都指向第一个数字
		如果两个指针指的数字相同，则快指针向前走一步
		如果不同，则两个指针都向前走一步
		当快指针走完整个数组后，慢指针当前的坐标加1就是数组中不同数字的个数
		https://github.com/MisterBooo/LeetCodeAnimation/blob/master/notes/LeetCode第26号问题：删除排序数组中的重复项.md
	 *  需要修改原输入数组
	 * LANG
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {

		int slow=0;int fast=0;
		while(slow<=fast&&fast<nums.length){
			if (nums[slow]==nums[fast]) {
				fast++;
			}else {
				slow++;
				nums[slow]=nums[fast];
			}
		}
		
		return slow+1;
	}
	
	/**
	 * 27. Remove Element
	 * Given nums = [0,1,2,2,3,0,4,2], val = 2,

		Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
	 * LANG
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
        
    }

	
	public static void main(String[] args) {
		TwoPoint twoPoint=new TwoPoint();
		int nums[]={1,1,2};
		System.out.println(twoPoint.removeDuplicates(nums));;
	}
}
