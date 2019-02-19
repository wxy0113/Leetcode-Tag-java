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
    public List<List<Integer>> vertical(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, List<Integer>> map = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        Queue<TreeNode> tq = new LinkedList<>();
        queue.offer(0);
        tq.offer(root);
        while (!tq.isEmpty()) {
            TreeNode node = tq.poll();
            int index = queue.poll();
            map.putIfAbsent(index, new ArrayList<>());
            map.get(index).add(node.val);
            if (node.left != null) {
                queue.offer(index-1);
                tq.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(index+1);
                tq.offer(node.right);
            }
        }
        for (int i = Collections.min(map.keySet());
             i <= Collections.max(map.keySet()); i++) {
            res.add(map.get(i));
        }
        return res;
    }
}