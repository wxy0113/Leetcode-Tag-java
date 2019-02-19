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
class Solution {
    public static void main(String[] args) {
    }

    // Traverse On
    public List<String> btPath(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, "");
        return res;
    }
    public void helper(List<String> res, TreeNode root, String s) {
        if (root.left == null && root.right == null) {
            res.add(s+root.val);
            return;
        }
        if (root.left != null) helper(res, root.left, s+"->"+root.val);
        if (root.right != null) helper(res, root.right, s+"->"+root.val);
    }

    // Divide and Conquer On
    public List<String> btPath2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        List<String> left = btPath2(root.left);
        List<String> right = btPath2(root.right);

        for (String s : left) {
            res.add(root.val+"->"+s);
        }

        for (String s : right) {
            res.add(root.val+"->"+s);
        }

        if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
        }
        return res;
    }

    // BFS
    public List<String> btPath3(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> path = new LinkedList<>();

        queue.offer(root);
        path.offer(String.valueOf(root.val));

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            String s = path.poll();

            if (node.left == null && node.right == null) res.add(s);
            if (node.left != null) {
                queue.offer(node.left);
                path.offer(s+"->"+node.left.val);
            }
            if (node.right != null) {
                queue.offer(node.right);
                path.offer(s+"->"+node.right.val);
            }
        }
        return res;
    }
}
