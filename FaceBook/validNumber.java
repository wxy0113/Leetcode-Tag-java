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
        System.out.print(validNum(".0e1"));
    }
    public static boolean validNum(String s) {
        if (s == null || s.length() == 0) return false;
        // Important!!!! put a space in the end to avoid out of idnex
        s = s.trim()+" ";
        int index = 0;
        char[] c = s.toCharArray();
        if (c[index] == '-' || c[index] == '+') index++;
        int digit = 0, dot = 0;
        while (Character.isDigit(c[index]) || c[index] == '.') {
            if (Character.isDigit(c[index])) digit++;
            else dot++;
            index++;
        }
        if (dot >= 2 || digit == 0) return false;
        if (c[index] == 'e') {
            index++;
            if (c[index] == '+' || c[index] == '-') index++;
            for (; index < c.length-1; index++) {
                if (!Character.isDigit(c[index])) return false;
            }
        }
        return index == c.length-1;
    }
}