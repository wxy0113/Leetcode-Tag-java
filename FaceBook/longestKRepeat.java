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
        String s = "abcabcbb";
        Solution sol = new Solution();
        System.out.print(sol.lengthNoRepeat2(s));
    }

    // Array On O1
    public int lengthNoRepeat(String s) {
        if (s == null || s.length() == 0) return 0;

        int[] cnt = new int[256];
        int max = 0, left = 0, i = 0;
        for (; i < s.length(); i++) {
            if (++cnt[s.charAt(i)] > 1) {
                max = Math.max(max, i-left);
                while (left < i) {
                    if (--cnt[s.charAt(left++)] == 1) break;
                }
            }
        }
        return Math.max(max, i-left);
    }

    // HashSet
    public int lengthNoRepeat3(String s) {
        Set<Character> set = new HashSet<>();
        int j = 0, ans = 0;
        for (int i = 0; i < s.length(); i ++) {
            while (set.contains(s.charAt(i)))
                set.remove(s.charAt(j ++));
            set.add(s.charAt(i));
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }

    // HashMap
    public int lengthNoRepeat2(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> map = new HashMap<>();
        int left = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                left = map.get(s.charAt(i))+1;
            }
            map.put(s.charAt(i), i);
            max = Math.max(max, i-left+1);
        }
        return max;
    }

    // 395 longest substring with at leat K repeat
    public int longestSubstring(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int[] cnt = new int[256];

        for (int i = 0; i < s.length(); i++) {
            cnt[s.charAt(i)] ++;
        }

        boolean valid = true;;
        for (int i : cnt) {
            if (i > 0 && i < k) valid = false;
        }
        if (valid) return s.length();

        int max = 0;
        int left = 0, cur = 0;
        while (cur < s.length()) {
            if (cnt[s.charAt(cur)] < k) {
                max = Math.max(max, longestSubstring(s.substring(left, cur), k));
                left = cur+1;
            }
            cur++;
        }

        max = Math.max(max, longestSubstring(s.substring(left), k));
        return max;
    }

    // 340: longest substring with at most K distinct
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k <= 0) return 0;
        int[] cnt = new int[256];
        int max = 0, left = 0, i = 0, dis = 0;
        for (; i < s.length(); i++) {
            if (++cnt[s.charAt(i)] == 1) dis++;
            if (dis == k+1) {
                max = Math.max(i-left, max);
                while (left < i) {
                    if (--cnt[s.charAt(left++)] == 0) dis--;
                    if (dis == k) break;
                }
            }
        }
        return Math.max(max, i-left);
    }

    // 487:  maximum number of consecutive 1s inarray if you can flip at most one
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int max = 0, left = 0, i = 0, index = -1;
        //boolean flip = false;

        for (; i < nums.length; i++) {
            if (nums[i] == 0) {
                // if (flip) {
                //     max = Math.max(i-left, max);
                //     left = index+1;
                //     index = i;
                // } else {
                //     flip = true;
                //     index = i;
                // }
                max = Math.max(i-left, max);
                left = index+1;
                index = i;
            }
        }

        return Math.max(max, i-left);
    }
}