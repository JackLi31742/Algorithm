package dfs;

public class Solution {

	public static void main(String[] args) {
		
	}
	
	/**
	 * 200. Number of Islands
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
     * 695. Max Area of Island
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
	
}
