package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 2sum，将问题转换为从数组查找target-num[i]是否存在，所以才会使用hashmap以及如果array是sort的，用双指针去查找
 * @author LANG
 *
 */
public class NSum {

	/**
	 * 1. Two Sum
	 * LANG
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
        int result[]=new int[2];
        Map<Integer, Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target-nums[i])) {
				result[0]=i;
				result[1]=map.get(target-nums[i]);
				return result;
			}
			map.put(nums[i], i);
		}
        return result;
    }
	/**
	 * 167. Two Sum II - Input array is sorted
	 * LANG
	 * @param numbers
	 * @param target
	 * @return
	 */
	
	public int[] twoSum2(int[] numbers, int target) {
		int result[]=new int[2];
        Map<Integer, Integer> map=new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
			if (map.containsKey(target-numbers[i])) {
				result[0]=map.get(target-numbers[i]);
				result[1]=i+1;
				return result;
			}
			map.put(numbers[i], i+1);
		}
        return result;
    }
	/**
	 * 上题的双指针
	 * LANG
	 * @param capacity
	 * @param weights
	 * @param numOfContainers
	 */
	public static void findOptimalWeights(double capacity, double[] weights, int numOfContainers){
        double min = 0.0;
        int minPos = 0;
        int maxPos = weights.length - 1;
        int i =0 , j =weights.length-1;

        Arrays.sort(weights);

        while(i < j){
            double sum = weights[i] + weights[j];

            if(sum > min && sum <= capacity){
                min = sum;
                minPos = i;
                maxPos = j;
            }

            if(sum > capacity){
                j--;
            }else {
                i++;
            }
        }

        System.out.println("The two numbers for which sum is closest to target are "
                + weights[minPos] + " and " + weights[maxPos]);
    }
	/**
	 * 无人机送货
	 * LANG
	 * @return
	 */
	public static List<List<Integer>> twoSum3(int sum,List<List<Integer>> forwarding 
			,List<List<Integer>> retrun){
		Comparator<List<Integer>> comparator=new Comparator<List<Integer>>() {
			
			public int compare(List<Integer> list1,List<Integer> list2){
				return list1.get(1)-list2.get(1);
			}
		};
		Collections.sort(forwarding,comparator);
		Collections.sort(retrun,comparator);
		List<List<Integer>> result = new ArrayList<>();

		int maxResult=0;
		for (int i = forwarding.size()-1; i >=0; i--) {
			for (int j = retrun.size()-1; j >=0; j--) {
				int temp=forwarding.get(i).get(1) + retrun.get(j).get(1);
				if (temp <= sum&&temp>=maxResult) {
					maxResult=temp;
					List<Integer> ele = new ArrayList<>();
					ele.add(forwarding.get(i).get(0));
					ele.add(retrun.get(j).get(0));
					result.add(ele);
				}
			}
		}
		
		/*list.sort((e1,e2)->e1.get(2)-e2.get(2));
		int max=list.get(list.size()-1).get(2);
		add(result, list, list.size()-1);
		for (int i = list.size()-2; i >0 ; i--) {
			if (list.get(i).get(2)<max) {
				break;
			}else {
				add(result, list, i);
			}
		}*/
		return result;
	}
	
	public static void add(List<List<Integer>> result,List<List<Integer>> list,int i){
		List<Integer> ele = new ArrayList<>();
		ele.add(list.get(i).get(0));
		ele.add(list.get(i).get(1));
		result.add(ele);
	}
	/**
	 * 双指针无人机送货 没做出来
	 * LANG
	 * @param sum
	 * @param forwarding
	 * @param retrun
	 * @return
	 */
	public static List<List<Integer>> twoSum4(int capacity,List<List<Integer>> forwarding 
			,List<List<Integer>> retrun){
		Comparator<List<Integer>> comparator=new Comparator<List<Integer>>() {
			
			public int compare(List<Integer> list1,List<Integer> list2){
				return list1.get(1)-list2.get(1);
			}
		};
		Collections.sort(forwarding,comparator);
		Collections.sort(retrun,comparator);
		int dis=Integer.MAX_VALUE;
		int minPos1=0; 
		int maxPos2=retrun.size()-1;
		int resPos1=0;int resPos2=0;
		List<List<Integer>> result = new ArrayList<>();
		while(minPos1<forwarding.size()&&maxPos2>=0){
			int sum=forwarding.get(minPos1).get(1)+retrun.get(maxPos2).get(1);
			if (Math.abs(sum-capacity)<dis) {
				resPos1=minPos1;
				resPos2=maxPos2;
				dis=Math.abs(sum-capacity);
			}
			if (sum>capacity) {
				maxPos2--;
			}else {
				minPos1++;
			}
		}
		
		return null;
	}
	
	/**
	 * 15. 3Sum
	 * Given an array nums of n integers, 
	 * are there elements a, b, c in nums such that a + b + c = 0? 
	 * Find all unique triplets in the array which gives the sum of zero.
	 * LANG
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
//		List<List<Integer>> result=new ArrayList<>();
		Set<List<Integer>> resultSet=new HashSet<>();
		if (nums!=null) {
			
			int len=nums.length;
			if (len>0) {
				
				Arrays.sort(nums);
//				int tempSum=nums[0];
				for (int i = 0; i < len; i++) {
					int sum=0-nums[i];
//					if (i>0) {
//						
//						if (tempSum!=sum) {
//							
//							tempSum=sum;
//						}else {
//							continue;
//						}
//					}
					int left=i+1;int right=len-1;
					List<Integer> list=null;
					while(left<right){
						int temp=nums[left]+nums[right];
						if (temp>sum) {
							right--;
						}else if (temp<sum) {
							left++;
						}else {
							list=new ArrayList<>();
							list.add(nums[i]);
							list.add(nums[left]);
							list.add(nums[right]);
							resultSet.add(list);
							left++;
						}
					}
					
				}
			}
		}
        return new ArrayList<>(resultSet);
    }
 
	/**
	 * 使用过滤
	 * LANG
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> result=new ArrayList<>();
		if (nums!=null) {
			int len=nums.length;
			if (len>0) {
				Arrays.sort(nums);
				for (int i = 0; i < len; i++) {
					if (i==0||i>0&&nums[i]!=nums[i-1]) {
						
						int sum=0-nums[i];
						int left=i+1;int right=len-1;
						while(left<right){
							int temp=nums[left]+nums[right];
							if (temp>sum) {
								right--;
							}else if (temp<sum) {
								left++;
							}else {
								result.add(Arrays.asList(nums[i],nums[left],nums[right]));
								//一直把相同的过滤完
								while(left<right&&nums[left]==nums[left+1]){
									left++;
								}
								while(left<right&&nums[right]==nums[right-1]){
									right--;
								}
								//相等的情況下，left移动，right不动，必然是不相等的，所以两个指针要一起动
									left++;
									right--;
							}
						}
					}
					
				}
			}
		}
		return result;
	}
	
	/**
	 * 16. 3Sum Closest
	 * Given an array nums of n integers and an integer target, 
	 * find three integers in nums such that the sum is closest to target. Return the sum of the three integers.
	 * 
	 * Given array nums = [-1, 2, 1, -4], and target = 1.

		The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
	 * LANG
	 */
	
	public int threeSumClosest(int[] nums, int target) {
        int dis=Integer.MAX_VALUE;
        int result=Integer.MIN_VALUE;
        if (nums!=null) {
        	int len=nums.length;
			if (len>0) {
				Arrays.sort(nums);
				int sum=0;
				for (int i = 0; i < len; i++) {
					int first=nums[i];
					int left=i+1;int right=len-1;
					while(left<right){
						sum=first+nums[left]+nums[right];
						int temp=Math.abs(sum-target);
						if (temp<=dis) {
							dis=temp;
							result=sum;
						}
						//不能把指针移动放在temp和dis的比较中
						if (sum<target) {
							left++;
						}else if (sum>target) {
							right--;
						}else{
							break;
						}
						
					}
				}
            }
			
        
       }
        
        return result;
	}
	
	/**
	 * 18. 4Sum
	 * a + b + c + d = target?
	 * The solution set must not contain duplicate quadruplets.
	 * 
	 * http://www.cnblogs.com/strugglion/p/6412116.html
	 * LANG
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> result = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return result;
		}
		int len = nums.length;
		Arrays.sort(nums);
		Map<Integer, List<List<Integer>>> map = new HashMap<>();
		int sum = 0;
		for (int i = 0; i < len; i++) {
//			if (i > 0 && nums[i] == nums[i - 1]) {
//				continue;
//			}
			for (int j = i + 1; j < len; j++) {
//				if ((j - i > 1) && (nums[j] == nums[j - 1])) {
//					continue;
//				}
				sum = nums[i] + nums[j];

				if (map.containsKey(sum)) {
					map.get(sum).add(Arrays.asList(i, j));
				} else {
					List<List<Integer>> pair = new ArrayList<>();
					pair.add(Arrays.asList(i, j));
					map.put(sum, pair);
				}
			}
		}

		Set<Integer> keys = map.keySet();
		for (Integer key : keys) {
			if (map.containsKey(target - key)) {

				List<List<Integer>> value1 = map.get(key);
				List<List<Integer>> value2 = map.get(target - key);
				for (int i = 0; i < value1.size(); i++) {
					for (int j = 0; j < value2.size(); j++) {
						List<Integer> v1 = value1.get(i);
						List<Integer> v2 = value2.get(j);
						if (v1.get(1) < v2.get(0) ) {
							List<Integer> temp = new ArrayList<>();
							temp.add(nums[v1.get(0)]);
							temp.add(nums[v1.get(1)]);
							temp.add(nums[v2.get(0)]);
							temp.add(nums[v2.get(1)]);
							if (!result.contains(temp)) {
								result.add(temp);
							}
						}
					}
				}
			}
		}
		return result;
    }

	/**
	 * 18. 4Sum
	 * 规约到2sum
	 * LANG
	 * @param nums
	 * @param target
	 * @return
	 */
	int len = 0;
	public List<List<Integer>> fourSum2(int[] nums, int target) {
		if (nums==null||nums.length<=0) {
			return Collections.emptyList();
		}
		len = nums.length;
		//sort 不能放在递归函数中
		Arrays.sort(nums);
		return kSum(nums,4,target,0);
	}
 
	public List<List<Integer>> kSum(int[]nums,int k, int target,int index){
		if (k<0) {
			return Collections.emptyList();
		}
		List<List<Integer>> result = new ArrayList<>();
		if (k==1) {
			return oneSum(nums, target);
		}
		if (k==2) {
			return twoSum4(nums, target,index);
		}else {
			//不用if(k>2)减少一次判断
		
											//减少轮次，类似于植树问题，数组里后边的数字数量不够，i就不用继续自增
			for (int i = index; i < len-k+1; i++) {
				//在这加，0000的test过不去
//				if (i==0||i>index+1&&nums[i]!=nums[i-1]) {
					
					int temp=target-nums[i];
					List<List<Integer>> subResult=kSum(nums, k-1, temp,i+1);
					if (subResult.size()>0) {
						for (int j = 0; j < subResult.size(); j++) {
							List<Integer> sublist=subResult.get(j);
							sublist.add(nums[i]);
//							result.add(sublist);
						}
						result.addAll(subResult);
					}
//				}
				//判断在后边，所以得和后一个比较，所以不能加i>0这样的判断
				while(i<len-1&&nums[i]==nums[i+1]){
					i++;
				}
			}
		}
		return result;
	}
	
	public List<List<Integer>> twoSum4(int[] nums, int target,int index){
		int left=index;int right=len-1;
		List<List<Integer>> result = new ArrayList<>();
		while(left<right){
			//只在下边加过滤就行
//			if (left==0||left>0&&nums[left]!=nums[left-1]) {
				
				int temp=nums[left]+nums[right];
				if (temp>target) {
					right--;
				}else if (temp<target) {
					left++;
				}else {
					List<Integer> list=new ArrayList<>();
					list.add(nums[left]);
					list.add(nums[right]);
					result.add(list);
					while(left<right&&nums[left]==nums[left+1]){
						left++;
					}
					while(left<right&&nums[right]==nums[right-1]){
						right--;
					}
					left++;
					right--;
				}
//			}else {
//				left++;
//			}
			
		}
		return result;
	}
	public List<List<Integer>> oneSum(int[] nums, int target){
		
		List<List<Integer>> result = new ArrayList<>();
		for (int i = 0; i < len; i++) {
			if (nums[i]==target&&(i==0||nums[i]!=nums[i-1])) {
				result.add(Arrays.asList(nums[i]));
			}
		}
		return result;
	}
	public static void main(String[] args) {
		NSum solution=new NSum();
		int[] nums1={0, 0, 0,0};
		
//		List<List<Integer>> result=solution.threeSum(nums1);
//		System.out.println(result);
		
		int []nums2={1, 0, -1, 0, -2, 2};
		System.out.println(solution.fourSum2(nums2, 0));;
		
		/*List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < nums1.length; i++) {
			temp.add(0,nums1[i]);
		}
		System.out.println(temp);*/
	}
}
