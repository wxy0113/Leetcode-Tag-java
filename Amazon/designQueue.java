import java.io.*;
import java.util.*;

// Design Circular Queue
// Design your implementation of the circular queue. The circular queue is a linear data structure in which the operations are performed based on FIFO (First In First Out) principle and the last position is connected back to the first position to make a circle. It is also called "Ring Buffer".

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
}

// 1. Using Linked List
class MyCircularQueue {
    class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
            this.next = next;
        }
    }
    Node head;
    Node tail;
    int capacity;
    int num;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        capacity = k;
        head = tail = null;
        num = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (num == capacity) return false;
        num++;
        if (head == null) {
            head = new Node(value);
            tail = head;
        } else {
            tail.next = new Node(value);
            tail = tail.next;
            tail.next = head;
        }
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (num == 0) return false;
        num--;
        if (head.next == null || head.next == head) {
            head = null;
            tail = head;
        } else {
            head = head.next;
            tail.next = head;
        }
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return head == null ? -1 : head.val;
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return tail == null ? -1 : tail.val;
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return num == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return num == capacity;
    }
}

// 2. Using array and %
class MyCircularQueue2 {
  final int[] queue;
  int front;
  int rear;
  int len;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue2(int k) {
        queue = new int[k];
      front = rear = -1;
      len = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) return false;
      rear = (rear+1)%queue.length;
      queue[rear] = value;
      len++;
      return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) return false;
      front = (front+1)%queue.length;
      len--;
      return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : queue[front];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : queue[rear];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == queue.length;
    }
}