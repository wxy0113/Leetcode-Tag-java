import java.io.*;
import java.util.*;


// Longest Turbulent Subarray
// A subarray A[i], A[i+1], ..., A[j] of A is said to be turbulent if and only if:

// For i <= k < j, A[k] > A[k+1] when k is odd, and A[k] < A[k+1] when k is even;
// OR, for i <= k < j, A[k] > A[k+1] when k is even, and A[k] < A[k+1] when k is odd.
// That is, the subarray is turbulent if the comparison sign flips between each adjacent pair of elements in the subarray.

// Return the length of a maximum size turbulent subarray of A.

class Solution {
  public static void main(String[] args) {
  }
  
  // O(n)
  // For each A[i]
  // inc: The length of current valid sequence which ends with two increasing numbers
  // dec: The length of current valid sequence which ends with two decreasing numbers
  public static int maxTurbulenceSize(int[] A) {
    if (A == null) return 0;
    
    int inc = 1, dec = 1;
    int res = 1;
    for (int i = 0; i < A.length-1; i++) {
      if (A[i] > A[i+1]) {
        dec = inc+1;
        inc = 1;
      } else if (A[i] < A[i+1]) {
        inc = dec+1;
        dec = 1;
      } else {
        inc = dec = 1;
      }
      
      res = Math.max(res, Math.max(inc, dec));
    }
    
    return res;
  }
}