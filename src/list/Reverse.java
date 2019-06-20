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
        ListNode cur=head;ListNode pre=null;
        int step=1;
        while(step<m){
        	pre=cur;
        	cur=cur.next;
        	step++;
        }
//        if (pre!=null) {
			
        	ListNode preCur=cur;
        	cur=cur.next;
        	while(step<n){
        		ListNode temp=cur.next;
        		cur.next=preCur;
        		preCur=cur;
        		cur=temp;
        		step++;
        	}
        	pre.next.next=cur;
        	pre.next=preCur;
//		}
//        else {
//			while(step<n){
//				ListNode temp=cur.next;
//				cur.next=pre;
//				pre=cur;
//				cur=temp;
//				step++;
//			}
//			if (cur.next!=null&&pre!=null) {
//				
//				pre.next=cur.next;
//				cur.next=pre;
//				return cur;
//			}else {
//				cur.next=pre;
//				return cur;
//			}
//		}
        
        return head;
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
        
    }

	public static void main(String[] args) {
		ListNode l1=new ListNode(1);
		ListNode l2=new ListNode(4);
		ListNode l3=new ListNode(5);
//		ListNode l4=new ListNode(1);
//		ListNode l5=new ListNode(3);
//		ListNode l6=new ListNode(4);
		l1.next=l2;
		l2.next=l3;
//		l3.next=l4;
//		l4.next=l5;
//		l5.next=l6;
		Reverse reverse=new Reverse();
		System.out.println(reverse.reverseBetween(l1,1,3));;
	}
}
