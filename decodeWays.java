// This is a sandbox to experiment with CoderPad's execution capabilities.
// It's a temporary, throw-away session only visible to you.

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static void main(String[] args) {
    }
    // General Case
    public int decodeWays(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] dp = new int[len+1];
        dp[len] = 1;
        dp[len-1] = s.charAt(len-1) == '0' ? 0 : 1;
        for (int i = len-2; i >= 0; i--) {
            if (s.charAt(i) == '0') continue;
            if (Integer.parseInt(s.substring(i, i+2)) <= 26) {
                dp[i] += dp[i+2];
            }
            dp[i] += dp[i+1];
        }
        return dp[0];
    }
    // Contains '*': '*' represents 1-9
    public int decodeWays2(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        long[] dp = new long[len+1];
        dp[len] = 1;
        dp[len-1] = s.charAt(len-1) == '*' ? 9 : s.charAt(len-1) == '0' ? 0 : 1;
        for (int i = len-2; i >= 0; i--) {
            char c1 = s.charAt(i), c2 = s.charAt(i+1);
            if (c1 == '0') continue;
            if (c1 == '*') {
                dp[i] += 9*dp[i+1];
            } else {
                dp[i] += dp[i+1];
            }

            if (c1 == '*') {
                if (c2 == '*') {
                    dp[i] += 15*dp[i+2];
                } else if (c2 <= '6') {
                    dp[i] += 2*dp[i+2];
                } else {
                    dp[i] += dp[i+2];
                }
            } else if (c1 == '1' || c1 == '2') {
                if (c2 == '*') {
                    dp[i] += c1 == '1' ? 9*dp[i+2] : 6*dp[i+2];
                } else if (c2 <= '6') {
                    dp[i] += dp[i+2];
                } else {
                    dp[i] += c1 == '1' ? dp[i+2] : 0;
                }
            }
            //dp[i] %= 1000000007;
        }
        return (int)dp[0];
    }
}