package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Solution {
	
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
	public static void levelOrder1(TreeNode root) {
		if (root == null) {
			return;
		}

		Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		while (queue.size() != 0) {
			root = queue.poll();

			System.out.print(root.val + "  ");

			if (root.left != null) {
				queue.offer(root.left);
			}
			if (root.right != null) {
				queue.offer(root.right);
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

		if (root!=null) {
			dep++;
			left=maxDepth(root.left);
			
			right=maxDepth(root.right);
			return left>right?left+1:right+1;
		}
		return 0;
    }
	
	/**
	 * 102. Binary Tree Level Order Traversal
	 * 层次遍历
	 * LANG
	 * @param root
	 * @return
	 */
	 public List<List<Integer>> levelOrder(TreeNode root) {
		 List<List<Integer>> result=new ArrayList<>();
	     if (root==null) {
			return result;
		 }
	     Queue<TreeNode> queue=new LinkedList<>();
	     
	     queue.add(root);
	     while(!queue.isEmpty()){
	    	 int size=queue.size();
	    	 List<Integer> list=new ArrayList<>();
	    	 while(size>0){
	    		 TreeNode node=queue.poll();
	    		 list.add(node.val);
	    		 if (node.left!=null) {
	    			 queue.add(node.left);
	    		 }
	    		 if (node.right!=null) {
	    			 queue.add(node.right);
	    		 }
	    		 size--;
	    	 }
	    	 result.add(list);
	     }
	     return result;
	 }
	 
	 /**
	  * 111. Minimum Depth of Binary Tree
	  * 我们需要添加较小的子深度 - 除非它是零，然后添加较大的深度。
	  * LANG
	  * @param root
	  * @return
	  */
	 public int minDepth(TreeNode root) {
	       
	        int dep=0;
	        if (root!=null) {
				dep++;
				int left=minDepth(root.left);
				
				int right=minDepth(root.right);
				return 1 + (Math.min(left, right) > 0 ? Math.min(left, right) : Math.max(left,right));
			}
	        return 0;
	    }
	 
	 /**
	  * 333 Largest BST Subtree
	  * 采用bottom-up的方法，简历新的class, 用来存储

		当前节点为root的subtree是否是BST
		若是，最小val 和最大val.
		size是当前subtree的大小.
		然后从下到上更新，若是中间过程中size 比 res大，就更新res.
		
		Time Complexity: O(n). 每个点不会访问超过两遍. Space: O(logn). Recursion stack space.
	  */
	 
	 public int largestBSTSubtree(TreeNode root) {
	        int [] res = {0};
	        helper(root, res);
	        return res[0];
	    }
	    
	    private Node helper(TreeNode root, int [] res){
	        Node cur = new Node();
	        if(root == null){
	            cur.isBST = true;
	            return cur;
	        }
	        Node left = helper(root.left, res);
	        Node right = helper(root.right, res);
	        if(left.isBST && root.val > left.max && right.isBST && root.val < right.min){
	            cur.isBST = true;
	            cur.min = Math.min(root.val, left.min);
	            cur.max = Math.max(root.val, right.max);
	            cur.size = left.size + right.size + 1;
	            if(cur.size > res[0]){
	                res[0] = cur.size;
	            }
	        }
	        return cur;
	    }
	    
	    /**
	     * 597. Subtree with Maximum Average
	     * 用分治+遍历(和全局变量每次打擂台)来做。思路就是分治法每次的的确确返回了当前的值供上一层用，但每次计算的过程中顺便打个擂台，
	     * 如果本次赢了就存到全局变量里。最后只要返回全局变量即可。

			1.注意避免13/4 > 3 是正确的却被乌龙掉的情况。可以1.average算的时候要用double，或者
				2.把两个int的除法转化为乘法！！！a/b < c/d转为 a*d < b*c，但要分母是正的的情况，
				本题average的分母正好是count，没问题。

			2.需要返回多个数据的时候(比如本题sum和count)，helper可以array或者list来存这些数据返回。
			或者你可以自定义一个写好constructorResultType的类，这个更好，甚至能解决要返回不同类型数据的情况。

			3.记得Double的最负值是Double.NEGATIVE_INFINITY，不是MIN_VALUE，MIN_VALUE是正数的最接近0的数。
			还有NEGATIVE中间有个A别拼错了
	     * LANG
	     * @param root
	     * @return
	     */
	    private TreeNode subtreeNode;
	    private Result subtreeResult;
	    public TreeNode findSubtree2(TreeNode root) {
	    	getSubtreeResult(root);
	    	return subtreeNode;
	    }
	    public Result getSubtreeResult(TreeNode root){
	    	if (root==null) {
				return new Result(0, 0);
			}
	    	Result left=getSubtreeResult(root.left);
	    	Result right=getSubtreeResult(root.right);
	    	Result result=new Result(
	    			left.sum+right.sum+root.val, 
	    			left.size+right.size+1
	    	);
	    	//subtreeResult.sum/subtreeResult.size<result.sum/result.size  当前结果大于全局则更新
	    	if (subtreeNode == null || result.sum * subtreeResult.size > result.size * subtreeResult.sum) {
	            subtreeResult = result;
	            subtreeNode = root;
	        }
	    	return result;
	    }
	    
	    /**
	     * amazon 节点是多个，不仅仅是两个子节点
	     * 类似于Subtree with Maximum Average
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
	     * lintcode 595. Binary Tree Longest Consecutive Sequence 连续序列
	     * Given a binary tree, find the length of the longest consecutive sequence path.

			The path refers to any sequence of nodes from some starting node to any node in the tree along 
			the parent-child connections. The longest consecutive path need to be from parent to child 
			(cannot be the reverse).
	     */
	    private int longest = 0;
	    public int longestConsecutive(TreeNode root) {
	        helper(root);
	        return longest;
	    }
	    private int helper(TreeNode root) {
	        if (root == null) {
	            return 0;
	        }
	        int left = helper(root.left);
	        int right = helper(root.right);
	        int subtreeMax = 1;
	        if (root.left != null && root.val + 1 == root.left.val) {
	            subtreeMax = Math.max(left + 1, subtreeMax);
	        }
	        if (root.right != null && root.val + 1 == root.right.val) {
	            subtreeMax = Math.max(right + 1, subtreeMax);
	        }
	        longest = Math.max(subtreeMax, longest);
	        return subtreeMax;
	    }

	    /**
	     * 475. Binary Tree Maximum Path Sum II
	     * 给一棵二叉树，找出从根节点出发的路径中，和最大的一条。 
			这条路径可以在任何二叉树中的节点结束，但是必须包含至少一个点（也就是根了）
	     * LANG
	     * @param args
	     */
	    public int maxPathSum2(TreeNode root) {
	        if (root == null) {
	            return 0;
	        }
	        int left = maxPathSum2(root.left);
	        int right = maxPathSum2(root.right);
	        return Math.max(0, Math.max(left, right)) + root.val;
	    }

	    /**
	     * 376. Binary Tree Path Sum
	     * Given a binary tree, 
	     * find all paths that sum of the nodes in the path equals to a given number target
	     * LANG
	     * @param root
	     * @param target
	     * @return
	     */
	    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
	        List<List<Integer>> result = new ArrayList<List<Integer>>();
	        if (root == null) {
	            return result;
	        }
	        List<Integer> list = new ArrayList<>();
	        list.add(root.val);
	        helper(root, target, root.val, list, result);
	        return result;
	    }
	    private void helper(TreeNode root,
	                        int target,
	                        int sum,
	                        List<Integer> list,
	                        List<List<Integer>> result) {
	        if (root.left == null && root.right == null) {
	            if (sum == target) {
	                result.add(new ArrayList<Integer>(list));
	            }
	        }
	        if (root.left != null) {
	            list.add(root.left.val);
	            helper(root.left, target, sum + root.left.val, list, result);
	            list.remove(list.size() - 1);
	        }
	        if (root.right != null) {
	            list.add(root.right.val);
	            helper(root.right, target, sum + root.right.val, list, result);
	            list.remove(list.size() - 1);
	        }
	    }

	    /**
	     * 480. Binary Tree Paths
			Given a binary tree, return all root-to-leaf paths.
	     * LANG
	     * @param root
	     * @return
	     */
	    public List<String> binaryTreePaths(TreeNode root) {
	        List<String> result = new ArrayList<>();
	        List<Integer> list = new ArrayList<>();
	        if (root == null) {
	            return result;
	        }
	        list.add(root.val);
	        helper(root, list, result);
	        return result;
	    }
	    private void helper(TreeNode root,
	                        List<Integer> list,
	                        List<String> result) {
	        if (root.left == null && root.right == null) {
	            result.add(toStr(new ArrayList<Integer>(list)));
	        }
	        if (root.left != null) {
	            list.add(root.left.val);
	            helper(root.left, list, result);
	            list.remove(list.size() - 1);
	        }
	        if (root.right != null) {
	            list.add(root.right.val);
	            helper(root.right, list, result);
	            list.remove(list.size() - 1);
	        }
	    }
	    private String toStr(List<Integer> list) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(list.get(0));
	        for (int i = 1; i < list.size(); i++) {
	            sb.append("->");
	            sb.append(list.get(i));
	        }
	        return sb.toString();
	    }

	    /**
	     * lintcode 88. leetcode 236.  Lowest Common Ancestor of a Binary Tree
	     * LANG
	     * @param root
	     * @param A
	     * @param B
	     * @return
	     */
	    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
	        if (root == null || root == A || root == B) {
	            return root;
	        }
	        TreeNode left = lowestCommonAncestor(root.left, A, B);
	        TreeNode right = lowestCommonAncestor(root.right, A, B);
	        if (left != null && right != null) {
	            return root;
	        }
	        if (left != null) {
	            return left;
	        }
	        if (right != null) {
	            return right;
	        }
	        return null;
	    }
	    
		/**
		 * leetcode 235. Lowest Common Ancestor of a Binary Search Tree
		 */
	    
	    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
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
	     * 474. Lowest Common Ancestor II
	     * 给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。 
			两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。 
			每个节点除了左右儿子指针以外，还包含一个父亲指针parent，指向自己的父亲。 
	     * LANG
	     * @param root
	     * @param A
	     * @param B
	     * @return
	     */
		public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
			ArrayList<ParentTreeNode> pathA = getPath(A);
			ArrayList<ParentTreeNode> pathB = getPath(B);
			int indexA = pathA.size() - 1;
			int indexB = pathB.size() - 1;
			ParentTreeNode ancestor = null;
			while (indexA >= 0 && indexB >= 0) {
				if (pathA.get(indexA) != pathB.get(indexB)) {
					break;
				}
				ancestor = pathA.get(indexA);
				indexA--;
				indexB--;
			}
			return ancestor;
		}
	
		private ArrayList<ParentTreeNode> getPath(ParentTreeNode node) {
			ArrayList<ParentTreeNode> list = new ArrayList<>();
			while (node != null) {
				list.add(node);
				node = node.parent;
			}
			return list;
		}
		
		
		/**
		 * 578. Lowest Common Ancestor III
		 * 给一棵二叉树和二叉树中的两个节点，找到这两个节点的最近公共祖先LCA。 
			两个节点的最近公共祖先，是指两个节点的所有父亲节点中（包括这两个节点），离这两个节点最近的公共的节点。 
			返回 null 如果两个节点在这棵树上不存在最近公共祖先的话。 
			注意事项 
			这两个节点未必都在这棵树上出现
		 * LANG
		 * @param root
		 * @param A
		 * @param B
		 * @return
		 */
		    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
		        ResultType result = helper(root, A, B);
		        if (result.existA && result.existB) {
		            return result.node;
		        } else {
		            return null;
		        }
		    }
		    private ResultType helper(TreeNode root, TreeNode A, TreeNode B) {
		        if (root == null) {
		            return new ResultType(null, false, false);
		        }
		        ResultType left = helper(root.left, A, B);
		        ResultType right = helper(root.right, A, B);
		        boolean exist_A = left.existA || right.existA || root == A;
		        boolean exist_B = left.existB || right.existB || root == B;
		        if (root == A || root == B) {
		            return new ResultType(root, exist_A, exist_B);
		        }
		        if (left.node != null && right.node != null) {
		            return new ResultType(root, exist_A, exist_B);
		        }
		        if (left.node != null) {
		            return new ResultType(left.node, exist_A, exist_B);
		        }
		        if (right.node != null) {
		            return new ResultType(right.node, exist_A, exist_B);
		        }
		        return new ResultType(null, exist_A, exist_B);
		    }
		
	
	    public static void main(String[] args) {
			CategoryNode node1=new CategoryNode(20);
			CategoryNode node2=new CategoryNode(12);
			CategoryNode node3=new CategoryNode(18);
			CategoryNode node4=new CategoryNode(11);
			CategoryNode node5=new CategoryNode(2);
			CategoryNode node6=new CategoryNode(3);
			CategoryNode node7=new CategoryNode(15);
			CategoryNode node8=new CategoryNode(8);
			
			node1.subCategoryNode.add(node2);
			node1.subCategoryNode.add(node3);
			
			node2.subCategoryNode.add(node4);
			node2.subCategoryNode.add(node5);
			node2.subCategoryNode.add(node6);
			
			node3.subCategoryNode.add(node7);
			node3.subCategoryNode.add(node8);
			
			System.out.println(getMostPopularNode(node1).value);;
		}
	}


