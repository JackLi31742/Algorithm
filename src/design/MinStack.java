package design;

import java.util.Arrays;

/**
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

	push(x) -- 将元素 x 推入栈中。
	pop() -- 删除栈顶的元素。
	top() -- 获取栈顶元素。
	getMin() -- 检索栈中的最小元素。


 * @author LANG
 *
 */
public class MinStack {

	//最小值
	private int[] min;
	//顺序栈数组
	private int[] arr;
	//栈的长度
	private int length;
	//栈内元素的个数
	private int count;
    public MinStack() {
        this.arr=new int[8];
        this.length=8;
        this.count=0;
        this.min=new int[length];
//        Arrays.fill(min, Integer.MAX_VALUE);
    }
    
    public void push(int x) {
    	if (count>=length) {
    		arr=Arrays.copyOf(arr, 2*length);
		}
    	this.arr[count]=x;
    	this.count++;
    	if (x<min[count]) {
			min[count]=x;
		}
    }
    
    public void pop() {
    	this.arr[this.count--]=0;
    }
    
    public int top() {
        return arr[this.count--];
    }
    
    public int getMin() {
        return this.min;
    }
}
