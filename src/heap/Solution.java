package heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


//初始化建堆的时间复杂度为O(n)，排序重建堆的时间复杂度为nlog(n)
public class Solution {

	
	/**
	 * lintcode
	 * 612. K Closest Points
	 * Time complexity: O(nlogk), where n is the number of points.
		Space complexity: O(k). A PriorityQueue of size k is used.
	 * LANG
	 * @param points
	 * @param origin
	 * @param k
	 * @return
	 */
	public static Point[] kClosest(Point[] points, Point origin, int k) {
        // write your code here
		if (k<1) {
			return null;
		}
		//大顶堆的比较器
		Comparator<Point> pointCompartor=new Comparator<Point>(){
			 
	        @Override
	        public int compare(Point p1,Point p2) {
	            return distanceCompare(p1,p2,origin);
	        }
	    };
	
		Queue<Point> heap=new PriorityQueue<>(k,pointCompartor);
		
		for (int i = 0; i < points.length; i++) {
			/*if (i<k) {
				heap.add(points[i]);
			}else {
				if (distanceCompare(points[i],heap.peek(),origin)>0) {
					heap.poll();
					heap.add(points[i]);
				}
			}*/
			heap.add(points[i]);
			if(heap.size()>k){
			    heap.poll();
			}
			
		}
		
		
		Point[] result=new Point[k];
		
		while(!heap.isEmpty()){
			result[k-1]=heap.poll();
			k--;
		}
		
		//小顶堆的比较器
	    /*Comparator<Point> pointCompartor2=new Comparator<Point>(){
	    	
	    	@Override
	    	public int compare(Point p1,Point p2) {
	    		return distanceCompare(p2,p1,origin);
	    	}
	    };
		Point[] result=heap.toArray(new Point[k]);
		Arrays.sort(result,pointCompartor2);*/
		return result;
    }
	
	

    /**
     * 大顶堆需要是2-1
     * LANG
     * @param p1
     * @param p2
     * @param origin
     * @return
     */
	public static int distanceCompare(Point p1,Point p2,Point origin){
		int dis1=(p1.x-origin.x)*(p1.x-origin.x)+(p1.y-origin.y)*(p1.y-origin.y);
		int dis2=(p2.x-origin.x)*(p2.x-origin.x)+(p2.y-origin.y)*(p2.y-origin.y);
		int compare=dis2-dis1;
		if (compare==0) {
			int comX=p2.x-p1.x;
			if (comX==0) {
				int comY=p2.y-p1.y;
				
				return comY;
				
			}else {
				return comX;
			}
		}else {
			
			return compare;
		}
	}
	
	/**
	 * 1562. Number of restaurants
	 * 给出一个List，里面的数据代表每一个餐厅的坐标[x, y]。顾客的坐标处于原点[0, 0]。
	 * 先找出n家离顾客位置最近的餐厅，然后取 n 家先出现在List中且与顾客的距离不超过 n 家离顾客最近的餐厅的最长距离。
	 * 返回这 n 家餐厅的坐标序列，按输入数据原始的顺序。
	 * LANG
	 * @param restaurant
	 * @param n
	 * @return
	 */
	 public static List<List<Integer>> nearestRestaurant(List<List<Integer>> restaurant, int n) {
	        // Write your code here
		 List<List<Integer>> result=null;
		 if (n<1||restaurant==null||restaurant.size()==0||n>restaurant.size()) {
			result=new ArrayList<>();
			return result;
		}
		 if(restaurant.size() == n) return restaurant;
		 result=new ArrayList<>(n);
		 Comparator<List<Integer>> comparator=new Comparator<List<Integer>>() {

			public int compare(List<Integer> o1, List<Integer> o2) {
				
				return Integer.compare(getDis(o2), getDis(o1));
			}
		};
		
		PriorityQueue<List<Integer>> heap=new PriorityQueue<>(n,comparator);
		
		for (int i = 0; i < restaurant.size(); i++) {
			heap.add(restaurant.get(i));
			if (heap.size()>n) {
				heap.poll();
			}
		}
		
		int maxDistance = getDis(heap.poll());
		for(int i = 0; i < restaurant.size(); i++) {
            if(result.size() == n) break;
            int distance =getDis(restaurant.get(i));
            if(distance <= maxDistance) {
                result.add(restaurant.get(i));
            }
        }
		return result;
	 }
	 
	 public static int getDis(List<Integer> o1){
		 return o1.get(0)*o1.get(0)+o1.get(1)*o1.get(1);
	 }
	 
