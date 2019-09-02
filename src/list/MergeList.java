package list;

import java.util.Comparator;
import java.util.PriorityQueue;

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
        ListNode newHead=new ListNode(0);
        ListNode p=newHead;
        while(lists.length>0){
        	int pos=0;
        	ListNode temp=lists[pos];
        	while(temp==null&&pos<lists.length-1){
        		pos++;
        		temp=lists[pos];
        	}
        	if (temp==null) {
				break;
			}
        	//i从pos开始，减少比较次数
        	for (int i = pos; i < lists.length; i++) {
        		if (lists[i]!=null) {
					
	        		if (lists[i].val<temp.val) {
	        			temp=lists[i];
	        			pos=i;
	        		}
        		}
        	}
//        	if (p!=null) {
				
        		p.next=temp;
//			}
        	//temp无需走到下一个
//        	if (temp!=null) {
//				
//        		temp=temp.next;
//			}
        		
//        	if (lists[pos]!=null) {
				
        		lists[pos]=lists[pos].next;
//			}
//        	if (p!=null) {
				
				p=p.next;
//			}
        }
        return newHead.next;
    }
	
	/**
	 *    利用小顶堆
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists==null||lists.length==0) {
			return null;
		}
		if (lists.length==1) {
			return lists[0];
		}
		Comparator<ListNode> comparator=new Comparator<ListNode>() {
			public int compare(ListNode node1,ListNode node2) {
				return Integer.compare(node1.val, node2.val);
			}
		};
		PriorityQueue<ListNode> minHeap=new PriorityQueue<>(lists.length,comparator);
		
		
		for (ListNode listNode : lists) {
			if (listNode!=null) {
				
				minHeap.add(listNode);
			}
		}
		
		ListNode newHead=new ListNode(0);
		ListNode p=newHead;
		while(minHeap.size()>0) {
			ListNode node=minHeap.poll();
			p.next=node;
			p=p.next;
			//添加下一个比较的节点
			if(node.next!=null) {
				minHeap.add(node.next);
			}
		}
		return newHead.next;
	}
	
	/**
	 *  利用divide and conquer
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists3(ListNode[] lists) {
		if (lists==null||lists.length==0) {
			return null;
		}
		return mergeKLists3(lists,0,lists.length-1);
	}
	
	public ListNode mergeKLists3(ListNode[] lists,int left,int right) {
		if (left>right) {
			return null;
		}
		if (left==right) {
			return lists[left];
		}
		//移位运算符要加括号
		int mid=left+((right-left)>>1);
		ListNode leftList=mergeKLists3(lists,left,mid);
		ListNode rightList=mergeKLists3(lists,mid+1,right);
		return mergeTwoLists(leftList, rightList);
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
		
		ListNode[] lists={null,l1,null,l4};
//		System.out.println(mergeList.mergeKLists3(lists));;
		
		int mid=2+(3-2)>>1;
		System.out.println(mid);
		
	}
}
