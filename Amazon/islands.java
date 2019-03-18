class Solution {
    int[] deltaX = {0, 0, 1, -1};
    int[] deltaY = {1, -1, 0, 0};
    
    // Number of islands
    // Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. 
    // An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. 
    // You may assume all four edges of the grid are all surrounded by water.
    // Using dfs: O(n^2)
    public int numIslands(char[][] grid) {
        if (grid == null) return 0;
        
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '1') {
                    res++;
                    dfs(grid, i, j);
                }
            }
        }
        return res;
    }
    public void dfs(char[][] grid, int x, int y) {
        grid[x][y] = '0';
        for (int i = 0; i < 4; i++) {
            int newx = x+deltaX[i], newy = y+deltaY[i];
            if (!isValid(grid, newx, newy)) continue;
            dfs(grid, newx, newy);
        }
    }
    public boolean isValid(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length) return false;
        if (y < 0 || y >= grid[0].length) return false;
        if (grid[x][y] == '0') return false;
        return true;
    }
    
    
    // Number of distinct islands
    // Given a non-empty 2D array grid of 0's and 1's, 
    // an island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) 
    // You may assume all four edges of the grid are surrounded by water.
    // Count the number of distinct islands.
    // An island is considered to be the same as another if and only if one island can be translated (and not rotated or reflected) to equal the other.
    // HashSet + DFS: O(n^2)
    int ox = 0, oy = 0;
    public int numDistinctIslands(int[][] grid) {
        if (grid == null) return 0;
        Set<String> set = new HashSet<>();
        
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    ox = i;
                    oy = j;
                    String islands = dfs(grid, i, j, "");
                    set.add(islands);
                }
            }
        }
        return set.size();
    }
    public String dfs(int[][] grid, int x, int y, String s) {
        s += (x-ox)+""+(y-oy);
        grid[x][y] = 0;
        for (int i = 0; i < 4; i++) {
            int newx = x+deltaX[i], newy = y+deltaY[i];
            if (!isValid(grid, newx, newy)) continue;
            s = dfs(grid, newx, newy, s);
        }
        return s;
    }
}