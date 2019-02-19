import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class heapMinMax {
    public static void main(String[] args) {
        int[] num = {87,45,78,32,17,65,53,9,122};
        buildHeap(num);
        minSort(num);
        System.out.print(Arrays.toString(num));
    }
    public static void buildHeap(int[] nums) {
        int len = nums.length;
        for (int i = (len)/2; i >= 0; i--) {
            shiftDown(nums, i, len);
        }
    }
    public static void shiftUp(int[] nums, int k, int len) {
        int temp = nums[k];
        while (k < len) {
            int left = k*2+1;
            int right = k*2+2;
            int max = k;
            if (left < len && nums[left] > nums[max]) max = left;
            if (right < len && nums[right] > nums[max]) max = right;
            if (max != k) {
                nums[k] = nums[max];
                nums[max] = temp;
                k = max;
            } else {
                break;
            }
        }
    }
    public static void shiftDown(int[] nums, int k, int len) {
        int temp = nums[k];
        while (k < len) {
            int left = k*2+1;
            int right = k*2+2;
            int min = k;
            if (left < len && nums[left] < nums[min]) min = left;
            if (right < len && nums[right] < nums[min]) min = right;
            if (min != k) {
                nums[k] = nums[min];
                nums[min] = temp;
                k = min;
            } else {
                break;
            }
        }
    }
    public static void maxSort(int[] nums) {
        int len = nums.length;
        for (int i = len-1; i >= 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            shiftUp(nums, 0, i);
        }
    }
    public static void minSort(int[] nums) {
        int len = nums.length;
        for (int i = len-1; i >= 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            shiftDown(nums, 0, i);
        }
    }
}