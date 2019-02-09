package al1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

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

	@Test
	public void test(){
		
		String d=reverseString2("hello");
		System.out.println(d);
	}
	public String reverseString(String s) {
		char[] arr=s.toCharArray();
		char[] out=new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
			out[arr.length-1-i]=arr[i];
		}
		return String.valueOf(out);
    }
	public String reverseString2(String s) {
		char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
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
	 * 二叉搜索树最近公共祖先
	 * LANG
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
			if (root==null) {
				return root;
			}
			while(root!=null){
				if ((p.val<=root.val&&q.val>=root.val)||(p.val>=root.val&&q.val<=root.val)) {
					return root;
				}
				if (p.val<=root.val&&q.val<=root.val) {
					root=root.left;
					continue;
				}
				if (p.val>=root.val&&q.val>=root.val) {
					root=root.right;
					continue;
				}
			}
        	
		return root;
    }
	/**
	 * 普通二叉树最近公共祖先
	 * LANG
	 * @param root
	 * @param p
	 * @param q
	 * @return
	 */
	
	public static TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q)
			return root;
		TreeNode left = lowestCommonAncestor3(root.left, p, q);
		TreeNode right = lowestCommonAncestor3(root.right, p, q);
		if (left == null) {
			return right;
		}else {
			if (right==null) {
				return left;
			}else {
				return root;
			}
		}
