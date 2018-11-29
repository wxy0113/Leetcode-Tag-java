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
        Solution sol = new Solution();
        System.out.print(sol.addOperators2("123", 6));
    }
    // General + - *
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        helper(num, target, res, new StringBuilder(), 0, 0, 0);
        return res;
    }
    public void helper(String s, int target, List<String> res,
                       StringBuilder sb, int index, long sum, long last) {
        if (index == s.length()) {
            if (sum == target) {
                res.add(sb.toString());
            }
            return;
        }
        long num = 0;
        int len = sb.length();
        for (int i = index; i < s.length(); i++) {
            num = num*10 + s.charAt(i)-'0';
            if (index == 0) {
                sb.append(num);
                helper(s, target, res, sb, i+1, num, num);
                sb.setLength(len);
            } else {
                sb.append('+').append(num);
                helper(s, target, res, sb, i+1, sum+num, num);
                sb.setLength(len);
                sb.append('-').append(num);
                helper(s, target, res, sb, i+1, sum-num, -num);
                sb.setLength(len);
                sb.append('*').append(num);
                helper(s, target, res, sb, i+1, sum-last+last*num, last*num);
                sb.setLength(len);
            }
            // Important !!!
            if (num == 0) break;
        }
    }

    // Simplified + -
    public List<String> addOperators2(String num, int target) {
        List<String> res = new ArrayList<>();
        if (num == null || num.length() == 0) return res;
        helper2(num, target, res, "", 0, 0);
        return res;
    }
    public void helper2(String s, int target, List<String> res,
                        String str, int index, long sum) {
        if (index == s.length()) {
            if (sum == target) {
                res.add(str);
            }
            return;
        }
        for (int i = index; i < s.length(); i++) {
            long num = Long.parseLong(s.substring(index, i+1));
            if (index == 0) {
                helper2(s, target, res, str+num, i+1, num);
            } else {
                helper2(s, target, res, str+"+"+num, i+1, sum+num);
                helper2(s, target, res, str+"-"+num, i+1, sum-num);
            }
            // Important !!!
            if (num == 0) break;
        }
    }
}