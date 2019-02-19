import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class nextprePermutation {
    public static void main(String[] args) {
        nextprePermutation sol = new nextprePermutation();
        int[] nums = new int[]{1,2,3,7,6,5};
        prePermutation(nums);
        System.out.print(Arrays.toString(nums));
    }
    public static void nextPermutation(int[] nums) {
        int i = nums.length-1;
        for (; i > 0; i--) {
            if (nums[i-1] < nums[i]) {
                break;
            }
        }
        if (i == 0) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        int big = i-1;
        for (int j = nums.length-1; j > big; j--) {
            if (nums[j] > nums[big]) {
                big = j;
                break;
            }
        }
        int temp = nums[i-1];
        nums[i-1] = nums[big];
        nums[big] = temp;
        reverse(nums, i, nums.length-1);
    }
    public static void prePermutation(int[] nums) {
        int i = nums.length-1;
        for (; i > 0; i--) {
            if (nums[i-1] > nums[i]) {
                break;
            }
        }
        if (i == 0) {
            reverse(nums, 0, nums.length-1);
            return;
        }
        int small = i-1;
        for (int j = nums.length-1; j > small; j--) {
            if (nums[j] < nums[small]) {
                small = j;
                break;
            }
        }
        int temp = nums[small];
        nums[small] = nums[i-1];
        nums[i-1] = temp;
        reverse(nums, i, nums.length-1);
    }
    public static void reverse(int[] nums, int l, int r) {
        int temp;
        while (l < r) {
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            l++;
            r--;
        }
    }
}