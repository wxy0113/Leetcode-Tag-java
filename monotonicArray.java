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
    }

    // One pass On O1
    public boolean isMonotonic(int[] nums) {
        boolean inc = true, dec = true;
        for (int i = 0; i < nums.length-1; i++) {
            inc &= nums[i] <= nums[i+1];
            dec &= nums[i] >= nums[i+1];
            if (!inc && !dec) return false;
        }
        return true;
    }

    // If vector is large, write a helper function:
    // int nextNum(), return next num in vector
}