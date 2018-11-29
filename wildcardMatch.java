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

    // DP
    public boolean isMatch2(String s, String p) {
        if (s.equals(p)) return true;
        if (s == null || p == null) return false;

        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            if (p.charAt(i-1) == '*') dp[0][i] = dp[0][i-1];
        }
        for (int i = 1; i <= s.length(); i++) {
            char s1 = s.charAt(i-1);
            for (int j = 1; j <= p.length(); j++) {
                char p1 = p.charAt(j-1);
                if (s1 == p1 || p1 == '?') {
                    dp[i][j] = dp[i-1][j-1];
                } else if (p1 == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    // DFS
    public boolean isMatch(String s, String p) {
        if (s.length() == 0) return isEmpty(p);
        if (s.equals(p)) return true;
        if (p.length() == 0) return false;

        char s1 = s.charAt(0);
        char p1 = p.charAt(0);
        if (p1 == '*') {
            return isMatch(s, p.substring(1)) || isMatch(s.substring(1), p);
        } else {
            if (s1 == p1 || p1 == '?') {
                return isMatch(s.substring(1), p.substring(1));
            } else {
                return false;
            }
        }
    }

    // DFS + Memory
    public boolean isMatch3(String s, String p) {
        if (s == null || p == null) {
            return false;
        }

        boolean[][] memo = new boolean[s.length()][p.length()];
        boolean[][] visited = new boolean[s.length()][p.length()];
        return isMatchHelper(s, 0, p, 0, memo, visited);
    }

    private boolean isMatchHelper(String s, int sIndex,
                                  String p, int pIndex,
                                  boolean[][] memo,
                                  boolean[][] visited) {
        // 如果 p 从pIdex开始是空字符串了，那么 s 也必须从 sIndex 是空才能匹配上
        if (pIndex == p.length()) {
            return sIndex == s.length();
        }

        // 如果 s 从 sIndex 是空，那么p 必须全是 *
        if (sIndex == s.length()) {
            return allStar(p, pIndex);
        }

        if (visited[sIndex][pIndex]) {
            return memo[sIndex][pIndex];
        }

        char sChar = s.charAt(sIndex);
        char pChar = p.charAt(pIndex);
        boolean match;

        if (pChar == '*') {
            match = isMatchHelper(s, sIndex, p, pIndex + 1, memo, visited) ||
                    isMatchHelper(s, sIndex + 1, p, pIndex, memo, visited);
        } else {
            match = charMatch(sChar, pChar) &&
                    isMatchHelper(s, sIndex + 1, p, pIndex + 1, memo, visited);
        }

        visited[sIndex][pIndex] = true;
        memo[sIndex][pIndex] = match;
        return match;
    }

    private boolean charMatch(char sChar, char pChar) {
        return (sChar == pChar || pChar == '?');
    }

    private boolean allStar(String p, int pIndex) {
        for (int i = pIndex; i < p.length(); i++) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }
        return true;
    }
}
