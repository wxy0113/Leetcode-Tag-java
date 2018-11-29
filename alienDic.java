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
        String[] strs = new String[] {"wrt", "wrf", "er", "ett", "rftt"};
        System.out.print(sol.alienOrder(strs));
    }
    //Topological Search
    public String alienOrder(String[] words) {
        Map<Character, Integer> degree = new HashMap<>();
        Map<Character, Set<Character>> next = new HashMap<>();
        for (String s : words) {
            for (int i = 0; i < s.length(); i++) {
                if (!degree.containsKey(s.charAt(i))) {
                    degree.put(s.charAt(i), 0);
                    next.put(s.charAt(i), new HashSet<Character>());
                }
            }
        }
        if (!buildGraph(words, degree, next)) return "";
        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        Set<Character> set = new HashSet<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.offer(c);
                set.add(c);
                //degree.remove(c);
            }
        }
        while (!queue.isEmpty()) {
            Character c = queue.poll();
            sb.append(c);
            for (Character n : next.get(c)) {
                if (degree.containsKey(n)) {
                    degree.put(n, degree.get(n)-1);
                }
                if (degree.get(n) == 0) {
                    if (set.contains(n)) continue;
                    queue.offer(n);
                    set.add(n);
                }
            }
        }
        if (sb.length() != next.size()) return "";
        return sb.toString();
    }
    public boolean buildGraph(String[] words, Map<Character, Integer> degree, Map<Character, Set<Character>> next) {
        for (int i = 0; i < words.length-1; i++) {
            if (words[i].length() > words[i+1].length() && words[i].indexOf(words[i+1]) == 0) return false;
            int len = Math.min(words[i].length(), words[i+1].length());
            for (int j = 0; j < len; j++) {
                if (words[i].charAt(j) != words[i+1].charAt(j)) {
                    if (!next.get(words[i].charAt(j)).contains(words[i+1].charAt(j))) {
                        next.get(words[i].charAt(j)).add(words[i+1].charAt(j));
                        degree.put(words[i+1].charAt(j), degree.get(words[i+1].charAt(j))+1);
                    }
                    break;
                }
            }
        }
        return true;
    }
    //是否满足alien dictionary
    public boolean alienOrder2(String[] strs, String dict) {
        int index = 0;
        Map<Character, Integer> dic = new HashMap<>();
        for (int j = 0; j < dict.length(); j++) {
            if (dic.containsKey(dict.charAt(j))) return false;
            else {
                dic.put(dict.charAt(j), index++);
            }
        }
        for (int m = 0; m < strs.length-1; m++) {
            if (strs[m].length() > strs[m+1].length() && strs[m].startsWith(strs[m+1])) {
                return false;
            }
            int len = Math.min(strs[m].length(), strs[m+1].length());
            for (int i = 0; i < len; i++) {
                if (strs[m].charAt(i) != strs[m+1].charAt(i)) {
                    if (!dic.containsKey(strs[m].charAt(i)) ||
                            !dic.containsKey(strs[m+1].charAt(i))) return false;
                    if (dic.get(strs[m].charAt(i)) > dic.get(strs[m+1].charAt(i))) return false;
                    // Important!
                    if (dic.get(strs[m].charAt(i)) < dic.get(strs[m+1].charAt(i))) break;
                }
            }
        }
        return true;
    }

}
