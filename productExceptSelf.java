import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class productExceptSelf {
    public static void main(String[] args) {
        int[] nums = {1,2,3,4, 0, 0};
        productExceptSelf sol = new productExceptSelf();
        System.out.print(Arrays.toString(sol.product2(nums)));
    }
    // Not using divide
    public int[] product(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length;
        int[] res = new int[len];
        res[0] = nums[0];
        for (int i = 1; i < len; i++) {
            res[i] = res[i-1]*nums[i];
        }
        int pro = 1;
        for (int i = len-1; i > 0; i--) {
            res[i] = res[i-1]*pro;
            pro *= nums[i];
        }
        res[0] = pro;
        return res;
    }
    // Using Divide
    public int[] product2(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        int len = nums.length, pro = 1, count = 0;
        int[] res = new int[len];
        for (int num : nums) {
            if (num == 0) count++;
            else pro *= num;
        }
        for (int i = 0; i < len; i++) {
            if (nums[i] == 0) {
                if (count > 1) res[i] = 0;
                else res[i] = pro;
            } else {
                if (count == 0) res[i] = pro/nums[i];
                else res[i] = 0;
            }
        }
        return res;
    }
}