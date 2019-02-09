package al1;

public class Test2 {
	public static ListNode reverseList2(ListNode head) {
		if (head!=null) {
			ListNode pre=null;
			
			ListNode post=head.next;
			while (head!=null) {
				head.next=pre;
				pre=head;
				if (post!=null) {
					head=post;
					
					post=post.next;
				}else {
					break;
				}
				
			}
		}
		return head;
    }
	
	public static ListNode reverseList(ListNode head) {
		ListNode p =null;
		if (head != null) {

			p = reverseList(head.next);
			if (p!=null) {
				
				p.next = head;
			}

		}
		return p;
	}
	
	public static void main(String[] args) {
		ListNode node1=new ListNode(4);
		ListNode node2=new ListNode(5);
		ListNode node3=new ListNode(1);
		ListNode node4=new ListNode(9);
		node1.next=node2;
		node2.next=node3;
		node3.next=node4;
		
//		ListNode node=reverseList(node1);
//		System.out.println(node);
		
		System.out.println(Integer.MIN_VALUE);
		
		char a=1;
		System.out.println(a==1);
		
		System.out.println(Math.pow(2.3, 3));
		
	}
}
