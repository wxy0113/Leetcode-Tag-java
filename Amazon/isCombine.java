import java.io.*;
import java.util.*;

//判断一个字符串是不是另外两个字符串的乱序组合

class Solution {
  public static void main(String[] args) {
    System.out.println(isCombine("aaaa","aa", "aa"));
  }
  
  // Using DFS
  static int step = 0;
  public static boolean isCombine(String s, String c1, String c2) {
    if (s == null || c1 == null || c2 == null) return false;
    
    boolean can = dfs(s, 0, c1, 0, c2, 0);
    System.out.println(step);
    return can;
  }
  
  public static boolean dfs(String s, int index,
                            String c1, int i1,
                            String c2, int i2) {
    step++;
    if (index == s.length()) {
      return i1 == c1.length() && i2 == c2.length();
    }
    //if (i1 == c1.length() && i2 == c2.length()) return false;
    if (i1 == c1.length()) return s.substring(index).equals(c2.substring(i2));
    if (i2 == c2.length()) return s.substring(index).equals(c1.substring(i1));
    boolean can = false;
    if (i1 < c1.length() && c1.charAt(i1) == s.charAt(index)) {
      can = dfs(s, index+1, c1, i1+1, c2, i2);
    }
    if (i2 < c2.length() && c2.charAt(i2) == s.charAt(index)) {
      can = dfs(s, index+1, c1, i1, c2, i2+1);
    }
    return can;
  }
}