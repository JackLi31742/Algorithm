package amazon_lint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;



public class Solution {

	/**
	 * K-Substring with K different characters
     * 双指针，O(n)时间法度。
		i从0开始loop，j也从0开始，但每一个新i的循环，j不返回，继续往前。
		用一个HashMap来保存当前substring里的字母，找到长度为k且不包含重复字母的substring后，remove i所指的字母，i++，继续寻找
		保持i不变，j增加，到了k的时候，break掉
		i增加
		
		i starts from 0 loop, j also starts from 0, but every new i loop, j does not return, continue to move forward.
		Use a HashMap to save the letters in the current substring, find the substring of length k and do not contain duplicate letters, 
		the letter pointed to by remove i, i++, continue to search
		Keep i unchanged, j increases, when it is k, break
		i increase
     * LANG
     * @param stringIn
     * @param K
     * @return
     */
    public static int KSubstring2(String stringIn, int K) {
        if (stringIn == null || stringIn.length() == 0 || K <= 0) {
            return 0;
        }
        HashMap<Character, Integer> charMap = new HashMap<>();
        HashSet<String> resultSet = new HashSet<String>();
        int len = stringIn.length();
        int j = 0;
        for (int i = 0; i < len; i++) {
        	//当j到了len的时候，i继续向后，缩小了窗口
            while (j < len && charMap.size() < K) {
                char c = stringIn.charAt(j);
                //如果出现过相同的，直接break掉while
                if (charMap.containsKey(c)) {
                    break;
                }
                charMap.put(c, 1);
                j++;
                if (charMap.size() == K) {
                    resultSet.add(stringIn.substring(i, j));
                }
            }
            charMap.remove(stringIn.charAt(i));
        }
        System.out.println(resultSet);
        return resultSet.size();
    }
    
