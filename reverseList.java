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
class ListNode {
    int val;
    ListNode next;
    public ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}
class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("Hello, World!");
        strings.add("Welcome to CoderPad.");
        strings.add("This pad is running Java " + Runtime.version().feature());

        for (String string : strings) {
            System.out.println(string);
        }
    }
    public static ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, tail = head;
        while (tail != null) {
            head.next = tail.next;
            tail.next = pre.next;
            pre.next = tail;
            tail = head.next;
        }
        return dummy.next;
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = head, tail;
        int len = n-m+1;
        while (m > 1) {
            pre = pre.next;
            m--;
        }

        if (pre == null || pre.next == null) return head;

        head = pre.next;
        tail = head;

        while (len > 0) {
            head.next = tail.next;
            tail.next = pre.next;
            pre.next = tail;
            tail = head.next;
            len--;
        }
        return dummy.next;
    }
}