import java.io.*;
import java.util.*;

// 3Sum
// Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

class Solution {
  public static void main(String[] args) {
    int[] nums = new int[]{-1,0,1,2,-1,-4};
    System.out.print(threeSum(nums));
  }
  
  // Based on Two Sum: O(n^2)
  // First sort, then apply two sum using two pointers
  public static List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
    Arrays.sort(nums);
    
    for (int i = 0; i < nums.length; i++) {
      // Avoid duplicate combinations
      if (i > 0 && nums[i] == nums[i-1]) continue;
      twoSum(res, i, nums);
    }
    
    return res;
  }
  public static void twoSum(List<List<Integer>> res, int index, int[] nums) {
    int left = index+1;
    int right = nums.length-1;
    while (left < right) {
      if (nums[left] + nums[right] == -nums[index]) {
        List<Integer> temp = new ArrayList<>();
        temp.add(index);
        temp.add(left);
        temp.add(right);
        res.add(temp);
        left++;
        right--;
        // Avoid duplicate combinations
        while (left < right && nums[left] == nums[left-1]) {
          left++;
        }
        while (left < right && nums[right] == nums[right+1]) {
          right--;
        }
      } else if (nums[left] + nums[right] > -nums[index]) {
        right--;
      } else {
        left++;
      }
    }
  }
}

// 4 Sum
// Given an array nums of n integers and an integer target, are there elements a, b, c, and d in nums such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

    // 两次循环，设立双指针，再再剩下的区间内使用双指针查找
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < nums.length; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) continue;
                int left = j+1, right = nums.length-1;
                while (left < right) {
                    if (nums[i]+nums[j]+nums[left]+nums[right] == target) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[left]);
                        temp.add(nums[right]);
                        res.add(temp);
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left-1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right+1]) {
                            right--;
                        }
                    } else if (nums[i]+nums[j]+nums[left]+nums[right] > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        
        return res;
    }