    /**
	 * 1562. Number of restaurants
	 * 给出一个List，里面的数据代表每一个餐厅的坐标[x, y]。顾客的坐标处于原点[0, 0]。
	 * 先找出n家离顾客位置最近的餐厅，然后取 n 家先出现在List中且与顾客的距离不超过 n 家离顾客最近的餐厅的最长距离。
	 * 返回这 n 家餐厅的坐标序列，按输入数据原始的顺序。
	 * 
	 * Time complexity: O(nlogk), where n is the number of points.即restaurant.size() k为n
	 * 构造大小为k的大顶堆，for循环，把每个餐厅都加到堆里，当堆的大小大于k的时候，poll堆顶元素，得到的堆保存的就是n家离顾客位置最近的餐厅
	 * 
	 * Construct a max heap of size k, for loop, add each restaurant distance to the heap.
	 *  When the size of the heap is larger than k, poll the top of heap, 
	 *  the heap holds the n restaurants closest to the customer.
	 *  distance=x^2+y^2
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
	  * 1636. Aerial Movie
	  * 为了防止乘客在旅途中过于无聊，LQ航空公司决定在航班飞行过程中播放2部电影。
	  * 由于飞机起降过程中不能播放电影，LQ航空公司必须保证两部电影加起来的时长小于等于飞行时长减去30分钟，
	  * 同时又希望两部电影总长度尽量长。现在给定t代表航班飞行时长(分钟)，一个dur[]数组代表所有电影的时间长度，
	  * 请从小到大分别输出两部电影的时长，如果有多组总时长一样的，选取包含单独最长的电影组. 题目保证有解
	  * 
	  * sort dur， two pointer,i=0;j=dur.length - 1; while loop ,sum = dur[i] + dur[j],
	  * if sum > max ,update max, Assignment result,
	  * if sum=max ,Determine whether the current result is larger than the saved result, 
	  * yes and update the result
	  * if sum<=t-30,i++;else j--;
	  * LANG
	  * @param t
	  * @param dur
	  * @return
	  */
	 public int[] aerial_Movie(int t, int[] dur) {
	        // Write your code here
		Arrays.sort(dur);
		int result[] = new int[2];
		int max = 0;
		int i = 0;
		int j = dur.length - 1;
		while (i < j) {

			int sum = dur[i] + dur[j];
			if (sum <= t - 30) {
				if (sum > max) {
					max = sum;
					result[0] = Math.min(dur[i], dur[j]);
					result[1] = Math.max(dur[i], dur[j]);
				}
				if (sum == max) {
					if (Math.max(dur[i], dur[j]) > Math.max(result[0], result[1])) {
						result[0] = Math.min(dur[i], dur[j]);
						result[1] = Math.max(dur[i], dur[j]);
					}
				}
				i++;
			}else {
				j--;
			}
			
		}
		return result;
	 }
	 /**
	  * 1635. Max Pair
		给两个数组，给一个最大值，在这两个数组里各找一个组成一对，求其和最接近最大值，但不大于最大值的所有数对
	  * LANG
	  * @param a
	  * @param b
	  * @param x
	  * @return
	  */
	 public static int[][] getAns(int[] a, int[] b, int x) {
	        // Write your code here.
		 Arrays.sort(a);
		 Arrays.sort(b);
		 int max=0;
		 Comparator<int[]> comparator=new Comparator<int[]>() {
			 public int compare(int[] o1,int[]o2){
				 
				 int com0=Integer.compare(o1[0], o2[0]);
				 if (com0!=0) {
					return com0;
				}else {
					return Integer.compare(o1[1], o2[1]);
				}
			 }
		};
		 Set<int[]> set=new TreeSet<>(comparator);
		 int i=0;
		 int j=b.length-1;
		 while(i<a.length&&j>=0){
			 int sum=a[i]+b[j];
				if (sum<=x) {
					if (sum>max) {
						set.clear();
						max=sum;
						int []temp={a[i],b[j]};
						set.add(temp);
					}
					if (sum==max) {
						max=sum;
						int []temp={a[i],b[j]};
						set.add(temp);
					}
					i++;
				}else {
					j--;
				}
		 }
		 
		 int [][]result=new int[set.size()][];
		 int k=0;
		 for (int[] is : set) {
			 if (k<set.size()) {
				 result[k]=is;
				 k++;
			}
		}
		 return result;
	 }
	 /**
	  * 1561. BST Node Distance
	  * 给定一个integer数组（无序）和2个node值，按给出的数组构建 BST(需要按给定的数组元素顺序，挨个插入树中来构建BST)，找出这两个node在 BST 中的距离
	  * 如果BST中没有出现这两个节点，返回 -1
	  * LANG
	  * @param numbers
	  * @param node1
	  * @param node2
	  * @return
	  */
	 public int bstDistance(int[] numbers, int node1, int node2) {
	        // Write your code here
		 int len=numbers.length;
		 if (len<=1) {
			return -1;
		 }
		 boolean flag1=false;
		 boolean flag2=false;
		 for (int i = 0; i < numbers.length; i++) {
			if (numbers[i]==node1) {
				flag1=true;
			}
			if (numbers[i]==node2) {
				flag2=true;
			}
		}
		 if (!(flag1&&flag2)) {
			return -1;
		}
		 
		 TreeNode root=new TreeNode(numbers[0]);
		 for (int i = 1; i < numbers.length; i++) {
			buildBTS(numbers[i], root);
		 }
		 return findDistWrapper(root,node1,node2);
	 }
	 
	 public void buildBTS(int n,TreeNode root){
		 if (n>root.val) {
			if (root.right!=null) {
				buildBTS(n, root.right);
			}else {
				root.right=new TreeNode(n);
			}
		}else {
			if (root.left!=null) {
				buildBTS(n, root.left);
			}else {
				root.left=new TreeNode(n);
			}
		}
	 }
	 
	 public int distanceFromRoot(TreeNode root, int x)  
		{  
		    if (root.val == x)  
		        return 0;  
		    else if (root.val > x)  
		        return 1 + distanceFromRoot(root.left, x);  
		    return 1 + distanceFromRoot(root.right, x);  
		}  
		  
