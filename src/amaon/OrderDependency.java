package amaon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

//https://www.jianshu.com/p/deceb6173865
public class OrderDependency {

	/**
	 * 入度代表的是这个节点被指向的次数。在例子那张图中，1节点的入度为2， 0节点的入度为0，以此类推。
		找入度为0的节点，BFS找它的所有邻居，然后把找到的所有邻居在入度记录表里面的入度数减一，如果某个邻居节点的入度数变成了0，
		那么加入Queue里面，成为下一个BFS的起点。
		其实说白了，就是先挑没有prerequisite的课上，上完之后看有没有解锁的课，如果有，那么接着上，
		如果没有，看看有没有其他的课没有prerequisite。
	 *
	 */
	public static class Order {
        String order = "";
        public Order(String str) {
            order = str;
        }
    }

    public static class Dependency {
        Order cur;
        Order pre;
        public Dependency(Order cur, Order pre) {
            this.cur = cur;
            this.pre = pre;
        }
    }

    public static List<Order> find(List<Dependency> list) {
        List<Order> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return result;
        }
//        int totalOrder = 0;
        Map<Order, List<Order>> adjList = new HashMap<>();
        //一个入度记录表：HashMap
        Map<Order, Integer> indegree = new HashMap<>();
        Queue<Order> queue = new LinkedList<>();

        // fill indegree and adjList
        for (Dependency dep : list) {
            Order cur = dep.cur;
            Order pre = dep.pre;
            // add to adjList
            if (adjList.containsKey(pre)) {
                adjList.get(pre).add(cur);
            } else {
                adjList.put(pre, new ArrayList<>());
                adjList.get(pre).add(cur);
            }
            // add to indegree
            if (indegree.containsKey(cur)) {
                indegree.put(cur, indegree.get(cur) + 1);
            } else {
                indegree.put(cur, 1);
            }
            if (!indegree.containsKey(pre)) {
                indegree.put(pre, 0);
            }
        }

        for (Order o : indegree.keySet()) {
            if (indegree.get(o) == 0) {
                queue.offer(o);
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            Order order = queue.poll();
            indegree.remove(order);
            result.add(order);
            if (adjList.containsKey(order)) {
                for (Order cur : adjList.get(order)) {
                    if (indegree.containsKey(cur)) {
                        indegree.put(cur, indegree.get(cur) - 1);
                    }
                    if (indegree.get(cur) == 0) {
                        queue.offer(cur);
                    }
                }
            }
        }

        if (indegree.size() != 0) {
            return null;
        }
        return result;
    }
}
