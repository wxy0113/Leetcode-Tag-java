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
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
class GTreeNode {
    int val;
    GTreeNode[] children;
    public GTreeNode(int val, int n) {
        this.val = val;
        this.children = new GTreeNode[n];
    }
}
class Solution {
    public static void main(String[] args) {
    }
    //Binary Tree
    int max = 0;
    public int diameterBT(TreeNode root) {
        if (root == null) return 0;
        helper(root);
        return max;
    }
    public int helper(TreeNode root) {
        int sum = 0, temp = 0;
        if (root.left != null) {
            int left = helper(root.left)+1;
            sum += left;
            temp = Math.max(temp, left);
        }
        if (root.right != null) {
            int right = helper(root.right)+1;
            sum += right;
            temp = Math.max(temp, right);
        }
        max = Math.max(max, sum);
        return temp;
    }
    //General Tree : n children
    int max = 0;
    public int diameterGT(GTreeNode root) {
        if (root == null) return 0;
        helper2(root);
        return max;
    }
    public int helper2(GTreeNode root) {
        int sum = Integer.MIN_VALUE, temp = Integer.MIN_VALUE;
        for (GTreeNode node : root.children) {
            if (node == null) continue;
            int child = helper(node);
            if (child > sum) {
                temp = sum;
                sum = child;
            } else if (child > temp) {
                temp = child;
            }
        }
        if (temp >= 0) max = Math.max(max, sum+temp+2);
        if (sum >= 0) {
            max = Math.max(sum+1, max);
            return sum+1;
        }
        return 0;
    }
}
