package al1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSumClosest {

	public static void main(String[] args) {
		Map<String, Integer> goMap=new HashMap<String, Integer>();
		Map<String, Integer> backMap=new HashMap<String, Integer>();
		
		goMap.put("1", 2000);
		goMap.put("2", 5000);
		backMap.put("1", 5000);
		backMap.put("2", 2000);
		backMap.put("3", 8000);
		
		int max=10000;
		List<Integer[]> list=new ArrayList<Integer[]>();
		for (int i = 1; i <= goMap.size(); i++) {
			for (int j = 1; j <= backMap.size(); j++) {
				if (goMap.get(i+"")+backMap.get(j+"")<=max) {
					Integer[] result=new Integer[2];
					result[0]=i;
					result[1]=j;
					list.add(result);
				}
			}
		}
		for (Integer[] integers : list) {
			
			System.out.println(integers[0]+","+integers[1]);
		}
	}
}
