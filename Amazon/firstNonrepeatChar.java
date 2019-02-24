import java.io.*;
import java.util.*;

// First non-repeat character
// Given a string, find the first non-repeating character in it. For example, if the input string is “GeeksforGeeks”, then output should be ‘f’ and if input string is “GeeksQuiz”, then output should be ‘G’.

class Solution {
  public static void main(String[] args) {
    System.out.print(firstNonrepeat2("wwddeacc"));
  }
  
  // O(n)
  // 用hashmap去存储，若仅出现一次即记录其位置，否则设为-1，遍历hashmap去寻找不为-1的最小值。
  public static Character firstNonrepeat(String s) {
    if (s == null) return null;
    
    HashMap<Character, Integer> map = new HashMap<>();
    
    for (int i = 0; i < s.length(); i++) {
      if (map.containsKey(s.charAt(i))) {
        map.put(s.charAt(i), -1);
      } else {
        map.put(s.charAt(i), i);
      }
    }
    int first = Integer.MAX_VALUE;
    for (Character c : map.keySet()) {
      if (map.get(c) != -1) {
        first = Math.min(first, map.get(c));
      }
    }
    
    return first == Integer.MAX_VALUE ? null : s.charAt(first);
  }
  
  // O(2n)
  // Two scan
    public static Character firstNonrepeat2(String s) {
    if (s == null) return null;
    
    int[] cnt = new int[256];
    
    for (int i = 0; i < s.length(); i++) {
      cnt[s.charAt(i)]++;
    }
    for (int i = 0; i < s.length(); i++) {
      if (cnt[s.charAt(i)] == 1) {
        return s.charAt(i);
      }
    }
    return null;
  }
}