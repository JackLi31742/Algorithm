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
        //指针不能在循环的条件这走
        while(l1!=null&&l2!=null) {
        	if (l1.val<=l2.val) {
				p.next=l1;
				l1=l1.next;
//				p=p.next;
			}else {
				p.next=l2;
				l2=l2.next;
//				p=p.next;
			}
        	p=p.next;
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
        if (lists==null||lists.length==0) {
			return null;
		}
        if (lists.length==1) {
			return lists[0];
		}
        //哨兵
        ListNode newHead=new ListNode(Integer.MIN_VALUE);
        ListNode p=newHead;
        while(lists.length>0){
        	int pos=0;
        	ListNode temp=lists[pos];
        	while(temp==null){
        		pos++;
        		temp=lists[pos];
        	}
        	for (int i = 0; i < lists.length; i++) {
        		if (lists[i].val<temp.val) {
        			temp=lists[i];
        			pos=i;
        		}
        	}
        	p.next=temp;
        	temp=temp.next;
        	if (lists[pos]!=null) {
				
        		lists[pos]=lists[pos].next;
			}
        	if (p!=null) {
				
				p=p.next;
			}
        }
        return newHead.next;
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
		MergeList mergeList=new MergeList();
//		System.out.println(mergeList.mergeTwoLists(l1, l4));;
		
		ListNode[] lists={l1,l4,l7};
		System.out.println(mergeList.mergeKLists(lists));;
		
	}
}
