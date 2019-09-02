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
	
	public static void main(String[] args) {
		System.out.println(')'-'(');
		System.out.println('}'-'{');
		System.out.println(']'-'[');
	}
}
