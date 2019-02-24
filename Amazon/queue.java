import java.io.*;
import java.util.*;

// Design Queue

class MyQueue {
  class Node {
    int val;
    Node next;
    public Node(int val) {
      this.val = val;
      this.next = null;
    }
  }
  
  Node head;
  Node tail;
  
  public MyQueue() {
    head = null;
    tail = head;
  }
  
  public int poll() {
    if (head == null) {
      throw new RuntimeException();
    }
    
    int res = head.val;
    head = head.next;
    return res;
  }
  
  public void add(int val) {
    if (head == null) {
      head = new Node(val);
      tail = head;
    } else {
      tail.next = new Node(val);
      tail = tail.next;
    }
  }
}

class Solution {
  public static void main(String[] args) {
    MyQueue queue = new MyQueue();
    queue.add(1);
    System.out.print(queue.poll());
    queue.add(1);
    queue.add(2);
    queue.add(3);
    System.out.print(queue.poll());
    System.out.print(queue.poll());
    System.out.print(queue.poll());
    
  }
}