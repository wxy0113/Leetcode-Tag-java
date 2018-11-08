import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Element {
    int row;
    int col;
    public Element(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
class mergeTwo {
    public static void main(String[] args) {
        mergeTwo sol = new mergeTwo();
        int[] nums1 = {1,2,3};
        int[] nums2 = {0,5,6};
        int[] nums3 = {4,5,6,7};
        int[][] arrays = {{1,3,5,7},{2,4,6},{0,8,9,10,11}};
        System.out.print(Arrays.toString(sol.mergeKArrays(arrays)));
    }
    public void merge(int[] nums1, int a, int[] nums2, int b) {
        while (a >= 1 && b >= 1) {
            if (nums1[a-1] >= nums2[b-1]) {
                nums1[a+b-1] = nums1[a-1];
                a--;
            } else {
                nums1[a+b-1] = nums2[b-1];
                b--;
            }
        }
        while (b >= 1) {
            nums1[b-1] = nums2[b-1];
            b--;
        }
    }
    public int[] mergeThree(int[] a, int[] b, int[] c) {
        int[] res = new int[a.length+b.length+c.length];
        int c1 = 0, c2 = 0, c3 = 0, cs = 0;
        while (c1 < a.length && c2 < b.length && c3 < c.length) {
            res[cs] = Math.min(a[c1], Math.min(b[c2], c[c3]));
            if (res[cs] == a[c1]) {
                c1++;
            } else if (res[cs] == b[c2]) {
                c2++;
            } else {
                c3++;
            }
            cs++;
        }
        while(c1 < a.length && c2 < b.length) {
            if (a[c1] < b[c2]) {
                res[cs++] = a[c1++];
            } else {
                res[cs++] = b[c2++];
            }
        }
        while(c1 < a.length && c3 < c.length) {
            if (a[c1] < c[c3]) {
                res[cs++] = a[c1++];
            } else {
                res[cs++] = c[c3++];
            }
        }
        while(c3 < c.length && c2 < b.length) {
            if (c[c3] < b[c2]) {
                res[cs++] = c[c3++];
            } else {
                res[cs++] = b[c2++];
            }
        }
        while (c1 < a.length) {
            res[cs++] = a[c1++];
        }
        while (c2 < b.length) {
            res[cs++] = b[c2++];
        }
        while (c3 < c.length) {
            res[cs++] = c[c3++];
        }
        return res;
    }
    public int[] mergeKArrays(int[][] arrays) {
        if (arrays == null || arrays.length == 0) return null;
        int n = 0;
        for (int[] arr : arrays) {
            n += arr.length;
        }
        int[] res = new int[n];
        Comparator<Element> myCom = new Comparator<>() {
            public int compare(Element e1, Element e2) {
                return arrays[e1.row][e1.col] - arrays[e2.row][e2.col];
            }
        };
        PriorityQueue<Element> pq = new PriorityQueue<>(arrays.length, myCom);
        for (int i = 0; i < arrays.length; i++) {
            pq.offer(new Element(i, 0));
        }
        int index = 0;
        while (!pq.isEmpty()) {
            Element e = pq.poll();
            res[index++] = arrays[e.row][e.col];
            e.col++;
            if (e.col < arrays[e.row].length) {
                pq.offer(e);
            }
        }
        return res;
    }
    public ListNode mergeKLists(List<ListNode> list) {
        ListNode teemo = new ListNode(0), head = teemo;
        Comparator<ListNode> myCom = new Comparator<>() {
            @Override
            public int compare(ListNode n1, ListNode n2) {
                return n1.val-n2.val;
            }
        };
        PriorityQueue<ListNode> pq = new PriorityQueue<>(list.size(), myCom);
        for (ListNode node : list) {
            if (node != null) {
                pq.offer(node);
            }
        }
        while (!pq.isEmpty()) {
            ListNode temp = pq.poll();
            head.next = temp;
            head = temp;
            if (temp.next != null) {
                pq.offer(temp.next);
            }
        }
        return teemo.next;
    }
}