package dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Solution {

	
	
	/**
	 * leetcode 200. Number of Islands
	 * Given a 2d grid map of '1's (land) and '0's (water), 
	 * count the number of islands. An island is surrounded by water and is 
	 * formed by connecting adjacent lands horizontally or vertically.
	 *  You may assume all four edges of the grid are all surrounded by water.
	 *  
	 *  //深度优先搜索，时间复杂度O(m*n)，空间复杂度O(1)，更改了原始数据
	 * LANG
	 * @param grid
	 * @return
	 */
	
	int row=0; int col=0;
    public int numIslands(char[][] grid) {
        row=grid.length;
        if (row == 0) return 0;
        col=grid[0].length;
        int count=0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(grid[i][j]=='1'){
                	dfs(grid, i, j);
                    count++;
                }
            }
        }
        
        return count;
    }
    
    public void dfs(char[][]grid,int i,int j){
    	if (i < 0 || j < 0 || i >= row || j >= col || grid[i][j] != '1') {
			return ;
		}
    	 	grid[i][j] = '0';
            dfs(grid,i+1,j);
            dfs(grid,i-1,j);
            dfs(grid,i,j-1);
            dfs(grid,i,j+1);
        
    }
    
    /**
     * leetcode 200. Number of Islands
     * 没有改变原数组
     */
    int row3=0; int col3=0;
    public int numIslands2(char[][] grid) {
       if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
    	row3=grid.length;
    	if (row3!=0) {
			col3=grid[0].length;
		}
    	
    	boolean[][]visited=new boolean[row3][col3];
    	
    	int count=0;
    	for (int i = 0; i < row3; i++) {
			for (int j = 0; j < col3; j++) {
				if (grid[i][j]=='1'&&visited[i][j]==false) {
					dfs(grid, i, j,visited);
					count++;
				}
			}
		}
    	return count; 
    }
    
    public void dfs(char[][] grid,int i, int j,boolean[][]visited){
    	if (i<0||i>=row3||j<0||j>=col3||grid[i][j]=='0'||visited[i][j]==true) {
			return ;
		}
    	
    	visited[i][j]=true;
    	dfs(grid, i+1, j,visited);
    	dfs(grid, i, j+1,visited);
    	dfs(grid, i-1, j,visited);
    	dfs(grid, i, j-1,visited);
    }
    
    //广度优先搜索，时间复杂度O(m*n)，空间复杂度复杂度O(m*n)，原始数据保持原样
    
    private int[][] stepArr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private int m, n;
    private int[][] visit;
    public int numIslands(boolean[][] grid) {
        if(null == grid || grid.length == 0 || grid[0].length == 0) return 0;
        m = grid.length;
        n = grid[0].length;
        visit = new int[m][n];
        int count = 0;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] == true && visit[i][j] == 0) {
                    bfs(grid, i, j);
                    count++;
                }
            }
        }
        return count;
    }
    public void bfs(boolean[][] grid, int i, int j) {
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(i * n + j);
        visit[i][j] = 1;
        while(null != queue.peek()) {
            int num = queue.poll();
            i = num / n;
            j = num % n;
            for(int k = 0; k < 4; k++) {
                int x =i + stepArr[k][0];
                int y =j + stepArr[k][1];
                if(x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == true && visit[x][y] == 0) {
                    queue.add(x * n + y);
                    visit[x][y] = 1;
                }
            }
        }
    }
    
    /**
     * lintcode 860. Number of Distinct Islands
     * 
     * 跟第一个1的坐标比较，组成字符串，利用String的特性去重
     */
 
    int row9 = 0, col9 = 0;
    Set<String> set = new HashSet<>();
    String tmp = "";
    boolean[][] vis = null;
    int ix = 0, iy = 0;
    int[] fx = new int[] { 1, -1, 0, 0 };
    int[] fy = new int[] { 0, 0, 1, -1 };

    public int numberofDistinctIslands(int[][] grid) {
    	row9 = grid.length;
    	col9 = grid[0].length;
        vis = new boolean[row9][col9];
        for (int i = 0; i < row9; ++i) {
            for (int j = 0; j < col9; ++j) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    tmp = "";
                    ix = i;
                    iy = j;
                    dfs(i, j, grid);
                    set.add(tmp);
                }
            }
        }
