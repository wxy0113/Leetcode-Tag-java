import java.io.*;
import java.util.*;

// Top K Frequent Words
// Given a non-empty list of words, return the k most frequent elements.

// Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.

class Solution {
  public static void main(String[] args) {
    ArrayList<String> strings = new ArrayList<String>();
    strings.add("Hello, World!");
    strings.add("Welcome to CoderPad.");
    strings.add("This pad is running Java " + Runtime.version().feature());

    for (String string : strings) {
      System.out.println(string);
    }
  }
  
  // Using HashMap to count each string times, then using sort to find top K
  public List<String> topKFrequent(String[] words, int k) {
    List<String> res = new ArrayList<>();
    if (words == null || words.length == 0 || k <= 0) return res;
    
    Map<String, Integer> map = new HashMap<>();
    for (String s : words) {
      map.put(s, map.getOrDefault(s, 0)+1);
    }
    
    List<String>[] bucket = new List[words.length+1];
    
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      if (bucket[entry.getValue()] == null) {
        bucket[entry.getValue()] = new ArrayList<String>();
      }
      bucket[entry.getValue()].add(entry.getKey());
    }
    
    int index = 0;
    for (int i = words.length-1; i > 0; i--) {
      if (bucket[i] == null) continue;
      Collections.sort(bucket[i]);
      for (int j = 0; j < bucket[i].size() && j <= k; j++) {
        res.add(bucket[i].get(j));
        index++;
      }
      if (index == k) {
        return res;
      }
    }
    
    return res;
  }
}