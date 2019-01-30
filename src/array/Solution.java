package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
	/**
	 * 169. Majority Element
	 * 由于是要找超过n/2的数字，所以在计数的时候，count一定是大于0 的
	 * LANG
	 * @param num
	 * @return
	 */
	public static int majorityElement(int[] num) {
		 int count=1;
		 int major=num[0];
		 for (int i = 1; i < num.length; i++) {
			if (major==num[i]) {
				count++;
			}else {
				count--;
			}
			if (count==0) {
				major=num[i];
				count++;
			}
		}
		 return major;
	 }
	
	/**
	 * 229. Majority Element II
	 * 超过n/3的数字，最多有2个
	 */
	
	public static List<Integer> majorityElement2(int[] nums) {
        int major1=0;int count1=0;
        int major2=0;int count2=0;
        for (int i = 0; i < nums.length; i++) {
			if (nums[i]==major1) {
				count1++;
			}
			else if (nums[i]==major2) {
				count2++;
			}
			else if (count1==0) {
				major1=nums[i];
				count1++;
			}
			else if (count2==0) {
				major2=nums[i];
				count2++;
			}
			else{
				count1--;
				count2--;
			}
			
		}
        List<Integer> list=new ArrayList<>(2);
        count1=count2=0;
        for (int i = 0; i < nums.length; i++) {
        	//有【0,0,0】的情况
        	if (major1!=major2) {
				
        		if (nums[i]==major1) {
        			count1++;
        		}
        		if (nums[i]==major2) {
        			count2++;
        		}
			}else {
				
				if (nums[i]==major1) {
					count1++;
				}
			}
		}
        if (count1>nums.length/3) {
			
        	list.add(major1);
		}
        if (count2>nums.length/3) {
			
        	list.add(major2);
		}
        return list;
    }
	
	
	public static void main(String[] args) {
		int[]num={1,2,2,4,5};
//		System.out.println(majorityElement2(num));;
		
//		int index=Arrays.binarySearch(num, 2);
//		System.out.println(index);
		
//		System.out.println(findClosestElements(num, 3, 2));;
		int index=binarySearch(num, 2);
		
		System.out.println(search(num, 2, index));
	}
	
	/**
	 *  658. Find K Closest Elements
	 *  https://blog.csdn.net/thesnowboy_2/article/details/77441914
	 * LANG
	 * @param arr
	 * @param k
	 * @param x
	 * @return
	 */
	public static List<Integer> findClosestElements(int[] arr, int k, int x) {
		int start = 0, end = arr.length-k;
    
		while(start<end) {
	        int mid = (start + end)/2;
	        if (x - arr[mid] > arr[mid+k]-x)
	            start = mid + 1;
	        else
	            end = mid;
		}

	    List<Integer> results = new ArrayList<Integer>();
	    for(int i=start;i<start+k;i++){
	        results.add(arr[i]);
	    }
	    return results;
    }
	
	/**
	 * 寻找sort数组中重复数组key第一个出现的位置
	 * LANG
	 * @return
	 */
	
	public static int search(int []arr,int key,int index){
		
		for (int i = index; i >=0 ; i--) {
			if (arr[i]==key) {
				continue;
			}else {
				return i+1;
			}
		}
		return index;
	}
	
	/**
	 * 二分法
	 * LANG
	 * @param arr
	 * @param key
	 * @return
	 */
	public static int binarySearch(int [] arr,int key){
		int low=0;int high=arr.length-1;
		while(low<high){
			int mid=low+(high-low)/2;
			if (arr[mid]>key) {
				high=mid;
			}else if (arr[mid]<key) {
				low=mid+1;
			}else {
				return mid;
			}
		}
		
		return -(low+1);
	}
}
