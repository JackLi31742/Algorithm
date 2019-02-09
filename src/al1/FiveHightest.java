package al1;

import java.util.ArrayList;
import java.util.List;

public class FiveHightest {

	public static void main(String[] args) {
		List<Product> list=new ArrayList<Product>();
		Product p1=new Product("1", 5);
		Product p4=new Product("1", 7);
		Product p5=new Product("1", 86);
		Product p8=new Product("1", 8);
		Product p2=new Product("2", 5);
		Product p6=new Product("2", 18);
		Product p10=new Product("2", 38);
		Product p9=new Product("2", 48);
		Product p3=new Product("3", 5);
		Product p7=new Product("3", 28);
		Product p11=new Product("3", 78);
		Product p12=new Product("3", 88);
		Product p13=new Product("3", 98);
		
		list.add(p13);
		list.add(p1);
		list.add(p2);
		list.add(p3);
		list.add(p4);
		list.add(p5);
		list.add(p6);
		list.add(p7);
		list.add(p8);
		list.add(p9);
		list.add(p10);
		list.add(p11);
		list.add(p12);
		
		for (int i = 0; i < list.size(); i++) {
			list.get(i).getProductRating();
		}
		
	}
}
