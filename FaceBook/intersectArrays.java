import java.io.*;
import java.util.*;


// Follow up:
// 1. Two arrays are sorted?
//   Two pointers
// 2. nums1 smaller than nums2?
//   put nums1 into HashMap
// 3. both are large
//   If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read 2 elements from each array at a time in memory, record intersections.


class intersectArrays {
    public static void main(String[] args) {
        intersectArrays sol = new intersectArrays();
        int[] res = sol.intersect4(new int[]{2,2}, new int[]{1,1,2,3});
        System.out.print(Arrays.toString(res));
    }
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) return null;
        List<Integer> res = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums1) {
            set.add(i);
        }
        for (int i : nums2) {
            if (set.contains(i)) {
                res.add(i);
                set.remove(i);
            }
        }
        int[] nums = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            nums[i] = res.get(i);
        }
        return nums;
    }
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : nums1) {
            map.put(i, map.getOrDefault(i, 0)+1);
        }
        List<Integer> list = new ArrayList<>();
        for (int i : nums2) {
            if (map.containsKey(i)) {
                list.add(i);
                map.put(i, map.get(i)-1);
                if (map.get(i) == 0) {
                    map.remove(i);
                }
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }
    //binary search
    public int[] intersect3(int[] nums1, int[] nums2) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums1) {
            if (binarysearch(num, nums2)) {
                list.add(num);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }
    public boolean binarysearch(int target, int[] A) {
        if (A == null || A.length == 0) return false;
        int start = 0, end = A.length-1;
        while (start+1 < end) {
            int mid = start+(end-start)/2;
            // if (target == A[mid]) {
            //   return true;
            // } else if (A[mid] > target) {
            //   end = mid;
            // } else {
            //   start = mid;
            // }
            if (A[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        return A[start] == target || A[end] == target;
    }
    public int[] intersect4(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        List<Integer> list = new ArrayList<>();
        int index = 0;
        for (int num : nums1) {
            int pos = bsfirst(num, nums2, index);
            if (pos != -1) {
                list.add(num);
                index = pos;
            }
        }
        int[] res = new int[list.size()];
        index = 0;
        for (int num : list) {
            res[index++] = num;
        }
        return res;
    }
    public int bsfirst(int target, int[] num, int index) {
        if (num == null || num.length == 0 || index >= num.length) return -1;
        int start = index;
        int end = num.length-1;
        while (start+1 < end) {
            int mid = start+(end-start)/2;
            if (num[mid] >= target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (num[start] == target) return start;
        if (num[end] == target) return end;
        return -1;
    }
}
