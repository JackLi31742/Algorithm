package list;

public class Reverse {


	/**
	 * 206. Reverse Linked List
	 * 指向头的左侧，在移动过程中，head最后会指向原始的null，pre指向最后的结点，也就是翻转字符串后的头结点
	 * LANG
	 * @param head
	 * @return
	 */
	public ListNode reverseList(ListNode head) {
		
        ListNode pre=null;
        while(head!=null){
        	ListNode temp=head.next;
        	head.next=pre;
        	pre=head;
        	head=temp;
        }
        return pre;
    }
	
	/**
	 * 92. Reverse Linked List II
	 * 
	 * Reverse a linked list from position m to n. Do it in one-pass.

		Note: 1 ≤ m ≤ n ≤ length of list.	
	 * LANG
	 * @param head
	 * @param m
	 * @param n
	 * @return
	 */
	public ListNode reverseBetween(ListNode head, int m, int n) {
		if (head==null||head.next==null||n==1) {
			return head;
		}
        ListNode cur=head;ListNode pre=null;ListNode preCur=null;ListNode tail=null;
        int step=1;
        
        while(step<m){
        	pre=cur;
        	cur=cur.next;
        	step++;
        }
        	//标记要反转的第一个节点，即反转后的最后一个节点
        	//原来是用pre.next，但如果pre是null，反转后的最后一个节点的下一个节点将会丢失，而且分情况讨论，也很复杂
			tail=cur;
        	preCur=cur;
        	cur=cur.next;
        	while(step<n){
        		ListNode temp=cur.next;
        		cur.next=preCur;
        		preCur=cur;
        		cur=temp;
        		step++;
        	}
        	//原来是pre.next.next=cur
        	tail.next=cur;
        	if (pre!=null) {
				
        		pre.next=preCur;
        		return head;
			}

        
        return preCur;
    }
	
	/**
	 * 24. Swap Nodes in Pairs
	 * Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 不能只修改值，需要换节点
	 * LANG
	 * @param head
	 * @return
	 */
	
	public ListNode swapPairs(ListNode head) {
        if (head==null||head.next==null) {
			return head;
		}
        
        ListNode first=head;ListNode second=first.next;
        ListNode newHead=second;
        while(first!=null&&second!=null){
        	ListNode temp=second.next;
        	second.next=first;
        	//奇数情况
        	//看后续是否只剩一个节点
        	if (temp!=null&&temp.next!=null) {
				first.next=temp.next;
			}else {
				first.next=temp;
			}
        	first=temp;
        	//偶数情况，temp就是null，first也是null，需要判断
        	if (first!=null) {
				
        		second=first.next;
			}
        	
        }
        
        return newHead;
    }

	/**
	 * 25. Reverse Nodes in k-Group
	 * Given this linked list: 1->2->3->4->5

		For k = 2, you should return: 2->1->4->3->5

		For k = 3, you should return: 3->2->1->4->5
		
		虽然是递归，但时间复杂度依然是O(N)，因为递归走的是后边的节点
	 * LANG
	 * @param head
	 * @param k
	 * @return
	 */
	public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null||k==1) {
			return head;
		}
        int count=1;
        ListNode newHead=head;
        while(newHead!=null&&count<k){
        	newHead=newHead.next;
        	count++;
        }
        if (count==k&&newHead!=null) {
			ListNode temp=newHead.next;
			ListNode tail=head;
			ListNode cur=head;
			ListNode pre=null;
			while(cur!=temp){
				ListNode next=cur.next;
				cur.next=pre;
				pre=cur;
				cur=next;
			}
			tail.next=reverseKGroup(temp, k);
			return newHead;
		}
        
        return head;
    }

	
	public static void main(String[] args) {
		ListNode l1=new ListNode(1);
		ListNode l2=new ListNode(2);
		ListNode l3=new ListNode(3);
		ListNode l4=new ListNode(4);
		ListNode l5=new ListNode(5);
//		ListNode l6=new ListNode(4);
		l1.next=l2;
		l2.next=l3;
		l3.next=l4;
		l4.next=l5;
//		l5.next=l6;
		Reverse reverse=new Reverse();
		System.out.println(reverse.reverseKGroup(l1,2));;
	}
}
