import java.util.*;

public class wordSearch {
    /**
     * 1. Find word int a character pattern, only right and down direction
     *
     * DFS+back tracking: time: O(mn), space: O(s.length)
     *
     * @param board character board
     * @param s string word
     */
    public static void wordSearch(char[][] board, String s) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == s.charAt(0)) {
                    List<String> path = new ArrayList<>();
                    if (dfs(board, s, path, 0, i, j)) {
                        for (String p : path) {
                            System.out.print(p+" -> ");
                        }
                    }
                }
            }
        }
    }
    public static boolean dfs(char[][] board, String s, List<String> path, int index, int x, int y) {
        if (index == s.length()) {
            return true;
        }
        if (!isValid(board, x, y)) return false;
        if (board[x][y] != s.charAt(index)) return false;
        char temp = board[x][y];
        board[x][y] = '*';
        path.add(String.valueOf(x)+", "+String.valueOf(y));

        if (dfs(board, s, path, index+1, x+1, y)) return true;
        if (dfs(board, s, path, index+1, x, y+1)) return true;

        board[x][y] = temp;
        path.remove(path.size()-1);
        return false;
    }
    static boolean isValid(char[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length && board[x][y] != '*';
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        wordSearch(board, "CES");
    }
}
