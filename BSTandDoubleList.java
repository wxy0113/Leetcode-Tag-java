import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
        left = null;
        right = null;
    }
};
class BSTandDoubleList {
    Node pre = null;
    // Tree to Double List
    public Node treeToDoublyList(Node root) {
        if (root == null) return null;
        Node dummy = new Node(0);
        pre = dummy;
        helper(root);
        pre.right = dummy.right;
        dummy.right.left = pre;
        return dummy.right;
    }
    public void helper(Node cur) {
        if (cur == null) return;
        helper(cur.left);
        pre.right = cur;
        cur.left = pre;
        pre = cur;
        helper(cur.right);
    }


    // Double List to BST
    public Node listToBST() {
        int n = countNode(pre);
        return helper2(n);
    }
    public int countNode(Node node) {
        int n = 0;
        Node temp = node;
        while (temp != null) {
            temp = temp.right;
            n++;
        }
        return n;
    }
    public Node helper2(int n) {
        if (n <= 0) return null;
        Node left = helper2(n/2);
        Node node = pre;
        node.left = left;
        pre = pre.right;
        node.right = helper2(n-n/2-1);
        return node;
    }
    public void push(int data) {
        Node node = new Node(data);
        node.right = pre;
        if (pre != null) {
            pre.left = node;
        }
        pre = node;
    }
    public void printList() {
        Node node = pre;
        while (node != null) {
            System.out.print(node.val+"->");
            node = node.right;
        }
        System.out.println("null");
    }
    public void preOrder(Node node) {
        if (node == null) return;
        System.out.print(node.val+"->");
        preOrder(node.left);
        preOrder(node.right);
    }
    public static void main(String[] args) {
        BSTandDoubleList llist = new BSTandDoubleList();

        /* Let us create a sorted linked list to test the functions
           Created linked list will be 7->6->5->4->3->2->1 */
        llist.push(7);
        llist.push(6);
        llist.push(5);
        llist.push(4);
        llist.push(3);
        llist.push(2);
        llist.push(1);

        System.out.println("Given Linked List ");
        llist.printList();

        /* Convert List to BST */
        Node root = llist.listToBST();
        System.out.println("");
        System.out.println("Pre-Order Traversal of constructed BST ");
        llist.preOrder(root);
    }
}
