import java.io.*;
import java.util.*;

// Bottom View of a Binary Tree
// Given a Binary Tree, we need to print the bottom view from left to right. 
// A node x is there in output if x is the bottommost node at its horizontal distance. 
// Horizontal distance of left child of a node x is equal to horizontal distance of x minus 1, and that of right child is horizontal distance of x plus 1.


class TreeNode {
  int val;
  TreeNode left;
  TreeNode right;
  public TreeNode(int value) {
    val = value;
    left = null;
    right = null;
  }
}

// Create new class with horizantal distance
class Node {
  TreeNode node;
  int distance;
  public Node(TreeNode node, int distance) {
    this.node = node;
    this.distance = distance;
  }
}
class Solution {
  public static void main(String[] args) {
    TreeNode root = new TreeNode(20); 
    root.left = new TreeNode(8); 
    root.right = new TreeNode(22); 
    root.left.left = new TreeNode(5); 
    root.left.right = new TreeNode(3); 
    root.right.left = new TreeNode(4); 
    root.right.right = new TreeNode(25); 
    root.left.right.left = new TreeNode(10); 
    root.left.right.right = new TreeNode(14);
    System.out.print(bottomView(root));
  }
  
  // Using TreeMap/HashMap + Queue: O(n) O(n)
  // 采用前序遍历preorder 根左右， 将不同distance的node存入map
  // 由于是前序遍历，上一层会被下一层同distance的点覆盖，左边的会被右边的覆盖
  // 若用TreeMap，则key自动排序，若用HashMap，则需要从小到大遍历keySet
  public static List<Integer> bottomView(TreeNode root) {
    if (root == null) return null;
    // Map<Integer, Integer> map = new TreeMap<>();
    Map<Integer, Integer> map = new HashMap<>();
    Queue<Node> queue = new LinkedList<>();
    
    queue.offer(new Node(root, 0));
    
    while (!queue.isEmpty()) {
      Node temp = queue.poll();
      
      int distance = temp.distance;
      
      map.put(distance, temp.node.val);
      
      if (temp.node.left != null) {
        queue.offer(new Node(temp.node.left, distance-1));
      }
      
      if (temp.node.right != null) {
        queue.offer(new Node(temp.node.right, distance+1));
      }
    }
    
    List<Integer> res = new ArrayList<>();
    
    // for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    //   res.add(entry.getValue());
    // }
    
    for (int i = Collections.min(map.keySet()); 
         i <= Collections.max(map.keySet()); i++) {
      if (map.containsKey(i)) {
        res.add(map.get(i));
      }
    }
    
    return res;
  }
}