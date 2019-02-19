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
        int[] nums = {1,2,3,3,3};
        randomPick2 rp = new randomPick2(nums);

        System.out.print(rp.pick(3));
        System.out.print(rp.pick(3));
        System.out.print(rp.pick(3));
        System.out.print(rp.pick(3));
        System.out.print(rp.pick(1));
    }

    // Reservoir Sampling: randomly pick k from n array
    public int[] randomPickK(int[] nums, int n, int k) {
        if (k >= n) return nums;

        Random rand = new Random();
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = nums[i];
        }

        for (int i = 0; i < n; i++) {
            int r = rand.nextInt(i+1);

// Last n-k items: k/n,
// First k items: [k/(k+1)] x [(k+1)/(k+2)] x [(k+2)/(k+3)] x … x [(n-1)/n] = k/n
            if (r < k) {
                res[r] = nums[i];
            }
        }

        return res;
    }
}

// Using HashMap On On
class randomPick {
    Map<Integer, List<Integer>> map;
    Random rand;

    public randomPick(int[] nums) {
        map = new HashMap<>();
        rand = new Random();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        if (!map.containsKey(target)) return -1;
        List<Integer> list = map.get(target);
        return list.get(rand.nextInt(list.size()));
    }
}

// Reservoir Sampling O1 On
class randomPick2 {
    int[] nums;
    Random rand;

    public randomPick2(int[] nums) {
        this.nums = nums;
        this.rand = new Random();
    }

    public int pick(int target) {
        int cnt = 0, res = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue;
            if (rand.nextInt(++cnt) == 0) {
                res = i;
            }
        }
        return res;
    }
}

// Random Pick With Weight
class randomPickWeight {

    Random random;
    int[] preSum;
    public randomPickWeight(int[] w) {
        this.random = new Random();
        this.preSum = new int[w.length];
        preSum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            preSum[i] = preSum[i-1] + w[i];
        }
    }

    public int pickIndex() {
        int len = preSum.length;
        int index = random.nextInt(preSum[len-1])+1;
        int left = 0;
        int right = len-1;
        while (left < right) {
            int mid = left+(right-left)/2;
            if (preSum[mid] == index) {
                return mid;
            } else if (preSum[mid] < index) {
                left = mid+1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

//Random Pick Blacklist
class randomPickBlack {
    Random rand;
    Map<Integer, Integer> map;
    int n;

    public randomPickBlack(int N, int[] bl) {
        rand = new Random();
        map = new HashMap<>();

        for (int i : bl) {
            map.put(i, -1);
        }

        n = N-map.size();

        for (int i : bl) {
            if (i >= n) continue;
            while (map.containsKey(N-1)) N--;
            map.put(i, N-1);
            N--;
        }
    }

    public int pick() {
        int r = rand.nextInt(n);
        if (map.containsKey(r)) return map.get(r);
        return r;
    }
}
