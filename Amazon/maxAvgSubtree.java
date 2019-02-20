import java.io.*;
import java.util.*;

// max average sum subtree (general tree, leaves not counted), return tree node
// 找到平均值最大的子树，平均值的定义是该节点加上所有儿子的值的和，
// 单个叶子不被考虑成子树（注意这个，要不有tc过不去），而且是多叉树

class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int val) {
    this.val = val;
    this.left = this.right = null;
  }
}
class ComponentNode {
  int val;
  List<ComponentNode> components;
  public ComponentNode(int val) {
    this.val = val;
    this.components = new ArrayList<ComponentNode>();
  }
}

class Solution {
  public static void main(String[] args) {
    ComponentNode node1 = new ComponentNode(20);
    ComponentNode node2 = new ComponentNode(5);
    ComponentNode node3 = new ComponentNode(5);
    node1.components.add(node2);
    node1.components.add(node3);
    Solution sol = new Solution();
    System.out.print(findSubtree2(node1).val);
  }
  
  // Binary Tree: Divide and Conquer
  static class Result {
    int sum;
    int size;
    public Result(int sum, int size) {
      this.sum = sum;
      this.size = size;
    }
  }
  static TreeNode res = null;
  static Result result = null;
  public static TreeNode findSubtree1(TreeNode root) {
    helper(root);
    return res;
  }
  public static Result helper(TreeNode root) {
    if (root == null) {
      return new Result(0, 0);
    }
    
    Result left = helper(root.left);
    Result right = helper(root.right);
    Result cur = new Result(left.sum+right.sum+root.val, left.size+right.size+1);
    
    if (res == null || result.sum*cur.size < result.size*cur.sum) {
      res = root;
      result = cur;
    }
    
    return cur;
  }
  
  // N-Array Tree
  static class ResultType{
    ComponentNode node;
    int size;
    int sum;
    public ResultType(ComponentNode node, int size, int num) {
      this.node = node;
      this.size = size;
      this.sum = sum;
    }
  }
  static ResultType type = null;
  public static ComponentNode findSubtree2(ComponentNode root) {
    if (root == null) {
      return null;
    }
    helper2(root);
    return type.node;
  }
  public static ResultType helper2(ComponentNode node) {
    if (node == null) {
      return new ResultType(null, 0, 0);
    }
    
    int sum = 0, size = 0;
    for (ComponentNode next : node.components) {
      ResultType temp = helper2(next);
      sum += temp.sum;
      size += temp.size;
    }
    
    ResultType cur = new ResultType(node, size+1, sum+node.val);
    if (type == null || type.size*cur.sum > cur.size*type.sum) {
      // Leaves not count
      if (cur.size > 1) {
        type = cur;
      }
    }
    
    return cur;
  }
}