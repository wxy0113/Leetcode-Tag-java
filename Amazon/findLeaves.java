import java.io.*;
import java.util.*;

// 366. Find Leaves of Binary Tree
// Given a binary tree, collect a tree's nodes as if you were doing this: Collect and remove all leaves, repeat until the tree is empty.

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
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }
  
  // Divide and Conquer O(n)
  // 由下向上，计算每个点的高度，按次序加入list中
  public static List<List<Integer>> findLeaves(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) return res;
    divide(root, res);
    return res;
  }
  public static int divide(TreeNode node, List<List<Integer>> res) {
    if (node == null) return -1;
    
    int left = divide(node.left, res);
    int right = divide(node.right, res);
    
    int cur = Math.max(left, right)+1;
    if (res.size() < cur+1) {
      res.add(new ArrayList<>());
    }
    
    res.get(cur).add(node.val);
    
    return cur;
  }
}