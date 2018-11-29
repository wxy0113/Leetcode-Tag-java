// This is a sandbox to experiment with CoderPad's execution capabilities.
// It's a temporary, throw-away session only visible to you.

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
        int[] nums = {1,3,-1,-3,5,3,6,7};
        Solution sol = new Solution();
        System.out.print(Arrays.toString(sol.medianSlidingWindow(nums, 3)));
    }
    // Deque time: On space: Ok
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return null;
        int len = nums.length-k+1;
        if (len <= 0) len = 1;
        int[] res = new int[len];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < k-1; i++) {
            inQueue(dq, nums[i]);
        }
        for (int i = k-1; i < nums.length; i++) {
            inQueue(dq, nums[i]);
            res[i-k+1] = dq.getFirst();
            outQueue(dq, nums[i-k+1]);
        }
        return res;
    }
    public void inQueue(Deque<Integer> dq, int num) {
        while (!dq.isEmpty() && dq.getLast() < num) {
            dq.pollLast();
        }
        dq.offerLast(num);
    }
    public void outQueue(Deque<Integer> dq, int num) {
        if (dq.getFirst() == num) dq.pollFirst();
    }
    // Deque can store value or index
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length-k+1;
        if (len <= 0) len = 1;
        int[] res = new int[len];
        Deque<Integer> dq = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!dq.isEmpty() && dq.getLast() < nums[i]) {
                dq.pollLast();
            }
            dq.offerLast(nums[i]);
            if (i >= k-1) {
                res[i-k+1] = dq.getFirst();
                if (dq.getFirst() == nums[i-k+1]) dq.pollFirst();
            }
        }
        return res;
    }

    // Follow up: Find Median Silding Window
    // Two PriorityQueue, like find median in data stream
    // Time: nlogn Space: n
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length-k+1;
        if (len <= 0) len = 1;
        double[] res = new double[len];
        PriorityQueue<Integer> small = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (small.size() == large.size()) {
                large.offer(nums[i]);
                small.offer(large.poll());
            } else {
                small.offer(nums[i]);
                large.offer(small.poll());
            }
            if (i >= k-1) {
                if (k%2 == 0) {
                    res[i-k+1] = ((double)small.peek()+(double)large.peek())/2.0;
                } else {
                    res[i-k+1] = small.peek();
                }
                if (nums[i-k+1] > res[i-k+1]) {
                    large.remove(nums[i-k+1]);
                } else {
                    small.remove(nums[i-k+1]);
                    small.offer(large.poll());
                }
            }
        }
        return res;
    }
}