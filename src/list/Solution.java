package list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Stack;

import org.junit.Test;



public class Solution {

	/**
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
	
	public RandomListNode copyRandomList2(RandomListNode head) {
		RandomListNode iter = head, next;

		  // First round: make copy of each node,
		  // and link them together side-by-side in a single list.
		  while (iter != null) {
		    next = iter.next;

		    RandomListNode copy = new RandomListNode(iter.label);
		    iter.next = copy;
		    copy.next = next;

		    iter = next;
		  }

		  // Second round: assign random pointers for the copy nodes.
		  iter = head;
		  while (iter != null) {
		    if (iter.random != null) {
		      iter.next.random = iter.random.next;
		    }
		    iter = iter.next.next;
		  }

		  // Third round: restore the original list, and extract the copy list.
		  iter = head;
		  RandomListNode pseudoHead = new RandomListNode(0);
		  RandomListNode copy, copyIter = pseudoHead;

		  while (iter != null) {
		    next = iter.next.next;

		    // extract the copy
		    copy = iter.next;
		    copyIter.next = copy;
		    copyIter = copy;

		    // restore the original list
		    iter.next = next;

		    iter = next;
		  }

		  return pseudoHead.next;
    }
	/**
	 * 反转字符串
	 * LANG
	 * @param head
	 * @return
	 */
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

	/**
	 * 141. Linked List Cycle
	 * Given a linked list, determine if it has a cycle in it.

		To represent a cycle in the given linked list, 
		we use an integer pos which represents the position (0-indexed) 
		in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.
	 * 是否有环
	 * LANG
	 * @param head
	 * @return
	 */
	public static boolean isLoopList(ListNode head){
		 
        if (head == null||head.next==null){
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

	/**
	 * 142. Linked List Cycle II
	 * 链表有环，环的起点在哪里
	 * https://blog.csdn.net/willduan1/article/details/50938210
	 * LANG
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        ListNode entry = head;
        
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        
        return null;
    }

	/**
	 * 21. Merge Two Sorted Lists
	 * LANG
	 * @param l1
	 * @param l2
	 * @return
	 */
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1==null) {
			return l2;
		}
        if (l2==null) {
			return l1;
		}
        ListNode result=new ListNode(0);
        ListNode head=result;
        while(l1!=null&&l2!=null){
        	if (l1.val<=l2.val) {
				head.next=l1;
				l1=l1.next;
			}else {
				head.next=l2;
				l2=l2.next;
			}
        	head=head.next;
        }
        if (l1!=null) {
			head.next=l1;
		}
        if (l2!=null) {
			head.next=l2;
		}
        
        return result.next;
    }
	
	/**
	 * 23. Merge k Sorted Lists
	 * Time complexity :O(Nlogk) where k is the number of linked lists.

		The comparison cost will be reduced to O(logk) for every pop and insertion to priority queue. 
		But finding the node with the smallest value just costs O(1) time.
		There are N nodes in the final linked list.
		Space complexity :
		
		O(n) Creating a new linked list costs O(n) space.
		O(k) The code above present applies in-place method which cost O(1) space. 
		And the priority queue (often implemented with heaps) costs O(k) space 
		(it's far less than N in most situations). 
	 * LANG
	 * @param lists
	 * @return
	 */
	public static ListNode mergeKLists(ListNode[] lists) {
        
		//小顶堆
		Comparator<ListNode> comparator=new Comparator<ListNode>() {
			
			public int compare(ListNode p1,ListNode p2){
				return p1.val-p2.val;
			}
		};
		
		PriorityQueue<ListNode> heap=new PriorityQueue<>(comparator);
		
		for (ListNode node : lists) {
			if (node!=null) {
				
				heap.add(node);
			}
		}
		
		ListNode head=new ListNode(0);
		ListNode pre=head;
		while(!heap.isEmpty()){
			ListNode node=heap.poll();
			pre.next=node;
			pre=node;
			if (node.next!=null) {
				heap.add(node.next);
			}
		}
		return head.next;
    }
	
	/**
	 * 148. Sort List
	 * Sort a linked list in O(n log n) time using constant space complexity.
	 * LANG
	 * @param head
	 * @return
	 */
	//merge two sorted list, return result head
    public ListNode merge(ListNode h1, ListNode h2){
        if(h1 == null){
            return h2;
        }
        if(h2 == null){
            return h1;
        }
        
        if(h1.val < h2.val){
            h1.next = merge(h1.next, h2);
            return h1;
        }
        else{
            h2.next = merge(h1, h2.next);
            return h2;
        }
        
    }
    
