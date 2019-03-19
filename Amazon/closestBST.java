import java.io.*;
import java.util.*;

// Find smallest TreeNode larger than or equal to p node
class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int myVal) {
    val = myVal;
    left = right = null;
  }
}
class Solution {
  public static void main(String[] args) {
    TreeNode t1 = new TreeNode(50);
    TreeNode t2 = new TreeNode(30);
    TreeNode t3 = new TreeNode(40);
    TreeNode t4 = new TreeNode(47);
    TreeNode t5 = new TreeNode(46);
    
    t1.left = t2;
    t2.right = t3;
    t3.right = t4;
    t4.left = t5;
    
    System.out.print(inorder(t1, new TreeNode(45)).val);
  }
  
  // 二叉树遍历：若大于，则记录父节点并向右循环
  //             若小于，则向右子树找至子叶节点值与记录值比较
  public static TreeNode inorder(TreeNode root, TreeNode p) {
    TreeNode pre = null;
    while (root != null) {
      if (root.val == p.val) {
        return p;
      } else if (root.val > p.val) {
        pre = root;
        root = root.left;
      } else {
        if (root.right == null) {
          return pre;
        }
        root = root.right;
        while (root.left != null && root.right != null) {
          if (root.val < p.val) {
            root = root.right;
          } else {
            pre = (pre.val < root.val) ? pre : root;
            root = root.left;
          }
          if (root == null) break;
        }
      }
    }
    return pre;
  }
}