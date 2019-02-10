package amaon;

public class Connections {
	public String first;
	public String second;
	public int cost;
	public Connections(String first, String second, int cost) {
		super();
		this.first = first;
		this.second = second;
		this.cost = cost;
	}
	public Connections() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Connections [first=" + first + ", second=" + second + ", cost=" + cost + "]";
	}
	
}
