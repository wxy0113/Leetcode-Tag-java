import java.io.*;
import java.util.*;

// Sorting a Queue without extra space
// Given a queue with random elements, we need to sort it. 
// We are not allowed to use extra space.

class Solution {
  public static void main(String[] args) {
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(5);
    queue.offer(4);
    queue.offer(3);
    queue.offer(2);
    queue.offer(1);
    sortQueue(queue);
    System.out.print(queue);
  }
  
  
  // Selection sort:
  // 每次排一个数，共需n次排完所有
  // 每次排序中，遍历queue得出最大/小的数字的位置，再将它放到queue最后
  // （遍历queue不会改变它的顺序）
  // 时间复杂度为 O(n*2n) = O(n)
  public static void sortQueue(Queue<Integer> queue) {
    for (int i = 0; i < queue.size(); i++) {
      int index = findMin(queue, queue.size()-i);
      insertMin(queue, index);
    }
  }
  public static int findMin(Queue<Integer> queue, int sorted) {
    int min = Integer.MAX_VALUE;
    int index = -1;
    for (int i = 0; i < queue.size(); i++) {
      int cur = queue.poll();
      if (cur < min && i < sorted) {
        min = cur;
        index = i;
      }
      queue.offer(cur);
    }
    //System.out.println("min: " + min + "Index: " + index);
    return index;
  }
  public static void insertMin(Queue<Integer> queue, int index) {
    int min = -1;
    // 事先记录queue长度，以免去除最小值后导致queue长度少一中断循环
    int len = queue.size();
    for (int i = 0; i < len; i++) {
      int cur = queue.poll();
      if (i == index) {
        min = cur;
      } else {
        queue.offer(cur);
      }
      //System.out.println(queue);
    }
    queue.offer(min);
    //System.out.println(queue);
    //System.out.println("Loop finished");
  }
}