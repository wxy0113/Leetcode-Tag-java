import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class TrieTreeMap {
    static class TrieTree {
        class TrieNode {
            boolean isLeaf = false;
            Map<Character, TrieNode> children = new HashMap<>();
        }
        TrieNode root;
        public TrieTree() {
            root = new TrieNode();
        }
        public void insert(String s) {
            if (s == null || s.length() == 0) return;
            TrieNode node = root;
            //discard the second last character
            //TrieNode pre = root;
            for (Character c : s.toCharArray()) {
                if (!node.children.containsKey(c)) {
                    node.children.put(c, new TrieNode());
                }
                //pre = node;
                node = node.children.get(c);
            }
            //pre.isLeaf = true;
            node.isLeaf = true;
        }
        // inert '.' as a extra node in the TrieTree
        public void insert2(String s) {
            if (s == null || s.length() == 0) return;
            insertHelper(root, s, 0);
        }
        public void insertHelper(TrieNode node, String s, int index) {
            if (index == s.length()) {
                node.isLeaf = true;
                return;
            }
            Character c = s.charAt(index);
            if (!node.children.containsKey(c)) {
                node.children.put(c, new TrieNode());
            }
            insertHelper(node.children.get(c), s, index+1);
            if (!node.children.containsKey('.')) {
                node.children.put('.', new TrieNode());
            }
            insertHelper(node.children.get('.'), s, index+1);
        }
        public boolean search(String s) {
            if (s == null || s.length() == 0 || root == null) return false;
            return dfs(root, s, 0);
        }
        public boolean dfs(TrieNode node, String s, int index) {
            if (node == null) return false;
            if (index == s.length()) return node.isLeaf;
            //if (index == s.length()-1) return true;
            Character c = s.charAt(index);
            if (c == '.') {
                for (Character t : node.children.keySet()) {
                    if (dfs(node.children.get(t), s, index+1)) return true;
                }
            } else {
                if (node.children.containsKey(c)) {
                    return dfs(node.children.get(c), s, index+1);
                }
            }
            return false;
        }
        // Search using '.' insert2
        public boolean search2(String s) {
            if (s == null || s.length() == 0 || root == null) return false;
            TrieNode node = root;
            for (Character c : s.toCharArray()) {
                if (!node.children.containsKey(c)) return false;
                node = node.children.get(c);
            }
            return node.isLeaf;
        }
    }
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert2("bad");
        tree.insert2("aad");
        System.out.println(tree.search2("aad"));
        System.out.println(tree.search2("bab"));
        System.out.println(tree.search2("ba."));
    }
}
