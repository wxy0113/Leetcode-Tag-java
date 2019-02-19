import java.io.*;
import java.util.*;

// Highest Five
// There are two properties in the node student id and scores, 
// to ensure that each student will have at least 5 points, 
// find the average of 5 highest scores for each person.

class Solution {
  class Record{
    public int id, score;
    public Record(int id, int score){
      this.id = id;
      this.score = score;
    }
  }
  public static void main(String[] args) {
  }
  
  // HashMap + PriorityQueue 记录每位学生前五门成绩，再用entry遍历计算
  public static Map<Integer, Double> highFive(Record[] results) {
    Map<Integer, Double> res = new HashMap<>();
    if (results == null || results.length == 0) return res;
    
    Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
    
    for (Record r : results) {
      if (!map.containsKey(r.id)) {
        map.put(r.id, new PriorityQueue<>(5));
      }
      PriorityQueue<Integer> pq = map.get(r.id);
      pq.offer(r.score);
      if (pq.size() > 5) {
        pq.poll();
      }
    }
    
    for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
      int sum = 0;
      PriorityQueue<Integer> pq = entry.getValue();
      while (!pq.isEmpty()) {
        sum += pq.poll();
      }
      res.put(entry.getKey(), (double)sum/5);
    }
    return res;
  }
}
