import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class findKth {
    public static void main(String[] args) {
        int[] nums = {3,2,1,5,6,4};
        findKth sol = new findKth();
        System.out.println(sol.findKthLarge(nums, 2));
    }
    //kth Small
    public int findKthSmall(int[] nums, int k) {
        quickSelect(nums, k-1, 0, nums.length-1);
        return nums[k-1];
    }
    public void quickSelect(int[] nums, int k, int left, int right) {
        int i = left, j = right;
        int pivot = nums[left];
        while (i <= j) {
            while (i <= j && nums[i] < pivot) i++;
            while (i <= j && nums[j] > pivot) j--;
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        if (j >= k) {
            quickSelect(nums, k, left, j);
        }
        if (i <= k) {
            quickSelect(nums, k, i, right);
        }
    }
    public void swap(int[] nums, int i, int j) {
        // nums[i] ^= nums[j];
        // nums[j] ^= nums[i];
        // nums[i] ^= nums[j];
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // kth large
    public int findKthLarge(int[] nums, int k) {
        // write your code here
        if(k < 0 || k > nums.length) return -1;
        quickselect2(nums, 0, nums.length-1, k-1);
        return nums[k-1];
    }
    public void quickselect2(int[] nums, int left, int right, int k){
        int p = nums[left];
        int i = left;
        int j = right;
        while(i <= j){
            while(i <= j && nums[i] > p){
                i++;
            }
            while(i <= j && nums[j] < p){
                j--;
            }
            if(i <= j){
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        if(i <= k){
            quickselect2(nums, i, right, k);
        }
        if(j >= k){
            quickselect2(nums, left, j, k);
        }
    }

}
