package array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution {
	
	/**
	 * 268. Missing Number
	 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
	 *  find the one that is missing from the array.
	 * 
	 */
    
    /**
     * 排序
     */
	public static int missingNumber(int[] nums) {
		Arrays.sort(nums);

        // Ensure that n is at the last index
        if (nums[nums.length-1] != nums.length) {
            return nums.length;
        }
        // Ensure that 0 is at the first index
        else if (nums[0] != 0) {
            return 0;
        }

        // If we get here, then the missing number is on the range (0, n)
        for (int i = 1; i < nums.length; i++) {
            int expectedNum = nums[i-1] + 1;
            if (nums[i] != expectedNum) {
                return expectedNum;
            }
        }

        // Array was not missing any numbers
        return -1;
    }
	/**
	 * hashset Time:O(N),space:O(N)
	 * 创建set是O(N),for 是O(N),加起来是O(2n)也就是O(N)
	 * LANG
	 * @param nums
	 * @return
	 */
	public int missingNumber2(int[] nums) {
        Set<Integer> numSet = new HashSet<Integer>();
        for (int num : nums) numSet.add(num);

        int expectedNumCount = nums.length + 1;
        for (int number = 0; number < expectedNumCount; number++) {
            if (!numSet.contains(number)) {
                return number;
            }
        }
        return -1;
    }
	/**
	 * 异或位运算，nums是顺序不重要，因为异或运算是可交换的
	 * Time:O(n) xor运算n次
	 * space:O(1)
	 * LANG
	 * @param nums
	 * @return
	 */
	public int missingNumber3(int[] nums) {
        int missing = nums.length;
        for (int i = 0; i < nums.length; i++) {
            missing ^= i ^ nums[i];
        }
        return missing;
    }
	/**
	 * 448. Find All Numbers Disappeared in an Array
	 * LANG
	 * @param nums
	 * @return
	 */
	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> list = new ArrayList<>();

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				list.add(i + 1);
			}

		}
		return list;
	}
	
	/**
	 * 287. Find the Duplicate Number
	 * LANG
	 * @param nums
	 * @return
	 */
	public int findDuplicate(int[] nums) {
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				return nums[i];
			}
			set.add(nums[i]);
		}
        return -1;
    }
	
	/**
	 * 二分法查找,关键就是nums里的成员都是1到n的
	 */
	public int findDuplicate2(int[] nums) {
		int min=0; int max=nums.length-1;
		while(min<=max){
			int mid=min+(max-min)/2;
			int count=0;
			// 计算总数组中有多少个数小于等于中间数
			for (int i = 0; i < nums.length; i++) {
				//所以比较的是用下标去和value比较
				if (nums[i]<=mid) {
					count++;
				}
			}
			if (count>mid) {
				max=mid-1;
			}else {
				min=mid+1;
			}
		}
		return min;
	}
	/**
	 * 双指针，类似于142. Linked List Cycle II
	 * https://segmentfault.com/a/1190000003817671
	 * LANG
	 * @param nums
	 * @return
	 */
	public int findDuplicate3(int[] nums) {
		int slow=0;int fast=0;int entry=0;
		while(slow<=nums.length-1||fast<=nums.length-1){
			//走一步
			slow=nums[slow];
			//走两步
			fast=nums[nums[fast]];
			if (slow==fast) {
				while(entry!=slow){
					entry=nums[entry];
					slow=nums[slow];
				}
				return entry;
			}
		}
		return -1;
	}
	/**
	 * 442. Find All Duplicates in an Array
	 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

		Find all the elements that appear twice in this array.
	 * LANG
	 * @param nums
	 * @return
	 */
	public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
			if (set.contains(nums[i])) {
				result.add(nums[i]);
			}
			set.add(nums[i]);
		}
        return result;
    }
	 
	
	

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
//		int[]num={48,86,110,115,153,169,194,203,237,275,277,284,296,315,344,354,377,380,418,447,473,488,516,531,538,548,578,582,592,605,643,677,685,713,743,744,750,784,803,828,832,853,881,919,955,990,1026,1051,1076,1097,1122,1135,1150,1160,1171,1186,1189,1198,1235,1241,1271,1293,1323,1354,1382,1411,1426,1464,1501,1513,1541,1547,1548,1555,1583,1618,1634,1643,1681,1700,1723,1748,1761,1782,1816,1853,1871,1883,1893,1913,1927,1935,1960,1981,1995,2010,2017,2045,2081,2085,2103,2139,2168,2195,2220,2233,2269,2303,2335,2356,2372,2391,2418,2441,2455,2463,2501,2521,2549,2560,2568,2589,2615,2647,2667,2698,2703,2707,2739,2755,2762,2786,2813,2827,2850,2869,2878,2893,2901,2904,2933,2940,2957,2985,3002,3018,3034,3038,3051,3083,3087,3124,3142,3147,3172,3181,3192,3200,3235,3250,3271,3307,3335,3350,3388,3411,3419,3426,3454,3467,3482,3519,3534,3545,3579,3584,3609,3641,3643,3668,3704,3730,3740,3768,3780,3805,3827,3840,3878,3909,3912,3945,3965,3987,4024,4027,4063,4096,4097,4126,4136,4173,4177,4191,4209,4220,4253,4255,4281,4300,4323,4329,4365,4373,4409,4411,4445,4464,4494,4526,4537,4566,4572,4605,4618,4639,4661,4671,4680,4685,4721,4754,4764,4789,4808,4830,4835,4858,4882,4887,4903,4925,4932,4969,4978,4984,4993,5014,5037,5064,5075,5081,5087,5124,5138,5140,5154,5181,5209,5243,5245,5249,5262,5288,5326,5331,5342,5375,5411,5419,5446,5456,5469,5497,5514,5543,5564,5591,5625,5654,5675,5711,5712,5713,5740,5776,5803,5808,5822,5847,5859,5866,5891,5907,5939,5976,6000,6035,6065,6072,6080,6083,6116,6127,6129,6166,6183,6221,6255,6264,6273,6289,6292,6294,6324,6339,6376,6408,6414,6446,6454,6484,6497,6518,6536,6548,6561,6574,6575,6583,6618,6646,6651,6682,6691,6708,6733,6734,6746,6783,6795,6816,6832,6843,6859,6873,6880,6907,6936,6961,6975,6984,7013,7041,7077,7079,7095,7104,7132,7151,7189,7225,7245,7254,7282,7310,7342,7348,7367,7390,7422,7432,7470,7499,7502,7521,7533,7547,7578,7602,7621,7656,7693,7707,7710,7727,7762,7763,7780,7793,7813,7823,7830,7833,7849,7862,7894,7924,7950,7965,7996,8032,8034,8060,8081,8092,8123,8160,8172,8190,8193,8211,8243,8254,8262,8275,8301,8338,8343,8349,8355,8390,8393,8419,8451,8489,8514,8545,8580,8596,8627,8649,8655,8686,8690,8710,8748,8750,8751,8755,8770,8782,8809,8823,8830,8832,8858,8870,8882,8902,8914,8944,8977,8978,9004,9025,9061,9094,9120,9133,9159,9171,9198,9205,9210,9225,9263,9291,9315,9341,9375,9405,9442,9466,9495,9514,9550,9551,9577,9584,9615,9644,9655,9687,9713,9738,9773,9791,9794,9827,9835,9873,9879,9917,9938,9969,9976,9988,10025,10048,10073,10090,10102,10132,10161,10191,10210,10224,10228,10244,10258,10275,10304,10314,10343,10355,10371,10372,10400,10421,10447,10451,10469,10471,10481,10493,10529,10565,10599,10612,10636,10671,10704,10710,10712,10718,10733,10738,10755,10774,10808,10840,10857,10867,10878,10900,10913,10923,10947,10954,10961,10980,11008,11039,11064,11088,11094,11121,11132,11144,11165,11184,11199,11226,11241,11274,11293,11327,11355,11376,11379,11412,11442,11444,11456,11465,11488,11510,11511,11520,11552,11585,11586,11612,11621,11640,11658,11659,11677,11696,11725,11743,11772,11801,11828,11835,11870,11882,11915,11925,11947,11951,11961,11991,12013,12044,12062,12072,12082,12117,12134,12165,12188,12223,12245,12248,12255,12270,12295,12332,12347,12372,12396,12398,12402,12432,12435,12439,12440,12458,12482,12512,12540,12564,12601,12639,12651,12682,12698,12714,12742,12779,12816,12820,12824,12849,12868,12886,12893,12929,12940,12959,12983,13003,13005,13026,13050,13076,13080,13109,13115,13117,13118,13123,13149,13155,13178,13212,13236,13268,13274,13301,13315,13339,13376,13404,13420,13430,13441,13460,13476,13477,13484,13486,13491,13509,13521,13525,13538,13563,13579,13582,13604,13622,13652,13680,13713,13727,13764,13782,13785,13804,13825,13858,13874,13903,13932,13943,13962,13968,13981,14012,14025,14047,14059,14062,14078,14084,14100,14114,14126,14162,14163,14172,14175,14184,14207,14227,14238,14261,14283,14297,14330,14367,14388,14389,14411,14435,14453,14471,14505,14520,14523,14530,14559,14590,14595,14602,14609,14632,14662,14681,14688,14709,14736,14750,14783,14798,14802,14838,14846,14863,14870,14890,14924,14945,14979,14995,15030,15052,15062,15065,15090,15091,15102,15123,15134,15161,15172,15187,15216,15226,15244,15269,15276,15281,15295,15324,15337,15356,15380,15405,15417,15429,15449,15474,15485,15496,15525,15551,15583,15605,15636,15669,15691,15692,15724,15749,15754,15774,15781,15785,15787,15791,15816,15827,15836,15854,15868,15906,15942,15979,15996,16032,16035,16055,16089,16097,16098,16099,16136,16170,16191,16194,16226,16248,16286,16317,16326,16341,16369,16376,16399,16416,16448,16450,16459,16486,16511,16516,16517,16542,16569,16574,16611,16620,16622,16648,16668,16704,16735,16738,16761,16769,16792,16793,16794,16817,16849,16858,16872,16903,16905,16943,16977,17011,17021,17057,17062,17084,17115,17145,17178,17199,17220,17230,17242,17276,17302,17309,17314,17324,17362,17372,17405,17407,17417,17451,17482,17513,17530,17566,17580,17599,17635,17672,17682,17692,17696,17726,17741,17760,17785,17791,17810,17842,17870,17892,17917,17950,17969,17981,17996,18034,18035,18067,18101,18135,18140,18154,18185,18196,18209,18245,18273,18275,18304,18312,18330,18333,18369,18396,18420,18423,18445,18478,18492,18525,18544,18581,18593,18595,18618,18634,18645,18676,18696,18699,18702,18728,18740,18763,18785,18798,18832,18854,18857,18862,18889,18919,18957,18985,19022,19043,19077,19098,19102,19129,19140,19159,19195,19222,19248,19286,19309,19334,19361,19368,19385,19408,19425,19437,19475,19498,19517,19534,19559,19564,19586,19589,19591,19617,19642,19652,19656,19661,19692,19730,19767,19769,19781,19819,19832,19846,19869,19888,19912,19947,19975,20011,20043,20055,20065,20082,20094,20096,20099,20135,20166,20204,20227,20261,20290,20306,20339,20342,20377,20401,20431,20467,20496,20522,20549,20566,20599,20634,20652,20655,20656,20687,20721,20725,20734,20750,20766,20772,20776,20778,20799,20811,20830,20859,20879,20881,20907,20919,20937,20957,20971,20995,21016,21051,21053,21068,21094,21095,21101,21138,21159,21163,21198,21206,21240,21248,21274,21285,21298,21325,21329,21338,21352,21362,21393,21416,21431,21465,21474,21487,21521,21528,21551,21556,21591,21604,21612,21626,21664,21673,21684,21703,21718,21732,21746,21768,21770,21791,21819,21853,21875,21889,21919,21948,21955,21962,21973,22009,22011,22024,22038,22048,22060,22064,22067,22094,22109,22143,22176,22198,22206,22226,22251,22271,22276,22284,22317,22347,22365,22366,22390,22392,22421,22442,22459,22496,22507,22537,22538,22539,22563,22579,22605,22639,22668,22681,22699,22703,22730,22759,22765,22792,22811,22841,22850,22860,22874,22907,22930,22964,22966,22983,22991,23006,23043,23077,23115,23127,23147,23158,23194,23231,23246,23267,23272,23309,23342,23367,23398,23406,23431,23438,23448,23477,23493,23521,23529,23547,23574,23598,23607,23645,23663,23670,23686,23719,23741,23768,23770,23805,23828,23839,23860,23862,23893,23895,23910,23946,23966,23998,24035,24038,24042,24047,24074,24092,24128,24148,24168,24195,24203,24237,24263,24269,24297,24300,24338,24376,24391,24426,24448,24463,24469,24482,24502,24523,24531,24534,24558,24583,24588,24617,24650,24656,24664,24676,24689,24727,24741,24765,24772,24785,24823,24857,24873,24884,24898,24900,24927,24952,24953,24966,25001,25034,25039,25072,25090,25117,25122,25147,25183,25185,25196,25212,25241,25257,25283,25287,25291,25308,25330,25348,25378,25387,25411,25439,25468,25505,25527,25535,25537,25566,25596,25626,25660,25688,25721,25759,25796,25831,25857,25872,25902,25919,25941,25950,25987,25996,26016,26024,26025,26057,26074,26090,26122,26126,26148,26185,26190,26197,26199,26231,26266,26297,26321,26337,26355,26387,26416,26454,26463,26494,26509,26519,26534,26571,26592,26594,26632,26670,26707,26711,26716,26728,26761,26772,26782,26789,26794,26797,26816,26835,26871,26883,26914,26922,26954,26984,27004,27015,27028,27029,27066,27093,27111,27115,27122,27145,27180,27204,27210,27224,27235,27267,27280,27286,27310,27341,27356,27390,27392,27428,27457,27490,27512,27546,27565,27587,27595,27621,27649,27679,27697,27706,27727,27748,27751,27763,27792,27802,27826,27846,27863,27901,27913,27944,27959,27997,28031,28067,28070,28102,28130,28158,28184,28197,28199,28228,28232,28270,28274,28300,28307,28336,28344,28368,28398,28419,28424,28430,28441,28465,28491,28496,28534,28567,28595,28613,28624,28630,28647,28656,28667,28675,28689,28703,28737,28747,28756,28778,28779,28800,28832,28837,28864,28884,28915,28930,28942,28944,28981,28994,29001,29017,29046,29058,29088,29100,29135,29144,29180,29206,29228,29256,29261,29288,29302,29339,29356,29368,29373,29384,29393,29406,29418,29441,29450,29456,29485,29504,29542,29573,29579,29585,29586,29591,29602,29620,29621,29658,29673,29703,29706,29712,29741,29745,29773,29790,29809,29812,29829,29866,29893,29930,29944,29972,29975,30001,30023,30055,30059,30073,30076,30090,30122,30149,30187,30221,30238,30244,30279,30289,30300,30313,30321,30331,30362,30377,30393,30421,30455,30471,30492,30500,30520,30547,30570,30596,30611,30620,30642,30670,30697,30701,30723,30728,30745,30748,30781,30803,30814,30816,30818,30833,30835,30843,30867,30895,30924,30931,30968,30985,31006,31021,31044,31049,31068,31094,31120,31123,31158,31162,31197};
//		System.out.println(majorityElement2(num));;
		
