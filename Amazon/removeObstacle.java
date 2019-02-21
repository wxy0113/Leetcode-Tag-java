import java.io.*;
import java.util.*;

//Remove Obstacle

class Solution {
  public static void main(String[] args) {
    int[][] grid = new int[][] {{1,0,0},{1,0,0},{1,9,1}};
    System.out.print(removeObstacle(3,3,grid));
  }
  static int[] deltaX = {0, 0, 1, -1};
  static int[] deltaY = {1, -1, 0, 0};
  static class Point {
    int x;
    int y;
    public Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  public static int removeObstacle(int row, int col, int[][] grid) {
    int steps = 0;
    Queue<Point> queue = new LinkedList<>();
    queue.offer(new Point(0, 0));
    
    while (!queue.isEmpty()) {
      int size = queue.size();
      for (int i = 0; i < size; i++) {
        Point cur = queue.poll();
        if (grid[cur.x][cur.y] == 9) return steps;
        grid[cur.x][cur.y] = 0;
        for (int j = 0; j < 4; j++) {
          if (isValid(grid, cur.x+deltaX[j], cur.y+deltaY[j])) {
            queue.offer(new Point(cur.x+deltaX[j], cur.y+deltaY[j]));
          }
        }
        steps++;
      }
    }
    
    return -1;
  }
  public static boolean isValid(int[][] grid, int x, int y) {
    if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
      return false;
    }
    if (grid[x][y] == 0) return false;
    return true;
  }
}