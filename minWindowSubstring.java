import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class minWindowSubstring {
    public static void main(String[] args) {
        minWindowSubstring sol = new minWindowSubstring();
        System.out.print(sol.minWindow2("acedbg", "xcbe"));
    }
    public String minWindow(String s, String t) {
        if (s == null || t == null) return null;
        Map<Character, Integer> map = new HashMap<>();
        for (Character c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        int left = 0, min = Integer.MAX_VALUE, count = map.size(), pos = 0;
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c, 0)-1);
            if (map.get(c) == 0) count--;
            while (left < right && map.get(s.charAt(left)) < 0) {
                map.put(s.charAt(left), map.get(s.charAt(left))+1);
                left++;
            }
            if (count == 0) {
                if (right - left + 1 < min) {
                    min = right-left+1;
                    pos = left;
                }
            }
        }
        if (min > s.length()) return "";
        return s.substring(pos, pos+min);
    }
    public String minWindow2(String s, String t) {
        if (s == null || t == null) return null;
        int[] td = new int[128];
        for (Character c : t.toCharArray()) {
            td[c] ++;
        }
        // count to t.length(), count = 1 if allow one different character
        int count = 0;
        // count to 0;
        // for (int i : td) {
        //   count += i == 0 ? 0 : 1;
        // }
        int left = 0, min = Integer.MAX_VALUE, pos = 0;
        for (int i = 0; i < s.length(); i++) {
            td[s.charAt(i)] --;
            if (td[s.charAt(i)] >= 0) count ++;
            while (count == t.length()) {
                if (i-left+1 < min) {
                    min = i-left+1;
                    pos = left;
                }
                td[s.charAt(left)]++;
                if (td[s.charAt(left)] > 0) count--;
                left++;
            }
            // if (td[s.charAt(i)] == 0) count--;
            // while (left < i && td[s.charAt(left)] < 0) {
            //   td[s.charAt(left)] ++;
            //   left++;
            // }
            // if (count == 0 && i-left+1 < min) {
            //   min = i-left+1;
            //   pos = left;
            // }
        }
        if (min > s.length()) return "";
        return s.substring(pos, pos+min);
    }
}
