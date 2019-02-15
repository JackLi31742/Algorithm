package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;






public class Solution {

	public static void main(String[] args) {
		Solution solution=new Solution();
		int [][]points = {{1,3},{-2,2}};int K = 3;
		int [][]result=solution.kClosest(points, K);
		for (int i = 0; i < result.length; i++) {
			for (int j = 0; j < result[0].length; j++) {
				System.out.println(result[i][j]);
			}
		}
	}
	/**
	 * deep copy
	 * 138. Copy List with Random Pointer
	 * A linked list is given such that each node contains an additional 
	 * random pointer which could point to any node in the list or null.

		Return a deep copy of the list.
		
		I just create all nodes and put <old, new> pairs into a map. 
		Then update next and random pointers for each new node.
		
		 takes O(N) space and O(N) time
	 */
	
	public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        RandomListNode cur = head;
        while(cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        
        for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
            final RandomListNode newNode = entry.getValue();
            newNode.next = map.get(entry.getKey().next);
            newNode.random = (entry.getKey().random == null) ? null : map.get(entry.getKey().random);
        }
        
        return map.get(head);
    }
	
	/**
	 * Order Dependency
	 * 207. Course Schedule
	 * 一节课得在另一节课上完后上
	 * LANG
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        // 把int[][]转换成adjacency list
        // 在graph中，index是每一个课程
        // 所连接的arraylist是这个课程所相连的其他所有课程
        for (int i = 0; i < prerequisites.length; i++) {
            if (graph[prerequisites[i][0]] == null) {
                graph[prerequisites[i][0]] = new ArrayList<>();
            }
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        // mark每个cell中具有3个值，0,1,2
        // 0 代表 没有被访问过
        // 1 代表 正在被访问
        int[] mark = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(mark, graph, i)) {
                return false;
            }
        }

        return true;
    }
    
    private boolean dfs(int[] mark, ArrayList[] graph, int course) {
        if (mark[course] == 1) {
            return false;
        } else if (mark[course] == 2) {
            return true;
        } else {
            mark[course] = 1; // 现在正在访问中
        }

        // 继续访问该课程相连的所有课程
        if (graph[course] != null) {
            for (int i = 0; i < graph[course].size(); i++) {
                if (!dfs(mark, graph, (int)graph[course].get(i))) {
                    return false;
                }
            }
        }
        mark[course] = 2;
        return true;
    }
    
    /**
	 * 1. Two Sum
	 * LANG
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
        int result[]=new int[2];
        Map<Integer, Integer> map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
			if (map.containsKey(target-nums[i])) {
				result[0]=i;
				result[1]=map.get(target-nums[i]);
				return result;
			}
			map.put(nums[i], i);
		}
        return result;
    }
	
	/**
	 * 上题的双指针
	 * 167. Two Sum II - Input array is sorted
	 * LANG
	 * @param capacity
	 * @param weights
	 * @param numOfContainers
	 */
	public static void findOptimalWeights(double capacity, double[] weights, int numOfContainers){
        double min = 0.0;
        int minPos = 0;
        int maxPos = weights.length - 1;
        int i =0 , j =weights.length-1;

        Arrays.sort(weights);

        while(i < j){
            double sum = weights[i] + weights[j];

            if(sum > min && sum <= capacity){
                min = sum;
                minPos = i;
                maxPos = j;
            }

            if(sum > capacity){
                j--;
            }else {
                i++;
            }
        }

        System.out.println("The two numbers for which sum is closest to target are "
                + weights[minPos] + " and " + weights[maxPos]);
    }
	
	
	/**
     * window sum
     * 一个数组，再给定一个长度，让你算出数组里面，在这个长度下，分别的连续和
     */
   
   public List<Integer> GetwindowSum(List<Integer> A, int k) {
   	   ArrayList<Integer> result  = new ArrayList<>();
   	   if (A == null || A.size() == 0 || k <= 0) return result;
   	   int count = 0;
   	   for (int i = 0; i < A.size(); i++) {
   	       count++;
   	       if (count >= k) {
   	           int sum = 0;
   	           for (int j = i; j >= i - k + 1; j--) {
   	               sum += A.get(j);
   	           }
   	           result.add(sum);
   	       }
   	   }
   	   return result;
   	}
   
       public int[] SumOfWindow(int[] array, int k) {
           if (array == null || array.length < k || k <= 0)    return null;
           
           /**
            * 如果窗口比arr长
            */
           if (k>array.length) {
				int sum=0;
           	for (int i = 0; i < array.length; i++) {
					sum+=array[i];
				}
           	return new int[]{sum};
			}
           int[] result = new int[array.length - k + 1];
           for (int i = 0; i < k; i++)
               result[0] += array[i];
           for (int i = 1; i < result.length; i++) {
               result[i] = result[i-1] - array[i-1] + array[i+k-1];
           }
           return result;
       }
       
	
	 /**
	  * 973. K Closest Points to Origin
	  * LANG
	  * @param points
	  * @param K
	  * @return
	  */
	 
	 public int[][] kClosest(int[][] points, int K) {
		 int[][]resut=null;
		 if (points==null||K<1) {
			return resut;
		}
		 if (K>points.length) {
			K=points.length;
		}
		 resut=new int[K][];
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
			Point[] result=null;
			if (k<1||points==null) {
				return new Point[0];
			}
			if (k>points.length) {
				k=points.length;
			}
			result=new Point[k];
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
		  * 5. Longest Palindromic Substring
		  * 动态规划：
		  * 状态dp[j][i]表示索引j到索引i的子串是否是回文串
		  * j=i,dp[j][i]=true;
			i-j=1,dp[j][i]=str[i]==str[j]
			i-j>1,dp[j][i]=(str[i]==str[j])&&dp[j+1][i-1]
			dp[j][i]为true时表示索引j到索引i形成的子串为回文子串，且子串起点索引为j,长度为i - j + 1。
			
			算法时间复杂度为O(N ^ 2) n is length of string
			https://www.jianshu.com/p/c82cada7e5b0
		  * LANG
		  * @param s
		  * @return
		  */
		 public String longestPalindrome2(String s) {
		        if (s==null||s.length()==0) {
					return s;
				}
		        int len=s.length();
		    	boolean [][]dp=new boolean[len][len];
		    	int maxlen=1;
		    	int start=0;
		    	for (int i = 0; i < len; i++) {
					for (int j = 0; j <= i; j++) {
						if (i-j<2) {
							dp[j][i]=(s.charAt(i)==s.charAt(j));
						}else {
							dp[j][i]=((s.charAt(i)==s.charAt(j))&&dp[j+1][i-1]);
						}
						
						if (dp[j][i]&&maxlen<i-j+1) {
							maxlen=i-j+1;
							start=j;
							
						}
					}
				}
		    	
		    	return s.substring(start,maxlen+start);
		    }
		 
		 /**
			 * 937. Reorder Log Files
			 * 字母日志在数字日志前
			 * Time Complexity: O(AlogA), where A is the total content of logs.

				Space Complexity: O(A).
			 * LANG
			 * @param logs
			 * @return
			 */
			public String[] reorderLogFiles(String[] logs) {
				
				Comparator<String> comparator=new Comparator<String>() {

					public int compare(String log1, String log2) {
						//最多分成2个
						String[] split1 = log1.split(" ", 2);
						String[] split2 = log2.split(" ", 2);
						// 第二个是数字还是字母
						boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
						boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
						if (!isDigit1 && !isDigit2) {
							int com = split1[1].compareTo(split2[1]);
							if (com != 0) {
								return com;
							}
							return split1[0].compareTo(split2[0]);
						}
						if (isDigit1) {
							if (isDigit2) {
								return 0;
							}else {
								return 1;
							}
						}else {
							return -1;
						}
					}
					
				};
				Arrays.sort(logs, comparator);
		        return logs;
		    }
	 /**
	     * amazon 节点是多个，不仅仅是两个子节点
	     * 类似于Subtree with Maximum Average
	     * 
	     * divide and conquer + traversal. 
	     * the divide-and-conquer method returns the current value for its father, 
	     * in the process of each calculation, if its value bigger than global,
	     * it will be stored in the global variable. Finally, just return the global variable.
	     * 
	     * Time complexity:O(n) n is the number of all nodes
	     */
	    private static CategoryNode subNode;
	    private static Result subResult;
	    public static CategoryNode getMostPopularNode(CategoryNode root){
	    	getResult(root);
	    	return subNode;
	    }
	    public static Result getResult(CategoryNode root){
	    	if (root==null) {
				return new Result(0, 0);
			}
	    	int sumAll=0;int sizeAll=0;
	    	ArrayList<CategoryNode> subCategoryNode=root.subCategoryNode;
	    	for (int i = 0; i < subCategoryNode.size(); i++) {
	    		CategoryNode cur=subCategoryNode.get(i);
	    		Result cuResult=getResult(cur);
	    		sumAll+=cuResult.sum;
	    		sizeAll+=cuResult.size;
			}
	    	
	    	Result result=new Result(sumAll+root.value, sizeAll+1);
	    	System.out.println("result:"+result);
	    	//subtreeResult.sum/subtreeResult.size<result.sum/result.size  当前结果大于全局则更新
	    	//当前结果为root和result
	    	if (subNode==null||((result.sum*subResult.size>result.size*subResult.sum)
	    			            &&(root.subCategoryNode.size()>0))) {
				subNode=root;
				subResult=result;
				System.out.println("临时结果："+subNode+","+subResult);
			}
	    	
	    	return result;
	    }
	    
	    /**
         * 长方形相交
         * 紫色代表矩形A，红色代表矩形B，并分别用p1,p2,p3,p4代表对应的左上角与右下角Up left and down right
         * 不重叠:
         * 即B矩阵，可能在A的左侧、右侧、上侧、下侧。如果用公式表示，即 
         * Do not overlap:the B matrix may be on the left, the right, the up, and the down of A.
			(p2.y≤p3.y)∨(p1.y≥p4.y)∨(p2.x≤p3.x)∨(p1.x≥p4.x) 
			则，两个矩阵重叠时，公式为 
			Then, when the two matrices overlap, the formula is
			¬[(p2.y≤p3.y)∨(p1.y≥p4.y)∨(p2.x≤p3.x)∨(p1.x≥p4.x)] 
			根据德·摩根定律可转换为 
			According to De Morgan's law, it can be converted to
			(p2.x>p3.x)∧(p2.y>p3.y)∧(p1.x<p4.x)∧(p1.y<p4.y)
			
			Time complexity:O(1)
         */
        
       public boolean isOverlap( Rect rc1, Rect rc2){
            if (rc1.x + rc1.width  > rc2.x &&
            	rc1.y + rc1.height > rc2.y &&
                rc2.x + rc2.width  > rc1.x &&
                rc2.y + rc2.height > rc1.y
               )
                return true;
            else
                return false;
        }
       
       /**
        * amazon Maximum Minimum Path
        * 给一个int[][]的matirx，对于一条从左上到右下的path pi，pi中的最小值是mi，求所有mi中的最大值。只能往下或右.
        * [8, 4, 7]
   	   [6, 5, 9]
   		有3条path：
   		8-4-7-9 min: 4
   		8-4-5-9 min: 4
   		8-6-5-9 min: 5
   		return: 5

   		time complexity:O(row+col)
        */
       
   	    private int min, max, row6, col6;
   	    public int maxMinPath(int[][] matrix) {
   	        row6 = matrix.length;
   	        col6 = matrix[0].length;
   	        min = Integer.MAX_VALUE;
   	        max = Integer.MIN_VALUE;
   	        dfsHelper(matrix, min, 0, 0);
   	        return max;
   	    }
   	
   	    public void dfsHelper(int[][] matrix, int min, int i, int j ){                
   	        if (i >= row6 || j >= col6) return;
   	        if (i == row6 - 1 && j == col6 - 1) {
   	            min = Math.min(min, matrix[i][j]);
   	            max = Math.max(max, min);
   	            return;
   	        }
   	        min = Math.min(min, matrix[i][j]);
   	        dfsHelper(matrix, min, i, j + 1);
   	        dfsHelper(matrix, min, i + 1, j);
   	    }
   	    
   	    
   	    
}
