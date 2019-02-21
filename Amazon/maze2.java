import java.io.*;
import java.util.*;



public class Solution {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public static void main(String[] args) {
      
    }
    class Point {
        int x;
        int y;
        int steps;
        public Point(int x, int y, int steps) {
            this.x = x;
            this.y = y;
            this.steps = steps;
        }
    }
    public int[] delataX = {0, 0, 1, -1};
    public int[] delataY = {1, -1, 0, 0};
  
  // Maze 2:
  // When the ball stops, it could choose the next direction.
  // BFS + Memory
    public static int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        if (maze == null || maze.length == 0 || maze[0].length == 0) return -1;
        if (maze[start[0]][start[1]] == 1 ||
            maze[destination[0]][destination[1]] == 1) return -1;
        
        
        Queue<Point> queue = new LinkedList<>();
        int[][] res = new int[maze.length][maze[0].length];
        for (int i = 0; i < res.length; i++) {
            Arrays.fill(res[i], -1);
        }
        queue.offer(new Point(start[0], start[1], 0));
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                Point cur = queue.poll();
                if (res[cur.x][cur.y] != -1 && cur.steps >= res[cur.x][cur.y]) {
                  continue;
                }
                res[cur.x][cur.y] = cur.steps;
                for (int j = 0; j < 4; j++) {
                    int x = cur.x, y = cur.y, steps = cur.steps;
                    while (x >= 0 && x < maze.length &&
                           y >= 0 && y < maze[0].length &&
                           maze[x][y] == 0) {
                        x += delataX[j];
                        y += delataY[j];
                        steps++;
                    }
                    x -= delataX[j];
                    y -= delataY[j];
                    steps--;
                    queue.offer(new Point(x, y, steps));
                }
            }
        }
        return res[destination[0]][destination[1]];
    } 
}