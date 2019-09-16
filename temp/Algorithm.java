import java.util.Arrays;

public class Algorithm{
	// public static int pivotIndex(int[] nums){
	// 	for (int i=1;i<=nums.length-2;i++){
	// 		if (isDeviedNum(nums,i)){
	// 			return i;
	// 		}
	// 	}
	// 	return -1;
	// }

	// public static boolean isDeviedNum(int[] nums, int s){
	// 	int sumleft = 0;
	// 	int sumright = 0;
	// 	for (int i=0;i<=s-1;i++){
	// 		sumleft = sumleft + nums[i];
	// 	}

	// 	for (int i=s+1;i<=nums.length-1;i++){
	// 		sumright = sumright + nums[i];
	// 	}

	// 	return sumleft==sumright;
	// }

	// public static int[] sort(int[] nums){
	// 	for (int i=1;i<nums.length ;i++ ) {
	// 		for (int j=0;j<nums.length-i ; j++) {
	// 			if (nums[j] > nums[j+1]) {
	// 				int m = nums[j+1];
	// 				nums[j+1]=nums[j];
	// 				nums[j]=m;
	// 			}
	// 		}
	// 	}
	// 	return nums;
	// }




	



	public static void main(String[] args) {
		int[] a = {9,9,9,9,9};
		int[] b = {2,3,4,5,6,3};
		int[] c = {1,3,4,0,8};
		System.out.println(Arrays.toString(sort(a)));
	}

}