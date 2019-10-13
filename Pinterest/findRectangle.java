import java.util.*;

public class findRectangle {
    /**
     * 1. 找到矩阵中由0构成的唯一长方形
     * @param matrix 由0，1构成的矩阵
     * @return 长方形的左上点和右下点坐标
     */
    public static int[] findRectangle1(int[][] matrix) {
        int[] res = new int[4];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    res[0] = i;
                    res[1] = j;
                    res[2] = i;
                    res[3] = j;
                    while (res[2] < matrix.length-1) {
                        if (matrix[res[2]+1][res[1]] == 0) {
                            res[2]++;
                        }
                        break;
                    }
                    while (res[3] < matrix[0].length-1) {
                        if (matrix[res[0]][res[3]+1] == 0) {
                            res[3]++;
                        }
                        break;
                    }
                    return res;
                }
            }
        }
        return res;
    }

    /**
     * 2. 找到01矩阵中所有的长方形
     * @param matrix 由0，1构成的矩阵
     * @return 所有长方形的左上右下点坐标
     */
    public static List<int[]> findRectangle2(int[][] matrix) {
        List<int[]> res = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    int[] temp = new int[]{i, j, i, j};
                    while (temp[2] < matrix.length-1) {
                        if (matrix[temp[2]+1][temp[1]] == 0) {
                            temp[2]++;
                        } else {
                            break;
                        }
                    }
                    while (temp[3] < matrix[0].length-1) {
                        if (matrix[temp[0]][temp[3]+1] == 0) {
                            temp[3]++;
                        } else {
                            break;
                        }
                    }
                    res.add(temp);
                    fill(temp, matrix);
                }
            }
        }

        return res;
    }
    public static void fill(int[] temp, int[][] matrix) {
        for (int i = temp[0]; i <= temp[2]; i++) {
            for (int j = temp[1]; j <= temp[3]; j++) {
                matrix[i][j] = 1;
            }
        }
    }

    /**
     * 3. 找到01矩阵中所有不规则box的形状
     * @param matrix 由0，1构成的矩阵
     * @return 所有box包含的点的坐标
     */
    public static List<List<int[]>> findRectangle3(int[][] matrix) {
        List<List<int[]>> res = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    List<int[]> shape = new ArrayList<>();
                    dfs(matrix, i, j, shape);
                    res.add(shape);
                }
            }
        }
        return res;
    }
    public static void dfs(int[][] matrix, int x, int y, List<int[]> shape) {
        if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length || matrix[x][y] == 1) return;
        shape.add(new int[]{x, y});
        matrix[x][y] = 1;
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            dfs(matrix, x+dir[0], y+dir[1], shape);
        }
    }

    public static void main(String[] args) {
        int[][] matrix1 = new int[][]{
                {1, 1, 1, 1},
                {0, 0, 1, 1},
                {0, 0, 1, 1}
        };
        System.out.println(Arrays.toString(findRectangle1(matrix1)));
        System.out.println("-----------------------");

        int[][] matrix2 = new int[][]{
                {1,1,1,1,1,1},
                {0,0,1,0,1,1},
                {0,0,1,0,1,0},
                {1,1,1,0,1,0},
                {1,0,0,1,1,1}
        };
        List<int[]> list2 = findRectangle2(matrix2);
        for (int[] arr : list2) {
            System.out.println(Arrays.toString(arr));
        }
        System.out.println("-----------------------");


        int[][] matrix3 = new int[][]{
                {1,1,1,1,1,1},
                {0,0,0,1,1,1},
                {1,1,0,1,0,0},
                {1,1,1,0,0,0},
                {1,1,1,1,1,1}
        };
        List<List<int[]>> list3 = findRectangle3(matrix3);
        for (int i = 0; i < list3.size(); i++) {
            System.out.println("Shape " + (i+1) + " path: ");
            for (int[] shape : list3.get(i)) {
                System.out.print(Arrays.toString(shape)+"->");
            }
            System.out.println();
        }
    }
}
