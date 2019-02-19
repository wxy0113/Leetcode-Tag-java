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
class TreeNodeIter {
    Stack<TreeNode> stack;
    public TreeNodeIter(TreeNode root) {
        stack = new Stack<>();
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }
    public boolean hasNext() {
        return !stack.isEmpty();
    }
    public TreeNode next() {
        if (!hasNext()) {
            return null;
        }
        TreeNode res = stack.peek();
        TreeNode cur = res;
        if (cur.right != null) {
            cur = cur.right;
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
        } else {
            cur = stack.pop();
            while (!stack.isEmpty() && stack.peek().left != cur) {
                cur = stack.pop();
            }
        }
        return res;
    }
}

class Node {
    int val;
    Node left;
    Node right;
    Node parent;
    public Node(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
        this.parent = null;
    }
}
class BST {
    Node last;
    class BSTIter implements Iterator<Node> {
        public BSTIter(Node node) {
            if (node == null) return;
            last = node;
            while (last.left != null) {
                last = last.left;
            }
        }
        @Override
        public boolean hasNext() {
            return last != null;
        }
        public Node next() {
            if (!hasNext()) return null;
            Node res = last;
            last = find(last);
            return res;
        }
        public Node find(Node node) {
            if (node == null) return null;
            if (node.right != null) {
                node = node.right;
                while (node.left != null) {
                    node = node.left;
                }
                return node;
            }
            Node father = node.parent;
            Node child = node;
            // right == child || left != child
            while (father != null && father.right == child) {
                child = father;
                father = father.parent;
            }
            return father;
        }
        Iterator<Node> iterator() {
            BSTIter iter = new BSTIter(last);
            return iter;
        }
    }
}
class BSTIterator {
    public static void main(String[] args) {
    }
}
