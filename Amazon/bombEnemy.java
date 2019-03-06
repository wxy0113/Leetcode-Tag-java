import java.io.*;
import java.util.*;

// Bomb Enemy
// Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
// The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
// Note: You can only put the bomb at an empty cell.

class Solution {
  public static void main(String[] args) {

  }
  
  // O(mn) + O(n)
  // 遍历所有点，分别统计该店所在行能炸到的最大数及该店所在列的最大数，再求和取最大值
  public int maxKilledEnemies(char[][] grid) {
    if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
    
    int m = grid.length, n = grid[0].length;
    int res = 0, row = 0;
    int[] col = new int[n];
    
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (j == 0 || grid[i][j-1] == 'W') {
          row = 0;
          for (int k = j; k < n; k++) {
            row += (grid[i][k] == 'E' ? 1 : 0);
          }
        }  
        if (i == 0 || grid[i-1][j] == 'W') {
          col[j] = 0;
          for (int k = i; k < m; k++) {
            col[j] += (grid[k][j] == 'E' ? 1 : 0);
          }
        }
        
        if (grid[i][j] == '0') {
          res = Math.max(res, row+col[j]);
        }
      }
    }
    
    return res;
  }
}