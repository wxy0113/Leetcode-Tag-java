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
  public TreeNode(int myVal) {
    val = myVal;
    left = null;
    right = null;
  }
}
class BST {
  TreeNode root;
  public BST(int val) {
    root = new TreeNode(val);
  }
  
  // Add new Node, add it as a leaf node, use recursion
  // O(h)
  public void add(int val) {
    if (root == null) {
      root = new TreeNode(val);
    }
    TreeNode node = root;
    addHelper(val, node);
  }
  private void addHelper(int val, TreeNode node) {
    if (node.val > val) {
      if (node.left == null) {
        node.left = new TreeNode(val);
        return;
      } else {
        addHelper(val, node.left);
      } 
    } else if (node.val < val) {
      if (node.right == null) {
        node.right = new TreeNode(val);
        return;
      } else {
        addHelper(val, node.right);
      }
    } else {
      throw new RuntimeException("Duplicate TreeNode with same value!");
    }
  }
  
  // Delete a node: first find the node, then handle 3 cases:
  // 1. If left is null, then replace it with right
  // 2. If right is null, then replace it with left
  // 3. if left & right are not null, then find its inorder successor(大于它的最小点)
  //    use it replace the node.
  // O(h)
  public void delete(int val) {
    root = deleteHelper(root, val);
  }
  private TreeNode deleteHelper(TreeNode node, int val) {
    if (node == null) return node;
    if (node.val > val) {
      node.left = deleteHelper(node.left, val);
    } else if (node.val < val) {
      node.right = deleteHelper(node.right, val);
    } else {
      if (node.left == null) return node.right;
      if (node.right == null) return node.left;
      
      node.val = InOrderSuccessor(root.right);
      node.right = deleteHelper(root.right, root.val);
    }
    return node;
  }
  private int InOrderSuccessor(TreeNode node) {
    while(node.left != null) {
      node = node.left;
    }
    return node.val;
  }
  
  public void printTree() {
    TreeNode node = root;
    System.out.println("In-order Traverse of BST is: ");
    print(node);
    System.out.println(" ");
  }
  private void print(TreeNode node) {
    if (node == null) return;
    print(node.left);
    System.out.print(node.val+" ");
    print(node.right);
  }
}
class Solution {
  public static void main(String[] args) {
    BST bst = new BST(5);
    bst.add(3);
    bst.add(9);
    bst.add(6);
    bst.add(8);
    bst.add(7);
    bst.printTree();
    bst.delete(5);
    bst.printTree();
  }
}