import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
  public static void main(String[] args) {
    int[] A = new int[]{2,3,4,1};
    heapSort(A);
    System.out.print(Arrays.toString(A));
  }
  
  // O(n) Shift Down
  // Build-Heap runs a loop from the index of the last internal node (heapsize/2) with height=1, to the index of root(1) with height = lg(n). 
  // Hence, Heapify takes different time for each node, which is O(h).
  // A heap of size n has at most (n/(2^(h-1))) nodes with height h
  // T(n) = Sigma(n/(2^(h-1)))*O(h), h = 0..logn
  public static void heapify(int[] A) {
    for (int i = A.length/2; i >= 0; i--) {
      shiftDown(A, i);
    }
  }
  public static void shiftDown(int[] A, int index) {
    while (index < A.length) {
      int left = 2*index+1;
      int right = 2*index+2;
      int min = index;
      
      if (left < A.length && A[left] < A[min]) min = left;
      if (right < A.length && A[right] < A[min]) min = right;
      
      if (min == index) break;
      swap(A, min, index);
      index = min;
    }
  }
  public static void swap(int[] A, int a, int b) {
    int temp = A[a];
    A[a] = A[b];
    A[b] = temp;
  }
  
  // O(nlogn) Shift Up
  public static void heapify2(int[] A) {
    for (int i = 1; i < A.length; i++) {
      shiftUp(A, i);
    }
  }
  public static void shiftUp(int[] A, int index) {
    while (index != 0) {
      int father = (index-1)/2;
      if (father >= 0 && A[father] > A[index]) {
        swap(A, father, index);
        index = father;
      } else {
        break;
      }
    }
  }
  
  public static void  heapSort(int[] A) {
    heapify(A);
    System.out.println(Arrays.toString(A));
    for (int i = A.length-1; i >= 0; i--) {
      int temp = A[0];
      A[0] = A[i];
      A[i] = temp;
      shiftDown(A, i);
      System.out.println(Arrays.toString(A));
    }
  }
}