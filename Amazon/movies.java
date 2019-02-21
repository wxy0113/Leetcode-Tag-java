import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] args) {
  
  }
  /**
     * @param t: the length of the flight
     * @param dur: the length of movies 
     * @return: output the lengths of two movies
     */
    public int[] aerial_Movie(int t, int[] dur) {
        // Write your code here
        Arrays.sort(dur);
        int[] res = new int[2];
        int left = 0, right = dur.length-1, max = 0;
        while (left < right) {
            int sum = dur[left] + dur[right];
            if (sum+30 > t) {
                right--;
            } else {
                if (sum > max) {
                    max = sum;
                    res[0] = dur[left];
                    res[1] = dur[right];
                }
                left++;
            }
        }
        return res;
    }
}