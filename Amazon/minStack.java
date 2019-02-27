import java.io.*;
import java.util.*;

// Min Stack
// Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

// push(x) -- Push element x onto stack.
// pop() -- Removes the element on top of the stack.
// top() -- Get the top element.
// getMin() -- Retrieve the minimum element in the stack.

// Using stack and int
// Each time when update min, push old min and new min into stack
// when pop value equals min, then pop again to update min
class MinStack {
  Stack<Integer> stack;
  int min;
  public MinStack() {
    stack = new Stack<>();
    min = Integer.MAX_VALUE;
  }
  
  public void push(int val) {
    if (val <= min) {
      stack.push(min);
      min = val;
    }
    stack.push(val);
  }
  
  public void pop() {
    if (stack.pop() == min) {
      min = stack.pop();
    }
  }
  public int getMin() {
    return min;
  }
  public int top() {
    return stack.peek();
  }
  
}
class Solution {
  public static void main(String[] args) {
  }
}