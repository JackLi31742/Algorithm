package amaon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;


public class FiveHightest {

	/**
	 * int n, list of Pairs(int id, double time)
 		n是list of Pairs的长度，只对C语言用户有用
		id有重复，比如可能有18个1，17个2，等等
		find the average number of top 5 times for each id. Return with a HashMap(int id, double time)
	 * LANG
	 * @param args
	 */
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
		
		
		int k=3;
		
		Map<String, Double> result=getAverage(list, k);
		
		for (Entry<String, Double> entry : result.entrySet()) {
			System.out.println("key:"+entry.getKey()+",value:"+entry.getValue());
		}
		
	}
	
	public static void add(PriorityQueue<Product> heap,Product product,int k){
		heap.add(product);
		if (heap.size()>k) {
			heap.poll();
		}
	}
	
	public static Map<String, Double> getAverage(List<Product> list,int k){
		Comparator<Product> comparator=new Comparator<Product>() {
			public int compare(Product p1,Product p2){
				
				return p1.getProductRating()-p2.getProductRating();
				
			}
		};
		
		HashMap<String, PriorityQueue<Product>> map=new HashMap<>();
		for (int i = 0; i < list.size(); i++) {
			Product product=list.get(i);
			String productId=product.getProductId();
			if (map.containsKey(productId)) {
				PriorityQueue<Product> heapPro=map.get(productId);
				add(heapPro, product, k);
				
			}else {
				
				PriorityQueue<Product> heap=new PriorityQueue<>(k,comparator);
				add(heap, product, k);
				map.put(productId, heap);
			}
		}
		
		HashMap<String, Double> result=new HashMap<>();
		for(Entry<String, PriorityQueue<Product>> entry:map.entrySet()){
			String key=entry.getKey();
			PriorityQueue<Product> value=entry.getValue();
//			System.out.println("key:"+key+",value:"+value);
			double sum=0.0;
			while(!value.isEmpty()){
				sum+=value.poll().getProductRating();
			}
			result.put(key, Double.valueOf(sum/k));
		}
		
		return result;
	} 
}
