package design;

import java.util.PriorityQueue;

/**
 * 703. Kth Largest Element in a Stream
For each call to the method KthLargest.add, return the element representing the kth largest element in the stream.
 *
 */
public class KthLargest {

	private int k;
	private PriorityQueue<Integer> heap;
	public KthLargest(int k, int[] nums) {
        this.k=k;
        heap=new PriorityQueue<>(k);
        for (int i = 0; i < nums.length; i++) {
			heap.add(nums[i]);
			if (heap.size()>k) {
				heap.poll();
			}
		}
    }
    
    public int add(int val) {
        heap.add(val);
        if (heap.size()>k) {
			heap.poll();
		}
        return heap.peek();
    }
}
