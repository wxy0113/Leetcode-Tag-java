import java.io.*;
import java.util.*;

// Integer Replacement:
// Given a positive integer n and you can do operations as follow:

// If n is even, replace n with n/2.
// If n is odd, you can replace n with either n + 1 or n - 1.
// What is the minimum number of replacements needed for n to become 1?

class Solution {
  public static void main(String[] args) {
    System.out.print(integerReplacement(9));
  }
  
  // DFS + Memory Search O(n)
  public static int integerReplacement(int n) {
    Map<Integer, Integer> map = new HashMap<>();
    return helper(n, map);
  }
  public static int helper(int n, Map<Integer, Integer> map) {
    int res = 0;
    if (map.containsKey(n)) {
        return map.get(n);
    }
    if (n == 1) return 0;
    if (n%2 == 0) {
        res = helper(n/2, map)+1;
    } else {
        res = Math.min(helper((n-1), map)+1, helper(1+(n-1)/2, map)+2);
    }
    map.put(n, res);
    return res;
  }
  
  // BitManipulation O(n)
  public static int integerReplacement2(int n) {
    if(n == 1) return 0;
    int res = 0;
    while (n != 1) {
      if (n%2 == 0) {
        n = n/2;
      } else if (n == 3 || Integer.bitCount(n+1) > Integer.bitCount(n-1)) {
        n--;
      } else {
        n++;
      }
      res++;
    }
    return res;
  }
  public static int integerReplacement3(int n) {
    if(n == 1) return 0;
    int res = 0;
    while (n != 1) {
      if ((n&1) == 0) {
        n >>>= 1;
      } else if (n == 3 || ((n>>>1)&1) == 0) {
        n--;
      } else {
        n++;
      }
      res++;
    }
    return res;
  }
}