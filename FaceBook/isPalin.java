import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class isPalin {
    public static void main(String[] args) {
        isPalin sol = new isPalin();
        System.out.print(sol.isPalindrome3("abac"));
    }
    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        s = s.toLowerCase();
        int left = 0;
        int right = s.length()-1;
        while (left < right) {
            if (!Character.isLetterOrDigit(s.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(s.charAt(right))) {
                right--;
                continue;
            }
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
    public boolean isPalindrome2(String s) {
        if (s == null || s.length() == 0) return true;

        s = s.trim().toLowerCase();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            while (l < r && !Character.isLetterOrDigit(s.charAt(l))) l ++;
            while (l < r && !Character.isLetterOrDigit(s.charAt(r))) r --;
            if (l < r && s.charAt(l ++) != s.charAt(r --)) return false;
        }
        return true;
    }
    public boolean isPalindrome3(String s) {
        if (s == null || s.length() == 0) return true;
        int left = 0, right = s.length()-1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) break;
            left++;
            right--;
        }
        if (left >= right) return true;
        return helper(s, left+1, right) || helper(s, left, right-1);
    }
    public boolean helper(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

}
