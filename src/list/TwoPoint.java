package list;

import java.util.HashSet;
import java.util.Set;

/**
 * 涉及到节点的next节点的操作 时，一定要注意null指针的问题
 * @author LANG
 *
 */
public class TwoPoint {

	/**
	 * 141. Linked List Cycle
	 * 
	 * 将所有的节点保存在set中，如果有重复的，说明有环
	 * LANG
	 * @param head
	 * @return
	 */
	public boolean hasCycle(ListNode head) {
        if (head==null||head.next==null) {
			return false;
		}
        
        Set<ListNode> set=new HashSet<>();
        while(head!=null){
        	if (set.contains(head)) {
				return true;
			}
        	set.add(head);
        	head=head.next;
        }
        return false;
    }
	
	/**
	 * 上题的双指针，用快慢指针，快指针走2步，慢指针走1步，如果快慢指针相遇，那么一定有环
	 * LANG
	 * @param head
	 * @return
	 */
	public boolean hasCycle2(ListNode head) {
		if (head==null||head.next==null) {
			return false;
		}
		ListNode fast=head;
		ListNode slow=head;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			slow=slow.next;
			if (fast==slow) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 142. Linked List Cycle II
	 * 
	 * 返回环开始的节点
	 * LANG
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		if (head==null||head.next==null) {
			return null;
		}
        
        Set<ListNode> set=new HashSet<>();
        while(head!=null){
        	//因为是顺序判断，所以找到第一个重复的点，就是环的起点
        	if (set.contains(head)) {
				return head;
			}
        	set.add(head);
        	head=head.next;
        }
        return null;
    }
	
	/**
	 * 上题的双指针
	 * 如果链表中存在环，那么fp和sp一定会相遇，当两个指针相遇的时候，我们设相遇点为c，
	 * 此时fp和sp都指向了c，接下来令fp继续指向c结点，sp指向链表头结点head，
	 * 此时最大的不同是fp的步数变成为每次走一步，令fp和sp同时走，每次一步，那么它们再次相遇的时候即为环的入口结点
	 * https://blog.csdn.net/willduan1/article/details/50938210
	 * 
	 * fast=a+x+n1r
		slow=a+x+n2r
		fast=2slow
		
		a+x+n1r=2(a+x+n2r)
		
		n1r=a+x+2n2r
		
		a+r-cd=n1r-2n2r
		
		
		a-cd=(n1-2n2-1)r
		也就是a-cd是环长的整数倍
		
		将点重新设置后
		
		fast=cd+n3r
		slow=a+n4r
		
		fast-slow=cd-a+(n3-n4)r=(2n2+1-n1+n3-n4)r 也是环长的整数倍
		
		可知两者一定相遇在环的入口
	 * LANG
	 * @param head
	 * @return
	 */
	public ListNode detectCycle2(ListNode head) {
		if (head==null||head.next==null) {
			return null;
		}
		ListNode fast=head;
		ListNode slow=head;
		ListNode entry=null;
		while(fast!=null&&fast.next!=null){
			fast=fast.next.next;
			slow=slow.next;
			//必须要指针开始走了之后再判断，因为初始的时候肯定是相同的
			if (fast==slow) {
				entry=fast;
				slow=head;
				break;
			}
		}
		
		while(entry!=null&&slow!=null){
			//必须先判断，有可能已经是同一个节点了
			if (entry==slow) {
				return entry;
			}
			entry=entry.next;
			slow=slow.next;
		}
		
		return null;
	}
	
	/**
	 * 19. Remove Nth Node From End of List
	 * remove the n-th node from the end of list and return its head.
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head==null) {
			return null;
		}
		
        int length=0;
        ListNode p=head;
        while(p!=null) {
        	length++;
        	p=p.next;
        }
        if (length<n) {
			return head;
		}
        if (length==n) {
        	return head.next;
		}
        int dif=length-n;
        ListNode pre=head;
        int count=1;
        //找到要删除节点的前一个节点
        while(count<dif) {
        	pre=pre.next;
        	count++;
        }
        //判断要删除的是否是尾节点
        if (pre.next!=null) {
			
        	pre.next=pre.next.next;
        	//下一个节点已经找不到了，无需再置为null
//        pre.next=null;
		}else {
			pre.next=null;
		}
        return head;
    }
	/**
	 * 上题的双指针，时间是一样的
	 * HP
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd2(ListNode head, int n) {
		if (head==null) {
			return head;
		}
		ListNode first = head;
		ListNode second = head;
		while(n>0&&first!=null) {
			first=first.next;
			n--;
		}
		//n大于链表的长度
		if (n>0) {
			return head;
		}
		//n等于链表的长度，也就是要删除头结点
		if (first==null) {
			return head.next;
		}
		while(first.next!=null) {
			first=first.next;
			second=second.next;
		}
		if (second.next!=null) {
			second.next=second.next.next;
		}else {
			second.next=null;
		}
		return head;
	}
	
	/**
	 * 上题的双指针，加入哨兵，不用处理头结点，first永远不为null
	 *
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd3(ListNode head, int n) {
		if (head==null) {
			return head;
		}
		//哨兵
		ListNode sentry=new ListNode(0);
		sentry.next=head;
		ListNode first = sentry;
		ListNode second = sentry;
		while(n>0&&first!=null) {
			first=first.next;
			n--;
		}
		//n大于链表的长度
		if (n>0) {
			return head;
		}
		//哨兵是减少了判断操作，其余的不变
		while(first.next!=null) {
			first=first.next;
			second=second.next;
		}
		if (second.next!=null) {
			second.next=second.next.next;
		}else {
			second.next=null;
		}
		return sentry.next;
	}
	
	/**
	 * 876. Middle of the Linked List
	 * 如果节点个数是双数，返回第二个节点
	 */
	public ListNode middleNode(ListNode head) {
		if(head==null||head.next==null){
			return head;
		}
        ListNode fast=head;
        ListNode slow =head;
        while(fast!=null&&fast.next!=null&&slow!=null){
        	fast=fast.next.next;
        	slow=slow.next;
        }
        return slow;
    }
	public static void main(String[] args) {
		ListNode l1=new ListNode(1);
		ListNode l2=new ListNode(2);
		ListNode l3=new ListNode(3);
		ListNode l4=new ListNode(4);
		ListNode l5=new ListNode(5);
		ListNode l6=new ListNode(6);
		l1.next=l2;
		l2.next=l3;
		l3.next=l4;
		l4.next=l5;
		l5.next=l6;
		TwoPoint twoPoint=new TwoPoint();
//		System.out.println(twoPoint.detectCycle2(l1));
		System.out.println(twoPoint.middleNode(l1));
	}

}
