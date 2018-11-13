import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class searchRange {
    public static void main(String[] args) {
        searchRange sol = new searchRange();
        int[] nums = {5,7,7,8,8,10};
        //System.out.print(Arrays.toString(sol.searchRange(nums, 6)));
        System.out.print(sol.searchRange2(nums, 8));
    }
    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1,-1};
        int left = findFirst(nums, target);
        int right = findLast(nums, target);
        int[] res = new int[2];
        res[0] = left;
        res[1] = right;
        return res;
    }
    public int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left+1 < right) {
            int mid = left+(right-left)/2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[left] == target) return left;
        if (nums[right] == target) return right;
        return -1;
    }
    public int findLast(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left+1 < right) {
            int mid = left+(right-left)/2;
            if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (nums[right] == target) return right;
        if (nums[left] == target) return left;
        return -1;
    }
    public int searchRange2(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = findFirst(nums, target);
        int right = findLast(nums, target);
        if (left == -1 && right == -1) return nums.length;
        return nums.length-(right-left+1);
    }
}