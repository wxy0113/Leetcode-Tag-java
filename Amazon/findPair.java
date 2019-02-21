import java.io.*;
import java.util.*;

public class Solution {
    /**
     * @param a: the first list
     * @param b: the second list
     * @param x: the max sum
     * @return: the pairs whose sum are not exceed x
     */
  public static void main(String[] args) {
  
  }
  // Two Pointers: sort first, then find max a+b <= x,
  // then find again to get all pairs equal to max
    public static int[][] getAns(int[] a, int[] b, int x) {
        // Write your code here.
        if (a == null || b == null || a.length == 0 || b.length == 0) return null;
        Arrays.sort(a);
        Arrays.sort(b);
        int left = 0, right = b.length-1, max = Integer.MIN_VALUE;
        while (left < a.length && right >= 0) {
            if (a[left] + b[right] > x) {
                right--;
            } else {
                max = Math.max(max, a[left] + b[right]);
                left++;
            }
        }
        
        left = 0;
        right = b.length-1;
        List<int[]> list = new ArrayList<>();
        while (left < a.length && right >= 0) {
            if(left < a.length-1 && a[left] == a[left+1]) {
                left++; continue;
            }
            if(right > 0 && b[right] == b[right-1]) {
                right--; continue;
            }
            if (a[left] + b[right] > x) {
                right--;
            } else {
                if (a[left] + b[right] == max) {
                    list.add(new int[]{a[left], b[right]});
                }
                left++;
            }
        }
        
        int[][] res = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i)[0];
            res[i][1] = list.get(i)[1];
        }
        return res;
    }
}