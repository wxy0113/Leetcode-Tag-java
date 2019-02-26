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
// Kth Smallest Element in a BST
// Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

// Note: 
// You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
class Solution {
  
  // O(n)
  // 首先遍历所有节点，统计每个节点的子节点数， 然后使用quick select查找
  // 如果该节点的右子树的子节点数 = k-1 即找到
  // 如果大于k， 则在左子树中找第k小
  // 如果大于k， 则在右子树中找第k-left-1小
    public int kthSmallest(TreeNode root, int k) {
        Map<TreeNode, Integer> map = new HashMap<>();
        countNodes(map, root);
        return findKth(root, map, k);
    }
    public int countNodes(Map<TreeNode, Integer> map, TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = countNodes(map, root.left);
        int right = countNodes(map, root.right);
        map.put(root, left+right+1);
        return left+right+1;
    }
    public int findKth(TreeNode root, Map<TreeNode, Integer> map, int k) {
        if (root == null) return 0;
        int left = root.left == null ? 0 : map.get(root.left);
        
        if (left+1 == k) {
            return root.val;
        } else if (left < k) {
            return findKth(root.right, map, k-left-1);
        } else {
            return findKth(root.left, map, k);
        }
    }
  
  // O(h+k) h为树的高度
  // 用stack进行中序遍历，首先找到最左边的子叶节点，由此向上找k次，即找到结果
  public int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        
        for (int i = 1; i < k; i++) {
            TreeNode temp = stack.peek();
            
            if (temp.right != null) {
                temp = temp.right;
                while (temp != null) {
                    stack.push(temp);
                    temp = temp.left;
                }
            } else {
                temp = stack.pop();
                
                while (!stack.isEmpty() && stack.peek().right == temp) {
                    temp = stack.pop();
                }
            }
        }
        
        return stack.peek().val;
    }
}