//		return left == null ? right : right == null ? left : root;
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

	/**
	 * 前序
	 * LANG
	 * @param Node
	 */
	public static List<TreeNode> preOrder1(TreeNode Node,TreeNode input) {
		Stack<TreeNode> stack = new Stack<>();
		List<TreeNode> list=new ArrayList<>();
		while (Node != null || !stack.empty()) {
			while (Node != null) {
//				System.out.print(Node.val + "   ");
				list.add(Node);
				if (Node.val==input.val) {
					break;
				}
				stack.push(Node);
				Node = Node.left;
			}
			if (!stack.empty()) {
				Node = stack.pop();
				Node = Node.right;
			}
		}
		return list;
	}
	/**
	 * 中序遍历
	 * LANG
	 * @param Node
	 */
	public static void midOrder1(TreeNode Node) {
		Stack<TreeNode> stack = new Stack<>();
		while (Node != null || !stack.empty()) {
			while (Node != null) {
				stack.push(Node);
				Node = Node.left;
			}
			if (!stack.empty()) {
				Node = stack.pop();
				System.out.print(Node.val + "   ");
				Node = Node.right;
			}
		}
	}
	
	/**
     * 后序遍历
     * 非递归
     */
	public static void posOrder1(TreeNode Node) {
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int i = 1;
		while (Node != null || !stack1.empty()) {
			while (Node != null) {
				stack1.push(Node);
				stack2.push(0);
				Node = Node.left;
			}

			while (!stack1.empty() && stack2.peek() == i) {
				stack2.pop();
				TreeNode Node1 = stack1.pop();
				System.out.print(Node1.val + "   ");
			}

			if (!stack1.empty()) {
				stack2.pop();
				stack2.push(1);
				Node = stack1.peek();
				Node = Node.right;
			}
		}
	}
    
	/*
     * 层序遍历
     * 非递归
     */
	public static void levelOrder1(TreeNode Node) {
		if (Node == null) {
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(Node);

		while (queue.size() != 0) {
			Node = queue.poll();

			System.out.print(Node.val + "  ");

			if (Node.left != null) {
				queue.offer(Node.left);
			}
			if (Node.right != null) {
				queue.offer(Node.right);
			}
		}
	}
    
	public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
		TreeNode root = null;
		if (t1!=null&&t2!=null) {
			root=new TreeNode(t1.val+t2.val);
			root.left=mergeTrees(t1.left, t2.left);
			root.right=mergeTrees(t1.right,t2.right);
		}
		if (t1==null) {
			root=t2;
		}
		if (t2==null) {
			root=t1;
		}
		return root;
        
    }
	public static int maxDepth(TreeNode root) {
		int left=0,right=0;
		int dep=0;
//        while (root!=null) {
//        	if (root.left!=null) {
//        		left++;
//        		root=root.left;
//			}
//			
//			if (root.right!=null) {
//				right++;
//				root=root.right;
//			}
//			
//		}
		if (root!=null) {
			dep++;
			left=maxDepth(root.left);
			
			right=maxDepth(root.right);
			return left>right?left+1:right+1;
		}
		return 0;
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

	public TreeNode deleteNode(TreeNode root, int key) {
		if (root == null)
			return root;
		if (root.val > key)
			root.left = deleteNode(root.left, key);
		else if (root.val < key)
			root.right = deleteNode(root.right, key);
		else { // found node to be deleted
			if (root.left == null)
				return root.right;
			else if (root.right == null)
				return root.left;
			// node with two children, replace with the inOrder
			// successor(minVal) in the right subtree
			root.val = getMin(root.right);
			root.right = deleteNode(root.right, root.val);
		}
		return root;
	}

	private int getMin(TreeNode root) {
		while (root.left != null) {
			root = root.left;
		}
		return root.val;
	}
	
		static boolean[][] visited;
	    public static boolean exist(char[][] board, String word) {
	        visited = new boolean[board.length][board[0].length];
	        
	        for(int i = 0; i < board.length; i++){
	            for(int j = 0; j < board[i].length; j++){
	                if((word.charAt(0) == board[i][j]) && search(board, word, i, j, 0)){
	                    return true;
	                }
	            }
	        }
	        
	        return false;
	    }
	    
	    private static boolean search(char[][]board, String word, int i, int j, int index){
	        if(index == word.length()){
	            return true;
	        }
	        
	        if(i >= board.length || i < 0 || j >= board[i].length || j < 0 || board[i][j] != word.charAt(index) || visited[i][j]){
	            return false;
	        }
	        
	        visited[i][j] = true;
	        if(search(board, word, i-1, j, index+1) || 
	           search(board, word, i+1, j, index+1) ||
	           search(board, word, i, j-1, index+1) || 
	           search(board, word, i, j+1, index+1)){
	            return true;
	        }
	        
	        visited[i][j] = false;
	        return false;
	    }
	    /*
	    public static void HeapSort(int[]arr,int k){
	    	buildHeap1(arr,k);
	    	for (int i = k; i < arr.length; i++) {
	    		if (arr[i]<arr[0]) {
					swap(i,0,arr);
	    			shiftHeap(arr,0,k);
				}
			}
	    	System.out.println(Arrays.toString(arr));
	    }
	    public static void buildHeap1(int []arr,int k){
	    	
	    	for (int i = (k/2-1); i >=0; i--) {
				shiftHeap(arr,i,k);
			}
	    	for(int j = k-1; j >0; j --)        //对大顶堆进行排序
			{
				swap(0,j,arr);
				shiftHeap(arr,0,j);
			}
	    }
	    
	    public static void shiftHeap(int []arr,int i,int len){
	    	int left=i*2+1;
	    	int right=i*2+2;
	    	int large=0;
	    	if (right<len&&arr[i]<arr[right]) {
				large=right;
			}else if (left<len&&arr[i]<arr[left]) {
				large=left;
			}else {
				large=i;
			}
	    	if (large!=i) {
				swap(large, i,arr);
				shiftHeap(arr, large, len);
			}
	    }
	    public static void swap(int x,int y,int[]arr){
	    	int temp=arr[x];
	    	arr[x]=arr[y];
	    	arr[y]=temp;
	    	
	    }
	    */
	/**
	 * 寻找第k大的元素
	 * 
	 * @param array
	 *            待调整的堆
	 * @param k
	 *            第几大
	 */
	public

	static

	int findNumberK(int[] array, int k) {

		// 1.用前k个元素构建小顶堆
		buildHeap(array, k);

		// 2.继续遍历数组，和堆顶比较

		for (int i = k; i < array.length; i++) {

			if (array[i] > array[0]) {
				array[0] = array[i];
				downAdjust(array, 0, k);
			}
		}

		// 3.返回堆顶元素

		return array[0];
	}

	/**
	 * 构建堆
	 * 
	 * @param array
	 *            待调整的堆
	 * @param length
	 *            堆的有效大小
	 */
	private

	static

	void buildHeap(int[] array, int length) {

		// 从最后一个非叶子节点开始，依次下沉调整

		for (int i = (length - 2) / 2; i >= 0; i--) {
			downAdjust(array, i, length);
		}
	}

	/**
	 * 下沉调整
	 * 
	 * @param array
	 *            待调整的堆
	 * @param index
	 *            要下沉的节点
	 * @param length
	 *            堆的有效大小
	 */
	private

	static

	void downAdjust(int[] array, int index, int length) {

		// temp保存父节点值，用于最后的赋值

		int temp = array[index];

		int childIndex = 2 * index + 1;

		while (childIndex < length) {

			// 如果有右孩子，且右孩子小于左孩子的值，则定位到右孩子

			if (childIndex + 1 < length && array[childIndex + 1] < array[childIndex]) {
				childIndex++;
			}

			// 如果父节点小于任何一个孩子的值，直接跳出

			if (temp <= array[childIndex])

				break;

			// 无需真正交换，单向赋值即可
			array[index] = array[childIndex];
			index = childIndex;
			childIndex = 2 * childIndex + 1;
		}
		array[index] = temp;
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
		
		
		System.out.println(Integer.toBinaryString(1));;
		
		
		/*int[] arr = new int[] { 4, 7, 6, 5, 3, 2, 8, 1 };
		quickSort(arr, 0, arr.length - 1);

		System.out.println(Arrays.toString(arr));
		
		
		System.out.println(isPalindrome("race a car"));;
		
		System.out.println(hammingDistance(1, 4));;
		
		char[][]board =
				{
				  {'A','B','C','E'},
				  {'S','F','C','S'},
				  {'A','D','E','E'}
				};
		String word = "SEE";
		
		System.out.println(exist(board,word));;*/
		
		
		int[] arr={16,7,3,20,17,8,22,23};
		//HeapSort(arr,arr.length);
//		System.out.println(findNumberK(arr, 8));;
//		System.out.println(Arrays.toString(arr));
		
		
		String s="babad";
		
//		System.out.println(longestPalindrome(s));;
		
		
		char[][] grid={{1,1,1,1,0},{1,1,0,1,0},{1,1,0,0,0},{0,0,0,0,0}};
		
//		numIslands(grid);
		System.out.println('1');
		
		fib(10);
		
		
		int [][] points ={{3,3},{5,-1},{-2,4}};
		
		
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
    
    int row=0; int col=0;
    public int numIslands(char[][] grid) {
        row=grid.length;
        if (row == 0) return 0;
        col=grid[0].length;
        int count=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                	dfs(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public void dfs(char[][]grid,int i,int j){
    	if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') {
			return ;
		}
    	 	grid[i][j] = '0';
            dfs(grid,i+1,j);
            dfs(grid,i-1,j);
            dfs(grid,i,j-1);
            dfs(grid,i,j+1);
        
    }
    
    
    public int maxAreaOfIsland(int[][] grid) {
        int max_area=0;
        row=grid.length;
        if(row==0) return 0;
        col=grid[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1){
                    max_area=Math.max(max_area,getArea(grid,i,j));
                }
            }
        }
        return max_area;
    }
    
    public int getArea(int[][] grid,int i,int j){
        if(grid[i][j]==1&&i<row&&j<col){
        	grid[i][j]=0;
            return 1+getArea(grid,i+1,j)+getArea(grid,i-1,j)+getArea(grid,i,j+1)+getArea(grid,i,j-1);
        }
        return 0;
    }
    
    public static void fib(int n){
    	int x=1;
    	int y=1;
    	int sum=x+y;
    	int num=0;
    	while(n>2){
    		 num=x+y;
    		System.out.println("num:"+num);
    		sum+=num;
    		System.out.println("sum:"+sum);
    		x=y;
    		y=num;
    		n--;
    	}
    	System.out.println("numAll:"+num);
    	System.out.println("sumAll:"+sum);
    }
    
    public int islandPerimeter(int[][] grid) {
        if(grid==null) return 0;
        int row=grid.length;
        if(grid[0]==null) return 0;
        int col=grid[0].length;
        int numIsland=0;
        int neighbor=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]==1){
                    numIsland++;
                    if(i<row-1&&grid[i+1][j]==1) neighbor++;
                    if(j<col-1&&grid[i][j+1]==1) neighbor++;
                }
            }
        }
        
        return 4*numIsland-2*neighbor;
    }
    
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set=new HashSet<>();
        Set<Integer> intersectSet=new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
			set.add(nums1[i]);
		}
        for (int i = 0; i < nums2.length; i++) {
			if (set.contains(nums2[i])) {
				intersectSet.add(nums2[i]);
			}
		}
        
        int [] result=new int[intersectSet.size()];
        int i = 0;
        for (Integer num : intersectSet) {
            result[i++] = num;
        }
        return result;
    }
    
    public static int[][] kClosest(int[][] points, int K) {
    	 PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> Double.valueOf(Math.pow((p1[0]-p2[0]),2)+Math.pow((p1[1]-p2[1]),2)).intValue());
    	    for (int[] p : points) {
    	        pq.offer(p);
    	        if (pq.size() > K) {
    	            pq.poll();
    	        }
    	    }
    	    int[][] res = new int[K][2];
    	    while (K > 0) {
    	        res[--K] = pq.poll();
    	    }
    	    return res;
    }
    
    public double euclideanDistance(int[][]points,int p1,int p2){
    	return Math.pow((points[p1][0]-points[p2][0]),2)+Math.pow((points[p1][1]-points[p2][1]),2);
    	
    }
}


