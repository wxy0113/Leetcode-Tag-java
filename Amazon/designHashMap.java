import java.io.*;
import java.util.*;

Design a HashMap without using any built-in hash table libraries.

// To be specific, your design should include these functions:

// put(key, value) : Insert a (key, value) pair into the HashMap. If the value already exists in the HashMap, update the value.
// get(key): Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
// remove(key) : Remove the mapping for the value key if this map contains the mapping for the key.

  
// 横向数组 纵向链表
class MyHashMap {
    class ListNode {
        int key, val;
        ListNode next;
        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
            this.next = null;
        }
    }
    
    final ListNode[] nodes = new ListNode[10000];
    /** Initialize your data structure here. */
    public MyHashMap() {
        
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = hashCode(key);
        if (nodes[hash] == null) {
            nodes[hash] = new ListNode(-1, -1);
        }
        ListNode pre = find(nodes[hash], key);
        if (pre.next == null) {
            pre.next = new ListNode(key, value);
        } else {
            pre.next.val = value;
        }
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int hash = hashCode(key);
        if (nodes[hash] == null) return -1;
        ListNode pre = find(nodes[hash], key);
        if (pre.next == null) {
            return -1;
        } else {
            return pre.next.val;
        }
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = hashCode(key);
        if (nodes[hash] == null) return;
        ListNode pre = find(nodes[hash], key);
        if (pre.next != null) {
            pre.next = pre.next.next;
        }
    }
    
  // get hash code
    public int hashCode(int key) {
        return Integer.hashCode(key) % nodes.length;
    }
    // find in list
    public ListNode find(ListNode head, int key) {
        while (head.next != null) {
            if (head.next.key == key) {
                return head;
            }
            head = head.next;
        }
        return head;
    }
}

// 设计hashCode函数和equals函数
class Person {
  String name;
  String id;
  public Person(String myName, String myId) {
    name = myName;
    id = myId;
  }
  @Override
  // 对于String，采用hashCode：
  // s.charAt(0)*31^(n-1) + .. + s.charAt(n-1)
  // 系统自带string.hashCode()
  public int hashCode() {
    if (id == null) return 0;
    char[] cs = id.toCharArray();
    int h = 0;
    for (char c : cs) {
      h = h*31+c;
    }
    return h;
  }
  @Override
  // equals, 形参是Object形式，将它定义成具体class在进行比较
  public boolean equals(Object obj) {
    return ((Person)obj).id.equals(id);
  }
}

class Solution {
  public static void main(String[] args) {
    MyHashMap hashMap = new MyHashMap();
    hashMap.put(1, 1);          
    hashMap.put(2, 2);         
    System.out.print(hashMap.get(1));            // returns 1
    System.out.print(hashMap.get(3));            // returns -1 (not found)
    hashMap.put(2, 1);          // update the existing value
    System.out.print(hashMap.get(2));            // returns 1 
    hashMap.remove(2);          // remove the mapping for 2
    System.out.print(hashMap.get(2));            // returns -1 (not foun
  }
}