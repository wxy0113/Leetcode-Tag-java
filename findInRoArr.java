import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class findInRoArr {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
    }
    public int findInRotateArr(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length-1;
        while (start+1 < end) {
            int mid = start+(end-start)/2;
            if (nums[start] < nums[mid]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else {
                if (target >= nums[mid] && target <= nums[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        return -1;
    }
    public boolean findInDupArr(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int start = 0;
        int end = nums.length-1;
        while (start+1 < end) {
            int mid = start+(end-start)/2;
            if (nums[mid] == target) return true;
            if (nums[start] < nums[mid]) {
                if (target >= nums[start] && target <= nums[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (nums[mid] < nums[start]) {
                if (target > nums[mid] && target < nums[start]) {
                    start = mid;
                } else {
                    end = mid;
                }
            } else {
                start++;
            }
        }
        if (nums[start] == target || nums[end] == target) return true;
        return false;
    }
}