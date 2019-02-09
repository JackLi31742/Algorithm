package bfs;

public class QueueNode {

	Point point;
	 // cell's distance of from the source 
	int dis;
	public Point getPoint() {
		return point;
	}
	public void setPoint(Point point) {
		this.point = point;
	}
	public int getDis() {
		return dis;
	}
	public void setDis(int dis) {
		this.dis = dis;
	}
	
	public QueueNode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public QueueNode(Point point, int dis) {
		super();
		this.point = point;
		this.dis = dis;
	}
	@Override
	public String toString() {
		return "QueueNode [point=" + point + ", dis=" + dis + "]";
	}
	

}
