package array;

public class BinarySearch {
	
	/**
	 * 35. Search Insert Position
	 * 二分查找
	 * LANG
	 * @param nums  a sorted array
	 * @param target
	 * @return
	 */
	public int searchInsert(int[] nums, int target) {
        int index=0;int len=nums.length;
        int i=0;
        while(i<len){
        	if (nums[i]<target) {
        		i++;
        	}else {
				index=i;
				//找到后需要break循环，后边的就不遍历了
				break;
			}
        	
        }
        //走到数组的最后一个元素，依然是小于target的，说明target的插入位置就在最后
        if (i==len) {
			index=i;
		}
		return index;
    }
	
	public static void main(String[] args) {
		BinarySearch binarySearch=new BinarySearch();
		int nums[]={1,3,5,6};
		System.out.println(binarySearch.searchInsert(nums, 7));;
	}
}
