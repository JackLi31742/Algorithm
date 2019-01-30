package sort;

import java.util.Arrays;

public class QuickSort {

	public static void sort(int[] arr){
		quickSort(arr,0,arr.length-1);
	}
	
	public static void quickSort(int[] arr,int start,int end){
		// 递归结束条件：startIndex大等于endIndex的时候
		if (start>=end) {
			return ;
		}
		int partition=partition(arr, start, end);
		
		quickSort(arr, partition+1, end);
		quickSort(arr, start, partition-1);
	}
	public static int partition(int[] arr,int start,int end){
		int pov=arr[start];
		while(start<end){
			while(start<end&&arr[start]<pov){
				start++;
			}
			while(start<end&&arr[end]>pov){
				end--;
			}
			// 交换left和right指向的元素
			swap(arr,start,end);
		}
		// pivot和指针重合点交换
		int temp=arr[start];
		arr[start]=pov;
		pov=temp;
		
		return start;
	}
	
	public static void swap(int[]arr,int x,int y){
		int temp=arr[x];
		arr[x]=arr[y];
		arr[y]=temp;
	}
	public static void main(String[] args) {
		int [] arr={20,13,6,9,0};
		sort(arr);
		System.out.println(Arrays.toString(arr));
	}
}
