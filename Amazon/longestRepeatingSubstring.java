import java.io.*;
import java.util.*;

// 求一个字符串的最长重复子串
// Input："I like apple, I like banana." Output: "I like"
// Input: "banana" Output: "ana"

class Solution {
  public static void main(String[] args) {
    System.out.print(longestRepeatingSubstring("banana"));
  }
  
  // O(n^2) O(1)
  // 两个指针，其中的间隔从1一直到s.length-1， 分别统计以j和j+i开始的相同字符串的长度
  public static String longestRepeatingSubstring(String s) {
    if (s == null) return null;
    
    int len = 0, start = -1;
    int k = 0;
    for (int i = 1; i < s.length()-1; i++) {
      for (int j = 0; j < s.length()-i; j++) {
        if (s.charAt(j) == s.charAt(j+i)) {
          k++;
        } else {
          k = 0;
        }
        
        if (k > len) {
          len = k;
          start = j-k+1;
        }
      }
    }
    
    return start == -1 ? null : s.substring(start, start+len);
  }
}