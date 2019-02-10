package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class Solution {

	public List<Connections> minumimCostConnections(int num,List<Connections> connections){
		
		List<Connections> result=new ArrayList<>();
		
		Comparator<Connections> comparator=new Comparator<Connections>() {

			public int compare(Connections o1, Connections o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.cost, o2.cost);
			}
		};
		
		Collections.sort(connections,comparator);
		
		Map<String, String> parent=new HashMap<>();
		Set<String> nodes=new HashSet<>();
		for (int i = 0; i < connections.size(); i++) {
			String first=connections.get(i).first;
			String second=connections.get(i).second;
			parent.put(first, first);
			parent.put(second, second);
			nodes.add(first);
			nodes.add(second);
		}
		
		for (int i = 0; i < connections.size(); i++) {
	        if (unionFind(connections.get(i), parent)) {
	        	result.add(connections.get(i));
	        }
	    }
	    if (result.size() != nodes.size() - 1) {
	    	result.clear();
	        return result;
	    }
		Comparator<Connections> resultComparator=new Comparator<Connections>() {

			public int compare(Connections o1, Connections o2) {
				int comFirst=o1.first.compareTo(o2.first);
				if (comFirst<0) {
			        return -1;
			    } else if (comFirst>0) {
			        return 1;
			    } else {
			    	int comSecond=o1.second.compareTo(o2.second);
			        if (comSecond<0) {
			            return -1;
			        } else {
			            return 1;
			        }
			    }
			}
		};
		
		Collections.sort(result, resultComparator);
		return result;
	}
	
	boolean unionFind(Connections con, Map<String, String> parent) {
		String p1 = find(con.first, parent);
		String p2 = find(con.second, parent);
	    if (p1.equals(p2) ) {
	        return false;
	    }
	    parent.put(p1, p2);
	    return true;
	}
	String find(String node, Map<String, String> parent) {
	    if (node .equals(parent.get(node)) ) return node;
	    return find(parent.get(node), parent);
	}
	public static void main(String[] args) {
		Connections connection1=new Connections("A","B",1);
		Connections connection2=new Connections("B","C",4);
		Connections connection3=new Connections("A","C",5);
		
		List<Connections> list=new ArrayList<>();
		list.add(connection1);
		list.add(connection2);
		list.add(connection3);
		
		Solution solution =new Solution();
		
		System.out.println(solution.minumimCostConnections(3, list));;
	}
}
