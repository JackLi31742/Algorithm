package al;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

	public void set(int value){
		byte [] b=new byte[value];
		  int byteIndex = value / 8;
		  int bitIndex = value % 8;
		  byte a=5 ;
//		  b[byteIndex] = b[byteIndex] | 1 << (7 - bitIndex);
		}

	public static void main(String[] args) {

		/*int num[]={3,4, 1, 2, 5, 6, 9 ,0 ,1 ,2 ,3 ,1};
		List<Integer> list=new ArrayList<>();
		for (int i = 0; i < num.length; i++) {
			list.add(num[i]);
		}
		System.out.println(jump_over_numbers(list));;*/
		
		long num=1325132435356l;
		System.out.println(digit_sum(num));;
	}
	
	public static int jump_over_numbers(List<Integer> list) {
        // Write your code here
		int len=list.size();
		int start=0;
		int pos=start;
		int count=0;
		while (pos<len) {
			if (list.get(pos)==0) {
				return -1;
			}
			pos=pos+list.get(pos);
			count++;
		}
		return count;
    }
	
	public static int digit_sum(long number) {
	      // Write your code here
		
		long num=Math.abs(number);
		String s=String.valueOf(num);
		int sum=0;
		char []ch=s.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			sum+=(ch[i]-48);
		}
		return sum;
	}
}
