package design;

import java.util.Arrays;

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
public class MinStack {

	//最小值数组
	private int[] min;
	
	//顺序栈数组
	private int[] arr;
	//栈的长度
	private int length;
	//栈内元素的个数
	private int count;
    public MinStack() {
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
    
    public static void main(String[] args) {
    	MinStack obj = new MinStack();
    	obj.push(-124);
    	obj.push(-164);
    	obj.push(-197);
    	obj.push(-24);
    	
    	
    	
	}
}
