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
        String s = "leetcode";
        List<String> list = new ArrayList<>();
        list.add("leet");
        list.add("code");

        String ss = "pineapplepenapple";
        List<String> list2 = new ArrayList<>();
        list2.add("apple");
        list2.add("pen");
        list2.add("applepen");
        list2.add("pine");
        list2.add("pineapple");

        Solution sol = new Solution();
        System.out.print(sol.wordBreak3(s, list));
    }

    // DP Omn On
    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || s.length() == 0 || wordDict == null) return false;

        int max = 0;
        for (String str : wordDict) {
            max = Math.max(max, str.length());
        }

        int len = s.length();
        boolean[] dp = new boolean[len+1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= max && j <= i; j++) {
                if (!dp[i-j]) continue;
                if (wordDict.contains(s.substring(i-j, i))) {
                    dp[i] = true;
                }
            }
        }
        return dp[len];
    }

    // Follow up: word break2 ouput all possible results HashMap
    public List<String> wordBreak2(String s, List<String> wordDict) {
        Map<String, List<String>> map = new HashMap<>();
        return helper(map, s, wordDict);
    }
    public List<String> helper(Map<String, List<String>> map,
                               String s, List<String> wordDict) {
        if (map.containsKey(s)) {
            return map.get(s);
        }

        List<String> list = new ArrayList<>();
        if (wordDict.contains(s)) list.add(s);

        for (int i = 0; i < s.length(); i++) {
            String temp = s.substring(0, i);

            if (!wordDict.contains(temp)) {
                continue;
            } else {
                List<String> sub = helper(map, s.substring(i), wordDict);
                for (String str : sub) {
                    list.add(temp+" "+str);
                }
            }
        }

        map.put(s, list);
        return list;
    }

    // Follow up: find minimum break
    public int wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return -1;
        }
        int max = 0;
        for (String str : wordDict) {
            max = Math.max(max, str.length());
        }
        int len = s.length();
        int[] dp = new int[len+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int i = 1; i <= len; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 1; j <= max && j <= i; j++) {
                if (dp[i-j] == -1) {
                    continue;
                } else {
                    if (wordDict.contains(s.substring(i-j, i))) {
                        min = Math.min(min, dp[i-j]+1);
                    }
                }
            }
            dp[i] = min == Integer.MAX_VALUE ? -1 : min;
        }
        //System.out.print(Arrays.toString(dp));
        return dp[len]-1;
    }
}