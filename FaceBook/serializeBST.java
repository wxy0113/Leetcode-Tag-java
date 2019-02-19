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
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }
    public void encode(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("null,");
        } else {
            sb.append(root.val).append(',');
            encode(root.left, sb);
            encode(root.right, sb);
        }
    }
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return decode(queue);
    }
    public TreeNode decode(Queue<String> queue) {
        String s = queue.poll();
        if (s.equals("null")) return null;
        TreeNode node = new TreeNode(Integer.parseInt(s));
        node.left = decode(queue);
        node.right = decode(queue);
        return node;
    }
}
class serializeBST {
    public static void main(String[] args) {
        String s = "1,2,3,null,null,null,null";
        Codec c = new Codec();
        System.out.print(c.serialize(c.deserialize(s)));
    }
}