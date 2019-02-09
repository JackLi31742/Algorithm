package list;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode(int x) {
		val = x;
	}

	
	
	public ListNode(int val, ListNode next) {
		super();
		this.val = val;
		this.next = next;
	}



	public ListNode() {
		super();
		// TODO Auto-generated constructor stub
	}



	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}
	
}
