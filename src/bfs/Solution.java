package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Solution {

	/*
	Description
	Given an 01 matrix gird of size n*m, 1 is a wall, 0 is a road, now you can turn a 1 in the grid into 0,
	Is there a way to go from the upper left corner to the lower right corner?
	If there is a way to go, how many steps to take at least?
	1≤n≤10^​3
	1≤m≤10^​3
	​​
	Example
	Given a = [[0,1,0,0,0],[0,0,0,1,0],[1,1,0,1,0],[1,1,1,1,0]]，return 7.
	Explanation:
	Change `1` at (0,1) to `0`, the shortest path is as follows:
	(0,0)->(0,1)->(0,2)->(0,3)->(0,4)->(1,4)->(2,4)->(3,4) There are many other options of length `7`, not listed here.
	Given a = [[0,1,1],[1,1,0],[1,1,0]], return -1.
	Explanation:
	Regardless of which `1` is changed to `0`, there is no viable path.
	 */

	/**
	 * Approach: BFS
	 * 求最短路径，因此可以想到使用 BFS 来解决这道问题。
	 * 我们需要求：
	 *  从 左上角 到 右下角 不经过障碍点的最短距离
	 *  从 右下角 到 左上角 不经过障碍点的最短距离
	 *  修改每个障碍点之后，到左上角和右上角的距离之和。
	 * 然后在这些值中取最小值即可。
	 * 
	 * Note:
	 *  本题的难点就是在于图的布局是可变的，但是我们不能对每个可变的点都进行一次 BFS.
	 *  因为这样时间复杂度肯定会超时的，所以我们可以利用一个 matrix 来存储计算好的结果。
	 *  也就是 空间换时间 的做法。
	 *
	 * 时间复杂度：O(MN)
	 * 空间复杂度：O(MN)
	 */
	public static final int[][] DIRS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    /**
     * @param grid: The gird
     * @return: Return the steps you need at least
     */
    public int getBestRoad(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        // 各个障碍点到左上角顶点的距离(包括右下角顶点)
        int[][] disToUL = new int[rows][cols];
        // 各个障碍点到右下角顶点的距离(包括左上角顶点)
        int[][] disToLR = new int[rows][cols];
        bfs(disToUL, grid, new int[]{0, 0}, new int[]{rows - 1, cols - 1});
        bfs(disToLR, grid, new int[]{rows - 1, cols - 1}, new int[]{0, 0});

        int minDistance = Integer.MAX_VALUE;
        if (disToUL[rows - 1][cols - 1] != 0) {
            minDistance = Math.min(minDistance, disToUL[rows - 1][cols - 1]);
        }
        if (disToLR[0][0] != 0) {
            minDistance = Math.min(minDistance, disToLR[0][0]);
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && disToUL[i][j] != 0 && disToLR[i][j] != 0) {
                    minDistance = Math.min(minDistance, disToUL[i][j] + disToLR[i][j]);
                }
            }
        }

        return minDistance == Integer.MAX_VALUE ? -1 : minDistance;
    }

    private void bfs(int[][] distance, int[][] grid, int[] start, int[] end) {
        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        queue.offer(new int[]{start[0], start[1]});
        visited[start[0]][start[1]] = true;

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int row = curr[0], col = curr[1];
                // 如果当前顶点为 1，则可以进行一次修改使得当前顶点是可达的。然后 continue
                // 如果是结束的目标位置，同样需要更新步数（距离）值
                if (grid[row][col] == 1 || (row == end[0] && col == end[1])) {
                    distance[row][col] = step;
                    continue;
                }

                for (int[] dir : DIRS) {
                    int nextRow = row + dir[0];
                    int nextCol = col + dir[1];
                    if (nextRow < 0 || nextRow >= rows || nextCol < 0 || nextCol >= cols
                            || visited[nextRow][nextCol]) {
                        continue;
                    }
                    queue.offer(new int[]{nextRow, nextCol});
                    visited[nextRow][nextCol] = true;
                }
            }
            step++;
        }
    }
    
    /**
     * Shortest path in a Binary Maze
     * 迷宫的最短路径（BFS，起点到终点）
     * Given a MxN matrix where each element can either be 0 or 1.
     *  We need to find the shortest path between a given source 
     *  cell to a destination cell. The path can only be created out of a cell if its value is 1.

		Expected time complexity is O(MN).
		
		1、We start from the source cell and calls BFS procedure.
		2、We maintain a queue to store the coordinates of the matrix 
			and initialize it with the source cell.
		3、We also maintain a Boolean array visited of same size as our 
			input matrix and initialize all its elements to false.
			（1）We LOOP till queue is not empty
			（2）Dequeue front cell from the queue
			（3）Return if the destination coordinates have reached.
			（4）For each of its four adjacent cells, if the value is 1 and they are not visited yet, 
			we enqueue it in the queue and also mark them as visited.
     */
    
    int row=0;int col=0;
    public int getDistance(int [][]maze,Point src,Point des){
    	if (maze[src.getX()][src.getY()]!=1||maze[des.getX()][des.getY()]!=1) {
			return -1;
		}
    	row=maze.length;
    	col=maze[0].length;
    	boolean [][]visited=new boolean[row][col];
    	// Mark the source cell as visited 
    	visited[0][0]=true;
    	return getDistance(maze,visited,src,des);
    }
    public int getDistance(int [][]maze,boolean [][]visited,Point src,Point des){
    	Queue<QueueNode> queue=new LinkedList<>();
    	// Distance of source cell is 0 
    	QueueNode srcNode=new QueueNode(src,0);
    	queue.add(srcNode);
    	// These arrays are used to get row and column 
    	// numbers of 4 neighbours of a given cell 
    	int rowNum[] = {-1, 0, 0, 1}; 
    	int colNum[] = {0, -1, 1, 0};
    	while(!queue.isEmpty()){
    		QueueNode queueNode=queue.peek();
    		Point point=queueNode.getPoint();
    		// If we have reached the destination cell, 
            // we are done 
    		if (point.getX()==des.getX()&&point.getY()==des.getY()) {
				return queueNode.getDis();
			}
    		 // Otherwise dequeue the front cell in the queue 
            // and enqueue its adjacent cells 
    		queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int x = point.getX() + rowNum[i]; 
                int y = point.getY() + colNum[i]; 
                // if adjacent cell is valid, has path and 
                // not visited yet, enqueue it. 
    			if (checkMaze(x, y)&&maze[x][y]==1&&!visited[x][y]) {
    				// mark cell as visited and enqueue it 
    				visited[x][y]=true;
    				QueueNode next=new QueueNode(new Point(x,y),queueNode.getDis()+1);
    				queue.add(next);
    			}
			}
    	}
    	// Return -1 if destination cannot be reached 
    	return -1;
    }
    
    public boolean checkMaze(int x,int y){
    	if (x>=0&&x<row&&y>=0&&y<col) {
			return true;
		}
    	return false;
    }
    /**
     * 给个array,其中只有一格是9，其他格子是0或1，0表示此路不通，1表示可以走，判断从（0,0) 点开始上下左右移动能否找到这个是9的格子
     * 老鼠在（0，0）开始，然后要测（0，0）就是终点（==9）的情况， 多么坑的一个corner case。
     */
    private final static int[] dx = {-1, 0, 0, 1};
    private final static int[] dy = {0, 1, -1, 0};
    public int getMaze(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return 0;
        if (matrix[0][0] == 9)
            return 1;
        int m = matrix.length, n = matrix[0].length;
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{0, 0});
        matrix[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int i = 0; i < 4; i++) {
                int[] next = {cur[0] + dx[i], cur[1] + dy[i]};
                if (next[0] >= 0 && next[0] < m && next[1] >= 0 && next[1] < n) {
                    if (matrix[next[0]][next[1]] == 9)
                            return 1;
                    else if (matrix[next[0]][next[1]] == 0) {
                        queue.offer(next);
                        matrix[next[0]][next[1]] = 1;
                    }
                }
            }
        }
        return 0;
    }

    /**
     * amazon 1能走 0 不能走，9是障碍物 从左上角到9的最小距离 否则返回-1
     * int[] store three element:x y distance 
     * time complexity is O(MN).M is length of row,N is length of column
		
		1、 start from the source  and call BFS .
		2、keep a queue to store the element of the matrix 
			and initialize it with the source .
		3、keep a Boolean array visited of same size as input matrix .
			（1）We LOOP till queue is empty
			（2）Dequeue front element from the queue
			（3）Return if the destination coordinates have reached.
			（4）For each of its four adjacent cells, if the value is 1 or 9 and they are not visited yet, 
			we enqueue it in the queue and also mark them as visited.
     * LANG
     * @param lot
     * @return
     */
    int row2;int col2;
    public int removeObstacle(int [][]lot){
    	if (lot==null||lot.length==0||lot[0].length==0||lot[0][0]==0) {
    		return -1;
    	}
    	if (lot[0][0]==9) {
    		return 1;
    	}
    	row2=lot.length; col2=lot[0].length;
    	boolean [][]visited=new boolean[row2][col2];
    	// Mark the source cell as visited 
    	visited[0][0]=true;
    	return getDistance(lot,visited);
    }
    
    public int getDistance(int [][]lot,boolean [][]visited){
    	Queue<int[]> queue=new LinkedList<>();
    	queue.add(new int[]{0,0,0});
    	int rowNum[] = {-1, 0, 0, 1}; 
    	int colNum[] = {0, -1, 1, 0};
    	while(!queue.isEmpty()){
    		int[]cur=queue.peek();
    		// If we have reached the destination cell, 
            // we are done 
    		if (lot[cur[0]][cur[1]]==9) {
				return cur[2];
			}
    		 // Otherwise dequeue the front cell in the queue 
            // and enqueue its adjacent cells 
    		queue.poll();
    		for (int i = 0; i < 4; i++) {
    			int x = cur[0] + rowNum[i]; 
                int y = cur[1] + colNum[i]; 
                // if adjacent cell is valid, has path and 
                // not visited yet, enqueue it. 
    			if (check(x, y)&&(lot[x][y]==1||lot[x][y]==9)&&!visited[x][y]) {
    				// mark cell as visited and enqueue it 
    				visited[x][y]=true;
    				queue.add(new int[]{x,y,cur[2]+1});
    			}
			}
    	}
    	return -1;
    }
    
    public boolean check(int x,int y){
    	if (x>=0&&x<row2&&y>=0&&y<col2) {
			return true;
		}
    	return false;
    }
    /**
     * 
     * 迷宫,求起点和终点的最短路径以及最短路径个数(百分数)
     * https://blog.csdn.net/caiyuzhu001/article/details/53431287
     */
    
    public void getPathAndNum(){
    	
    }
    /**
     * lintcode 120. Word Ladder 词语阶梯
		leetcode 127. Word Ladder
		
		beginWord = “hit” 
		endWord = “cog” 
		wordList = [“hot”,”dot”,”dog”,”lot”,”log”]
		
		As one shortest transformation is “hit” -> “hot” -> “dot” -> “dog” -> “cog”, 
		return its length 5.


		https://www.cnblogs.com/grandyang/p/4539768.html
		类似于一棵树，先找到第一个匹配的，然后用26个字母去替换，如果在set中，就加到队列里，然后继续找第二个，
		每一层都把所有可能结果都加入，这么一层层找下去，如果找到最后没找到，那么step就是0
		
		time：26*start.length*wordList.size
     * LANG
     * @param start
     * @param end
     * @param wordList
     * @return
     */
    
    
	public int ladderLength(String start, String end, List<String> wordList) {
		Set<String> set = new HashSet<>(wordList);
		Queue<String> q = new LinkedList<>();
		q.offer(start);
		int step = 1;
		while (!q.isEmpty()) {
			int size = q.size();
			System.out.println("size:"+size);
			for (int j = 0; j < size; j++) {
				String cur = q.poll();
				for (int i = 0; i < end.length(); i++) {
					for (char letter = 'a'; letter <= 'z'; letter++) {
						StringBuilder newWord = new StringBuilder(cur);
						newWord.setCharAt(i, letter);
						if (set.contains(newWord.toString())) {
							System.out.println("new:"+newWord);
							if (newWord.toString().equals(end)) {
								//因为不执行step++了，所以在这加1
								return step + 1;
							}
							//防止死循环
							set.remove(newWord.toString());
							q.offer(newWord.toString());
						}
					}
				}

			}
			step++;
		}
		return 0;
	}
	/**
	 * 126. Word Ladder II  暂时还是不对的
	 * 要找到所有的路径，没有则返回空
	 * LANG
	 * @param beginWord
	 * @param endWord
	 * @param wordList
	 * @return
	 */
	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<String>> result = new ArrayList<List<String>>();
        if(beginWord==null||beginWord==""
        		||endWord==null||endWord==""
        		||beginWord.length()!=endWord.length()||!wordList.contains(endWord)) {
            return result;
        }
		Set<String> set = new HashSet<>(wordList);
		Queue<String> q = new LinkedList<>();
		q.offer(beginWord);
		while (!q.isEmpty()) {
			int size = q.size();
			System.out.println("size:"+size);
			List<String> path = new ArrayList<String>();
			for (int j = 0; j < size; j++) {
				String cur = q.poll();
			     path.add(cur);
				for (int i = 0; i < endWord.length(); i++) {
					for (char letter = 'a'; letter <= 'z'; letter++) {
						StringBuilder newWord = new StringBuilder(cur);
						newWord.setCharAt(i, letter);
						if (set.contains(newWord.toString())) {
							System.out.println("new:"+newWord);
//							path.add(newWord.toString());
							result.add(path);
							if (newWord.toString().equals(endWord)) {
								//因为不执行step++了，所以在这加1
								return result;
							}
							//防止死循环
							q.offer(newWord.toString());
						}
					}
				}

			}
			set.remove(q.peek());
		}
		return result;
    }
    public static void main(String[] args) {
		Solution solution=new Solution();
		
		int mat[][] = 
		    { 
		        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
		        { 1, 0, 1, 0, 1, 1, 1, 0, 1, 1 }, 
		        { 1, 1, 1, 0, 1, 1, 0, 1, 0, 1 }, 
		        { 0, 0, 0, 0, 1, 0, 0, 0, 0, 1 }, 
		        { 1, 1, 1, 0, 1, 1, 1, 0, 1, 0 }, 
		        { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 }, 
		        { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, 
		        { 1, 0, 1, 1, 1, 1, 0, 1, 1, 1 }, 
		        { 1, 1, 0, 0, 0, 0, 1, 0, 0, 1 } 
		    }; 
		
		
		int lot[][]={   {1,0,0},
						{1,0,0},
						{1,9,1}};
		int lot1[][]={
				{1,1,1,1},
				{0,1,1,1},
				{0,1,0,1},
				{1,1,9,1},
				{0,0,1,1}
		};
		/*Point source = new Point(0, 0); 
		Point dest =new Point(1, 2);
		int dis=solution.getDistance(lot, source, dest);
		System.out.println(dis);*/
//		System.out.println(solution.removeObstacle(lot));;
		
		String begin="hit";
		String end="cog";
		String []words={"hot","dot","dog","lot","log","cog"};
		System.out.println(solution.findLadders(begin, end, Arrays.asList(words)));;
	}
}
