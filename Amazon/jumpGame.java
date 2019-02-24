import java.io.*;
import java.util.*;

// Jump Game
// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Your goal is to reach the last index in the minimum number of jumps.

class Solution {
  public static void main(String[] args) {
  }
  
  public static boolean jumpGame1(int[] nums) {
    if (nums == null) return false;
    
    int jump = nums[0];
    int index = 0;
    
    while (jump > 0) {
      if (index+jump >= nums.length-1) return true;
      jump--;
      index++;
      jump = Math.max(jump, nums[index]);
    }
    
    return false;
  }
  
  // curFar: 统计下一步能抵达的最远位置， curEnd: 统计这一步可以抵达的最远位置
  public static int jumpGame2(int[] nums) {
    if (nums == null) return -1;
    if (nums.length <= 1) return 0;
    
    int curFar = 0, curEnd = 0, step = 0;
    for (int i = 0; i < nums.length-1; i++) {
      curFar = Math.max(curFar, i+nums[i]);
      if (curEnd == i) {
        curEnd = curFar;
        step++;
      }
    }
    return curFar >= nums.length-1 ? step : -1;
  }
}