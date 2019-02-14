package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Solution2 {

	/**
	 * 反转字符串
	 * LANG
	 * @param s
	 * @return
	 */
	public String reverseString(String s) {
		char[] arr=s.toCharArray();
		char[] out=new char[arr.length];
        for (int i = 0; i < arr.length; i++) {
			out[arr.length-1-i]=arr[i];
		}
		return String.valueOf(out);
    }
	public String reverseString2(String s) {
		char[] word = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            char temp = word[i];
            word[i] = word[j];
            word[j] = temp;
            i++;
            j--;
        }
        return new String(word);
	}
	/**
	 * 检验括号

		给你一个str,里面只有 '('和‘)’,让你数valid pairs一共有多少,如果不是valid就返回-1. 
		(判断是不是valid的parenthesis string，不是的话返回-1，是的话返回valid pair个数，即String.length() / 2 )
		ASCII 40 ( 41 )
	 * LANG
	 * @param s
	 * @return
	 */
	public boolean isValidParentheses(String s) {
        if (s == null || s.length() == 0)   return true;
        Stack<Character> stack = new Stack<Character>();
        
        for (int i = 0; i < s.length(); i++) {
            if (stack.empty())  stack.push(s.charAt(i));
            else if (s.charAt(i) - stack.peek() == 1 || s.charAt(i) - stack.peek() == 2)    stack.pop();
            else    stack.push(s.charAt(i));
        }
        
        return stack.empty();
    }

	/**
	 * 937. Reorder Log Files
	 * 字母日志在数字日志前
	 * Time Complexity: O(AlogA), where A is the total content of logs.

		Space Complexity: O(A).
	 * LANG
	 * @param logs
	 * @return
	 */
	public String[] reorderLogFiles(String[] logs) {
		Arrays.sort(logs, (log1, log2) -> {
			//最多分成2个
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            //第二个是数字还是字母
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
	/**
	 * leetcode 165. Compare Version Numbers
	 * LANG
	 * @param version1
	 * @param version2
	 * @return
	 */
	public int compareVersion(String version1, String version2) {
        String[]str1=version1.split("\\.");
        String[]str2=version2.split("\\.");
        
        int max=Math.max(str1.length, str2.length);
        for (int i = 0; i < max; i++) {
        	//补0
			Integer v1=i<str1.length?Integer.parseInt(str1[i]):0;
			Integer v2=i<str2.length?Integer.parseInt(str2[i]):0;
			int com= v1.compareTo(v2);
			if (com!=0) {
				return com;
			}
		}
        return 0;
    }
	
	
	 
	
	/**
	 * 9. Palindrome Number
	 * LANG
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		
		String s=String.valueOf(x);
		char [] arr=s.toCharArray();
		if (arr.length==1) {
			return true;
		}
		for (int i = 0; i < arr.length/2;i++) {
			
			if (arr[i]!=arr[arr.length-1-i]) {
				return false;
			}
			continue;
		}
		return true;
        
    }
	
	 public boolean isPalindrome2(int x) {
        int p = x, q = 0;//p是从右往左用来计算的，q是结果
	    while (p >= 1) {
	        q *= 10;
	        q += p % 10;
	        p /= 10;
	    }
	    return q == x;

    }
	 
	 /**
	  * 5. Longest Palindromic Substring
	  * 动态规划：
	  * 状态dp[j][i]表示索引j到索引i的子串是否是回文串
	  * j=i,dp[j][i]=true;
		i-j=1,dp[j][i]=str[i]==str[j]
		i-j>1,dp[j][i]=(str[i]==str[j])&&dp[j+1][i-1]
		dp[j][i]为true时表示索引j到索引i形成的子串为回文子串，且子串起点索引为j,长度为i - j + 1。
		算法时间复杂度为O(N ^ 2)
		https://www.jianshu.com/p/c82cada7e5b0
	  * LANG
	  * @param s
	  * @return
	  */
	 public String longestPalindrome2(String s) {
	        if (s==null||s.length()==0) {
				return s;
			}
	        int len=s.length();
	    	boolean [][]dp=new boolean[len][len];
	    	int maxlen=1;
	    	int start=0;
	    	for (int i = 0; i < len; i++) {
				for (int j = 0; j <= i; j++) {
					if (i-j<2) {
						dp[j][i]=(s.charAt(i)==s.charAt(j));
					}else {
						dp[j][i]=((s.charAt(i)==s.charAt(j))&&dp[j+1][i-1]);
					}
					
					if (dp[j][i]&&maxlen<i-j+1) {
						maxlen=i-j+1;
						start=j;
						
					}
				}
			}
	    	
	    	return s.substring(start,maxlen+start);
	    }
	 /**
	  * Manacher算法：
https://blog.csdn.net/sinat_35261315/article/details/78267046
	  * LANG
	  * @param s
	  * @return
	  */
	 public static String longestPalindrome(String s) {
			if (s==null||s.length()<=1) {
				return s;
			}
			String sub="";
			for (int i = 0; i < 2*s.length()+1; i++) {
				sub=findPalindrome(i-1,i+1,s,sub);
			}
			return sub;
			
		}
	    public static String findPalindrome(int left,int right,String s,String sub) {
			while (left>=0&&right<2*s.length()+1) {
				if (left%2!=0&&right%2!=0&&s.charAt(left/2)!=s.charAt(right/2)) {
					break;
				}
				left--;
				right++;
			}
			if (left==-1) {
				left=-2;
			}
			int start=left/2+1;
			int end=right/2;
			if (sub.length()<=end-start) {
				sub=s.substring(start,end);
			}
			return sub;
		}
	    
	    /**
	     * 647. Palindromic Substrings
	     * https://leetcode.com/problems/palindromic-substrings/discuss/105688/Very-Simple-Java-Solution-with-Detail-Explanation
	     * Lets take a string "aabaa"

			Step 1: Start a for loop to point at every single character from where we will trace the palindrome string.
			checkPalindrome(s,i,i); //To check the palindrome of odd length palindromic sub-string
			checkPalindrome(s,i,i+1); //To check the palindrome of even length palindromic sub-string

			Step 2: From each character of the string, we will keep checking 
			if the sub-string is a palindrome and increment the palindrome count. 
			To check the palindrome, keep checking the left and right of the character if it is same or not.
	     */
	    int count =1;
	    public int countSubstrings(String s) {
	        if(s.length()==0) 
	            return 0;
	        for(int i=0; i<s.length()-1; i++){
	            checkPalindrome(s,i,i);     //To check the palindrome of odd length palindromic sub-string
	            checkPalindrome(s,i,i+1);   //To check the palindrome of even length palindromic sub-string
	        }
	        return count;
	    }    

	    private void checkPalindrome(String s, int i, int j) {
	        while(i>=0 && j<s.length() && s.charAt(i)==s.charAt(j)){    //Check for the palindrome string 
	            count++;    //Increment the count if palindromin substring found
	            i--;    //To trace string in left direction
	            j++;    //To trace string in right direction
	        }
	    }
	    /**
	     * 214. Shortest Palindrome
	     * Given a string s, you are allowed to convert it to a palindrome 
	     * by adding characters in front of it. Find and return the shortest 
	     * palindrome you can find by performing this transformation.
	     * 
	     * Initialize i=0
			Iterate over j from n−1 to 0:
			If s[i]==s[j], increase i by 1
			If i equals the size of s, the entire string is palindrome, and hence return the entire string s.
			Else:
			Return reverse of remaining substring after i to the 
			end of string + shortestPalindrome routine on substring
			 from start to index i−1 + remaining substring after i to the end of string.
			 
			 Time complexity: O(n^2)
				Each iteration of shortestPalindrome is linear in 
				size of substring and the maximum number of recursive calls can be n/2 
				times as shown in the Intuition section.
				Let the time complexity of the algorithm be T(n). 
				Since, at the each step for the worst case, 
				the string can be divide into 2 parts and we require only one part 
				for further computation. Hence, the time complexity for the worst case 
				can be represented as : T(n)=T(n−2)+O(n). So, T(n)=O(n)+O(n−2)+O(n−4)+...+O(1) which is O(n^2)
				Thanks @CONOVER for the time complexity analysis.

			Space complexity: O(n) extra space for remain_revremain_rev string.
	     * LANG
	     * @param s
	     * @return
	     */
	    public String shortestPalindrome(String s) {
	    	 int j = 0;
	    	 for (int i = s.length() - 1; i >= 0; i--) {
	    	    if (s.charAt(i) == s.charAt(j)) { 
	    	        j += 1; 
	    	    }
	    	}
	    	 if (j == s.length()) { 
	    		 return s; 
	    	}
	    	String suffix = s.substring(j);
	    	return new StringBuffer(suffix).reverse().toString() 
	    			+ shortestPalindrome(s.substring(0, j)) + suffix;
	    }
	    
	    
	    /**
		 * 3. Longest Substring Without Repeating Characters
		 * https://www.cnblogs.com/grandyang/p/4480780.html
		 * 
		 * 建立一个HashMap，建立每个字符和其最后出现位置之间的映射，然后我们需要定义两个变量res和left，
		 * 其中res用来记录最长无重复子串的长度，left指向该无重复子串左边的起始位置的前一个，由于是前一个，
		 * 所以初始化就是-1，然后我们遍历整个字符串，对于每一个遍历到的字符，如果该字符已经在HashMap中存在了，
		 * 并且如果其映射值大于left的话，那么更新left为当前映射值。然后映射值更新为当前坐标i，
		 * 这样保证了left始终为当前边界的前一个位置，然后计算窗口长度的时候，直接用i-left即可，用来更新结果res
		 * LANG
		 * @param s
		 * @return
		 */
		public int lengthOfLongestSubstring(String s) {
	        int left=-1;int result=0;
	        HashMap<Character, Integer> map=new HashMap<>();
	        for (int i = 0; i < s.length(); i++) {
	        	char ch=s.charAt(i);
				if (map.containsKey(ch)&&map.get(ch)>left) {
					left=map.get(ch);
				}
				map.put(ch, i);
				result=Math.max(result, i-left);
			}
	        return result;
	    }
		
	    /**
	     * 395. Longest Substring with At Least K Repeating Characters
	     * 先遍历整个string，并记录每个不同的character的出现次数。如果所有character出现次数都不小于k，
	     * 那么说明整个string就是满足条件的longest substring，返回原string的长度即可；
	     * 如果有character的出现次数小于k，假设这个character是c，因为满足条件的substring永远不会包含c，
	     * 所以满足条件的substring一定是在以c为分割参考下的某个substring中。所以我们需要做的就是把c当做是split的参考，
	     * 在得到的String[]中再次调用我们的method，找到最大的返回值即可。
	     * LANG
	     * @param s
	     * @param k
	     * @return
	     */
	    public static int longestSubstring(String s, int k) {
	    	if (s==null||s.length()==0) {
				return 0;
			}
	        int [] ch=new int[26];
	        
	        for (int i = 0; i < s.length(); i++) {
				ch[s.charAt(i)-'a']+=1;
			}
	        
	        boolean flag=true;
	        for (int i = 0; i < ch.length; i++) {
				if (ch[i]>0&&ch[i]<k) {
					flag=false;
				}
			}
	        if (flag) {
				return s.length();
			}
	        
	        int result=0;
	        int cur=0;
	        char delimiter = 0;
	        while(cur<s.length()){
	        	//找到第一个小于k的，作为split的
	        	if (ch[s.charAt(cur)-'a']<k) {
	        		delimiter=s.charAt(cur);
                    break;
				}
	        	cur++;
	        }
	        String[] splits = s.split(""+delimiter);
	        for(String str: splits){
	        	result = Math.max(result, longestSubstring(str,k));
	        }
	        return result;
	    }
	    /**
	     * 下边的更快
	     * LANG
	     * @param s
	     * @param k
	     * @return
	     */
	    public int longestSubstring2(String s, int k) {
	        if (s == null || s.length() == 0) return 0;
	        char[] chars = new char[26];
	        // record the frequency of each character
	        for (int i = 0; i < s.length(); i += 1) chars[s.charAt(i) - 'a'] += 1;
	        boolean flag = true;
	        for (int i = 0; i < chars.length; i += 1) {
	            if (chars[i] < k && chars[i] > 0) flag = false;
	        }
	        // return the length of string if this string is a valid string
	        if (flag == true) return s.length();
	        int result = 0;
	        int start = 0, cur = 0;
	        // otherwise we use all the infrequent elements as splits
	        while (cur < s.length()) {
	            if (chars[s.charAt(cur) - 'a'] < k) {
	                result = Math.max(result, longestSubstring(s.substring(start, cur), k));
	                start = cur + 1;
	            }
	            cur++;
	        }
	        result = Math.max(result, longestSubstring(s.substring(start), k));
	        return result;
	    }
	    /**
	     * leetcode 340 Longest Substring with At Most K Distinct Characters
	     *  
	     *  滑动窗口，当map的大小超过k，开始移除，从左到右，依次减一，当为0的时候，移除，同时left自增
	     */
	    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
	        // write your code here
	    	int result=0; int left=0;
	    	Map<Character, Integer> map=new HashMap<>();
 	    	for (int i = 0; i < s.length(); i++) {
	    		char ch=s.charAt(i);
				if (map.containsKey(ch)) {
					map.put(ch, map.get(ch)+1);
				}else {
					map.put(ch, 1);
				}
				while (map.size()>k) {
					char check=s.charAt(left);
					Integer temp=map.get(check)-1;
					if (temp==0) {
						map.remove(check);
					}else {
						//更新
						map.put(check, temp);
					}
					left++;
				}
				result=Math.max(result, i-left+1);
			}
	    	return result;
	    }
	    
	    /**
	     * 
	     * count number of substrings with exactly k unique characters 
	     * 从第一位开始遍历，找到所有符合的子串，再从第二位开始遍历
	     * Time Complexity : O(n*n)
	     */
	    
	   public static int countkDist(String str, int k) { 
		   
		    if (str==null||str.length()==0||k==0) {
				return 0;
			}
		    if (str.length()==1) {
				return 1;
			}
	        // Initialize result 
	        int res = 0; 
	  
	        int n = str.length(); 
	  
	        // To store count of characters from 'a' to 'z' 
	        int cnt[] = new int[256]; 
	        List<String> list=new ArrayList<>();
	        // Consider all substrings beginning with str[i] 
	        for (int i = 0; i < n; i++) 
	        { 
	        	//不同字符的数量
	            int dist_count = 0; 
	  
	            // Initializing count array with 0 
	            Arrays.fill(cnt, 0); 
	  
	            // Consider all substrings between str[i..j] 
	            for (int j=i; j<n; j++) 
	            { 
	                // If this is a new character for this 
	                // substring, increment dist_count. 
	                if (cnt[str.charAt(j) - 'a'] == 0) 
	                    dist_count++; 
	  
	                // Increment count of current character 
	                cnt[str.charAt(j) - 'a']++; 
	  
	                // If distinct character count becomes k, 
	                // then increment result. 
	                if (dist_count == k) {
	                    res++; 
	                    list.add(str.substring(i,j+1));
	                }
	                
	            } 
	        } 
	        System.out.println(list);
	        return res; 
	    }
	   
	   /**
	     * find the substrings of size K with K distinct characters
	     * Given a string and number K, find the substrings of size K 
	     * with K distinct characters. If no, output empty list. 
	     * Remember to emit the duplicate substrings, i.e. if the substring repeated twice, 
	     * only output once
	     * LANG
	     */
	    public static List<String> getAllSubtringWithK(String str,int k){
	    	// Initialize result 
	  
	        int n = str.length(); 
	  
	        // To store count of characters from 'a' to 'z' 
	        int cnt[] = new int[256]; 
	        List<String> list=new ArrayList<>();
	        // Consider all substrings beginning with str[i] 
	        for (int i = 0; i < n; i++) 
	        { 
	        	//不同字符的数量
	            int dist_count = 0; 
	  
	            // Initializing count array with 0 
	            Arrays.fill(cnt, 0); 
	  
	            // Consider all substrings between str[i..j] 
	            for (int j=i; j<n; j++) 
	            { 
	                // If this is a new character for this 
	                // substring, increment dist_count. 
	                if (cnt[str.charAt(j) - 'a'] == 0) 
	                    dist_count++; 
	  
	                // Increment count of current character 
	                cnt[str.charAt(j) - 'a']++; 
	  
	                // If distinct character count becomes k, 
	                // then increment result. 
	                if (dist_count == k) {
	                    list.add(str.substring(i,j+1));
	                }
	                
	            } 
	        } 
	        return list; 
	    }
	    /**
	     * longest substring with k unique characters 
	     * https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
	     * Time:O(N)
	     * LANG
	     */
	   
	    public static String longestSubtringwithK(String s,int k){
	    	//字符串里有多少不同的字符 number of unique characters
	    	int num=0;
	    	//store the count of characters 
	    	int []count=new int[26];
	    	//得到num的数量
	    	for (int i = 0; i < s.length(); i++) {
	    		char ch=s.charAt(i);
				if (count[ch-'a']==0) {
					num++;
				}
				count[ch-'a']++;
			}
	    	if (num<k) {
				return "";
			}
	    	int start=0;int end=0;
	    	int windowSize=0;int windowStart=0;
	    	Arrays.fill(count, 0);
	    	count[s.charAt(0)-'a']++;
	    	
	    	for (int i = 1; i < s.length(); i++) {
				count[s.charAt(i)-'a']++;
				end++;
				
				while(!isValid(count,k)){
					count[s.charAt(start)-'a']--;
					start++;
				}
				
				if (end-start+1>windowSize) {
					windowSize=end-start+1;
					windowStart=start;
				}
				
			}
	    	return s.substring(windowStart,windowStart+windowSize);
	    }
	    
	    public static boolean isValid(int[]count,int k){
	    	int num=0;
	    	for (int i = 0; i < count.length; i++) {
				if (count[i]>0) {
					num++;
				}
			}
	    	// Return true if k is greater than or equal to val 
	    	return (k>=num);
	    }
	    
	    /**
	     * 819. Most Common Word
	     * Time Complexity: O(P + B), where P is the size of paragraph and B is the size of banned.

			Space Complexity: O(P + B), to store the count and the banned set.
	     * LANG
	     * @param paragraph
	     * @param banned
	     * @return
	     */
		public static String mostCommonWord(String paragraph, String[] banned) {
			String s=paragraph.replaceAll("[!?',;.]", " ").toLowerCase();
			String[] words = s.split(" ");
			HashMap<String, Integer> map = new HashMap<>(words.length);
			Set<String> set=new HashSet<>(banned.length);
			set.addAll(Arrays.asList(banned));
			for (String word : words) {
				if (!word.equals("")) {
					if (!set.contains(word)) {
						if (map.containsKey(word)) {
							map.put(word, map.get(word)+1);
						}else {
							map.put(word,1);
						}
					}
				}
			}
			//用set去掉banned
			/*for (String word : banned) {
				if (map.containsKey(word)) {
					map.remove(word);
				}
			}*/
			String res = null;
			for (String word : map.keySet()) {
				if (res == null || map.get(word) > map.get(res)) {
					res = word;
				}
			}
			return res;
		}
		/**
		 * 273. Integer to English Words
		 * Input: 123
			Output: "One Hundred Twenty Three"
		 */
		private final String[] belowTen = new String[] {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
	    private final String[] belowTwenty = new String[] {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	    private final String[] belowHundred = new String[] {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	    
	    public String numberToWords(int num) {
	        if (num == 0) return "Zero";
	        return helper(num); 
	    }
	    
	    private String helper(int num) {
	        String result = new String();
	        if (num < 10) result = belowTen[num];
	        else if (num < 20) result = belowTwenty[num -10];
	        else if (num < 100) result = belowHundred[num/10] + " " + helper(num % 10);
	        else if (num < 1000) result = helper(num/100) + " Hundred " +  helper(num % 100);
	        else if (num < 1000000) result = helper(num/1000) + " Thousand " +  helper(num % 1000);
	        else if (num < 1000000000) result = helper(num/1000000) + " Million " +  helper(num % 1000000);
	        else result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
	        return result.trim();
	    }
	    public static void main(String[] args) {
//			String s = "pqpqs";int k = 2;
//			System.out.println(getAllSubtringWithK(s, k));;
	    	
	    	String s ="a, a, a, a, b,b,b,c, c";

	    	String[] banned={"a"};
	    	
	    	System.out.println(mostCommonWord(s,banned));;
	    	
		}
}
