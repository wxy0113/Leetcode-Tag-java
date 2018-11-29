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
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        int[] drain = {0,1,0,2,1,-1,0,3,2,1,2,1};
        Solution sol = new Solution();
        System.out.print(sol.trapDrain3(drain));
    }

    // 1. One scan Two Pointers
    public int trap1(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int leftMost = height[0], rightMost = height[height.length-1];
        int left = 0, right = height.length-1;
        int res = 0;

        while (left < right) {
            if (leftMost < rightMost) {
                res += leftMost - height[left++];
                leftMost = Math.max(leftMost, height[left]);
            } else {
                res += rightMost - height[right--];
                rightMost = Math.max(rightMost, height[right]);
            }
        }
        return res;
    }
    // Follow up 1: -1 means drain water
    public int trapDrain1(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int leftMost = height[0], rightMost = height[height.length-1];
        int left = 0, right = height.length-1;
        int res = 0, llast = 0, rlast = 0;

        while (left < right) {
            if (leftMost < rightMost) {
                if (height[left] == -1) {
                    res -= llast;
                    llast = 0;
                    leftMost = 0;
                    left++;
                } else {
                    res += leftMost - height[left++];
                }
                if (leftMost < height[left]) {
                    llast = res;
                    leftMost = height[left];
                }
            } else {
                if (height[right] == -1) {
                    res -= rlast;
                    rlast = 0;
                    rightMost = 0;
                    right--;
                } else {
                    res += rightMost - height[right--];
                }
                if (rightMost < height[right]) {
                    rlast = res;
                    rightMost = height[right];
                }
            }
        }
        return res;
    }
    // Follow up 2: each bar has a width
    public int trapWidth1(int[] height, int[] width) {
        if (height == null || height.length <= 2) return 0;
        int leftMost = height[0], rightMost = height[height.length-1];
        int left = 0, right = height.length-1;
        int res = 0;

        while (left < right) {
            if (leftMost < rightMost) {
                res += (leftMost - height[left])*width[left];
                left++;
                leftMost = Math.max(leftMost, height[left]);
            } else {
                res += (rightMost - height[right])*width[right];
                right--;
                rightMost = Math.max(rightMost, height[right]);
            }
        }
        return res;
    }

    // 2. Two scan
    public int trap2(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int[] water = new int[height.length];
        int bar = 0, res = 0;

        for (int i = 0; i < height.length; i++) {
            bar = Math.max(bar, height[i]);
            water[i] = bar;
        }

        bar = 0;
        for (int i = height.length-1; i >= 0; i--) {
            bar = Math.max(bar, height[i]);
            water[i] = Math.min(water[i], bar);
            res += water[i]-height[i];
        }
        return res;
    }
    // Follow up 1: -1 means drain water
    public int trapDrain2(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int[] water = new int[height.length];
        int bar = 0, res = 0;

        for (int i = 0; i < height.length; i++) {
            if (height[i] == -1) {
                int index = i;
                while (index > 0 && water[index-1] >= water[index]) {
                    water[index--] = 0;
                }
                continue;
            } else {
                bar = Math.max(bar, height[i]);
            }
            water[i] = bar;
        }

        bar = 0;
        int last = 0;
        for (int i = height.length-1; i >= 0; i--) {
            if (height[i] == -1) {
                res -= last;
                last = 0;
                bar = 0;
                continue;
            } else {
                bar = Math.max(bar, height[i]);
            }

            if (water[i] < bar) {
                res += water[i]-height[i];
            } else {
                res += bar-height[i];
                water[i] = bar;
                last = res;
            }
        }
        return res;
    }
    // Follow up 2: each bar has a width
    public int trapWidth2(int[] height, int[] width) {
        if (height == null || height.length <= 2) return 0;
        int[] water = new int[height.length];
        int bar = 0, res = 0;

        for (int i = 0; i < height.length; i++) {
            bar = Math.max(bar, height[i]);
            water[i] = bar;
        }

        bar = 0;
        for (int i = height.length-1; i >= 0; i--) {
            bar = Math.max(bar, height[i]);
            water[i] = Math.min(water[i], bar);
            res += (water[i]-height[i])*width[i];
        }
        return res;
    }

    // 3. Stack
    public int trap3(int[] height) {
        if (height == null || height.length <= 2) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int cur = height[stack.pop()];
                if (!stack.isEmpty()) {
                    res += (i-stack.peek()-1)*(Math.min(height[i], height[stack.peek()])-cur);
                }
            }
            stack.push(i);
        }
        return res;
    }
    // Follow up 1: -1 means drain water
    public int trapDrain3(int[] height) {
        if (height == null || height.length <= 2) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++) {
            if (height[i] == -1) stack.clear();
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]) {
                int cur = height[stack.pop()];
                if (!stack.isEmpty()) {
                    res += (i-stack.peek()-1)*(Math.min(height[i], height[stack.peek()])-cur);
                }
            }
            stack.push(i);
        }
        return res;
    }
}
