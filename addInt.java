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
    }
}

class addInt {
    public static void main(String[] args) {
        addInt sol = new addInt();
        System.out.println(sol.addBinary("111", "100"));
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        ListNode res = sol.addTwoNum2(l1, l2, 10);
        while (res != null) {
            System.out.print(res.val);
            res = res.next;
        }
    }
    public String addBinary(String s1, String s2) {
        if (s1 == null && s2 == null) return null;
        StringBuilder sb = new StringBuilder();
        int i1 = s1.length()-1;
        int i2 = s2.length()-1;
        int sum = 0;
        while (i1 >= 0 || i2 >= 0) {
            if (i1 >= 0) {
                sum += s1.charAt(i1)-'0';
                i1--;
            }
            if (i2 >= 0) {
                sum += s1.charAt(i2)-'0';
                i2--;
            }
            sb.append(sum%2);
            sum /= 2;
        }
        if (sum != 0) {
            sb.append(sum);
        }
        return sb.reverse().toString();
    }
    public ListNode addTwoNum(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0), head = res;
        int sum = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode temp = new ListNode(sum%10);
            res.next = temp;
            res = temp;
            sum /= 10;
        }
        if (sum != 0) {
            res.next = new ListNode(sum);
        }
        return head.next;
    }
    public ListNode addTwoNum2(ListNode l1, ListNode l2, int base) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = null;
        int sum = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            if (!stack1.isEmpty()) {
                sum += stack1.pop();
            }
            if (!stack2.isEmpty()) {
                sum += stack2.pop();
            }
            ListNode temp = new ListNode(sum%base);
            temp.next = head;
            head = temp;
            sum /= base;
        }
        if (sum != 0) {
            ListNode temp = new ListNode(sum);
            temp.next = head;
            head = temp;
        }
        return head;
    }
}