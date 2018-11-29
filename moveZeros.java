// This is a sandbox to experiment with CoderPad's execution capabilities.
// It's a temporary, throw-away session only visible to you.

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
        int[] nums = new int[] {1, 0, 3, 4, 0, 2};
        move2(nums);
        System.out.print(Arrays.toString(nums));
    }
    //One pass O(n) guarantee order
    public static void move(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i == index) index++;
                else {
                    nums[index++] = nums[i];
                    nums[i] = 0;
                }
            }
        }
        System.out.println(nums.length-index);
    }
    //Two pointers O(n) least write
    public static void move2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int left = 0, right = nums.length-1;
        while (left < right) {
            if (nums[left] != 0) {
                left++;
                continue;
            }
            if (nums[right] == 0) {
                right--;
                continue;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        System.out.println(nums.length-left);
    }
}
