import java.io.*;
import java.util.*;


// MaximumMinimumPath
// You are gonna climb mountains represented by a matrix. 
// Each integer in the matrix represents the altitude at that point. 
// You are supposed to climb from the top-left corner to the bottom-right corner 
// and only move rightward or downward in each step.
// You can have many paths to do so. 
// Each path has a minimum altitude. 
// Find the maximum among all the minimum altitudes of all paths.

class Solution {
  public static void main(String[] args) {
    int[][] matrix = new int[][]{{8, 4, 7}, {6, 5, 9}};
    System.out.print(MaximumMinimumPath2(matrix));
  }
  
  // DFS
  static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
  public static int MaximumMinimumPath1(int[][] matrix) {
    helper(matrix, 0, 0);
    return max;
  }
  public static void helper(int[][] matrix, int x, int y) {
    if (x >= matrix.length || y >= matrix[0].length) return;
    
    if (x == matrix.length-1 && y == matrix[0].length-1) {
      min = Math.min(min, matrix[matrix.length-1][matrix[0].length-1]);
      max = Math.max(max, min);
    }
    
    min = Math.min(min, matrix[x][y]);
    helper(matrix, x+1, y);
    helper(matrix, x, y+1);
  }
  
  // DP
  public static int MaximumMinimumPath2(int[][] matrix) {
    int[] dp = new int[matrix[0].length];
    dp[0] = matrix[0][0];
    for (int i = 1; i < matrix[0].length; i++) {
      dp[i] = Math.min(dp[i-1], matrix[0][i]);
    }
    
    for (int i = 1; i < matrix.length; i++) {
      dp[0] = Math.min(dp[0], matrix[i][0]);
      for (int j = 1; j < matrix[i].length; j++) {
        dp[j] = (dp[j] < matrix[i][j] && dp[j-1] < matrix[i][j])? 
          Math.max(dp[j-1], dp[j]) : matrix[i][j];
      }
    }
    return dp[dp.length-1];
  }
}