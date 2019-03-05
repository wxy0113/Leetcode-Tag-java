import java.io.*;
import java.util.*;

// Palindrome Pairs
// Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.


// Using TrieTree
class Solution {
    class TrieNode {
        Map<Character, TrieNode> children;
        int num; 
        String word;
        public TrieNode() {
            children = new HashMap<>();
            num = -1;
            word = null;
        }
    }
    class Trie {
        TrieNode root;
        public Trie() {
            root = new TrieNode();
        }
        public void insert(String s, int index) {
            if (s == null) return;
            TrieNode node = root;
            for (int i = 0; i < s.length(); i++) {
                if (!node.children.containsKey(s.charAt(i))) {
                    node.children.put(s.charAt(i), new TrieNode());
                }
                node = node.children.get(s.charAt(i));
            }
            node.word = s;
            node.num = index;
        }
        public boolean checkPalin(char[] s, int left, int right) {
            if (reverse && left > right) {
                return false;
            }
            while (left < right) {
                if (s[left++] != s[right--]) {
                    return false;
                }
            }
            return true;
        }
      // Find handle two cases:
      // 1 when there exists empty string
      // 2 abc   dddcba: when the longer string fits the later part in the trie
      //   then we need to check (0 - i) part is palindrome
        public List<List<Integer>> find(String s, int index) {
            List<List<Integer>> res = new ArrayList<>();
            char[] cs = s.toCharArray();
            TrieNode node = root;
            if (node.word != null && node.num != index && checkPalin(cs, 0, cs.length-1)) {
                res.add(reverse ? Arrays.asList(index, node.num) : Arrays.asList(node.num, index));
            }
            for (int i = cs.length-1; i >= 0; i--) {
                node = node.children.get(cs[i]);
                if (node == null) break;
                if (node.word != null && node.num != index && checkPalin(cs, 0, i-1)) {
                    res.add(reverse ? Arrays.asList(index, node.num) : Arrays.asList(node.num, index));
                }
            }
            return res;
        }
    }
    boolean reverse = false;
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> res = helper(words);
      // After Reverse, we handle 3rd case:
      // abc cbaddd: after reversing we get cba, dddabc
      // then we can apply it to find()
        for (int i = 0; i < words.length; i++) {
            words[i] = new StringBuilder(words[i]).reverse().toString();
        }
        
        reverse = true;
        
        res.addAll(helper(words));
        return res;
    }
    public List<List<Integer>> helper(String[] words) {
        List<List<Integer>> res = new ArrayList<>();
        Trie trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            trie.insert(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            res.addAll(trie.find(words[i], i));
        }
        return res;
    }
}