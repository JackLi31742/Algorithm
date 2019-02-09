package tree;

public class Node {
	boolean isBST;
	int min;
	int max;
	int size;
	public Node(){
		isBST = false;
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		size = 0;
	}
}
