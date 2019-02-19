/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
import java.util.*;
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
}
class Codec {

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        List<Integer> list = new LinkedList<>();
        encode(root, list);
        StringBuilder sb = new StringBuilder();
        ListIterator iter = list.listIterator();
        while (iter.hasNext()) {
            sb.append(iter.next()).append(',');
        }
        return sb.toString();
    }
    public void encode(Node node, List<Integer> list) {
        if (node == null) return;
        list.add(node.val);
        list.add(node.children.size());
        for (Node next : node.children) {
            encode(next, list);
        }
    }


    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] strs = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(strs));
        return decode(queue);
    }
    public Node decode(Queue<String> queue) {
        Node node = new Node();
        node.val = Integer.parseInt(queue.poll());
        int size = Integer.parseInt(queue.poll());
        node.children = new ArrayList<Node>(size);
        for (int i = 0; i < size; i++) {
            node.children.add(decode(queue));
        }
        return node;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));