//		int index=Arrays.binarySearch(num, 2);
//		System.out.println(index);
		
//		System.out.println(findClosestElements(num, 3, 2));;
//		int index=binarySearch(num, 2);
		
//		System.out.println(search(num, 2, index));
		
		/*for (int i : kClosestNumbers(num,929,174)) {
			System.out.println(i);;
			
		}*/
//		System.out.println(0x3f3f3f3f);
		
		List<List<Integer>> forward=new ArrayList<>();
		List<Integer> list1=new ArrayList<>();
		list1.add(1);
		list1.add(2000);
		List<Integer> list2=new ArrayList<>();
		list2.add(2);
		list2.add(10000);
		List<Integer> list3=new ArrayList<>();
		list3.add(3);
		list3.add(9000);
		List<Integer> list8=new ArrayList<>();
		list8.add(4);
		list8.add(5000);
		forward.add(list1);
		forward.add(list2);
		forward.add(list3);
		forward.add(list8);
		
		List<List<Integer>> retu=new ArrayList<>();
		List<Integer> list4=new ArrayList<>();
		list4.add(1);
		list4.add(7000);
		List<Integer> list5=new ArrayList<>();
		list5.add(2);
		list5.add(12000);
		List<Integer> list6=new ArrayList<>();
		list6.add(3);
		list6.add(8000);
		List<Integer> list7=new ArrayList<>();
		list7.add(4);
		list7.add(5000);
		retu.add(list4);
		retu.add(list5);
		retu.add(list6);
		retu.add(list7);
		//List<List<Integer>> result=twoSum3(11000,forward,retu);
		
		//System.out.println(result);;
		
		
		Solution solution=new Solution();
		/*int[]arr={1,2,3,4,5,6,7};
		List<Integer> list=new ArrayList<>();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		int[]arr1=solution.SumOfWindow(arr, 3);
		for (int i = 0; i < arr1.length; i++) {
			System.out.println(arr1[i]);
		}
		System.out.println();*/
		/*double capacity=20.0; double[] weights={10,3,7,17,5};
		findOptimalWeights(capacity,weights,0);
		
		int []nums1 = {4,5,6,0,0,0};
		
		int[] nums2={1,2,3};
		merge(nums1, 3, nums2, 3);
		
		
		String []A={"amazon","apple","facebook","google","leetcode"};
		String [] B ={"e","o"};
		wordSubsets(A, B);*/
		
		/*int[]nums={9,6,4,2,3,5,7,0,1};
		missingNumber(nums);*/
		
