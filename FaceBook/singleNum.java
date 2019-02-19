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
        int[] nums = {0,1,0,1,0,1,99};
        System.out.print(singleNum2(nums));
    }
    // 136: appear twice find single
    // a^b^a = (a^a)^b = b
    public static int singleNum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int res = 0;
        for (int i : nums) {
            res ^= i;
        }
        return res;
    }

    // 137: appear tripple find single
    // count each bit, if applear triple then set 0
    public static int singleNum2(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int res = 0;
        int[] bits = new int[32];
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < nums.length; j++) {
                bits[i] += (nums[j] >> i & 1);
                bits[i] %= 3;
            }
            res |= bits[i] << i;
        }
        return res;
    }

    // 260: appear twice find two single
    public static int[] singleNum3(int[] nums) {
        if (nums == null || nums.length == 0) return new int[2];

        int x = 0;
        for (int num : nums) {
            x ^= num;
        }

        x = Integer.lowestOneBit(x);
        int a = 0, b = 0;
        for (int num : nums) {
            if ((x & num) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }
        return new int[]{a, b};
    }
}
