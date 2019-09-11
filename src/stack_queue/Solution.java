package stack_queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * stack 继承自Vector，是synchronized的，顺序栈
 * 方法有，push，pop，peek
 * @author LANG
 *
 */
public class Solution {

	/**
	 * 20. 有效的括号
	 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
	 * 注意空字符串可被认为是有效字符串。
	 * LANG
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
        if (s==null) {
			return false;
		}
        if (s=="") {
			return true;
		}
        Map<Character, Character> map=new HashMap<Character, Character>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        
        char[] arr=s.toCharArray();
        LinkedList<Character> stack=new LinkedList<>();
        for (int i = 0; i < arr.length; i++) {
			if (arr[i]=='('||arr[i]=='{'||arr[i]=='[') {
				stack.push(arr[i]);
			}else {
				if (map.get(arr[i])==stack.peek()) {
					stack.pop();
				}else {
					return false;
				}
			}
			
		}
        
        if (stack.isEmpty()) {
			return true;
		}else {
			return false;
		}
    }
	
	/**
	 * 844. Backspace String Compare
	 * 比较经过退格符（#）之后，两个字符串是否相等
	 * 
	 *  Input: S = "ab#c", T = "ad#c"
		Output: true
		Explanation: Both S and T become "ac".
	 * LANG
	 * @param S
	 * @param T
	 * @return
	 */
	public boolean backspaceCompare(String S, String T) {
		if (S.equals(T)) {
			return true;
		}
        LinkedList<Character> stack1=new LinkedList<>();
        LinkedList<Character> stack2=new LinkedList<>();
        
        char[] arr1=S.toCharArray();
        char[] arr2=T.toCharArray();
        for (int i = 0; i < arr1.length; i++) {
			if (arr1[i]!='#') {
				stack1.push(arr1[i]);
			}else {
				if (!stack1.isEmpty()) {
					
					stack1.pop();
				}
			}
		}
        
        for (int i = 0; i < arr2.length; i++) {
        	if (arr2[i]!='#') {
        		stack2.push(arr2[i]);
        	}else {
        		if (!stack2.isEmpty()) {
					
        			stack2.pop();
				}
        	}
        }
        
        if (stack1.size()!=stack2.size()) {
			return false;
		}
        
        for (int i = 0; i < stack1.size(); i++) {
			if (stack1.get(i)!=stack2.get(i)) {
				return false;
			}
		}
        return true;
    }

	/**
	 * 用指针，从后遍历，遍历到'#'，表示前边要跳过几个字符
	 * LANG
	 * @param S
	 * @param T
	 * @return
	 */
	public boolean backspaceCompare2(String S, String T) {
		char[] arr1=S.toCharArray();
		char[] arr2=T.toCharArray();
		
		int skipS=0;int skipT=0;
		StringBuilder sb1=new StringBuilder();
		StringBuilder sb2=new StringBuilder();
		for (int i = arr1.length-1; i >=0; ) {
			if (arr1[i]=='#') {
				skipS++;
				i--;
				continue;
			}
			//不能一次把skipS都减到0，要看之前的字符有没有#，所以不能用while，用if，进行下一个字符的判断
			if(skipS>0){
				i--;
				skipS--;
				continue;
			}
			sb1.append(arr1[i]);
			i--;
		}
		for (int i = arr2.length-1; i >=0; ) {
			if (arr2[i]=='#') {
				skipT++;
				i--;
				continue;
			}
			if(skipT>0){
				i--;
				skipT--;
				continue;
			}
			sb2.append(arr2[i]);
			i--;
		}
		
		return sb1.toString().equals(sb2.toString());
	}
	
