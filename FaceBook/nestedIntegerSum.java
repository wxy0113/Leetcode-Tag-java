// This is a sandbox to experiment with CoderPad's execution capabilities.
// It's a temporary, throw-away session only visible to you.

import java.io.*;
import java.util.*;

/*
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

class Solution {
    public static void main(String[] args) {
    }
    // Recurrsive
    public static int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    public static int helper(List<NestedInteger> list, int depth) {
        if (list == null || list.size() == 0) return 0;
        int sum = 0;
        for (NestedInteger ni : list) {
            if (ni.isInteger()) {
                sum += ni.getInteger()*depth;
            } else {
                sum += helper(list.getList(), depth+1);
            }
        }
        return sum;
    }
    //Interative
    public static int depthSum2(List<NestedInteger> nestedList) {
        if (nestedList == null || nestedList.size() == 0) return 0;
        int sum = 0;
        Queue<NestedInteger> queue = new LinkedList();
        for (NestedInteger i : nestedList) {
            queue.offer(i);
        }
        int depth = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger temp = queue.poll();
                if (temp.isInteger()) {
                    sum += temp.getInteger*depth;
                } else {
                    for (NestedInteger n : temp.getList())
                        queue.offer(n);
                }
            }
            depth++;
        }
        return sum;
    }
    //Follow up: output sum String
    // [[1, 1], 2, [1, 1]] = (1 + 1) * 2 + 2 + (1 + 1) * 2
    public int depthSum(List<NestedInteger> nestedList) {
        return helper(nestedList, 1);
    }
    public int helper(List<NestedInteger> list, int depth) {
        if (list == null || list.size() == 0) return 0;
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            NestedInteger temp = list.get(i);
            if (i == 0 && depth > 1) {
                System.out.print("(");
            }
            if (i > 0) {
                System.out.print("+");
            }
            if (temp.isInteger()) {
                sum += temp.getInteger()*depth;
                System.out.print(temp.getInteger());
            } else {
                sum += helper(temp.getList(), depth+1);
            }
            if (depth > 1 && i == list.size()-1) {
                System.out.print(")*"+depth);
            }
        }
        return sum;
    }
}
