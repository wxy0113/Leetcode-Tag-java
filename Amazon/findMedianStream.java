import java.io.*;
import java.util.*;

// Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

// For example,
// [2,3,4], the median is 3

// [2,3], the median is (2 + 3) / 2 = 2.5

// Design a data structure that supports the following two operations:

// void addNum(int num) - Add a integer number from the data stream to the data structure.
// double findMedian() - Return the median of all elements so far.

class MedianFinder {
  // addNum O(logn) findMedian O(1)
  // 用两个priorityQueue 一个从小到大排 一个从大到小排， 保证两个queue相差不超过一个数
  // 则中位数即为large。peek 或者两个的平均数
  PriorityQueue<Integer> small;
  PriorityQueue<Integer> large;
    /** initialize your data structure here. */
  public MedianFinder() {
    small = new PriorityQueue<>();
    large = new PriorityQueue<>(Collections.reverseOrder());
  }
    
  public void addNum(int num) {
    large.offer(num);
    small.offer(large.poll());
    if (large.size() < small.size()) {
      large.offer(small.poll());
    }
  }
    
  public double findMedian() {
    if (large.size() == 0) return 0;
    if (large.size() == small.size()) {
      return (large.peek()+small.peek())/2.0;
    } else {
      return large.peek();
    }
  }
}
/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
class Solution {
  public static void main(String[] args) {
    MedianFinder mf = new MedianFinder();
    mf.addNum(1);
    mf.addNum(2);
    System.out.println(mf.findMedian());
    mf.addNum(3);
    System.out.println(mf.findMedian());
  }
}