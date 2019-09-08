package design;

import java.util.Stack;

/**
 * 232. Implement Queue using Stacks
 * @author LANG
 *
 */
public class MyQueue{
	
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	//保存队首元素 	
	private int front;
	public MyQueue(){
		stack1=new Stack<>();
        stack2=new Stack<>();
	}
	public void push(int x) {
		if (stack1.isEmpty()) {
			front=x;
		}
		stack1.push(x);
	}
	public int pop() {
		if (stack2.isEmpty()) {
			
    		while(!stack1.isEmpty()){
    			stack2.push(stack1.pop());
    		}
		}
        return stack2.pop();
	}
	
	public int peek() {
		if (stack2.isEmpty()) {
			return front;
		}
		return stack2.peek(); 
	}
	//无需if else判断了
	public boolean empty() {
		return stack1.isEmpty()&&stack2.isEmpty();
	}
}
class MyQueue2 {
	
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	/** Initialize your data structure here. */
    public MyQueue2() {
        stack1=new Stack<>();
        stack2=new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	
    	stack1.push(x);
		
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	if (stack2.isEmpty()) {
			
    		while(!stack1.isEmpty()){
    			stack2.push(stack1.pop());
    		}
		}
        return stack2.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        if (stack2.isEmpty()) {
        	while(!stack1.isEmpty()){
    			stack2.push(stack1.pop());
    		}
		}
        return stack2.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (stack1.isEmpty()&&stack2.isEmpty()) {
			return true;
		}
        return false;
    }
}
