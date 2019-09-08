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
        
        return result;
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
			if (ops[i]=="C") {
				if (!stack.isEmpty()) {
					stack.pop();
				}
			}else if (ops[i]=="D") {
				stack.push(stack.peek()*2);
			}else if (ops[i]=="+") {
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

	public static void main(String[] args) {
		Solution so=new Solution();
		String S="ab#c";
		String T="ad#c";
//		System.out.println(so.backspaceCompare2(S, T));;
		
		String []ops={"5","2","C","D","+"};
		System.out.println(so.calPoints(ops));;
//		System.out.println((int)'(');
//		System.out.println((int)')');
//		System.out.println((int)'+');
//		System.out.println((int)'-');
//		System.out.println((int)'*');
//		System.out.println((int)'/');
	}
}
