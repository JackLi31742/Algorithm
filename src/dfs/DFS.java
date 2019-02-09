package dfs;

public class DFS {
    //输入的迷宫矩阵
    private static int[][] matrix= {
            {0,1,0,0,1},
            {0,0,1,1,1},
            {1,0,0,0,0},
            {1,0,1,1,0},
            {1,0,0,0,0},
    };

    //迷宫的宽度
    private static  int width=5;
    //迷宫的高度
    private static int heigth=5;

    //标记迷宫中各个位置是否已经被走过
    private static boolean[][] flag=null;
    //在迷宫中的移动方向
    private static int[][] direction={
            {0,1},
            {0,-1},
            {-1,0},
            {1,0}
    };

    public static void main(String[] args) {
        //起始点
        Point start=new Point(0,0);
        //目标点
        Point end=new Point(width-1,heigth-1);


        flag=new boolean[width][heigth];
        if(existPath(start,end)) System.out.println("yes");
        else System.out.println("no");
   
   }

    //判断是否存在路径
    public static boolean existPath(Point start,Point end){
        //到达目标点
        if(start.getX()==end.getX()&&start.getY()==end.getY()){
            return true;
        }
        //从四个方向进行再次遍历
        for(int i=0;i<4;i++){
            int nx=start.getX()+direction[i][0];
            int ny=start.getY()+direction[i][1];

            //检验方向的合法性
            if(nx>=0&&nx<width&&ny>=0&&ny<heigth&&flag[nx][ny]==false&&matrix[nx][ny]==0){
                //标记的位置需要对应一下
                flag[nx][ny]=true;
                if(existPath(new Point(nx,ny),end)) return true;
                //当前这个方向不能完成，则回退到前一个结果，进行再次探索
                flag[nx][ny]=false;
            }
        }
        return false;
    }  
}

//位置点的辅助类
class Point {
    private int x;
    private int y;
    public Point(int xx, int yy){
        this.x=xx;
        this.y=yy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "[" + x +
                ", "+y+"]";
    }
}

