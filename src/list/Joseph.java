package list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 已知n个人（以编号1，2，3...n分别表示）围坐在一张圆桌周围，
 * 从编号为k的人开始报数，数到m的那个人出列；他的下一个人又从1开始报数，
 * 数到m的那个人又出列；依此规律重复下去，直到圆桌周围的人只有一个没有出列
 * @author LANG
 *
 */
public class Joseph {
	
	//这个方法是错的
	public int josephProblem(int n, int k, int m){
		
		LinkedList<Integer> list=new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			list.add(i);
		}
		while(list.size()>1){
			int index=0;
			for (int i = 0; i < list.size(); ) {
				if (i==0) {
					index=(k-1+m)%list.size();
				}else {
					
					index=(i+m)%list.size();
				}
				System.out.print("下标："+(index-1-i/m));
				System.out.println(",要删除的元素是："+list.get(index-1-i/m)+",");
				list.remove(index-1-i/m);
				i=(i+m)%list.size();
			}
			System.out.println();
		}
		return list.get(0);
	}

	

	//环里一个元素不剩
	public void yuesefu(int n,int startNum, int m) {  
		//使用ArrayList比LinkedList快是因为在删除时，删除的是下标，ArrayList虽然每次都copy，
		//但是LinkedList根据下标找到该元素，是遍历，而ArrayList的copy比LinkedList的遍历快
        List<Integer> list = new ArrayList<Integer>();  
        for (int i = 1; i <= n; i++) {  
            list.add(i);  
        }  
        //从下标为K开始计数  
        int k = startNum-1;  
        while (list.size() >0) {  
            //第m人的索引位置  
            k = (k + m- 1) % (list.size()) ;  
           // 判断是否到队尾  到队尾时候k=-1
           System.out.println("k="+k+","+list.get(k));  
           list.remove(k);  
        }  
    }  
	
	//剩余一个，计数从1号开始
	public int yuesefu(int n, int m) { 
        //使用ArrayList比LinkedList快是因为在删除时，删除的是下标，ArrayList虽然每次都copy，
        //但是LinkedList根据下标找到该元素，是遍历，而ArrayList的copy比LinkedList的遍历快
        List<Integer> list = new ArrayList<Integer>(); 
        for (int i = 1; i <= n; i++) { 
            list.add(i); 
        } 
        //从下标为K开始计数 
        int k = 0; 
        while (list.size() >1) { 
            //第m人的索引位置 
            k = (k + m- 1) % (list.size()) ; 
           // 判断是否到队尾  到队尾时候k=-1
//           System.out.println("k="+k+","+list.get(k)); 
           list.remove(k); 
        }
        return list.get(0);
	}
	public static void main(String[] args) {
		Joseph joseph=new Joseph();
//		long start=System.currentTimeMillis();
		System.out.println(joseph.yuesefu(8, 3));;
//		long end=System.currentTimeMillis();
//		System.out.println(end-start+"ms");
	}
}
