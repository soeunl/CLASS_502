package Test_Java;

import java.util.Arrays;

public class Q6 {
    public static void main(String[] args) {
        int[] nums = {21, 22, 30, 11, 99, 31};
        int[] nums2 = new int[nums.length];
        int j = 0;
        for (int i = nums.length - 1; i >= 0; i--) {
            nums2[j] = nums[i];
            j++;
        }
        for (int k = 0; k < nums2.length; k++) {
            System.out.println(Arrays.toString(nums2));
        }
    }
}