	public static void main(String[] args) {
		/*Point p1=new Point(4, 6);
		Point p2=new Point(4, 7);
		Point p3=new Point(4, 4);
		Point p4=new Point(2, 5);
		Point p5=new Point(1, 1);
		Point[]points={p1,p2,p3,p4,p5};
		Point origin=new Point(0,0);
		
		Point[] result=kClosest(points, origin, 3);
		for (Point point : result) {
			System.out.println(point);
		}*/
		
		List<List<Integer>> restaurant=new ArrayList<>();
		List<Integer> inner=new ArrayList<>();
		inner.add(1);
		inner.add(2);
		restaurant.add(inner);
		
		System.out.println(nearestRestaurant(restaurant, 1));;
		
	}
	
	
	 
	 /**
	  * 973. K Closest Points to Origin
	  * LANG
	  * @param points
	  * @param K
	  * @return
	  */
	 
	 public int[][] kClosest(int[][] points, int K) {
	     Comparator<int[]> comparator=new Comparator<int[]>() {
	    	  
	    	  public int compare(int[]point1,int[]point2){
	    		 return disCom(point1, point2);  
	    	  }
		};
		PriorityQueue<int[]> heap=new PriorityQueue<>(K,comparator);
		
		for (int i = 0; i < points.length; i++) {
			heap.add(points[i]);
			if (heap.size()>K) {
				heap.poll();
			}
		}
		
		int[][]resut=new int[K][];
		
		while(!heap.isEmpty()){
			resut[K-1]=heap.poll();
			K--;
		}
		return resut;
	 }
	 
	 public int disCom(int[]point1,int[]point2){
		 int dis1=point1[0]*point1[0]+point1[1]*point1[1];
		 int dis2=point2[0]*point2[0]+point2[1]*point2[1];
		 return dis2-dis1;
	 }
	 /**
	  * 692. Top K Frequent Words
	  * Given a non-empty list of words, return the k most frequent elements.

		Your answer should be sorted by frequency from highest to lowest. 
		If two words have the same frequency, then the word with the lower alphabetical order comes first.
	  * Time Complexity: O(Nlogk), where N is the length of words. 
			We count the frequency of each word in O(N) time, 
			then we add N words to the heap, each in O(logk) time.
		  	Finally, we pop from the heap up to k times.
		  	As k≤N, this is  	O(Nlogk) in total.
	  *   Space Complexity: O(N), the space used to store our count.
	  * LANG
	  * @param words
	  * @param k
	  * @return
	  */
	 public List<String> topKFrequent(String[] words, int k) {
		 Map<String, Integer> count = new HashMap<>();
	        for (String word: words) {
	            count.put(word, count.getOrDefault(word, 0) + 1);
	        }
	        PriorityQueue<String> heap = new PriorityQueue<String>(
	                (w1, w2) -> count.get(w1).equals(count.get(w2)) ?
	                w2.compareTo(w1) : count.get(w1) - count.get(w2) );

	        for (String word: count.keySet()) {
	            heap.offer(word);
	            if (heap.size() > k) heap.poll();
	        }

	        List<String> ans = new ArrayList<>();
	        while (!heap.isEmpty()) ans.add(heap.poll());
	        Collections.reverse(ans);
	        return ans;
	 }
	/**
	 * 347. Top K Frequent Elements
	 * Given a non-empty array of integers, return the k most frequent elements.
	 * The first step is to build a hash map element -> its frequency. 
	 * In Java we could use data structure HashMap but have to fill it manually. 
		This step takes O(N) time where N is number of elements in the list.

		The second step is to build a heap. 
		The time complexity of adding an element in a heap isO(log(k)) 
		and we do it N times that means O(Nlog(k)) time complexity for this step.

		The last step to build an output list has
		O(klog(k)) time complexity.
		
		Time complexity : O(Nlog(k)). The complexity of Counter method is O(N). 
		To build a heap and output list takes O(Nlog(k)). 
		Hence the overall complexity of the algorithm is O(N+Nlog(k))=O(Nlog(k)).

		Space complexity : O(N) to store the hash map.
	 * LANG
	 * @param nums
	 * @param k
	 * @return
	 */
	 public List<Integer> topKFrequentElement(int[] nums, int k) {
		// build hash map : character and how often it appears
		    HashMap<Integer, Integer> count = new HashMap<>();
		    for (int n: nums) {
		      count.put(n, count.getOrDefault(n, 0) + 1);
		    }

		    // init heap 'the less frequent element first'
		    PriorityQueue<Integer> heap =
		            new PriorityQueue<Integer>((n1, n2) -> count.get(n1) - count.get(n2));

		    // keep k top frequent elements in the heap
		    for (int n: count.keySet()) {
		      heap.add(n);
		      if (heap.size() > k)
		        heap.poll();
		    }

		    // build output list
		    List<Integer> top_k = new LinkedList<>();
		    while (!heap.isEmpty())
		      top_k.add(heap.poll());
		    Collections.reverse(top_k);
		    return top_k;
	 }
}
