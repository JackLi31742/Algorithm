package al;

public class ENode {

	int adjvex; // 邻接顶点序号
	ENode nextadj; // 下一个邻接表结点
	public ENode(int adjvex) {
		super();
		this.adjvex = adjvex;
	}
	@Override
	public String toString() {
		return "ENode [adjvex=" + adjvex + ", nextadj=" + nextadj + "]";
	}
	public ENode() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
