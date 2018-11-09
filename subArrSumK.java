import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class subArrSumK {
    public static void main(String[] args) {
        subArrSumK sol = new subArrSumK();
        int[] nums = {2, 3, 1, 2, 1, 5, 3};
        System.out.print(sol.subSum(nums, 8));
    }
    //HashMap
    public int subSum(int[] nums, int k) {
        if (nums == null) return 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,1);
        int sum = 0, res = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            res += map.getOrDefault(sum-k, 0);
            map.put(sum, map.getOrDefault(sum, 0)+1);
        }
        return res;
    }

    //Two Pointers: Only for positive int
    public int subSum2(int[] nums, int k) {
        if (nums == null) return 0;
        int i = 0, sum = 0, res = 0;
        for (int num : nums) {
            sum += num;
            while (sum > k) sum -= nums[i++];
            if (sum == k) res++;
        }
        return res;
    }
    // Output subarray
    public List<List<Integer>> subSum3(int[] nums, int k) {
        if (nums == null) return null;
        List<List<Integer>> res = new ArrayList<>();
        int j = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum > k) sum -= nums[j++];
            if (sum == k) {
                List<Integer> list = new ArrayList<>();
                for (int m = j; m <= i; m++) {
                    list.add(nums[m]);
                }
                res.add(list);
            }
        }
        return res;
    }
}