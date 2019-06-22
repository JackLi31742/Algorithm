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
	
	public static void main(String[] args) {
		ListNode l1=new ListNode(1);
		ListNode l2=new ListNode(2);
		l1.next=l2;
		l2.next=l1;
		TwoPoint twoPoint=new TwoPoint();
		System.out.println(twoPoint.detectCycle2(l1));
	}

}
