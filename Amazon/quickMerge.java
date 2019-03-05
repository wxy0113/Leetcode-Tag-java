import java.io.*;
import java.util.*;

// Merge Sort
// Like QuickSort, Merge Sort is a Divide and Conquer algorithm. It divides input array in two halves, calls itself for the two halves and then merges the two sorted halves. The merge() function is used for merging two halves. The merge(arr, l, m, r) is key process that assumes that arr[l..m] and arr[m+1..r] are sorted and merges the two sorted sub-arrays into one. See following C implementation for details.

class Solution {
  public static void main(String[] args) {
    int[] nums = new int[]{1, 0, -2, 4, 3};
    quickSort(nums);
    System.out.print(Arrays.toString(nums));
  }
  
  // Merge + Sort: O(nlogn) + O(n) 
  // Time Complexity: Sorting arrays on different machines. Merge Sort is a recursive algorithm and time complexity can be expressed as following recurrence relation.
  // T(n) = 2T(n/2) + \Theta(n)
  // The above recurrence can be solved either using Recurrence Tree method or Master method. It falls in case II of Master Method and solution of the recurrence is \Theta(nLogn).
  // Time complexity of Merge Sort is \Theta(nLogn) in all 3 cases (worst, average and best) as merge sort always divides the array in two halves and take linear time to merge two halves.

Auxiliary Space: O(n)
  public static void mergeSort(int[] nums, int lo, int hi) {
    if (lo >= hi) return;
    
    int m = lo+(hi-lo)/2;
    mergeSort(nums, lo, m);
    mergeSort(nums, m+1, hi);
    
    merge(nums, lo, m, hi);
  }
  public static void merge(int[] nums, int lo, int m, int hi) {
    int n1 = m-lo+1;
    int n2 = hi-m;
    
    int[] temp1 = new int[n1];
    int[] temp2 = new int[n2];
    
    for (int i = 0; i < n1; i++) {
      temp1[i] = nums[i+lo];
    }
    for (int i = 0; i < n2; i++) {
      temp2[i] = nums[i+m+1];
    }
    
    int left = 0, right = 0, index = lo;
    while (left < n1 && right < n2) {
      if (temp1[left] < temp2[right]) {
        nums[index] = temp1[left];
        left++;
      } else {
        nums[index] = temp2[right];
        right++;
      }
      index++;
    }
    
    while (left < n1) {
      nums[index++] = temp1[left++];
    }
    
    while (right < n2) {
      nums[index++] = temp2[right++];
    }
  }
  
  // Quick Sort:  Average:O(nlogn)  Worst:O(n^2)
  // Worst Case: The worst case occurs when the partition process always picks greatest or smallest element as pivot. If we consider above partition strategy where last element is always picked as pivot, the worst case would occur when the array is already sorted in increasing or decreasing order. Following is recurrence for worst case.
  // T(n) = T(0) + T(n-1) + \theta(n) -> T(n) = T(n-1) + \theta(n)
  // Best Case: The best case occurs when the partition process always picks the middle element as pivot. Following is recurrence for best case. 
  // T(n) = 2T(n/2) + \theta(n)
  public static void quickSort(int[] nums) {
    if (nums == null || nums.length == 0) return;
    quick(nums, 0, nums.length-1);
  }
  public static void quick(int[] nums, int lo, int hi) {
    if (lo > hi) return;
    
    int left = lo, right = hi;
    int pivot = nums[lo+(hi-lo)/2];
    
    while (left <= right) {
      while (left <= right && nums[left] < pivot) {
        left++;
      }
      while (left <= right && nums[right] > pivot) {
        right--;
      }
      
      if (left <= right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
        
        left++;
        right--;
      }
    }
    quick(nums, lo, right);
    quick(nums, left, hi);
    
  }
}
