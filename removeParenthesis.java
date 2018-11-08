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
}
