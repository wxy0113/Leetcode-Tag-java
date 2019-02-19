// 1. Longest Palindromic Substring
// Given a string s, find the longest palindromic substring in s.
// You may assume that the maximum length of s is 1000.
import java.io.*;
import java.util.*;

class longestPalinSub {
    public static void main(String[] args) {
        System.out.print(longestPalindrome2("cbbd"));
    }

    // 1. 中心枚举法: O(n^2)
    public static String longestPalindrome1(String s) {
        if (s == null || s.length() == 0) return s;
        int max = 0; // Longest Palindromic Substring length
        int start = 0;   // longest substring starting index
        int len = 0;     // palindrome length

        for (int i = 0; i < s.length(); i++) {
            // Case1: odd length   cbabc
            len = findPalindrome(s, i, i);
            if (len > max) {
                max = len;
                start = i-len/2;
            }

            // Case2: even length  cbbc
            len = findPalindrome(s, i, i+1);
            if (len > max) {
                max = len;
                start = i-(len/2-1);
            }
        }

        return s.substring(start, start+max);
    }
    public static int findPalindrome(String s, int left, int right) {
        int len = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) == s.charAt(right)) {
                len += left == right ? 1 : 2;
                left--;
                right++;
            } else {
                break;
            }
        }
        return len;
    }

    // 2. Manancher's Algorithm(马拉车算法) O(n)
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0) return s;

        // abd -> #a#b#d#   ab -> #a#b#:convert any string into odd length
        String str = generateStr(s);
        int[] palin = new int[str.length()];

        int mid = 0, max = 1;
        palin[0] = 1;
        for (int i = 1; i < str.length(); i++) {
            int len = 1;
            if (mid + max > i) {
                int mirrorOfI = mid - (i-mid);
                len = Math.min(palin[mirrorOfI], mid+max-i);
            }

            while (i-len >= 0 && i+len < str.length()) {
                if (str.charAt(i-len) != str.charAt(i+len)) {
                    break;
                }
                len++;
            }

            if (len > max) {
                max = len;
                mid = i;
            }

            palin[i] = len;
        }

        max--;
        int start = (mid-1)/2 - (max-1)/2;
        return s.substring(start, start+max);

    }
    public static String generateStr(String s) {
        String res = "#";
        for (int i = 0; i < s.length(); i++) {
            res += s.charAt(i);
            res += "#";
        }
        return res;
    }
}