package list;

public class DetectCycle {

	/**
	 * leetcode 141 
	 */
	public boolean hasCycle(ListNode head) {
		//快慢指针
		ListNode fast=head;
        ListNode slow=head;
        while(slow!=null&&fast!=null){
            slow=slow.next;
            if(fast.next!=null){
                fast=fast.next.next;
            }else {
            	//环一定没有节点为null
                return false;
            }
            if(fast==slow){
                return true;
            }
        }
        return false;
    }
	
	/**
	 * leetcode 142
	 * 上题，两个指针相遇后，将fast指向head，与slow同步开始走，当两者再次相遇，就是环的入口
	 * @param head
	 * @return
	 */
	public ListNode detectCycle(ListNode head) {
		ListNode fast=head;
		ListNode slow=head;
		while(slow!=null&&fast!=null) {
			slow=slow.next;
			if (fast.next!=null) {
				fast=fast.next.next;
			}else {
				return null;
			}
			//必须要指针开始走了之后再判断，因为初始的时候肯定是相同的
			if (fast==slow) {
				fast=head;
				break;
			}
		}
		while(fast!=null&&slow!=null) {
			//必须先判断，有可能已经是同一个节点了，
			//如果后判断，[1,2]，2指向1的这种情况，返回的就是2，而不是1
            if (fast==slow) {
				return fast;
			}
			fast=fast.next;
			slow=slow.next;
			
		}
		return null;
    }
}
