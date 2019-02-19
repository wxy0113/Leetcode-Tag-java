import java.io.*;
import java.util.*;


// K-Substring with K different characters
// Given a string S and an integer K.
// Calculate the number of substrings of length K and containing K different characters

// 记录每个字符出现的位置，若(left, i]的间距大于等于K，说明其中存在以i为结尾的子串满足条件
class KSubstring {
  public static void main(String[] args) {
    System.out.print(KSubstring("abcc", 3));
  }
  public static int KSubstring(String stringIn, int K) {
    if (K <= 0 || stringIn == null || stringIn.length() == 0) return 0;
    int[] cnt = new int[256];
    for (int i = 0; i < cnt.length; i++) {
      cnt[i] = -1;
    }
    
    int left = 0;
    Set<String> set = new HashSet<>();
    for (int i = 0; i < stringIn.length(); i++) {
      char c = stringIn.charAt(i);
      if (cnt[c] != -1) {
        left = Math.max(cnt[c]+1, left);
      }
      
      cnt[c] = i;
      if (i-left+1 >= K) {
        set.add(stringIn.substring(i-K+1, i+1)); 
      }
    }
    
    List<String> list = new ArrayList<>(set);
    System.out.println(list);
    
    return set.size();
  }
}