//		int[]states={1,1,1,0,1,1,1,1};
//		System.out.println(solution.cellCompete(states, 2));;
		
		
		
		Interval i1=new Interval(65,424);
		Interval i2=new Interval(351,507);
		Interval i3=new Interval(314,807);
		Interval i4=new Interval(387,722);
		Interval i5=new Interval(19,797);
		Interval i6=new Interval(259,722);
		Interval i7=new Interval(165,221);
		Interval i8=new Interval(136,897);
		
		List<Interval> intervals=new ArrayList<>();
		intervals.add(i1);
		intervals.add(i2);
		intervals.add(i3);
		intervals.add(i4);
		intervals.add(i5);
		intervals.add(i6);
		intervals.add(i7);
		intervals.add(i8);
//		System.out.println(solution.minMeetingRooms(intervals));;
		
		int []nums={4,3,2,7,8,2,3,1};
		solution.findDisappearedNumbers(nums);
		
	}
	
	/**
	 *  658. Find K Closest Elements
	 *  https://blog.csdn.net/thesnowboy_2/article/details/77441914
	 *  直接返回需要的k个数字的最左的下标，首先初始化left=0，right=arr.size()-k，保证在二分过程中不会越界，
	 *  每次比较arr[mid]和arr[mid+k]哪个离x近，如果arr[mid+k]近，那么意味着如下不变约束成立：
		abs(arr[mid+k+d]-target)<abs(arr[mid+d]-target)  0<=d<k
		即对以arr[mid+k]为起点向右数k个数肯定比以arr[mid]为起点向右数k个数更靠近target。
		所以以arr[mid]为起点及小于mid的部分肯定不是最优解，所以抛弃mid的左半部分（包含mid），即left=mid+1。
		同理对于另外一边也是相同的思想，最后的结论是right=mid。这里要注意死循环处理。即：
		
		abs(arr[mid+k+d]-target)>=abs(arr[mid+d]-target)
		这里一定要包含等于符号，否则对于如下的输入[1,2,3,4,5] k=4,target=3。
		最后会出现两个满足条件的结果[1,2,3,4]和[2,3,4,5]由于题目要求当距离相等时，优先考虑左边，
		所以等于符号的左右便是当出现距离的情况是，让right=mid，也罢右边界收缩到mid，从而保留左半部分。

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
	 * 460. Find K Closest Elements
	 * lintcode
	 * LANG
	 * @param A
	 * @param target
	 * @param k
	 * @return
	 */
	public static int[] kClosestNumbers(int[] A, int target, int k) {
		int start =0;int end=A.length-k;
		while(start<end){
			int mid=start+(end-start)/2;
			if (target-A[mid]>A[mid+k]-target) {
				start=mid+1;
			}else {
				end=mid;
			}
		}
		
		
		List<Integer> list=new ArrayList<Integer>(k);
		for (int i = start; i < start+k; i++) {
			list.add(A[i]);
		}
		Comparator<Integer> comparator=new Comparator<Integer>() {
			
			public int compare(Integer x,Integer y){
				return compareElement(x,y,target);
			}
		};
		Collections.sort(list,comparator);
		
		return list.stream().mapToInt(Integer::valueOf).toArray();
	}
	
	public static int compareElement(int x,int y,int target){
		int disX=Math.abs(x-target);
		int disY=Math.abs(y-target);
		int disCom=disX-disY;
		if (disCom==0) {
			return x-y;
		}
		return disCom;
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
	 * 88. Merge Sorted Array
	 * 双指针
	 * LANG
	 * @param nums1
	 * @param m
	 * @param nums2
	 * @param n
	 */
	public static void merge(int[] nums1, int m, int[] nums2, int n) {
		int j=nums1.length-1;
        while(m>=1&&n>=1){
        	if (nums1[m-1]<nums2[n-1]) {
				nums1[j]=nums2[n-1];
				n--;
			}else {
				nums1[j]=nums1[m-1];
				m--;
			}
        	j--;
        }
        while (n>=1) {
			nums1[j]=nums2[n-1];
			j--;
			n--;
		}
    }
	/**
	 * 56. Merge Intervals
	 * Input: [[1,3],[2,6],[8,10],[15,18]]
		Output: [[1,6],[8,10],[15,18]]
		Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
		
		Time complexity : O(nlgn)

		Other than the sort invocation, we do a simple linear scan of the list, 
		so the runtime is dominated by the O(nlgn) complexity of sorting.
		
		Space complexity : O(1) (or O(n))
		
		If we can sort intervals in place, we do not need more than constant additional space. 
		Otherwise, we must allocate linear space to store a copy of intervals and sort that.
	 * LANG
	 * @param intervals
	 * @return
	 */
	public List<Interval> merge(List<Interval> intervals) {
		
		Comparator<Interval> comparator=new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return Integer.compare(a.start, b.start);
			}
		};
		Collections.sort(intervals, comparator);

        LinkedList<Interval> result = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (result.isEmpty() || result.getLast().end < interval.start) {
                result.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous intervals.
            else {
                result.getLast().end = Math.max(result.getLast().end, interval.end);
            }
        }

        return result;
    }
	
	/**
	 * lint 920. Meeting Rooms
	 * 类似于上一题，只是不是合并，看是否有重叠
	 * LANG
	 * @param intervals
	 * @return
	 */
	public boolean canAttendMeetings(List<Interval> intervals) {
		
		Comparator<Interval> comparator=new Comparator<Interval>() {
			public int compare(Interval i1,Interval i2){
				return Integer.compare(i1.start, i2.start);
			}
		};
		
		Collections.sort(intervals,comparator);
		
		for (int i = 1; i < intervals.size(); i++) {
			if (intervals.get(i).start<intervals.get(i-1).end) {
				return false;
			}
		}
		return true;
    }
	
	/**
	 * 919. Meeting Rooms II
	 * 计算需要几个不同的房间才可以同时进行会议
	 * 
	 * 复杂度
		Time Complexity: O(NlogN)，Space: O(N)。
		
		heap
		因为要知道之前有overlap的最小的end，所以可以用一个min heap。每次检查新的start是否比heap的top元素小，
		是的话就把保存原来的end，同时放进新的end；否则就放新的end同时poll出原来的，因为没有overlap且新的end较大。
		最后heap的大小就是需要的房间数。比如：
		[1, 5], [2, 4], [3, 6], [5, 7]
		
		heap: [5]。[2, 4]的start是2，比5小，所以放入4。
		heap: [4, 5]。接着[3 ,6]的start是3，比4小，所以又放入6。
		heap: [4, 5, 6]。[5, 7]的start是5，比4大，因此poll出4，放入7。
		heap: [5, 6, 7]。最后heap的size为3。
		4被pop出来是因为[2, 4]和[5, 7]公用一个房间，只要放7进去就可以了。
	 */
	
	public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
		
		Comparator<Interval> comparator=new Comparator<Interval>() {
			public int compare(Interval a, Interval b) {
				return Integer.compare(a.start, b.start);
			}
		};
		Collections.sort(intervals, comparator);

		PriorityQueue<Integer> heap=new PriorityQueue<>();
		heap.offer(intervals.get(0).end);
		for (int i = 1; i < intervals.size(); i++) {
			if (heap.peek()<=intervals.get(i).start) {
				heap.poll();
			}
			heap.offer(intervals.get(i).end);
		}
		return heap.size();
    }
	
	/**
	 * 621. Task Scheduler
	 * 需要最少的任务时间：（最多任务数-1）*（n + 1） + （相同最多任务的任务个数）
	 * https://blog.csdn.net/Koala_Tree/article/details/78498586
	 * LANG
	 * @param tasks
	 * @param n
	 * @return
	 */
	public int leastInterval(char[] tasks, int n) {
        int [] ch=new int[26];
        for (int i : tasks) {
			ch[i-'A']++;
		}
        Arrays.sort(ch);
        int max=25;
        while(max>=0&&ch[max]==ch[25]){
        	max--;
        }
        return Math.max(tasks.length, (n+1)*(ch[25]-1)+(25-max));
    }
	
	/**
     * 78. Subsets
     * While iterating through all numbers, for each new number, we can either pick it or not pick it
		1, if pick, just add current number to every existing subset.
		2, if not pick, just leave all existing subsets as they are.
		We just combine both into our result.
		
		For example, {1,2,3} intially we have an emtpy set as result [ [ ] ]
		Considering 1, if not use it, still [ ], if use 1, add it to [ ], so we have [1] now
		Combine them, now we have [ [ ], [1] ] as all possible subset
		
		Next considering 2, if not use it, we still have [ [ ], [1] ], if use 2, just add 2 to each previous subset, we have [2], [1,2]
		Combine them, now we have [ [ ], [1], [2], [1,2] ]
		
		Next considering 3, if not use it, we still have [ [ ], [1], [2], [1,2] ], if use 3, 
		just add 3 to each previous subset, we have [ [3], [1,3], [2,3], [1,2,3] ]
		Combine them, now we have [ [ ], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3] ]
     * LANG
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
    	List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for(int n : nums){
            int size = result.size();
            for(int i=0; i<size; i++){
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(n);
                result.add(subset);
            }
        }
        return result;
    }
    
    /**
     * 90. Subsets II
     * The Basic idea is: use "while (i < n.length && n[i] == n[i - 1]) {i++;}" 
     * to avoid the duplicate. For example, the input is 2 2 2 3 4. 
     * Consider the helper function. The process is:

		each.add(n[i]); --> add first 2 (index 0)
		helper(res, new ArrayList<>(each), i + 1, n); --> go to recursion part, list each is <2 (index 0)>
		while (i < n.length && n[i] == n[i - 1]) {i++;} --> after this, i == 3, add the element as in subset I
     * LANG
     * @param nums
     * @return
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {i++;}
        }
        return;
    }
    
    /**
     * 916. Word Subsets
     * Time Complexity: O(A+B), where A and B is the total 
     * 	amount of information in A and B respectively.

		Space Complexity: O(A.length+B.length). 
     * LANG
     * @param A
     * @param B
     * @return
     */
    public static List<String> wordSubsets(String[] A, String[] B) {
        int[] bmax = new int[26];
        int i;
        for (String b: B) {
            int[] bCount = count(b);
            for (i = 0; i < 26; ++i){
                bmax[i] = Math.max(bmax[i], bCount[i]);
            }
        }

        List<String> result = new ArrayList<>();
        for (String a: A) {
            int[] aCount = count(a);
            for (i = 0; i < 26; ++i){
            	//A里字符串对应的数组要比整个B数组里的要多，而且要把所有26个字母都比较完，如果i==26，就添加到结果集里
            	if (aCount[i] < bmax[i])
            		break;
            }
            if (i == 26){
            	result.add(a);
            }
        }

        return result;
    }

    public static int[] count(String S) {
        int[] res = new int[26];
        for (char c: S.toCharArray()){
            res[c - 'a']++;
        }
        return res;
    }
    
    
    /**
	 * Rotate Matrix
	 * You are given an n x n 2D matrix representing an image.

		Rotate the image by 90 degrees (clockwise).
		In the first loop, only the outer side of matrix will be scanned. Inner loop will not.
Four Points/Values on each side of the matrix are scanned at the same time.
The loop will only loop along the upper side of the matrix.(Attention: the upper-right corner is one exception.)
If the length of matrix is n, the loop will scanned from matrix[0][0] to matrix[0][n-1-1].
If outer side has been scanner, i = i+1, then j = i.
The maximum of j is hard to understand. But because we scanned the array symmetrically,
If the minimum of j is i, the maximum value should also n-i - c, here c is a constant.
	 */
	public void rotate(int[][] matrix) {
        int n=matrix.length;
      //If we scan along the upper side, the lower side will also be scanned
        //Therefore,the maximum value of i will be 2*i<=row -1
	    for (int i=0; i<n/2; i++){ 
	    	//The lower bound of j should be the same as i.
            //If we scan the left side or left side of the inner loop, the right side will also be scanned.
            //Therefore, the maximum value of j will be as follows
            //i+1 will be the length of the right side have already been scanned with the corresponding i.
	        for (int j=i; j<n-i-1; j++) {
	            int tmp=matrix[i][j];
	            matrix[i][j]=matrix[n-j-1][i];
	            matrix[n-j-1][i]=matrix[n-i-1][n-j-1];
	            matrix[n-i-1][n-j-1]=matrix[j][n-i-1];
	            matrix[j][n-i-1]=tmp;
	        }
	    }
    }
	
    /**
     * 643. Maximum Average Subarray I
     * Instead of creating a cumulative sum array first, and then traversing over 
     * it to determine the required sum, we can simply traverse over nums just once,
     *  and on the go keep on determining the sums possible for the subarrays of length k.
     *   To understand the idea, assume that we already know the sum of elements from index 
     *   ii to index i+k, say it is x.

		Now, to determine the sum of elements from the index i+1 to the index i+k+1, 
		all we need to do is to subtract the element nums[i] from x and to add the element 
		nums[i+k+1] to x. We can carry out our process based on this idea and determine
		 the maximum possible average
     * Time complexity : O(n). We iterate over the given numsnums array of length nn once only.

		Space complexity : O(1). Constant extra space is used.
     * LANG
     * @param nums
     * @param k
     * @return
     */
	public double findMaxAverage(int[] nums, int k) {
		double sum = 0;
		for (int i = 0; i < k; i++)
			sum += nums[i];
		double res = sum;
		for (int i = k; i < nums.length; i++) {
			sum += nums[i] - nums[i - k];
			res = Math.max(res, sum);
		}
		return res / k;
	}
    
     /**
      * window sum
      * 一个数组，再给定一个长度，让你算出数组里面，在这个长度下，分别的连续和
      */
    
    public List<Integer> GetwindowSum(List<Integer> A, int k) {
    	   ArrayList<Integer> result  = new ArrayList<>();
    	   if (A == null || A.size() == 0 || k <= 0) return result;
    	   int count = 0;
    	   for (int i = 0; i < A.size(); i++) {
    	       count++;
    	       if (count >= k) {
    	           int sum = 0;
    	           for (int j = i; j >= i - k + 1; j--) {
    	               sum += A.get(j);
    	           }
    	           result.add(sum);
    	       }
    	   }
    	   return result;
    	}
    
        public int[] SumOfWindow(int[] array, int k) {
            if (array == null || array.length < k || k <= 0)    return null;
            
            /**
             * 如果窗口比arr长
             */
            if (k>array.length) {
				int sum=0;
            	for (int i = 0; i < array.length; i++) {
					sum+=array[i];
				}
            	return new int[]{sum};
			}
            int[] result = new int[array.length - k + 1];
            for (int i = 0; i < k; i++)
                result[0] += array[i];
            for (int i = 1; i < result.length; i++) {
                result[i] = result[i-1] - array[i-1] + array[i+k-1];
            }
            return result;
        }
        
        
        /**
         * 长方形相交
         * 紫色代表矩形A，红色代表矩形B，并分别用p1,p2,p3,p4代表对应的左上角与右下角
         * 不重叠:
         * 即B矩阵，可能在A的左侧、右侧、上侧、下侧。如果用公式表示，即 
			(p2.y≤p3.y)∨(p1.y≥p4.y)∨(p2.x≤p3.x)∨(p1.x≥p4.x) 
			则，两个矩阵重叠时，公式为 
			¬[(p2.y≤p3.y)∨(p1.y≥p4.y)∨(p2.x≤p3.x)∨(p1.x≥p4.x)] 
			根据德·摩根定律可转换为 
			(p2.x>p3.x)∧(p2.y>p3.y)∧(p1.x<p4.x)∧(p1.y<p4.y)
         */
        
       public boolean isOverlap( Rect rc1, Rect rc2){
            if (rc1.x + rc1.width  > rc2.x &&
            	rc1.y + rc1.height > rc2.y &&
                rc2.x + rc2.width  > rc1.x &&
                rc2.y + rc2.height > rc1.y
               )
                return true;
            else
                return false;
        }
       

		/**
		 * 如果单元格的两个边上的邻居都是活动的或不活动的，则在第二天，该单元变得不活动。
		 * LANG
		 * @param states
		 * @param days
		 * @return
		 */
		public List<Integer> cellCompete(int[] states, int days){
	        
			List<Integer> result=new ArrayList<>(states.length);
			if (days==0) {
				for (int i = 0; i < states.length; i++) {
					result.add(states[i]);
				}
				return result;
			}
			int [] temp=new int[states.length];
			for (int i = 0; i < days; i++) {
				for (int j = 0; j < states.length; j++) {
					if (j==0) {
						temp[0]=0^states[j+1];
					}else if (j==states.length-1) {
						temp[j]=0^states[j-1];
					}else {
						
						temp[j]=states[j-1]^states[j+1];
					}
				}
				for (int j = 0; j < temp.length; j++) {
					states[j]=temp[j];
				}
			}
			
			for (int i = 0; i < temp.length; i++) {
				result.add(temp[i]);
			}
			return result;
			
			
	    }
}
