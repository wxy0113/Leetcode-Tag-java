import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class removeParenthesis {
    public static void main(String[] args) {
        removeParenthesis sol = new removeParenthesis();
        System.out.print(sol.remove("((((()))"));
    }

    // Stack
    public String remove(String str) {
        Stack<Integer> stack = new Stack<>();
        char[] cs = str.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    cs[i] = '.';
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            cs[stack.pop()] = '.';
        }
        return new String(cs);
    }

    // Two scan
    public String remove2(String str) {
        char[] cs = str.toCharArray();
        int left = 0, right = 0;
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '(') {
                left++;
            }
            if (cs[i] == ')') {
                if (left != 0) {
                    left--;
                } else {
                    cs[i] = '.';
                }
            }
        }
        for (int i = cs.length-1; i >= 0; i--) {
            if (cs[i] == ')') {
                right++;
            }
            if (cs[i] == '(') {
                if (right != 0) {
                    right--;
                } else {
                    cs[i] = '.';
                }
            }
        }
        return new String(cs);
    }

    // DFS output all cases
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        int[] count = countLeftRight(s);
        dfs(res, s, count[0], count[1], 0);
        return res;
    }
    public void dfs(List<String> res, String s, int left, int right, int index) {
        if (left == 0 && right == 0 && isValid(s)) {
            res.add(s);
            return;
        }
        for (int i = index; i < s.length(); i++) {
            if (i > index && s.charAt(i) == s.charAt(i-1)) continue;
            if (s.charAt(i) == '(') {
                dfs(res, s.substring(0,i)+s.substring(i+1), left-1, right, i);
            }
            if (s.charAt(i) == ')') {
                dfs(res, s.substring(0,i)+s.substring(i+1), left, right-1, i);
            }
        }
    }
    public int[] countLeftRight(String s) {
        int[] res = new int[2];
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') res[0]++;
            if (s.charAt(i) == ')') {
                if (res[0] != 0) {
                    res[0]--;
                } else {
                    res[1]++;
                }
            }
        }
        return res;
    }
    public boolean isValid(String s) {
        int[] count = countLeftRight(s);
        return count[0] == 0 && count[1] == 0;
    }
}
