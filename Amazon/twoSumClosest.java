import java.io.*;
import java.util.*;

// closest two sum < target, return pair

class Solution {
  public static void main(String[] args) {
    System.out.print(Arrays.toString(twoSumClosest(new int[]{1,3,4,5,2}, 8)));
  }
  // 双指针 O(n)
  public static int[] twoSumClosest(int[] nums, int target) {
    if (nums == null || nums.length == 0 || target <= 0) {
      throw new RuntimeException();
    }
    
    Arrays.sort(nums);
    int[] res = new int[2];
    int min = Integer.MAX_VALUE;
    int left = 0, right = nums.length-1;
    while (left < right) {
      int sum = nums[left] + nums[right];
      if (sum <= target) {
        if (target - sum < min) {
          min = target-sum;
          res[0] = nums[left];
          res[1] = nums[right];
        }
        left++;
      } else {
        right--;
      }
    }
    return res;
  }
}