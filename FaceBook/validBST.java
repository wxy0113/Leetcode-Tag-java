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
    TreeNdoe left;
    TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
class Solution {
    public static void main(String[] args) {
    }
    // PreOrder
    public boolean isValidBST1(TreeNode root) {
        return helper(root, null, null);
    }
    public boolean helper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return helper(root.left, min, root) && helper(root.right, root, max);
    }
    //Inorder
    public boolean isValidBST2(TreeNode root) {
        return helper(root);
    }
    TreeNode pre;
    public boolean helper(TreeNode root) {
        if (root == null) return true;
        if (!helper(root.left)) return false;
        if (pre != null && root.val <= pre.val) return false;
        pre = root;
        return helper(root.right);
    }
    //PostOrder
    public boolean isValidBST3(TreeNode root) {
        return helper(root, null, null);
    }
    public boolean helper(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return true;
        if (!helper(root.left, min, root)) return false;
        if (!helper(root.right, root, max)) return false;
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
        return true;
    }
    //Balanced Tree
    public boolean isBalanced(TreeNode root) {
        return depth(root) != -1;
    }
    public int depth(TreeNode root) {
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) return -1;
        return Math.max(left, right)+1;
    }
    //Balanced BST
    public boolean isBalancedBST(TreeNode root) {
        return depth2(root, null, null) != -1;
    }
    public int depth2(TreeNode root, TreeNode min, TreeNode max) {
        if (root == null) return 0;
        int left = depth2(root.left, min, root);
        int right = depth2(root.right, root, max);
        if (left == -1 || right == -1 || Math.abs(left-right) > 1) return -1;
        if (min != null && root.val <= min.val) return -1;
        if (max != null && root.val >= max.val) return -1;
        return Math.max(left, right)+1;
    }
}