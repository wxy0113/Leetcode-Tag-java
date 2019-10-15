import java.util.*;


class Treasure {
    static int[] deltaX = new int[]{0, 0, 1, -1};
    static int[] deltaY = new int[]{1, -1, 0, 0};
    /**
     * 1. 有一个-1，0组成的board，-1是墙，0可以走，
     * 给一个cell=0的起始点，返回他上下左右四个相邻且为0的点。
     * @param board 01 board
     * @param startX start point x
     * @param startY start point y
     * @return possible neighbour point
     *
     * time: O(1) space: O(1)
     */
    public static List<int[]> treasure1(int[][] board, int startX, int startY) {
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int x = startX + deltaX[i], y = startY + deltaY[i];
            if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) continue;
            if (board[x][y] == 0) res.add(new int[]{x, y});
        }
        return res;
    }

    /**
     * 2. 有一个-1，0组成的board，-1是墙，0可以走，
     * 给一个cell=0的end point，判断是不是所有的0都可以通到end
     * @param board 01 board
     * @param endX end point X
     * @param endY end point Y
     * @return whether or not
     *
     * BFS: time: O(mn), space: O(mn)
     */
    public static boolean treasure2(int[][] board, int endX, int endY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{endX, endY});
        while (!queue.isEmpty()) {
            int[] xy = queue.poll();
            board[xy[0]][xy[1]] = 2;
            for (int i = 0 ; i < 4; i++) {
                int x = xy[0]+deltaX[i], y = xy[1]+deltaY[i];
                if (x >= 0 && x < board.length && y >= 0 && y < board[0].length
                        && board[x][y] == 0) {
                    queue.offer(new int[]{x, y});
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) return false;
            }
        }
        return true;
    }

    /**
     * 3. 有一个-1，0, 1组成的board，-1是墙，0可以走，1是宝藏，
     * 给一start point 和一个 end point，要求找到走过所有宝藏的不重复的最短路径
     * @param board 01 board
     * @param startX start point X
     * @param startY start point Y
     * @param endX end point X
     * @param endY end point Y
     * @return shortest path
     *
     * DFS+back tracking: time: O(mn), space: O(mn)
     */
    public static List<int[]> treasure3(int[][] board, int startX, int startY, int endX, int endY) {
        int num = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                num += board[i][j] == 1 ? 1 : 0;
            }
        }
        List<List<int[]>> list = new ArrayList<>();
        dfs(board, startX, startY, endX, endY, 0, num, list, new ArrayList<>());
        if (list.size() < 1) return null;
        List<int[]> shortest = list.get(0);
        for (List<int[]> l : list) {
            if (l.size() < shortest.size()) shortest = l;
        }
        return shortest;
    }
    public static void dfs(int[][] board, int x, int y, int endX, int endY,
                           int cur, int total, List<List<int[]>> paths,
                           List<int[]> curPath) {
        if (x == endX && y == endY) {
            if (cur == total) {
                List<int[]> res = new ArrayList<>(curPath);
                res.add(new int[]{endX, endY});
                paths.add(res);
                return;
            }
        }
        if (!isValid(board, x, y)) return;
        int temp = board[x][y];
        if (temp == 1) cur++;
        board[x][y] = 2;
        curPath.add(new int[]{x, y});
        for (int i = 0; i < 4; i++) {
            int newX = x+deltaX[i], newY = y+deltaY[i];
            dfs(board, newX, newY, endX, endY, cur, total, paths, curPath);
        }
        board[x][y] = temp;
        curPath.remove(curPath.size()-1);
    }
    public static boolean isValid(int[][] board, int x, int y) {
        return x >= 0 && x < board.length && y >= 0 && y < board[0].length &&
                board[x][y] != -1 && board[x][y] != 2;
    }

    public static void printList(List<int[]> list) {
        if (list == null) return;
        for (int[] arr : list) {
            System.out.println(arr[0] + ", " + arr[1]);
        }
    }

    public static void main(String[] args) {
        int[][] board = new int[][] {
                {0, -1, 0, 0},
                {0, 0, -1, 0},
                {0, 0, 0, -1}
        };
        int[][] board3 = new int[][] {
                {  1,  0,  0, 0, 0 },
                {  0, -1, -1, 0, 0 },
                {  0,  0,  0, 1, 0 },
                { -1,  0,  0, 0, 0 },
                {  0,  1, -1, 0, 0 },
                {  0,  0,  0, 0, 0 }
        };

        System.out.println(treasure1(board, 1, 1).size());
        System.out.println(treasure2(board, 1, 1));
        printList(treasure3(board3, 5, 0, 0, 4));
    }

}