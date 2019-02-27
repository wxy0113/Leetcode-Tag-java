import java.io.*;
import java.util.*;


// Add Two Numbers
// You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
  
class ListNode {
  int val;
  ListNode next;
  public ListNode(int val) {
    this.val = val;
    this.next = null;
  }
}
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
  
  // Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
  // Output: 7 -> 0 -> 8
  public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummy = new ListNode(0);
    ListNode head = dummy;
    int sum = 0;
    int cs = 0;
    while (l1 != null || l2 != null) {
      sum = cs;
      if (l1 != null) {
        sum += l1.val;
      }
      if (l2 != null) {
        sum += l2.val;
      }
      cs = sum/10;
      head.next = new ListNode(sum%10);
      head = head.next;
    }
    if (cs != 0) {
      head.next = new ListNode(cs);
    }
    
    return dummy.next;
  }
  
  // Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
  // Output: 7 -> 8 -> 0 -> 7
  // Using Stack to store two list, each result add it to the head,
  // finally change dummy node based on carry
  public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    
    while (l1 != null) {
      stack1.push(l1.val);
      l1 = l1.next;
    }
    while (l2 != null) {
      stack2.push(l2.val);
      l2 = l2.next;
    }
    
    ListNode dummy = new ListNode(0);
    ListNode head = null;
    
    int sum = 0, cs = 0;
    while (!stack1.isEmpty() || !stack2.isEmpty()) {
      sum = cs;
      if (!stack1.isEmpty()) {
        sum += stack1.pop();
      }
      if (!stack2.isEmpty()) {
        sum += stack2.pop();
      }
      
      cs = sum/10;
      ListNode temp = new ListNode(sum%10);
      dummy.next = temp;
      temp.next = head;
      head = temp;
    }
    
    if (cs != 0) {
      dummy.val = cs;
    }
    
    return dummy.val == 0 ? dummy.next : dummy;
  }
}