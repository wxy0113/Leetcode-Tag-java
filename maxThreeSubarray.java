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
        int[] nums = {1,5,2,-2,3,4};
        System.out.print(((maxSumOfThreeSubarrays3(nums, 3))));
    }
    // DP
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] res = new int[3];
        if (nums == null || nums.length == 0) return res;

        int len = nums.length;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i-1]+nums[i];
            if (i >= k) {
                sum[i] -= nums[i-k];
            }
        }

        int[] left = new int[len];
        int leftMax = k-1;
        for (int i = k-1; i < len; i++) {
            left[i] = sum[i] > sum[leftMax] ? i : leftMax;
            leftMax = left[i];
        }

        int[] right = new int[len];
        int rightMax = len-1;
        for (int i = len-1; i >= k; i--) {
            right[i] = sum[i] > sum[rightMax] ? i : rightMax;
            rightMax = right[i];
        }

        int max = Integer.MIN_VALUE;
        for (int i = 2*k-1; i < len-k; i++) {
            int temp = sum[i] + sum[left[i-k]] + sum[right[i+k]];
            if (temp > max) {
                res[0] = left[i-k]-k+1;
                res[1] = i-k+1;
                res[2] = right[i+k]-k+1;
                max = temp;
            }
        }
        return res;
    }

    //new DP
    public static int[] maxSumOfThreeSubarrays2(int[] nums, int k) {
        int[][] res = new int[3][3];
        int sum = 0;
        int[] max = new int[3];

        int len = nums.length;
        int[] pre = new int[len];
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i-k];
            }
            pre[i] = sum;

            if (i >= 3*k-1) {
                if (pre[i-2*k] > max[0]) {
                    max[0] = pre[i-2*k];
                    res[0] = new int[] {i-3*k+1, 0, 0};
                }
                if (max[0] + pre[i-k] > max[1]) {
                    max[1] = max[0] + pre[i-k];
                    res[1] = new int[] {res[0][0], i-2*k+1, 0};
                }
                if (max[1] + pre[i] > max[2]) {
                    max[2] = max[1] + pre[i];
                    res[2] = new int[] {res[1][0], res[1][1], i-k+1};
                }
            }
        }
        return res[2];
    }
    // Follow up: Two Intervals of K sum max
    public static int maxSumOfThreeSubarrays3(int[] nums, int k) {
        int res = Integer.MIN_VALUE;

        int len = nums.length;
        int[] pre = new int[len+1];
        for (int i = 1; i <= len; i++) {
            pre[i] = pre[i-1]+nums[i-1];;
        }
        for (int i = 1; i < k; i++) {
            int[] max = new int[2];
            Arrays.fill(max, Integer.MIN_VALUE);
            for (int j = k; j <= len; j++) {
                if ((pre[j-i]-pre[j-k]) > max[0]) {
                    max[0] = (pre[j-i]-pre[j-k]);
                }
                if (max[0] + (pre[j]-pre[j-i]) > max[1]) {
                    max[1] = max[0] + (pre[j]-pre[j-i]);
                }
            }
            res = Math.max(res, max[1]);
        }
        return res;
    }
}