	/**
	 * 224. 基本计算器
	 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
	 * LANG
	 * @param s
	 * @return
	 */
	public int calculate(String s) {
        char [] arr=s.toCharArray();
        LinkedList<Integer> numStack=new LinkedList<>();
        LinkedList<Character> opStack=new LinkedList<>();
        Map<Character, Integer> level=new HashMap<>();
        level.put('(', 2);
        level.put(')', 3);
        level.put('+', 1);
        level.put('-', 1);
        int result=0;
        for (int i = 0; i < arr.length; i++) {
        	if (arr[i]==' ') {
				continue;
			}
        	
			if (arr[i]=='('||arr[i]==')'||arr[i]=='+'||arr[i]=='-') {
				if (!opStack.isEmpty()) {
					if (level.get(arr[i])>level.get(opStack.peek())) {
						
					}
				}
				opStack.push(arr[i]);
			}
			
			numStack.push((int)arr[i]);
		}
        
//        return result;
    }

	
	/**
	 * 682. 棒球比赛
	 * LANG
	 * @param ops
	 * @return
	 */
	public int calPoints(String[] ops) {
        int result=0;
        LinkedList<Integer> stack=new LinkedList<>();
        for (int i = 0; i < ops.length; i++) {
        	//String 判断相等用equals
			if (ops[i].equals("C")) {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			}else if (ops[i].equals("D")) {
				stack.push(stack.peek()*2);
			}else if (ops[i].equals("+")) {
				int temp1=stack.pop();
				int temp2=stack.peek();
				stack.push(temp1);
				stack.push(temp1+temp2);
			}else {
				stack.push(Integer.parseInt(ops[i]));
			}
		}
        
        //不能用for循环，stack的大小在减少，i在增加，会导致i大于stack的size
//        for (int i = 0; i < stack.size(); i++) {
//        }
        
        while(!stack.isEmpty()){
        	
        	result+=stack.pop();
        }
        
        return result;
    }

	
	/**
	 * 496. Next Greater Element I
	 * nums1是nums2的子集，遍历nums1，在nums2中找到第一个比这个数字大的，找不到返回-1
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int [] result=new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
        	boolean flag=false;
			for (int j = 0; j < nums2.length; j++) {
				if (nums2[j]==nums1[i]) {
					if (j==nums2.length-1) {
						result[i]=-1;
						break;
					}
					flag=true;
				}
				if (flag) {
					//这样会使得j多加很多次  
//					j++;
//					if (j<nums2.length) {
						if (nums2[j]==nums1[i]) {
							continue;
						}
						if (nums2[j]>nums1[i]) {
							result[i]=nums2[j];
							break;
						}else {
							result[i]=-1;
						}
//					}else {
//						result[i]=-1;
//					}
				}
			}
		}
        
        return result;
    }

	/**
	   *  找到index，再去遍历nums2，减少判断次数
	 */
	public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
		int [] result=new int[nums1.length];
		
		for (int i = 0; i < nums1.length; i++) {
			int greater=-1;
			int index=findIndex(nums1[i],nums2);
			for (int j = index+1; j < nums2.length; j++) {
				if (nums2[j]>nums1[i]) {
					greater=nums2[j];
					break;
				}
			}
			result[i]=greater;
		}
		return result;
	}
	
	public int findIndex(int num,int[]nums2) {
		for (int i = 0; i < nums2.length; i++) {
			if (nums2[i]==num) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 用map映射num和index的关系
	 * LANG
	 * @param nums1
	 * @param nums2
	 * @return
	 */
	public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
		Map<Integer, Integer> map=new HashMap<>();
		for (int i = 0; i < nums2.length; i++) {
			map.put(nums2[i], i);
		}
		for (int i = 0; i < nums1.length; i++) {
			map.get(nums1[i]);
		}
	}
	/**
	 * 503. Next Greater Element II
	 * 数组的最后一个元素的下一个元素是数组的第一个元素，意思是数组是环形队列
	 * @param nums
	 * @return
	 */
	public int[] nextGreaterElements(int[] nums) {
		if (nums==null) {
			return null;
		}
		if (nums.length==0) {
			return new int[] {};
		}
		if (nums.length==1) {
			return new int[] {-1};
		}
		int len=nums.length;
		int[] result=new int[len];
		for (int i = 0; i < len; i++) {
			int greater=-1;
								//终止条件，当j累加到再次等于i时，跳出循环，所以每次进行时，是不等于
			for (int j = i+1; (j %len)!=i; j++) {
				
				if (nums[j%len]>nums[i]) {
					greater=nums[j%len];
					break;
				}
			}
			result[i]=greater;
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		Solution so=new Solution();
		String S="ab#c";
		String T="ad#c";
//		System.out.println(so.backspaceCompare2(S, T));;
		
		String []ops={"5","2","C","D","+"};
//		System.out.println(so.calPoints(ops));;
//		System.out.println((int)'(');
//		System.out.println((int)')');
//		System.out.println((int)'+');
//		System.out.println((int)'-');
//		System.out.println((int)'*');
//		System.out.println((int)'/');
		int[] nums1= {1,2,1};
//		int[] nums2= {1,3,4,2};
		so.nextGreaterElements(nums1);
		
//		System.out.println(new int[]{-1,2});
	}
}
