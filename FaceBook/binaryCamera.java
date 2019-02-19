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
class binaryCamera {
    public static void main(String[] args) {
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(0);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(0);
        TreeNode n6 = new TreeNode(0);
        TreeNode n1 = new TreeNode(0);
        n1.left = n2;
        n1.right = n3;
        n2.right = n4;
        n3.left = n5;
        n4.left = n6;
        int[] res = findCamera(n1);
        System.out.print(Math.min(res[0], res[1]));
    }
    public static int[] findCamera(TreeNode node) {
        if (node == null) return new int[2];
        if (node.left == null && node.right == null) return new int[]{0,1};
        int[] res = new int[2];
        int[] left = findCamera(node.left);
        int[] right = findCamera(node.right);
        res[1] = Math.min(left[0]+right[0], Math.min(left[1]+right[0], left[0]+right[1]))+1;
        if (node.left == null) {
            res[0] = right[1];
        } else if (node.right == null) {
            res[0] = left[1];
        } else {
            res[0] = Math.min(left[0]+right[1], Math.min(left[1]+right[0], left[1]+right[1]));
        }
        //res[0] = left[1]+right[1];
        return res;
    }
}
