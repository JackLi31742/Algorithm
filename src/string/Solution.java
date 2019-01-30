package string;

public class Solution {

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
	
	
	 
}
