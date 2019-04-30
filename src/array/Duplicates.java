package array;

public class Duplicates {

	/**
	 * 26. Remove Duplicates from Sorted Array
	 *  O(1) extra memory
	 *  需要修改原输入数组
	 * LANG
	 * @param nums
	 * @return
	 */
	public int removeDuplicates(int[] nums) {

		int len = 0;int left=0;int right=0;
		for (int i = right; i < nums.length; ++i) {
			if (i == 0) {
				len++;
			} else {
				if (nums[i] == nums[i - 1]) {
					left=i;
					while (nums[i] == nums[i - 1]) {
						++i;
					}
					if (i>left) {
						nums[left]=nums[i];
					right=i;
//					i=left;
//						i--;
						len++;
					}
				}
			}
		}
		return len;
	}
	
	public static void main(String[] args) {
		Duplicates duplicates=new Duplicates();
		int nums[]={0,0,1,1,1,2,2,3,3,4};
		duplicates.removeDuplicates(nums);
	}

}
