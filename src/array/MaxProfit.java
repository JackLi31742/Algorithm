package array;

import javax.print.attribute.standard.MediaName;

import org.omg.CORBA.INTERNAL;

public class MaxProfit {

	/**
	 * 121. Best Time to Buy and Sell Stock
	 * 
	 * LANG
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
        int len=prices.length;
        int maxProfit=0;
        
        for (int i = 0; i < len; i++) {
			for (int j = i+1; j < len; j++) {
				if (prices[j]-prices[i]>maxProfit) {
					maxProfit=prices[j]-prices[i];
				}
			}
		}
        return maxProfit;
    }
	
	 //不能先循环找到之前最小的，再循环找这个最小值后边的
	 //[2,4,1]
	public int maxProfit1_2(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
        int len=prices.length;
        int min=prices[0];int maxProfit=0;
//        int index=0;
        
        
        for (int i = 1; i < len; i++) {
			if (prices[i]<min) {
				min=prices[i];
//				index=i;
			}else {
				
				//每次都实时更新maxProfit
				if (prices[i]-min>maxProfit) {
					maxProfit=prices[i]-min;
				}
			}
		}
        
//        if (index<len-1) {
//			for (int i = index; i < len; i++) {
//			}
//		}
        
        return maxProfit;
    }
	
	
	/**
	 * 122. Best Time to Buy and Sell Stock II
	 * 
	 * 多次买，多次卖，但是在买之前必须卖掉
	 * 
	 * 1, 2, 3, 6, 7, 6, 8
	 * 
	 * maxProfit=(7-1)+(8-6)=(2-1)+(3-2)+(6-3)+(7-6)+(8-6)
	 * 
	 * (7-1)+(8-6)>8-1
	 * LANG
	 * @param prices
	 * @return
	 */
	public int maxProfit2(int[] prices) {
		if (prices==null||prices.length==0) {
			return 0;
		}
        int len=prices.length;
        int maxProfit=0;
        for (int i = 1; i < len; i++) {
			if (prices[i]>prices[i-1]) {
				maxProfit+=prices[i]-prices[i-1];
			}
		}
        
        return maxProfit;
    }
	
	public static void main(String[] args) {
		int []prices={7,1,5,3,6,4};
		MaxProfit maxProfit=new MaxProfit();
		System.out.println(maxProfit.maxProfit2(prices));;
	}

}