		// Returns minimum distance beween a and b.  
		// This function assumes that a and b exist  
		// in BST.  
		public int distanceBetween2(TreeNode root, int a, int b)  
		{  
		    if (root == null)  
		        return 0;  
		  
		    // Both keys lie in left  
		    if (root.val > a && root.val > b)  
		        return distanceBetween2(root.left, a, b);  
		  
		    // Both keys lie in right  
		    if (root.val < a && root.val < b) // same path  
		        return distanceBetween2(root.right, a, b);  
		  
		    // Lie in opposite directions (Root is  
		    // LCA of two nodes)  
		    if (root.val >= a && root.val <= b)  
		        return distanceFromRoot(root, a) + distanceFromRoot(root, b); 
		          
		    return 0; 
		}  
		  
		// This function make sure that a is smaller  
		// than b before making a call to findDistWrapper()  
		public int findDistWrapper(TreeNode root, int a, int b) {
			int temp = 0;
			if (a > b) {
				temp = a;
				a = b;
				b = temp;
			}
			return distanceBetween2(root, a, b);
		}
		/**
		 * 1637. Tree problem
		 * Subtree with Maximum Average 其实就是，只不过入参不一样
		 * 给定一棵n个结点的树，第i个结点的父亲为fa[i-1]，价值为val[i-1]。
			特别地，1表示根节点, 2 表示第二个节点，以此类推，并且保证根节点的父亲是 -1 即 fa[0] = -1。
			某子树的平均价值为，该子树所有的结点val和除以该子树的结点数。
			求该树的子树最大平均价值的为多少, 返回这颗子树的根节点编号。
			
			解法：
			先遍历一遍两个vector(fa和val)，记下每个节点的sum和count(用mp map)，然后再反向遍历map，
			将每个节点的sum和count加到其父节点上()。然后又遍历一遍map，记下max average。
		 * LANG
		 * @param fa
		 * @param val
		 * @return
		 */
	public int treeProblem(int[] fa, int[] val) {
		int len = fa.length;
		if (len == 0)
			return 0;

		Map<Integer, Result> map = new HashMap<>(); // id, sum_count

		map.put(0, new Result(val[0], 1));

		for (int i = 1; i < len; ++i) {
			
			map.put(i, new Result(val[i], 1));
		}

		// add the sum and count to the father node
		for (int i = len - 1; i > 0; --i) { // do not consider fa[0]=-1
			map.get(fa[i] - 1).sum += map.get(i).sum;
			map.get(fa[i] - 1).size += map.get(i).size;
		}

		int maxId = -1;
		double maxAve = 0; // from <limits>

		for (int i = 0; i < len; ++i) {
			double ave = map.get(i).sum / map.get(i).size;
			if (ave > maxAve) {
				maxId = i;
				maxAve = ave;
			}
		}
		return maxId + 1;
	}
    /**
     * 1638. Least Substring

     * 给定一个包含n个小写字母的字符串s，要求将字符串划分成若干个连续子串，子串中的字母类型相同，同时子串的字母个数不超过k，输出最少划分的子串数量
     * 输入: s = "aabbbc", k = 3
		输出: 3
		解释: 
		划分成 "aa", "bbb", "c" 三个子串
		
		输入: s = "aabbbc", k = 2
		输出: 4
		解释:
		划分成 "aa", "bb", "b", "c" 四个子串
     * LANG
     * @param s
     * @param k
     * @return
     */
    public int getAns(String s, int k) {
        // Write your code here
         int n = s.length(), ans = 1, cnt = 1;
        for (int i = 1; i < n; i++) {
            if (s.charAt (i) == s.charAt (i - 1) && cnt < k) {
                cnt++;
            } else {
                ans++;
                cnt = 1;
            }
        }
        return ans;
    }
    
    public static void main(String[] args) {
    	Solution solution=new Solution();
		/*int a[]={4,8,5,9,2,10,23};
		int b[]={12,42,23,34,31,29};
		int[][]resutl=getAns(b, a, 19);
		for (int i = 0; i < resutl.length; i++) {
			for (int j = 0; j < resutl[0].length; j++) {
				System.out.print(resutl[i][j]+",");;
			}
			System.out.println();
		}*/
    	
    	int []arr={10,18,12,16,19,13,20,3,1,7,14};
    	System.out.println(solution.bstDistance(arr, 18, 7));;
	}
}
