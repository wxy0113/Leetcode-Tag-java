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
        String s = "abb";
        String t = "a*bb";
        Solution sol = new Solution();
        System.out.print(sol.isMatch2(s, t));
    }

    // DFS
    public boolean isMatch(String s, String t) {
        if (s.length() == 0) return isEmpty(t);
        if (t.length() == 0) return s.length() == 0;
        char s1 = s.charAt(0);
        char t1 = t.charAt(0);
        char t2 = '0';

        if (t.length() > 1) t2 = t.charAt(1);

        if (t2 == '*') {
            if (compareChar(s1, t1)) {
                //     aac - a*c                     aaa - a*a
                return isMatch(s.substring(1), t) || isMatch(s, t.substring(2));
            } else {
                return isMatch(s, t.substring(2));
            }
        } else {
            if (compareChar(s1, t1)) {
                return isMatch(s.substring(1), t.substring(1));
            } else {
                return false;
            }
        }
    }
    public boolean compareChar(char c1, char c2) {
        return c1 == c2 || c2 == '.';
    }
    public boolean isEmpty(String s) {
        if (s.length()%2 != 0) return false;
        for (int i = 1; i < s.length(); i+=2) {
            if (s.charAt(i) != '*') return false;
        }
        return true;
    }

    // DP
    public boolean isMatch2(String s, String t) {
        if (s.equals(t)) return true;
        if (s == null || t == null) return false;

        boolean[][] dp = new boolean[s.length()+1][t.length()+1];
        dp[0][0] = true;

        for (int i = 1; i <= t.length(); i++) {
            if (i >= 2 && t.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }


        for (int i = 1; i <= s.length(); i++) {
            char s1 = s.charAt(i-1);
            for (int j = 1; j <= t.length(); j++) {
                char t1 = t.charAt(j-1);
                if (s1 == t1 || t1 == '.') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (t1 == '*') {
                    if (j >= 2 && (s1 == t.charAt(j-2) || t.charAt(j-2) == '.')) {
                        //          abc - abcd*   abccc - adc*
                        dp[i][j] = (dp[i][j-2] || dp[i-1][j]);
                    } else {
                        //         abc - adcd*
                        dp[i][j] = dp[i][j-2];
                    }
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}