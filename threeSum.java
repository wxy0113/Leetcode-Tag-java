import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class threeSum {
    public static void main(String[] args) {
        threeSum sol = new threeSum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.print(sol.threeSum(nums));
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null ||nums.length < 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            helper(nums, i, res);
        }
        return res;
    }
    public void helper(int[] nums, int index, List<List<Integer>> list) {
        int left = index+1;
        int right = nums.length-1;
        int target = -nums[index];
        // If elements can be used multi times:
        // left = index
        // while (left <= right)
        while (left < right) {
            if (nums[left]+nums[right] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[left]);
                temp.add(nums[right]);
                temp.add(-target);
                list.add(temp);
                left++;
                right--;
            } else if (nums[left]+nums[right] < target) {
                left++;
            } else {
                right--;
            }
        }
    }
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null ||nums.length < 3) return res;
        Map<Integer, Integer> map = new HashMap<>();
        Set<List<Integer>> set = new HashSet<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.get(nums[i])-1);
            if (map.get(nums[i]) == 0) {
                map.remove(nums[i]);
            }
            for (int j = 0; j < i; j++) {
                int target = -nums[i]-nums[j];
                if (map.containsKey(target)) {
                    List<Integer> list = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(list);
                    set.add(list);
                }
            }
        }
        res.addAll(set);
        return res;
    }
}