package design;

import java.util.Stack;

public class MyQueue {
	
	private Stack<Integer> stack1;
	private Stack<Integer> stack2;
	/** Initialize your data structure here. */
    public MyQueue() {
        stack1=new Stack<>();
        stack2=new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	
    	stack1.push(x);
		
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        
        
    }
    
    /** Get the front element. */
    public int peek() {
        
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        
    }
}
