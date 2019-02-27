import java.io.*;
import java.util.*;

// Shortest Word Distance
// Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters. 
  
// Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
// Input: word1 = “coding”, word2 = “practice”
// Output: 3
// Input: word1 = "makes", word2 = "coding"
// Output: 1
  

// Using HashMap + ArrayList
// Initiate: O(n)
// Shortest: O(m+n)
class WordDistance {
    Map<String, List<Integer>> map;
    public WordDistance(String[] words) {
        map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            if (!map.containsKey(words[i])) {
                map.put(words[i], new ArrayList<>());
            }
            map.get(words[i]).add(i);
        }
    }
    public int shortest(String word1, String word2) {   
        int min = Integer.MAX_VALUE;
        List<Integer> w1 = map.get(word1);
        List<Integer> w2 = map.get(word2);
        // System.out.println(w1);
        // System.out.println(w2);
        int i1 = 0, i2 = 0;
        while (i1 < w1.size() && i2 < w2.size()) {
            min = Math.min(min, Math.abs(w1.get(i1)-w2.get(i2)));
            if (w1.get(i1) < w2.get(i2)) {
                i1++;
            } else {
                i2++;
            }
        }
        return min;
    }
}
class Solution {
  public static void main(String[] args) {
  }
  // Shortest Word Distance 1, find once
  // Using two pointers O(n)
  public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) return -1;
        int min = Integer.MAX_VALUE;
        
        int w1 = 0;
        int w2 = 0;
        
        while (w1 < words.length && w2 < words.length) {
            while (w1 < words.length && !words[w1].equals(word1)) {
                w1++;
            }
            while (w2 < words.length && !words[w2].equals(word2)) {
                w2++;
            }
            if (w1 == words.length || w2 == words.length) break;
            min = Math.min(min, Math.abs(w1-w2));
            if (w1 < w2) {
                w1++;
            } else {
                w2++;
            }
        }
        
        return min;
    }
}