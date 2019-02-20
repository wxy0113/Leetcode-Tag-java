import java.io.*;
import java.util.*;

// Sort a stack using a temporary stack
// Given a stack of integers, sort it in ascending order using another temporary stack

class Solution {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<>();
    stack.push(5);
    stack.push(9);
    stack.push(7);
    stack.push(1);
    stack.push(4);
    stack.push(3);
    Stack<Integer> sort = sortStack1(stack);
    while(!sort.isEmpty()) {
      System.out.println(sort.pop());
    }
  }
  
  // Sort using one stack: insert sort O(n^2)
  public static Stack<Integer> sortStack1(Stack<Integer> stack) {
    if (stack.isEmpty()) return stack;
    Stack<Integer> sort = new Stack<>();
    
    while (!stack.isEmpty()) {
      int num = stack.pop();
      while (!sort.isEmpty() && sort.peek() < num) {
        stack.push(sort.pop());
      }
      sort.push(num);
    }
    return sort;
  }
  
  // Sort using two stacks: bubble sort O(n^2)
  public static Stack<Integer> sortStack2(Stack<Integer> stack) {
    if (stack.isEmpty()) return stack;
    Stack<Integer> sort = new Stack<>();
    Stack<Integer> temp = new Stack<>();
    int size = stack.size();
    for (int i = 0 ; i < size; i++) {
      sort.push(stack.pop());
      for (int j = 0; j < size-i-1; j++) {
        int num = stack.pop();
        if (sort.peek() >= num) {
          temp.push(num);
        } else {
          temp.push(sort.pop());
          sort.push(num);
        }
      }
      while (!temp.isEmpty()) {
        stack.push(temp.pop());
      }
    }
    return sort;
  }
}