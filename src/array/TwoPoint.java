package array;

import java.util.Arrays;

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
				//赋值
				nums[slow]=nums[fast];
			}
		}
		
		return slow+1;
	}
	
	/**
	 * 80. Remove Duplicates from Sorted Array II
	 * sorted数组内的数最多可以重复两次
	 * LANG
	 * @param nums
	 * @return
	 */
	public int removeDuplicates2(int[] nums) {
		int slow=0;int fast=0;int len=0;int count=0;
		int arrLen=nums.length;int pos=0;
		while(slow<=fast&&fast<arrLen){
			if (nums[slow]>nums[fast]) {
				if (fast<arrLen-1) {
					
					fast++;
				}
			}
			if (nums[slow]==nums[fast]) {
				if (fast<arrLen-1) {
					
					fast++;
				}
				len++;
			}
			if (nums[slow]<nums[fast]) {
				if (len>2+count) {
					
					pos=fast-(len-2-count);
				}else {
					pos=fast;
				}
				while (len>2+count&&fast<arrLen) {
					int temp=nums[slow+2];
					nums[slow+2]=nums[fast];
					nums[fast]=temp;
//					len--;
					slow++;
					fast++;
					count++;
				}
//				if (nums[fast]>=nums[slow]) {
					
					slow=pos;
					fast=slow;
					len=0;
//					count--; 
					count=0;
//				}else {
//					fast++;
//				}
			}
		}
        return slow;
    }
	
	
	
	/**
	 * 27. Remove Element
	 * Given nums = [0,1,2,2,3,0,4,2], val = 2,

		Your function should return length = 5, with the first five elements of nums containing 0, 1, 3, 0, and 4.
		
		排序后，等于val的肯定是在中间，再用后边的进行替换
	 * LANG
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement(int[] nums, int val) {
       
        Arrays.sort(nums);
        int left=0;int arrLen=nums.length;int len=0;
        for (int i = 0; i < arrLen; i++) {
			if (nums[i]==val) {
				left=i;
				break;
			}
		}
        for (int i = left; i < arrLen; i++) {
			if (nums[i]==val) {
				len++;
			}
		}
        if (len>0) {
			int range=arrLen-len-left>len?len:arrLen-len-left;
        	for (int i = 0; i < range; i++) {
        		nums[left+i]=nums[arrLen-1-i];
        	}
		}
        return arrLen-len;
    }
	
	/**
	 * 27. Remove Element
	 * 双指针
	 * LANG
	 * @param nums
	 * @param val
	 * @return
	 */
	public int removeElement2(int[] nums, int val) {
		 int left=0;int right=nums.length-1;
		 while(left<=right){
			 if (nums[left]!=val) {
				left++;
			}else {
				while (right>=0&&nums[right]==val) {
					right--;
				}
				//要对左右指针进行判断，符合条件再进行赋值，以及下一次指针的移动
				if (left<=right) {
					
					nums[left]=nums[right];
					right--;
					left++;
				}
			}
		 }
		 return left;
	}

	
	
	public static void main(String[] args) {
		TwoPoint twoPoint=new TwoPoint();
		int nums[]={0,0,1,1,1,1,2,3,3};
		int len=twoPoint.removeDuplicates2(nums);
		for (int i = 0; i < len; i++) {
			
			System.out.println(nums[i]);;
		}
	}
}
