package al;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class Solution {

	public static int[] twoSum(int[] nums, int target) {

		int[] temp=nums;
		Arrays.sort(temp);
		//如果它包含在数组中，则返回搜索键的索引；否则返回 (-(插入点) - 1) 
		int index=Arrays.binarySearch(temp, target);
		System.out.println(index);
		int insert=0;
		if (index<0) {
			insert=Math.abs(index)-1;
			System.out.println(insert);
		}else {
			insert=index;
		}
		List<Integer> list=new ArrayList<Integer>();
		
		for (int i = 0; i < insert; i++) { 
			for (int j = i+1; j < insert; j++) {
				if (temp[i]+temp[j]==target) {
//					System.out.println(nums[i]+","+nums[j]);
					list.add(temp[i]);
					list.add(temp[j]);
				}
				
			}
		}
//		System.out.println(list.toString());
		int arr[]=new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			arr[i]=Arrays.binarySearch(nums, list.get(i));
			System.out.println(arr[i]);
		}
		
		return arr;
		
	}
	
	
	public static int reverse(int x) {

		boolean b=false;
		if (x<0) {
			x=Math.abs(x);
			b=true;
		}
		String s=String.valueOf(x);
		StringBuilder sb=new StringBuilder(s);
		sb.reverse();
		
		s=sb.toString();
		
		if (s.startsWith("0")) {
			s=s.substring(1,s.length());
		}
		try {
			
			x=Integer.valueOf(s);
		} catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
		if (b) {
			x=-x;
		}
		System.out.println(x);
		return x;
	        
	}
	
	
	public static boolean isPalindrome(int x) {
		
		String s=String.valueOf(x);
		char [] arr=s.toCharArray();
		if (arr.length==1) {
			return true;
		}
		for (int i = 0; i < arr.length/2;i++) {
			
			if (arr[i]!=arr[arr.length-1-i]) {
				return false;
			}
			continue;
		}
		return true;
        
    }

	
	
	
	public static boolean isPalindrome(String s){
		int i=0;int j=s.length()-1;
		boolean flag=true;
		while(i<j) {
			while (!(isLetterOrDigi(s.charAt(i)))&&i<j) {
				i++;
			}
			while (!(isLetterOrDigi(s.charAt(j)))&&i<j) {
				j--;
			}
			char ch1=s.charAt(i);
			char ch2=s.charAt(j);
			if ((ch1==ch2)||(ch1==ch2+32)||(ch2==ch1+32)) {
				i++;
				j--;
			}else {
				flag=false;
				break;
			}
			
		}
		return flag;
		
	}
	
	public static boolean isLetterOrDigi(char ch1){
		if ((ch1>=48&&ch1<=57)||(ch1>=65&&ch1<=90)||(ch1>=97&&ch1<=122)) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean canWinNim(int n) {
        int []first={1,2,3};
        if (n<4) {
			return true;
		}
        while (n>1) {
			
			
		}
		return false;
    }
	
	public String reverseWords(String s) {
		String[] arr=s.split(" ");
		String s1="";
		for (int i = 0; i < arr.length; i++) {
			String word=arr[i];
			if (i==arr.length-1) {
				s1+=reverseString(word);
			}else {
				
				s1+=reverseString(word)+" "; 
			}
		}
		return s1;
        
    }
	
	public static void deleteNode(ListNode node,int a) {
        ListNode p=node;
        ListNode q=node;
        while (p.next!=null) {
			if (p.val!=a) {
				p=p.next;
				q.next=p;
			}
			q.next=p.next;
		}
    }

	public static ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode reverseHead = null;
		// 指针1：当前节点
		ListNode currentNode = head;
		// 指针2：当前节点的前一个节点
		ListNode prevNode = null;

		while (currentNode != null) {
			// 指针3：当前节点的后一个节点
			ListNode nextNode = currentNode.next;
			if (nextNode == null) {
				reverseHead = currentNode;
			}
			// 将当前节点的后一个节点指向前一个节点
			currentNode.next = prevNode;
			// 将前一个节点指向当前节点
			prevNode = currentNode;
			// 将当前节点指向后一个节点
			currentNode = nextNode;
		}

		return reverseHead;
	}
	
	public static ListNode reverseList2(ListNode head){
		
		if (head==null) {
			return head;
		}
		
		ListNode pre=null;
		ListNode current=head;
		while(current!=null){
			ListNode next=current.next;
			current.next=pre;
			pre=current;
			current=next;
			
		}
		return pre;
	}
	public static int singleNumber(int[] nums) {
		int num = 0;  
        for(int i = 0; i < nums.length; i++) {  
           
            num ^= nums[i];  
        }  
        return num;  
    }

	public  static int[] singleNumber1(int[] nums) {
        int sum = 0;    //记录所有异或的值，即两个只出现一次数的异或
        for(int i=0;i<nums.length;i++){
            sum ^= nums[i];
        }
        int[] res = new int[2];
 
        sum &= -sum;    //得出两个数异或结果的最右边的一个1，其他的为零，这样进行&操作就可以将两个不同的数分到不同的两组去
        for(int i=0;i<nums.length;i++){
            if((sum&nums[i])==0) 
                res[0] ^= nums[i];
            else 
                res[1]^=nums[i];
        }
        return res;
    }


	public static int singleNumber2(int[] nums) {
        int result = 0;
        for(int i=0;i<32;i++){
            int mask = 1<<i;
            int count = 0;
            for(int j=0;j<nums.length;j++){
               if((mask&nums[j])!=0)
                   count++;
            }
           if(count%3==1)
               result = mask|result;
        }
        return result;
    }

	
	
	/**
	 * 普通二叉树最近公共祖先
	 * LANG
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	
	public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
		if (root==null) {
			return root;
		}
		while(root!=null){
			List<TreeNode> pList=preOrder1(root, p);
			List<TreeNode> qList=preOrder1(root, q);
			int length=pList.size()<qList.size()?pList.size():qList.size();
			
			for (int i = 0; i < length; i++) {
				if (pList.get(i).val!=qList.get(i).val) {
					return pList.get(i-1);
				}
			}
		}
		
		return root;
	}

	
    
	
	public TreeNode invertTree(TreeNode root) {
        if(root!=null){
        	TreeNode temp=root.left;
        	root.left=root.right;
        	root.right=temp;
        	invertTree(root.left);
        	invertTree(root.right);
        }
        return root;
    }
	
	public static List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<>();
		int n = nums.length;
		for (int i = 0; i < nums.length; i++){
			nums[(nums[i] - 1) % n] += n;
		}
		for (int i = 0; i < nums.length; i++){	
			if (nums[i] <= n){
				res.add(i + 1);
			}
		}
		return res;
	}

	public  static void findMultiString(){
		String[] arr={"a","c","b","a"};
		int sum=arr[0].hashCode();
		for (int i = 1; i < arr.length; i++) {
			System.out.println(arr[i].hashCode());
			sum^=arr[i].hashCode();
		}
		System.out.println(sum);
	}
	
	public static ListNode test(ListNode head,int x){
		
		if(head == null){
	        return  null;
	    }
	 
		ListNode left = new ListNode(0);
		ListNode right = new ListNode(0);
	 
		ListNode dummyLeft = left;
	    ListNode dummyRight = right;
	 
	    while(head != null){
	        if(head.val < x){
	            dummyLeft.next = head;
	            dummyLeft = dummyLeft.next;
	        }else{
	            dummyRight.next = head;
	            dummyRight = dummyRight.next;
	        }
	        head = head.next;
	    }
	 
	    dummyLeft.next = right.next;
	    right.next = null;
	 
	    return left.next;
	}
	
	public static ListNode getMid(ListNode head){
	      if(head == null){
	         return null;
	      }
	 
	      ListNode slow = head;
	      ListNode fast = head;
	 
	      // fast.next = null 表示 fast 是链表的尾节点
	      while(fast != null && fast.next != null){
	         fast = fast.next.next;
	         slow = slow.next;
	      }
	      return slow;
	    }
	
	
	static boolean isLoopList(ListNode head){
		 
        if (head == null){
            return false;
        }
 
 
        ListNode slow = head;
        ListNode fast = head.next;
 
        //如果不是循环链表那么一定有尾部节点 此节点 node.next = null
        while(slow != null && fast != null && fast.next != null){
            if (fast == slow || fast.next == slow){
                return true;
            }
            // fast 每次走两步  slow 每次走一步
            fast =fast.next.next;
            slow = slow.next;
        }
        //如果不是循环链表返回 false
        return false;
    }
	
	//如何构造辅助函数的参数？
	//逐个字母匹配的时候，如何传递下一个要匹配的字母？---通过索引参数的形式
	//走过的路要标记，不管可行不可行，都要重新标记？
	//边界条件的考虑？
	
	public static boolean exist(char[][]board, String word) {
	         int m = board.length;
	         int n = board[0].length;
	        boolean[][] visited=new boolean[m][n];//将访问标记数组置空
	        for(int i = 0; i < m; i++){
	            for(int j = 0; j < n; j++){
	                if(dfs(board, word, 0, i, j, visited)){//单词可在任意位置开始匹配
	                    return true;  //只要有一个位置完全匹配即匹配
	                }
	            }
	        }
	        return false;
	    }
	public static boolean dfs(char [][] board, String word, int index,
	                    int x, int y, boolean[][] visited)//辅助函数，自定义参数
	    {
	        if(index == word.length())    //单词大小和索引相等即匹配
	                                    //当单词为空的时候是满足的
	                                    //下一个要查找的索引和单词大小相等说明，
	                                    //word的0 - index的位置的字母已经匹配
	            return true;
	        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length) //不可越界
	            return false;
	        if(visited[x][y]) //如果之前访问过，那么直接返回false
	            return false;
	        if(board[x][y] != word.charAt(index)) //不相等，这条路走不通，剪枝
	            return false;
	        visited[x][y] = true; //先标记为走过，因为下一次会走向四个方向
	        boolean ret = dfs(board, word, index + 1, x, y + 1, visited) ||
	                dfs(board, word, index + 1, x, y - 1, visited)    ||
	                dfs(board, word, index + 1, x - 1, y, visited)     || 
	                dfs(board, word, index + 1, x + 1, y, visited); 
	        visited[x][y] = false;  //走过之后，不过不匹配，要还原为没有走过
	        return ret;
	    }
	
	
	public static int hammingDistance(int x, int y) {
		int xor = x^y;
       /* int res = 0;
        while(xor!=0) {
            res+= xor & 1;
            xor >>= 1;
        }
        return res;*/
		//return  Integer.bitCount(xor);
		String temp = Integer.toString(x^y, 2);
		String temp_a = temp.replaceAll("1","");
		return temp.length() - temp_a.length();
    }
	
	public static void quickSort(int[] arr, int startIndex, int endIndex) {

		// 递归结束条件：startIndex大等于endIndex的时候

		if (startIndex >= endIndex) {

			return;
		}

		// 得到基准元素位置

		int pivotIndex = partition(arr, startIndex, endIndex);

		// 根据基准元素，分成两部分递归排序
		quickSort(arr, startIndex, pivotIndex - 1);
		quickSort(arr, pivotIndex + 1, endIndex);
	}

	private static int partition(int[] arr, int startIndex, int endIndex) {

		// 取第一个位置的元素作为基准元素

		int pivot = arr[startIndex];

		int left = startIndex;

		int right = endIndex;

		while (left != right) {

			// 控制right指针比较并左移

			while (left < right && arr[right] > pivot) {
				right--;
			}

			// 控制right指针比较并右移

			while (left < right && arr[left] <= pivot) {
				left++;
			}

			// 交换left和right指向的元素

			if (left < right) {

				int p = arr[left];
				arr[left] = arr[right];
				arr[right] = p;
			}
		}

		// pivot和指针重合点交换

		int p = arr[left];
		arr[left] = arr[startIndex];
		arr[startIndex] = p;

		return left;
	}

	public static TreeNode deleteNode(TreeNode root, int key) {
		if (root==null||root.val==key) {
			return deleteNode(root);
		}
		TreeNode p=root;
		while(true){
			if (p.val>key) {
				if (p.left==null||p.left.val==key) {
					p.left=deleteNode(p.left);
					break;
				}
				p=p.left;
			}else {
				if (p.right==null||p.right.val==key) {
					p.right=deleteNode(p.right);
					break;
				}
				p=p.right;
			}
		}
		
		return root;
		
	}

	public static TreeNode deleteNode(TreeNode root){
		if (root==null) {
			return root;
		}
		
		if (root.right==null) {
			return root.left;
		}
		if (root.left==null) {
			return root.right;
		}
		TreeNode temp=root.right;
		while(temp.left!=null){
			temp=temp.left;
		}
		temp.left=root.left;
		return root.right;
	}
	
	public static void main(String[] args) {
//		int[] arr={3,2,4,3,1,5};
//		int target=6;
//		int [] test=twoSum(arr, target);
//		for (int i = 0; i < test.length; i++) {
//			System.out.println(test[i]);
//		}
		
//		reverse(120);
//		boolean b=isPalindrome(1000021);
//		System.out.println(b);
		
//		Object o=5;
//		int a=(int)o;
//		System.out.println(a);
		
//		int a=-30;
//		System.out.println(a>>>1);;
		
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
//		node4.next=node1;
//		ListNode result=test(node1,3);
//		ListNode result=getMid(node1);
		boolean result=isLoopList(node1);
		System.out.println(result);
		
		System.out.println(reverseList2(node1));;
//		deleteNode(node1);
//		ListNode node5=reverseList(node1);
		
//		int num[]={4,1,2,1,2,1,2};
		
//		int []arr=singleNumber1(num);
//		for (int i = 0; i < arr.length; i++) {
//			System.out.println(arr[i]);
//		}
		
//		System.out.println(singleNumber2(num));;
//		int a=5;
//		int b=2;
//		int c=-5;
//		System.out.println(a=a&c);
		
		TreeNode root1=new TreeNode(6);
		TreeNode root2=new TreeNode(2);
		TreeNode root3=new TreeNode(8);
		TreeNode root4=new TreeNode(0);
		TreeNode root5=new TreeNode(4);
		TreeNode root6=new TreeNode(7);
		TreeNode root7=new TreeNode(9);
		TreeNode root8=new TreeNode(3);
		TreeNode root9=new TreeNode(5);
		root1.left=root2;
		root1.right=root3;
		root2.left=root4;
		root2.right=root5;
		root5.left=root8;
		root5.right=root9;
		root3.left=root6;
		root3.right=root7;
		
		System.out.println(deleteNode(root1, 4));;
		
//		TreeNode root=lowestCommonAncestor3(root1,root2,root5);
//		System.out.println(root);
//		preOrder1(root1);
//		midOrder1(root1);
//		posOrder1(root1);
//		levelOrder1(root1);
		
//		System.out.println(maxDepth(root1));;/
		//int []arr={4,3,2,7,8,2,3,1};
//		findDisappearedNumbers(arr);
//		findMultiString();
		
		
		/*System.out.println(Integer.toBinaryString(1));;
		
		
		int[] arr = new int[] { 4, 7, 6, 5, 3, 2, 8, 1 };
		quickSort(arr, 0, arr.length - 1);

		System.out.println(Arrays.toString(arr));
		
		
		System.out.println(isPalindrome("race a car"));;
		
		System.out.println(hammingDistance(1, 4));;*/
		
		char[][]board ={
		          {'A','B','C','E'},
		          {'S','F','C','S'},
		          {'A','D','E','E'}
		};

		String word="SEE";
		
		System.out.println(exist(board, word));;
		
		String s ="abcdbbfcba";
		System.out.println(longestPalindrome2(s));;
	}
	
	/**
	 * 最长回文子串
	 * LANG
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if (s==null||s.length()<=1) {
			return s;
		}
		String sub="";
		for (int i = 0; i < 2*s.length()+1; i++) {
			sub=findPalindrome(i-1,i+1,s,sub);
		}
		return sub;
		
	}
    public static String findPalindrome(int left,int right,String s,String sub) {
		while (left>=0&&right<2*s.length()+1) {
			if (left%2!=0&&right%2!=0&&s.charAt(left/2)!=s.charAt(right/2)) {
				break;
			}
			left--;
			right++;
		}
		if (left==-1) {
			left=-2;
		}
		int start=left/2+1;
		int end=right/2;
		if (sub.length()<=end-start) {
			sub=s.substring(start,end);
		}
		return sub;
	}
    
    public static String longestPalindrome2(String s){
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
	
	public List<Interval> merge(List<Interval> intervals) {
        
		return null;
    }
	
	
}
