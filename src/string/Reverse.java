package string;

public class Reverse {

	/**
	 * 344. Reverse String
	 * LANG
	 * @param s
	 */
	public void reverseString(char[] s) {
        int len=s.length;
        for (int i = 0; i < len/2; i++) {
        	//由于Java中的数组没有指针的概念，如果用引用去换的话，换掉的是引用的值，而不是数组本身的值
//			char left=s[i];
//			char right=s[len-1-i];
			char temp=s[i];
			s[i]=s[len-1-i];
			s[len-1-i]=temp;
		}
    }
	
	/**
	 * 541. Reverse String II
	 * 
	 * 反转2k字符中的前k个
	 * LANG
	 * @param s
	 * @param k
	 * @return
	 */
	public String reverseStr(String s, int k) {
        if (s==null||s==""||k==0||k==1) {
			return s;
		}
        char[] arr=s.toCharArray();
        int len=arr.length;
        if (len<k) {
        	reverseString(arr);
		}else {
			int j=0;
			for (int i = 0; i < len; i++) {
				int times=(i+1)/k;
				int left=(1+2*(j-1));
				int right=(1+2*j);
				if (times<right&&times>left) {
					char temp=arr[i];
					arr[i]=arr[right*k-1];
					arr[right*k-1]=temp;
				}else {
					if (times>right) {
						
						j++;
					}
				}
			}
		}
        
        return String.copyValueOf(arr);
    }
	
	public static void main(String[] args) {
		String s = "abcdefg";
		Reverse reverse=new Reverse();
		System.out.println(reverse.reverseStr(s, 3));
	}
}