    public ListNode sortList(ListNode head) {
        //bottom case
        if(head == null){
            return head;
        }
        if(head.next == null){
            return head;
        }
        
        //p1 move 1 step every time, p2 move 2 step every time, pre record node before p1
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode pre = head;
        
        while(p2 != null && p2.next != null){
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        //change pre next to null, make two sub list(head to pre, p1 to p2)
        pre.next = null;
        
        //handle those two sub list
        ListNode h1 = sortList(head);
        ListNode h2 = sortList(p1);
        
        return merge(h1, h2);
        
    }
	/**
	 * 2. Add Two Numbers
	 * Whenever one of the two ListNode is null, replace it with 0.
	 * LANG
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		if (l1==null) {
			return l2;
		}
		if (l2==null) {
			return l1;
		}
        ListNode pre=new ListNode(0);
        ListNode head=pre;
        int temp=0;
        //temp不等于0就是为了处理进位
        while(l1!=null||l2!=null||temp!=0){
        	ListNode cur = new ListNode(0);
        	int sum=((l1==null)?0:l1.val)+((l2==null)?0:l2.val)+temp;
        	cur.val=sum%10;
        	temp=sum/10;
        	pre.next=cur;
        	pre=cur;
        	l1=(l1==null)?l1:l1.next;
        	l2=(l2==null)?l2:l2.next;
        }
        return head.next;
    }
	
	/**
	 * 445. Add Two Numbers II
	 * Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
		Output: 7 -> 8 -> 0 -> 7
		栈，后进先出
	 * LANG
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<ListNode> stack1=new Stack<>();
        Stack<ListNode> stack2=new Stack<>();
        while(l1!=null){
        	stack1.push(l1);
        	l1=l1.next;
        }
        
        while(l2!=null){
        	stack2.push(l2);
        	l2=l2.next;
        }
        
        int sum=0;
        ListNode head=null;
        while(!stack1.isEmpty()||!stack2.isEmpty()||sum!=0){
        	if (!stack1.isEmpty()) {
				sum+=stack1.pop().val;
			}
        	if (!stack2.isEmpty()) {
				sum+=stack2.pop().val;
			}
        	ListNode node=new ListNode(sum%10);
        	node.next=head;
        	head=node;
        	sum=sum/10;
        }
        return head;
    }
	
	 
	/**
	 * 头插法
	 * LANG
	 */
	@Test
	public void headInsert(){
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		
		List<ListNode> list=new ArrayList<>();
		list.add(node1);
		list.add(node2);
		list.add(node3);
		list.add(node4);
		ListNode head=new ListNode();
//		System.out.println(head.val+","+head.next);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).next=head.next;
			head.next=list.get(i);
		}
		
		System.out.println(head.next);
	}
	
	/**
	 * 尾插法
	 * LANG
	 */
	@Test
	public void tailInsert(){
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		
		List<ListNode> list=new ArrayList<>();
		list.add(node1);
		list.add(node2);
		list.add(node3);
		list.add(node4);
		
		ListNode head=new ListNode();
		ListNode tail=head;
		for (int i = 0; i < list.size(); i++) {
			
			tail.next=list.get(i);
			tail=list.get(i);
			
		}
		
		System.out.println(head.next);
	}
	/**
	 * 按照绝对值排序的链表，按照自然排序
	 * LANG
	 */
	@Test
	public void sort(){
		ListNode node1=new ListNode(-14);
		ListNode node2=new ListNode(9);
		ListNode node3=new ListNode(-5);
		ListNode node4=new ListNode(3);
		ListNode node5=new ListNode(-2);
		ListNode node6=new ListNode(1);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		node4.next=node5;
		node5.next=node6;
		
//		System.out.println(node1);
		
		ListNode newHead=new ListNode(); ListNode positiveTail=newHead;
		ListNode negative=new ListNode();
		ListNode old=null;
		if (node1==null||node1.next==null) {
			return ;
		}
		while(node1.next!=null){
			old=node1.next;
			if (node1.val>0) {
				positiveTail.next=node1;
				positiveTail=node1;
			}else {
				node1.next=negative.next;
				negative.next=node1;
			}
				node1=old;
		}
		
		positiveTail.next=negative.next;
		System.out.println(newHead.next);
		
	}
	public static void main(String[] args) {
		ListNode l1=new ListNode(1);
		ListNode l2=new ListNode(4);
		ListNode l3=new ListNode(5);
		ListNode l4=new ListNode(1);
		ListNode l5=new ListNode(3);
		ListNode l6=new ListNode(4);
		ListNode l7=new ListNode(2);
		ListNode l8=new ListNode(6);
		l1.next=l2;
		l2.next=l3;
		l4.next=l5;
		l5.next=l6;
		l7.next=l8;
		ListNode[]lists={l1,l4,l7};
		
		System.out.println(mergeKLists(lists));;
	}
}
