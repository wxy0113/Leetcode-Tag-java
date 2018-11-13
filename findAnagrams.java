import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class findAnagrams {
    public static void main(String[] args) {
        findAnagrams sol = new findAnagrams();
        System.out.print(sol.findAnagrams("cbaebabacd", "abc"));
    }
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] cnt = new int[26];
        int count = 0;
        for (Character c : p.toCharArray()) {
            if (++cnt[c-'a'] == 1) count++;
        }
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (--cnt[c-'a'] == 0) count--;
            if (i >= p.length()) {
                if (++cnt[s.charAt(i-p.length())-'a'] == 1) {
                    count++;
                }
            }
            if (count == 0) {
                res.add(i-p.length()+1);
            }
        }
        return res;
    }
}
