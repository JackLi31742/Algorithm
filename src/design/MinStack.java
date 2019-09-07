package design;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * 155. Min Stack
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

	push(x) -- 将元素 x 推入栈中。
	pop() -- 删除栈顶的元素。
	top() -- 获取栈顶元素。
	getMin() -- 检索栈中的最小元素。


 * @author LANG
 *
 */

//列表头插法，模拟栈；每个节点保存到该节点为止的最小值
public class MinStack{
	
	private Node head;
	public MinStack() {
		head=new Node(0,null,Integer.MAX_VALUE);
	}
	
	public void push(int x) {
		Node node=new Node(x,head,Math.min(x, head.min));
		//移动指针
		head=node;
	}
	
	public void pop() {
		head=head.next;
	}
	
	public int top() {
		return head.value;
    }
    
	public int getMin() {
		return head.min;
    }
	
	private static class Node{
		int value;
		Node next;
		int min;
		public Node(int value, Node next, int min) {
			super();
			this.value = value;
			this.next = next;
			this.min = min;
		}
		
		@Override
		public String toString() {
			return "Node [value=" + value + ", next=" + next + ", min=" + min + "]";
		}
		
		
	}
	
	public static void main(String[] args) {
		MinStack obj=new MinStack();
		obj.push(2);
		obj.push(0);
		obj.push(3);
		obj.push(0);
		
		obj.getMin();
		obj.pop();
		obj.getMin();
		obj.pop();
		obj.getMin();
		obj.pop();
		obj.getMin();
	}
}

/*
 * LinkedList中的push，是addFirst，pop和peek也是操作的第一个元素
 * 
 */


class MinStack3{
	
	private LinkedList<Integer> stack;
	
	private LinkedList<Integer> min;
	public MinStack3() {
        stack=new LinkedList<>();
        min=new LinkedList<>();
    }
    
	public void push(int x) {
        stack.push(x);
        if (min.size()==0) {
			
        	min.add(x);
		}else {
			int pos=-1;
			for (int i = 0; i < min.size(); i++) {
				if (min.get(i)<x) {
					pos=i;
					break;
				}
			}
			if (pos==-1) {
				pos=min.size();
			}
			min.add(pos, x);
		}
    }
    
	public void pop() {
//		int pos=0;
//		for (int i = 0; i < min.size(); i++) {
//			
//			if (min.get(i).equals(stack.peek())) {
//				pos=i;
//				break;
//			}
//		}
//		min.remove(pos);
		min.remove(stack.peek());
        stack.pop();
        
    }
    
	public int top() {
        return stack.peek();
    }
    
	public int getMin() {
        return min.peekLast();
    }
	
	
}


class MinStack2 {

	//最小值数组
	private int[] min;
	
	//顺序栈数组
	private int[] arr;
	//栈的长度
	private int length;
	//栈内元素的个数
	private int count;
    public MinStack2() {
    	this.length=8;
        this.arr=new int[length];
        this.count=0;
        this.min=new int[length];
        Arrays.fill(min, Integer.MIN_VALUE);
    }
    
    public void push(int x) {
    	//由于插入的时候，会用到count位置，等于的时候扩容就会数组越界
    	if (count==length-1) {
    		int newLength=2*length;
    		arr=Arrays.copyOf(arr, newLength);
    		min=Arrays.copyOf(min, newLength);
    		//扩容后要更新长度
    		length=newLength;
		}
    	this.arr[count]=x;
    	this.count++;
    	insertSort(min, x);
    	
    }
    
    //插入排序
    private void insertSort(int[]min,int x) {
		int pos=-1;
		for (int i = 0; i < count; i++) {
			//如果x=Integer.MIN_VALUE，pos的值无法更新
			if (min[i]<=x) {
				pos=i;
				break;
			}
		}
		if (pos==-1) {
			pos=count;
		}
		if (pos<count) {
			
			for (int i = min.length-1-1; i >=pos; i--) {
				min[i+1]=min[i];
			}
		}
		min[pos]=x;
		
    }
    
    private void removeMin(int[]min,int x) {
    	int pos=0;
    	for (int i = 0; i < count; i++) {
			if (min[i]==x) {
				pos=i;
				break;
			}
		}
    	for (int i = pos; i < min.length-1; i++) {
			min[i]=min[i+1];
		}
    }
    public void pop() {
    	//弹出要删除min中的元素
    	removeMin(min, arr[count-1]);
    	this.arr[count-1]=0;
    	count--;
    	
    }
    
    public int top() {
        return arr[count-1];
    }
    
    public int getMin() {
        return min[count-1];
    }
    
    
}
