package list;

public class MergeList {

	/**
	 * 21. Merge Two Sorted Lists
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
        ListNode newHead=null;
        if (l1.val<=l2.val) {
			newHead=l1;
			//链表的指针要再这走
			l1=l1.next;
		}else {
			newHead=l2;
			l2=l2.next;
		}
        ListNode p=newHead;
        //指针不能再这走
        while(l1!=null&&l2!=null) {
        	if (l1.val<=l2.val) {
				p.next=l1;
				l1=l1.next;
				p=p.next;
			}else {
				p.next=l2;
				l2=l2.next;
				p=p.next;
			}
        }
        if (l1!=null) {
			p.next=l1;
		}
        if (l2!=null) {
			p.next=l2;
		}
        return newHead;
    }
	/**
	 * 
	 * 23. Merge k Sorted Lists
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
        
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
		
		MergeList mergeList=new MergeList();
		System.out.println(mergeList.mergeTwoLists(l1, l4));;
		
	}
}
