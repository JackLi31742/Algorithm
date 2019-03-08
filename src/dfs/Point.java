package dfs;

//位置点的辅助类
public class Point 
//implements Comparable<Point>
{

	private int x;
	private int y;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

//	@Override
//	public int compareTo(Point p) {
//		// TODO Auto-generated method stub
//		int difx=this.getX()-p.getX();
//		if(difx==0){
//			return this.getY()-p.getY();
//		}
//		return difx;
//		
//	}

	
	
	
}
