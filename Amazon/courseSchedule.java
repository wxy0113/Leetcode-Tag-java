import java.io.*;
import java.util.*;

// Course Schedule II
// There are a total of n courses you have to take, labeled from 0 to n-1.

// Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

// Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

// There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

class Solution {
  public static void main(String[] args) {
  }
  
  // Course Schedule O(n)
  // Using Topological Sort
  // First using hashmap to store the graphy, int[] to store indegree
  // Then using BFS with queue
  public static int[] canFinish(int n, int[][] pre) {
    if (n < 1 || pre.length == 0 || pre[0].length == 0) return null;
    
    Map<Integer, List<Integer>> map = new HashMap<>();
    int[] cnt = new int[n];
    for (int i = 0; i < pre.length; i++) {
      if (!map.containsKey(pre[i][1])) {
        map.put(pre[i][1], new ArrayList<>());
      }
      map.get(pre[i][1]).add(pre[i][0]);
      cnt[pre[i][0]]++;
    }
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < cnt.length; i++) {
      if (cnt[i] == 0) {
        queue.offer(i);
      }
    }
    
    int res = 0;
    int[] order = new int[n];
    while (!queue.isEmpty()) {
      int temp = queue.poll();
      order[res++] = temp;
      if (!map.containsKey(temp)) {
        continue;
      }
      
      List<Integer> list = map.get(temp);
      for (int i = 0; i < list.size(); i++) {
        cnt[list.get(i)]--;
        if (cnt[list.get(i)] == 0) {
          queue.offer(list.get(i));
        }
      }
      map.remove(temp);
    }
    
    return res == n ? order : new int[]{};
  }
  
//   630. Course Schedule III
// There are n different online courses numbered from 1 to n. Each course has some duration(course length) t and closed on dth day. A course should be taken continuously for t days and must be finished before or on the dth day. You will start at the 1st day.

// Given n online courses represented by pairs (t,d), your task is to find the maximal number of courses that can be taken.
  
  // Using PriorityQueue:
  // First sort based on ending date;
  public int scheduleCourse(int[][] course) {
    Arrays.sort(course, new Comparator<>() {
      public int compare(int[] i1, int[] i2) {
        return i1[1]-i2[1];
      }
    });
    
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b-a);
    int time = 0;
    for (int i = 0; i < course.length; i++) {
      if (time + course[i][0] < course[i][1]) {
        time += course[i][0];
        pq.offer(course[i][0]);
      } else if (!pq.isEmpty() && pq.peek() > course[i][0]) {
        time += (course[i][0]-pq.poll());
        pq.offer(course[i][0]);
      }
    }
    return pq.size();
  }
}