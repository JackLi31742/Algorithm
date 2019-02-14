package amaon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://shmilyaw-hotmail-com.iteye.com/blog/2010747
 * Kruskal’s Minimum Spanning Tree
 * 							increasing
 *  1. Sort all the edges in non-decreasing order of their weight.
	2. Pick the smallest edge. Check if it will form a cycle with the spanning tree formed already. 
		If cycle is not formed, include this edge. Else, discard it.
	3. Repeat step#2 until there are (V-1) edges in the spanning tree.



 * https://www.geeksforgeeks.org/?p=26604/
 * Time Complexity: O(ElogE) or O(ElogV). 
 * Sorting of edges takes O(ELogE) time.
 *  After sorting, we iterate through all edges 
 *  and apply find-union algorithm. 
 *  The find and union operations can take atmost 
 *  O(LogV) time. So overall complexity is O(ELogE + ELogV) time. 
 *  The value of E can be atmost O(V2), so O(LogV) are O(LogE) same. 
 *  Therefore, overall time complexity is O(ElogE) or O(ElogV)
 * @author LANG
 *
 */
public class MST {

	//Time Complexity:O(ElogE)+O(E)+O(E)*O(V)+O(ElogE)=O(E*(logE+V)) E是connections.size() V是num
	public List<Connections> minumimCostConnections(int num, List<Connections> connections) {

		List<Connections> result = new ArrayList<>();
		// 先把空的情形挡掉
		if (connections == null || connections.size() == 0) {
			return result;
		}

		Comparator<Connections> comparator = new Comparator<Connections>() {

			public int compare(Connections o1, Connections o2) {
				// TODO Auto-generated method stub
				return Integer.compare(o1.cost, o2.cost);
			}
		};

		//Time:O(ElogE) E是connections.size()
		Collections.sort(connections, comparator);

		//space:O(V) V是num key是自己，value是根
		Map<String, String> parentMap = new HashMap<>();
		//space:O(V)
		Set<String> nodesSet = new HashSet<>();
		//Time:O(E)
		for (int i = 0; i < connections.size(); i++) {
			String first = connections.get(i).first;
			String second = connections.get(i).second;
			parentMap.put(first, first);
			parentMap.put(second, second);
			nodesSet.add(first);
			nodesSet.add(second);
		}

		/**
		 * 按权值由小到大一次选择边，如果边的两顶点分别属于不同的等价类，则将这条边添加到MST并将这对顶点所属的等价类合并
		 */
		//Time:O(E)*O(V)最差
		for (int i = 0; i < connections.size(); i++) {
			if (unionFind(connections.get(i), parentMap)) {
				result.add(connections.get(i));
			}
		}
		if (result.size() != nodesSet.size() - 1) {
			result.clear();
			return result;
		}

		Comparator<Connections> resultComparator = new Comparator<Connections>() {

			public int compare(Connections o1, Connections o2) {
				int comFirst = o1.first.compareTo(o2.first);
				if (comFirst < 0) {
					return -1;
				} else if (comFirst > 0) {
					return 1;
				} else {
					int comSecond = o1.second.compareTo(o2.second);
					if (comSecond < 0) {
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
	
	//如果两个在不同的等价类中，则合并
	//Time:最差O(V)
	public boolean unionFind(Connections con, Map<String, String> parentMap) {
		String p1 = find(con.first, parentMap);
		String p2 = find(con.second, parentMap);
		if (p1.equals(p2)) {
			return false;
		}
		parentMap.put(p1, p2);
		return true;
	}
	/**
	 * 找根 Time:最差O(V)
	 */
	public String find(String node, Map<String, String> parentMap) {
		if (node.equals(parentMap.get(node))) {
			return node;
		}
		return find(parentMap.get(node), parentMap);
	}

	public static void main(String[] args) {
		Connections connection1 = new Connections("A", "B", 2);
		Connections connection2 = new Connections("B", "C", 2);
		Connections connection3 = new Connections("A", "C", 3);

		List<Connections> list = new ArrayList<>();
		list.add(connection1);
		list.add(connection2);
		list.add(connection3);

		MST solution = new MST();

		System.out.println(solution.minumimCostConnections(3, list));
	}
}
