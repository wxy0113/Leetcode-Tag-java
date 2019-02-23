import java.io.*;
import java.util.*;

// 如下：假设有N个连续的灯泡，序号为1-N，每次点亮其中一个，
// 如果这个灯泡之前（就是序号比它小）的所有灯泡都已经点亮，那么这个灯泡就会顺便闪一下。
// 现在给出点亮灯泡的顺序，问中间闪了几次

class Solution {
  public static void main(String[] args) {
    int[] arr = new int[]{5, 1, 3, 2, 4};
    System.out.print(countFlash(arr));
  }
  
  // O(n) O(1)
  // 从后向前遍历，notOnMin 记录右边还未点亮的最小值，
  // 若arr[i]小于最小值，说明之前的都已点亮，闪； 否则说明之前的有未点亮的，不闪
  public static int countFlash(int[] arr) {
    if (arr == null || arr.length == 0) {
      throw new RuntimeException();
    }
    
    int res = 0;
    int notOnMin = Integer.MAX_VALUE;
    
    for (int i = arr.length-1; i >= 0; i--) {
      if (arr[i] < notOnMin) {
        res++;
      }
      notOnMin = Math.min(notOnMin, arr[i]);
    }
    
    return res;
  }
}