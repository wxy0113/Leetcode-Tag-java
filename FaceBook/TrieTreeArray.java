import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class TrieTreeArray {
    static class TrieTree {
        class TrieNode {
            boolean isLeaf = false;
            TrieNode[] children = new TrieNode[27];
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
                if (node.children[c-'a'] == null) {
                    node.children[c-'a'] = new TrieNode();
                }
                //pre = node
                node = node.children[c-'a'];
            }
            // pre.isLeaf = true;
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
            if (node.children[c-'a'] == null) {
                node.children[c-'a'] = new TrieNode();
            }
            insertHelper(node.children[c-'a'], s, index+1);
            if (node.children[26] == null) {
                node.children[26] = new TrieNode();
            }
            insertHelper(node.children[26], s, index+1);
        }
        public boolean search(String s) {
            if (s == null || s.length() == 0 || root == null) return false;
            return dfs(root, s, 0);
        }
        public boolean dfs(TrieNode node, String s, int index) {
            if (node == null) return false;
            //if (index == s.length()) return node.isLeaf;
            //if (index == s.length()-1) return true;
            Character c = s.charAt(index);
            if (c == '.') {
                for (TrieNode t : node.children) {
                    if (dfs(t, s, index+1)) return true;
                }
            } else {
                return dfs(node.children[c-'a'], s, index+1);
            }
            return false;
        }
        // Search using '.' insert2
        public boolean search2(String s) {
            if (s == null || s.length() == 0 || root == null) return false;
            TrieNode node = root;
            for (Character c : s.toCharArray()) {
                int temp = c == '.' ? 26 : c-'a';
                if (node.children[temp] == null) return false;
                node = node.children[temp];
            }
            return node.isLeaf;
        }
    }
    public static void main(String[] args) {
        TrieTree tree = new TrieTree();
        tree.insert("bad");
        tree.insert("aad");
        System.out.println(tree.search("."));
        System.out.println(tree.search("bab"));
        System.out.println(tree.search("ba."));
    }
}