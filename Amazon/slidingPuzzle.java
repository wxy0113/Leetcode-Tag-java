import java.io.*;
import java.util.*;

// Sliding Puzzle
// On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, 
// and an empty square represented by 0.
// A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
// The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
// Given a puzzle board, return the least number of moves required so that the state of the board is solved. 
// If it is impossible for the state of the board to be solved, return -1.
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int myVal) {
    val = myVal;
    left = right = null;
  }
}
class Solution {
  public static void main(String[] args) {
    int[][] board = new int[][]{{3,2,4},{1,5,0}};
    System.out.print(slidingPuzzle(board));
  }
  
  // BFS: 用字符串进行比较判断是否完成，hashset保存防止出现环
  // 记录0所在位置可能的移动位置，采用BFS搜索
  public static int slidingPuzzle(int[][] board) {
        String target = "123450";
        
        Set<String> set = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        
        int[][] dirs = new int[][]{{1,3},{0,2,4},{1,5},{0,4},{1,3,5},{2,4}};
        
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        
        Queue<String> queue = new LinkedList<>();
        String s = sb.toString();
        queue.offer(s);
        set.add(s);
        
        int steps = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                s = queue.poll();
                if (s.equals(target)) return steps;
                int zero = s.indexOf('0');
                for (int dir : dirs[zero]) {
                    String temp = swap(s, zero, dir);
                    if (set.contains(temp)) continue;
                    queue.offer(temp);
                    set.add(temp);
                }
            }
            steps++;
        }
        return -1;
    }
    public static String swap(String s, int i, int j) {
        StringBuilder sb = new StringBuilder(s);
        sb.setCharAt(i, s.charAt(j));
        sb.setCharAt(j, s.charAt(i));
        return sb.toString();
    }
}