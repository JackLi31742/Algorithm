package amaon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Solution {

	/**
	 * deep copy
	 * 138. Copy List with Random Pointer
	 * A linked list is given such that each node contains an additional 
	 * random pointer which could point to any node in the list or null.

		Return a deep copy of the list.
		
		I just create all nodes and put <old, new> pairs into a map. 
		Then update next and random pointers for each new node.
		
		 takes O(N) space and O(N) time
	 */
	
	public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        
        Map<RandomListNode, RandomListNode> map = new HashMap<RandomListNode, RandomListNode>();

        RandomListNode cur = head;
        while(cur != null) {
            map.put(cur, new RandomListNode(cur.label));
            cur = cur.next;
        }
        
        for (Map.Entry<RandomListNode, RandomListNode> entry : map.entrySet()) {
            final RandomListNode newNode = entry.getValue();
            newNode.next = map.get(entry.getKey().next);
            newNode.random = (entry.getKey().random == null) ? null : map.get(entry.getKey().random);
        }
        
        return map.get(head);
    }
	
	/**
	 * Order Dependency
	 * 207. Course Schedule
	 * 一节课得在另一节课上完后上
	 * LANG
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        // 把int[][]转换成adjacency list
        // 在graph中，index是每一个课程
        // 所连接的arraylist是这个课程所相连的其他所有课程
        for (int i = 0; i < prerequisites.length; i++) {
            if (graph[prerequisites[i][0]] == null) {
                graph[prerequisites[i][0]] = new ArrayList<>();
            }
            graph[prerequisites[i][0]].add(prerequisites[i][1]);
        }
        // mark每个cell中具有3个值，0,1,2
        // 0 代表 没有被访问过
        // 1 代表 正在被访问
        int[] mark = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(mark, graph, i)) {
                return false;
            }
        }

        return true;
    }
    
    private boolean dfs(int[] mark, ArrayList[] graph, int course) {
        if (mark[course] == 1) {
            return false;
        } else if (mark[course] == 2) {
            return true;
        } else {
            mark[course] = 1; // 现在正在访问中
        }

        // 继续访问该课程相连的所有课程
        if (graph[course] != null) {
            for (int i = 0; i < graph[course].size(); i++) {
                if (!dfs(mark, graph, (int)graph[course].get(i))) {
                    return false;
                }
            }
        }
        mark[course] = 2;
        return true;
    }
}