//        System.out.println(set);
        return set.size();
    }

    public void dfs(int x, int y, int grid[][]) {
        vis[x][y] = true;
//        tmp += "x:"+(x - ix) + ",y:" + (y - iy)+";";
        tmp += (x - ix) + "" + (y - iy);
        for (int i = 0; i < 4; ++i) {
            int nx = x + fx[i], ny = y + fy[i];
            if (nx < 0 || nx >= row9 || ny < 0 || ny >= col9 || vis[nx][ny] || grid[nx][ny] == 0){
                continue;
            }
            dfs(nx, ny, grid);
        }
    }
    
    /**
     * 下边的有的test过不去
     */
    int row8=0; int col8=0;
    public int numberofDistinctIslands2(int[][] grid) {
    	if (grid==null||grid.length==0||grid[0].length==0) {
			return 0;
		}
    	row8=grid.length;
    	if (row8!=0) {
			col8=grid[0].length;
		}
    	
    	boolean[][]visited=new boolean[row8][col8];
    	
    	Comparator<Point> pComparator=new Comparator<Point>() {

			@Override
			public int compare(Point p1, Point p2) {
				int difx=p1.getX()-p2.getX();
				if(difx==0){
					return p1.getY()-p2.getY();
				}
				return difx;
			}
		};
    	Comparator<List<Point>> comparator=new Comparator<List<Point>>() {

			public int compare(List<Point> list1, List<Point> list2) {
//				System.out.println("list1:"+list1);
//				System.out.println("list2:"+list2);
				int dif=list1.size()-list2.size();
				if (dif==0) {
//					Set<Point> tempSet=new TreeSet<>(pComparator);
					Set<Point> tempSet=new HashSet<>();
					for (int i = 0; i < list1.size(); i++) {
						Point p1=list1.get(i);
						Point p2=list2.get(i);
						Point p=new Point(p1.getX()-p2.getX(),p1.getY()-p2.getY());
						tempSet.add(p);
					}
					System.out.println("temp:"+tempSet);
					if (tempSet.size()==1) {
						System.out.println("1:");
						return 0;
					}else {
						//问题应该是在这
						System.out.println("不是1");
						return 1;
					}
				}
				System.out.println("---------------------------");
				return dif;
			}
    		
		};
    	Set<List<Point>> set=new TreeSet<>(comparator);
    	for (int i = 0; i < row8; i++) {
			for (int j = 0; j < col8; j++) {
				if (grid[i][j]==1&&visited[i][j]==false) {
					List<Point> list=new ArrayList<>();
					dfs(grid, i, j,visited,list);
					set.add(list);
				}
			}
		}


    	System.out.println(set);
    	return set.size();
    }
    
    public void dfs(int[][] grid,int i, int j,boolean[][]visited,List<Point> list){
    	if (i<0||i>=row8||j<0||j>=col8||grid[i][j]==0||visited[i][j]==true) {
			return ;
		}
//    	list.add(new int[]{i,j});
    	list.add(new Point(i,j));
    	visited[i][j]=true;
    	dfs(grid, i+1, j,visited,list);
    	dfs(grid, i, j+1,visited,list);
    	dfs(grid, i-1, j,visited,list);
    	dfs(grid, i, j-1,visited,list);
    }
    
    /**
     * 804. Number of Distinct Islands II
     * 除了上边的，翻转，旋转也认为是同一个岛屿
     * LANG
     * @param grid
     * @return
     */
    public int numDistinctIslands2(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length;
        Set<String> res = new HashSet<>();
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == 1) {
                    List<Point> island = new ArrayList<>();
                    dfs(grid, i, j, island);
                    res.add(getUnique(island));
                }
            }
        }
        
        return res.size();
    }
    
    private void dfs(int[][]grid, int x, int y, List<Point> island) {
        int m = grid.length, n = grid[0].length;
        
        island.add(new Point(x, y));
        grid[x][y] = 0;
        
        int[] dirs = {-1, 0, 1, 0, -1};
        for (int i = 0; i < 4; ++i) {
            int _x = x + dirs[i], _y = y + dirs[i + 1];
            if (_x >= 0 && _x < m && _y >= 0 && _y < n && grid[_x][_y] == 1) {
                dfs(grid, _x, _y, island);
            }
        }
    }
    
    private String getUnique(List<Point> island) {
        List<String> sameIslands = new ArrayList<>();
        
        int[][] trans={{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        
        for (int i = 0; i < 4; ++i) {
            List<Point> l1 = new ArrayList<>(), l2 = new ArrayList<>();
            
            for (Point point : island) {
                int x = point.x, y = point.y;
                l1.add(new Point(x * trans[i][0], y * trans[i][1]));
                l2.add(new Point(y * trans[i][0], x * trans[i][1]));
            }
            sameIslands.add(getStr(l1));
            sameIslands.add(getStr(l2));
        }
        
        Collections.sort(sameIslands);
        return sameIslands.get(0);
    }
    
    private String getStr(List<Point> island) {
        
        Collections.sort(island, new Comparator<Point>() {
            public int compare(Point a, Point b) {
                if (a.x != b.x) {
                    return a.x - b.x;
                }
                return a.y - b.y;
            }
        });
        
        StringBuilder sb = new StringBuilder();
        int x = island.get(0).x, y = island.get(0).y;
        
        for (Point point : island) {
            sb.append((point.x - x) + " " + (point.y - y) + " ");
        }
        return sb.toString();
    }
    
    
    /**
     * 695. Max Area of Island
     * Given a non-empty 2D array grid of 0's and 1's, 
     * an island is a group of 1's (representing land) 
     * connected 4-directionally (horizontal or vertical.) 
     * You may assume all four edges of the grid are surrounded by water.

		Find the maximum area of an island in the given 2D array. 
		(If there is no island, the maximum area is 0.)
		Time Complexity: O(R*C) grid的rows和columns
		
     * LANG
     * @param grid
     * @return
     */
    int row1=0;int col1=0;
    public int maxAreaOfIsland(int[][] grid) {
        row1=grid.length;
        if (row1==0) {
			return 0;
		}
        col1=grid[0].length;
        int count=0;
        for (int i = 0; i < row1; i++) {
			for (int j = 0; j < col1; j++) {
				if (grid[i][j]==1) {
					count=Math.max(count, dfs1(grid, i, j));
				}
			}
		}
        return count;
    }
    
    public int dfs1(int[][]grid,int i,int j){
    	if (i<0||j<0||i>row1-1||j>col1-1||grid[i][j]!=1) {
			return 0;
		}
    	grid[i][j]=0;
    	return 1+dfs1(grid, i+1, j)+dfs1(grid, i-1, j)+dfs1(grid, i, j+1)+dfs1(grid, i, j-1);
    }
	
    /**
     * Black and White
     * 给定2D阵列形式的黑白图像，计算图像中有多少黑色区域。 区域被定义为连续的黑色像素，
     * 其中如果两个像素水平，垂直或对角地相互接触，则彼此相邻
     * 和200. Number of Islands类似
     * LANG
     * @param args
     */
    public int AreaCount(int row,int col,char[][]area){
    	int count=0;
    	for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (area[i][j]=='B') {
					dfs2(row,col,area,i,j);
					count++;
				}
			}
		}
    	return count;
    	
    }
    
    public void dfs2(int row,int col,char[][]area,int i,int j){
    	if (i<0||i>=row||j<0||j>=col||area[i][j]!='B') {
			return ;
		}
    	area[i][j]='W';
    	dfs2(row, col, area, i+1, j);
    	dfs2(row, col, area, i-1, j);
    	dfs2(row, col, area, i, j+1);
    	dfs2(row, col, area, i, j-1);
    	dfs2(row, col, area, i+1, j+1);
    	dfs2(row, col, area, i+1, j-1);
    	dfs2(row, col, area, i-1, j+1);
    	dfs2(row, col, area, i-1, j-1);
    }
    public static void main(String[] args) {
		int[][]matrix={{1,1,0,1,1}, 
			           {1,0,0,0,0}, 
			           {0,0,0,0,1}, 
			           {1,1,0,1,1} 
	        }; ;
	        
	       /* int[][]matrix={{0,0,1,0,1,1,0,0,1,1,1,0,1,1,1,0,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1}, 
			        		{0,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,1,0,0,0,0,1,0,0,1,1,0,1,1,0,1,0,0,1,0,0,1}, 
			        		{0,0,1,0,0,1,0,1,0,1,0,1,0,1,1,0,1,0,1,0,1,1,0,1,1,1,0,0,0,0,1,0,1,1,0,1,1,0,0,1,1,0},
			        		{0,0,0,1,1,0,1,1,1,0,1,0,1,0,0,1,0,1,1,0,0,1,0,0,1,1,0,1,1,0,0,0,0,0,1,0,0,1,0,1,1,0},
			        		{0,1,1,0,1,1,1,1,1,0,0,1,0,1,0,0,1,1,0,1,1,1,0,0,0,0,1,1,1,0,1,1,1,1,0,1,1,0,1,1,0,1},
			        		{0,1,0,1,1,0,0,1,1,1,0,0,0,0,1,0,0,1,0,1,1,0,1,1,0,1,0,1,1,1,1,0,0,0,1,1,0,0,0,1,1,0},
			        		{0,0,0,0,0,0,0,1,0,1,1,0,1,0,0,1,0,0,1,0,1,1,1,0,0,1,1,1,1,0,0,0,1,1,1,1,0,0,0,1,0,1},
			        		{1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,0,1,0,1,0,1,1,0,0,1,1,0,1},
			        		{0,0,0,0,0,1,1,1,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,1,1,0,1,0,1,0,0,0,0,0,0,0,0,1},
			        		{1,0,1,1,0,0,0,1,0,1,0,1,0,0,0,0,0,1,0,0,1,1,1,1,1,1,1,1,0,0,1,1,0,1,1,0,1,1,0,1,0,0},
			        		{0,0,0,1,0,0,0,1,1,1,0,1,0,0,1,1,0,0,1,0,1,1,0,1,0,0,1,0,1,1,1,1,1,0,1,0,1,0,0,1,0,0},
			        		{0,0,1,1,1,0,0,1,0,1,1,1,1,0,0,1,0,0,0,1,0,0,1,1,1,0,0,1,1,1,0,0,1,0,1,0,1,0,0,0,0,1},
			        		{1,0,0,0,0,0,1,0,0,1,1,0,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,1,1,1,0,1,1,1,0,0,0,0,0,1,0},
			        		{1,1,1,0,0,1,0,1,0,1,1,1,1,0,0,0,1,1,0,0,1,0,0,0,0,1,1,1,1,0,0,1,0,0,1,1,1,0,1,1,0,0},
			        		{0,1,0,0,1,0,0,1,1,1,1,1,1,0,1,1,1,1,0,0,0,1,0,1,0,0,0,0,1,0,1,1,1,0,1,1,1,1,1,0,1,0},
			        		{1,1,1,0,0,0,1,1,1,1,0,1,1,0,1,0,0,0,1,0,1,1,1,1,0,0,0,0,0,1,1,1,0,0,1,0,1,1,1,0,0,0},
			        		{1,1,1,0,0,1,1,0,0,1,0,0,0,1,1,0,1,1,1,0,0,0,0,0,1,1,0,0,1,1,1,1,0,1,1,1,0,1,0,0,1,0},
			        		{0,0,1,0,0,1,1,1,1,1,0,0,0,1,1,0,0,1,0,1,0,0,0,0,1,0,1,1,1,1,1,1,0,1,0,0,1,1,1,0,0,1},
			        		{1,0,0,0,0,1,1,1,0,0,1,1,0,1,0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,0,0,1,1,0,0,0},
			        		{1,1,1,0,1,0,1,1,1,0,0,0,1,0,0,1,0,0,1,1,1,0,0,1,0,0,0,1,0,1,0,0,0,0,1,0,0,0,1,1,0,1},
			        		{0,0,0,1,0,1,1,1,1,0,1,0,1,0,1,0,1,1,0,0,1,1,1,0,1,0,1,1,1,0,0,0,0,0,1,1,0,1,1,1,1,1},
			        		{0,0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,0,1,0,0,1,0,0,1,0,0,1,0,1,0,0,1,0,0,0,1,1,1,0,0,1,1},
			        		{0,1,1,1,1,1,1,1,1,0,0,1,1,0,0,1,1,0,0,0,0,0,1,1,0,0,0,0,1,1,0,0,0,0,0,1,1,0,1,1,0,1},
			        		{0,1,1,1,0,0,1,0,0,1,0,0,1,0,1,1,0,0,0,0,0,0,1,0,0,0,1,1,1,1,0,1,1,1,0,1,0,1,0,1,0,1},
			        		{1,1,0,1,0,1,1,1,1,0,0,0,0,1,0,0,0,1,1,0,1,1,1,1,0,0,1,1,1,1,1,0,0,0,1,0,0,0,1,1,0,0},
			        		{0,1,1,0,0,1,0,1,0,1,1,0,0,1,0,1,1,1,1,1,1,1,0,1,0,0,0,1,0,0,0,1,1,0,1,1,1,1,0,1,0,0},
			        		{1,0,0,0,0,1,0,1,1,1,1,1,0,0,1,1,0,1,0,0,0,1,0,1,0,0,1,0,1,1,1,1,0,0,0,1,0,0,0,1,0,0},
			        		{1,0,1,0,0,1,1,0,1,1,1,0,0,0,0,1,1,0,0,0,1,0,0,1,0,1,0,1,1,0,1,1,1,0,1,0,1,0,1,1,0,1},
			        		{0,1,0,1,0,1,0,1,0,1,0,1,1,1,1,1,1,0,1,0,0,0,1,0,1,0,1,1,0,0,1,0,1,1,0,0,1,0,0,1,0,1},
			        		{1,1,0,0,1,0,1,0,1,1,0,0,0,0,1,0,1,0,1,0,0,0,0,1,1,0,1,1,0,1,1,0,0,1,1,1,1,1,0,0,1,1},
			        		{0,1,0,0,1,1,1,1,1,0,1,0,0,1,0,1,0,1,0,0,1,0,0,1,1,1,1,1,0,1,0,1,1,0,0,0,0,0,0,0,0,0},
			        		{0,1,1,0,1,1,1,1,1,0,1,1,0,1,0,1,1,1,0,1,1,1,0,0,0,1,1,0,0,1,0,1,1,0,0,1,1,0,0,1,1,1},
			        		{1,1,0,0,0,0,0,1,0,0,0,1,0,0,1,0,1,0,1,1,0,1,0,1,0,0,0,1,0,1,1,0,0,0,0,0,0,0,0,1,1,0},
			        		{1,1,0,0,1,1,1,0,1,1,0,1,0,0,0,1,0,0,0,1,0,1,0,0,0,1,1,1,0,1,1,1,0,1,0,0,0,0,1,0,0,0},
			        		{1,0,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1,0,1,0,1,1,1,0,1,1,1,1,0,1,0,0,1,1,0,0,1,1,0,1,0,1},
			        		{1,0,1,1,1,0,0,0,1,0,0,0,1,1,1,0,1,1,1,0,0,1,0,0,0,1,1,0,1,1,0,1,0,1,1,1,0,0,1,1,1,1},
			        		{1,0,0,1,1,0,0,1,0,0,0,0,0,1,1,1,1,0,1,1,0,1,1,1,1,0,1,0,0,0,0,1,1,1,1,0,1,1,1,0,1,1},
			        		{1,1,1,0,1,0,1,1,1,1,0,1,1,1,1,1,1,0,0,0,1,0,1,0,0,0,1,0,0,1,1,1,0,0,0,1,0,0,0,1,1,0},
			        		{1,0,0,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,1,1,1,0,1,0,0,0,0,1,1,0,1,0,1,1,1,0,0,0,0,0,1,0},
			        		{0,1,0,1,1,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,1,1,0,1,0,0,0,1,1,0,0,0,1,1,0,0,0,0,0,1,1,0},
			        		{1,1,0,0,0,0,0,0,1,1,1,1,1,0,1,1,1,1,1,0,0,1,0,1,1,0,1,1,1,1,0,1,0,1,0,1,1,1,0,0,1,1},
			        		{0,1,1,0,1,0,0,0,1,0,0,1,1,1,0,1,1,0,1,0,1,0,0,0,0,0,0,1,0,1,1,0,1,0,0,0,0,1,0,1,1,0},
			        		{0,1,0,0,0,0,0,1,1,1,0,1,0,0,1,0,1,1,0,0,0,1,0,0,0,1,1,1,1,0,0,1,1,0,1,0,0,1,1,0,0,0},
			        		{0,1,1,0,1,1,0,1,0,0,0,1,0,1,0,1,0,0,0,1,1,0,0,1,0,1,1,1,0,1,1,1,1,0,0,1,0,1,0,1,0,0},
			        		{0,1,1,1,0,1,0,0,1,1,0,0,0,0,1,0,1,1,0,0,1,0,0,0,0,0,0,1,1,1,0,0,1,1,1,1,1,0,1,0,0,1}
	        }; ;*/
		
		Solution solution=new Solution();
		/*int [][]result=solution.routMaze(matrix);
		 for (int i = 0; i < solution.row2; i++) 
	        { 
	            for (int j = 0; j < solution.col2; j++) 
	                System.out.print(" " + result[i][j] + 
	                                 " "); 
	            System.out.println(); 
	        } */
		
		System.out.println(solution.numDistinctIslands2(matrix));;
		char[][]whiteAndBlack={{'W','W','B','W','B','B','W','W'},
				{'W','W','B','B','W','W','W','B'},
				{'B','W','W','B','B','B','W','B'},
				{'W','B','W','W','W','W','W','B'},
				{'W','W','W','W','W','W','B','B'}
		};
		
//		System.out.println(solution.AreaCount(5, 8,whiteAndBlack));;
		
		int [][]maze={{8,4,7},{6,5,9}};
		
//		System.out.println(solution.maxMinPath(maze));
	}
	/**
	 * 542. 01 Matrix
	 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

		The distance between two adjacent cells is 1.
	 * LANG
	 * @param matrix
	 * 
	 * @return
	 */
    
	public static int[][] updateMatrix(int[][] matrix) {
        int[][] mark = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
               matrix[i][j] = getDistance(matrix, i, j, mark);
            }
        }
        return matrix;
    }

    public static int getDistance(int[][] matrix, int i, int j, int[][] mark) {
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length || mark[i][j] == 1) {
            return 100000;
        }

        if (i - 1 >= 0 && matrix[i - 1][j] == 0) {
            return 1;
        }
        if (j - 1 >= 0 && matrix[i][j - 1] == 0) {
            return 1;
        }
        if (i + 1 < matrix.length && matrix[i + 1][j] == 0) {
            return 1;
        }
        if (j + 1 < matrix[0].length && matrix[i][j + 1] == 0) {
            return 1;
        }
        mark[i][j] = 1;
        int searchUp = 1 + getDistance(matrix, i - 1, j, mark);
        int searchLeft = 1 + getDistance(matrix, i, j - 1, mark);
        int searchDown = 1 + getDistance(matrix, i + 1, j, mark);
        int searchRight = 1 + getDistance(matrix, i, j + 1, mark);
        mark[i][j] = 0;
        return Math.min(Math.min(searchDown, searchLeft), Math.min(searchRight, searchUp));
    }
    
    /**
     * maze amazon
     * https://www.geeksforgeeks.org/rat-in-a-maze-backtracking-2/
     * 左上角到右下角maze[0][0] ->maze[N-1][N-1]
     * 0能走，1不能走
     * 用辅助二维数组
     * 如果能到达右下角，则返回，返回是其中一个可行的路线
     * 否则的话，从右走，递归找，从下走，递归找
     * 如果都找不到，则返回false，将二维数组标记为1
     */
    
    int row2=0;int col2=0;
    public int[][] routMaze(int maze[][]){
    	row2=maze.length;
    	col2=maze[0].length;
    	int [][]temp=new int[row2][col2];
    	for (int i = 0; i < row2; i++) {
			for (int j = 0; j < col2; j++) {
				temp[i][j]=1;
			}
		}
    	if (getPath(maze,0,0,temp)) {
			return temp;
		}
    	return null;
    }
    public boolean getPath(int maze[][],int x,int y,int[][]temp){
    	// if (x,y is goal) return true 
    	if (x==row2-1&&y==col2-1) {
    		temp[x][y]=0;
			return true;
		}
    	 // Check if maze[x][y] is valid 
    	if(checkMaze(maze,x,y,temp)){
    		 // Check if maze[x][y] is valid 
    		temp[x][y]=0;
    		if (getPath(maze, x+1, y, temp)) {
				return true;
			}
    		if (getPath(maze, x-1, y, temp)) {
    			return true;
    		}
    		if (getPath(maze, x, y-1, temp)) {
    			return true;
    		}
    		if (getPath(maze, x, y+1, temp)) {
    			return true;
    		}
    		//当前这个方向不能完成，则回退到前一个结果，进行再次探索
    		temp[x][y]=1;
    		return false;
    	}
    	return false;
    }
    
    public boolean checkMaze(int [][]maze,int x,int y,int[][]temp){
    	if (x>=0&&x<row2&&y>=0&&y<col2&&maze[x][y]==0&&temp[x][y]==1) {
			return true;
		}
    	return false;
    }
    
    /**
     * 在迷宫中有一个球，里面有空的空间和墙壁。球可以通过滚上，下，左或右移动，
		但它不会停止滚动直到撞到墙上。当球停止时，它可以选择下一个方向。
		
		给定球的起始位置，目的地和迷宫，确定球是否可以停在终点。
		
		迷宫由二维数组表示。1表示墙和0表示空的空间。你可以假设迷宫的边界都是墙。开始和目标坐标用行和列索引表示
		
		Time complexity : O(mn). Complete traversal of maze will be done in the worst case. 
		Here, m and n refers to the number of rows and coloumns of the maze.
		
		Space complexity : O(mn). visited array of size m*n is used
     * LANG
     * @param maze
     * @param start
     * @param destination
     * @return
     */
    int[] deltaX = new int[] {0, 0, -1, 1};
    int[] deltaY = new int[] {1, -1, 0, 0};
    
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if(maze == null || maze.length == 0 || maze[0].length == 0 
        		|| start == null || start.length == 0 
        		|| destination == null || destination.length == 0){
            return false;
        }
        
        return dfs(maze, start, destination, new boolean[maze.length][maze[0].length]);
    }
    
    private boolean dfs(int[][] maze, int[] start, int[] destination, boolean[][] visited){
        if(start[0] == destination[0] && start[1] == destination[1]){
            return true;
        }
        
        int x = start[0], y = start[1];
        
        if(!isValid(x, y, maze) || visited[x][y]){
            return false;
        }
        
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++){
            int xx = x;
            int yy = y;
            
            while(isValid(xx + deltaX[i], yy + deltaY[i], maze)){
                xx += deltaX[i];
                yy += deltaY[i];
            }
            if(dfs(maze, new int[]{xx, yy}, destination, visited)){
                return true;
            }
        }
        return false;
    }
    
    private boolean isValid(int x, int y, int[][] maze){
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0;
    }
    
    /**
     * amazon Maximum Minimum Path
     * 给一个int[][]的matirx，对于一条从左上到右下的path pi，pi中的最小值是mi，求所有mi中的最大值。只能往下或右.
     * [8, 4, 7]
	   [6, 5, 9]
		有3条path：
		8-4-7-9 min: 4
		8-4-5-9 min: 4
		8-6-5-9 min: 5
		return: 5

		time complexity:O(row+col)
     */
    
	    private int min, max, row6, col6;
	    public int maxMinPath(int[][] matrix) {
	        row6 = matrix.length;
	        col6 = matrix[0].length;
	        min = Integer.MAX_VALUE;
	        max = Integer.MIN_VALUE;
	        dfsHelper(matrix, min, 0, 0);
	        return max;
	    }
	
	    public void dfsHelper(int[][] matrix, int min, int i, int j ){                
	        if (i >= row6 || j >= col6) return;
	        if (i == row6 - 1 && j == col6 - 1) {
	            min = Math.min(min, matrix[i][j]);
	            max = Math.max(max, min);
	            return;
	        }
	        min = Math.min(min, matrix[i][j]);
	        dfsHelper(matrix, min, i, j + 1);
	        dfsHelper(matrix, min, i + 1, j);
	    }

    /**
     * 79. Word Search
     * Given a 2D board and a word, find if the word exists in the grid.

		The word can be constructed from letters of sequentially adjacent cell, 
		where "adjacent" cells are those horizontally or vertically neighboring. 
		The same letter cell may not be used more than once
     */
    
	public boolean exist(char[][] board, String word) {
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];// 将访问标记数组置空
		for (int i = 0; i < m; i++)
			for (int j = 0; j < n; j++)
				if (dfs(board, word, 0, i, j, visited)) // 单词可在任意位置开始匹配
					return true; // 只要有一个位置完全匹配即匹配
		return false;
	}

	public boolean dfs(char[][] board, String word, int index, int x, int y, boolean[][] visited)// 辅助函数，自定义参数
	{
		if (index == word.length()) // 单词大小和索引相等即匹配
									// 当单词为空的时候是满足的
									// 下一个要查找的索引和单词大小相等说明，
									// word的0 - index的位置的字母已经匹配
			return true;
		if (x < 0 || y < 0 || x >= board.length || y >= board[0].length) // 不可越界
			return false;
		if (visited[x][y]) // 如果之前访问过，那么直接返回false
			return false;
		if (board[x][y] != word.charAt(index)) // 不相等，这条路走不通，剪枝
			return false;
		visited[x][y] = true; // 先标记为走过，因为下一次会走向四个方向
		boolean ret = dfs(board, word, index + 1, x, y + 1, visited) || dfs(board, word, index + 1, x, y - 1, visited)
				|| dfs(board, word, index + 1, x - 1, y, visited) || dfs(board, word, index + 1, x + 1, y, visited);
		visited[x][y] = false; // 走过之后，不过不匹配，要还原为没有走过
		return ret;
	}
	
	/**
	 * 212. Word Search II
	 * Given a 2D board and a list of words from the dictionary, find all words in the board.
	 * 
	 * Backtracking + Trie
	Intuitively, start from every cell and try to build a word in the dictionary. Backtracking (dfs) is the powerful way to exhaust every possible ways. Apparently, we need to do pruning when current character is not in any word.

	How do we instantly know the current character is invalid? HashMap?
	How do we instantly know what's the next valid character? LinkedList?
	But the next character can be chosen from a list of characters. "Mutil-LinkedList"?
	Combing them, Trie is the natural choice. Notice that:

	TrieNode is all we need. search and startsWith are useless.
	No need to store character at TrieNode. c.next[i] != null is enough.
	Never use c1 + c2 + c3. Use StringBuilder.
	No need to use O(n^2) extra space visited[m][n].
	No need to use StringBuilder. Storing word itself at leaf node is enough.
	No need to use HashSet to de-duplicate. Use "one time search" trie.
	For more explanations, check out dietpepsi's blog.

	Code Optimization

	UPDATE: Thanks to @dietpepsi we further improved from 17ms to 15ms.

	59ms: Use search and startsWith in Trie class like this popular solution.
	33ms: Remove Trie class which unnecessarily starts from root in every dfs call.
	30ms: Use w.toCharArray() instead of w.charAt(i).
	22ms: Use StringBuilder instead of c1 + c2 + c3.
	20ms: Remove StringBuilder completely by storing word instead of boolean in TrieNode.
	20ms: Remove visited[m][n] completely by modifying board[i][j] = '#' directly.
	18ms: check validity, e.g., if(i > 0) dfs(...), before going to the next dfs.
	17ms: De-duplicate c - a with one variable i.
	15ms: Remove HashSet completely. dietpepsi's idea is awesome.
	 */
	
	public List<String> findWords(char[][] board, String[] words) {
	    List<String> res = new ArrayList<>();
	    TrieNode root = buildTrie(words);
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j++) {
	            dfs (board, i, j, root, res);
	        }
	    }
	    return res;
	}

	public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
	    char c = board[i][j];
	    if (c == '#' || p.next[c - 'a'] == null) return;
	    p = p.next[c - 'a'];
	    if (p.word != null) {   // found one
	        res.add(p.word);
	        p.word = null;     // de-duplicate
	    }

	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j ,p, res); 
	    if (j > 0) dfs(board, i, j - 1, p, res);
	    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	    board[i][j] = c;
	}
	
	public TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String w : words) {
	        TrieNode p = root;
	        for (char c : w.toCharArray()) {
	            int i = c - 'a';
	            if (p.next[i] == null) p.next[i] = new TrieNode();
	            p = p.next[i];
	       }
	       p.word = w;
	    }
	    return root;
	}
}
