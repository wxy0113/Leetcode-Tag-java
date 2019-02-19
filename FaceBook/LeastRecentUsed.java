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

class Solution {
    public static void main(String[] args) {
        LRU2 cache = new LRU2(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // returns 1
        cache.put(3, 3);    // evicts key 2
        System.out.println(cache.get(2));       // returns -1 (not found)
        cache.put(4, 4);    // evicts key 1
        System.out.println(cache.get(1));       // returns -1 (not found)
        System.out.println(cache.get(3));       // returns 3
        System.out.println(cache.get(4));
    }
}

// Using LinkedHashMap
class LRU1 {
    Map<Integer, Integer> map;
    int capacity;
    public LRU1(int capacity) {
        this.capacity = capacity;
        this.map = new LinkedHashMap<>(capacity+1);
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        int res = map.get(key);
        map.remove(key);
        map.put(key, res);
        return res;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, val);
        } else {
            map.put(key, val);
            if (map.size() > capacity) {
                map.remove(map.entrySet().iterator().next().getKey());
            }
        }
    }
}

// Using HashMap + Double Linked List
class LRU2 {
    class DLL {
        int key, val;
        DLL pre, next;
        DLL(int key, int val) {
            this.key = key;
            this.val = val;
            pre = next = null;
        }
    }
    public void insert(DLL node) {
        node.pre = tail.pre;
        node.next = tail;
        node.pre.next = node;
        node.next.pre = node;
    }
    public void remove(DLL node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    int capacity;
    Map<Integer, DLL> map;
    DLL head, tail;

    public LRU2(int cap) {
        capacity = cap;
        map = new HashMap<>();
        head = new DLL(0, 0);
        tail = new DLL(0, 0);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) return -1;
        DLL temp = map.get(key);
        remove(temp);
        insert(temp);
        return temp.val;
    }

    public void put(int key, int val) {
        if (map.containsKey(key)) {
            remove(map.get(key));
            insert(map.get(key));
            map.get(key).val = val;
        } else {
            if (map.size() == capacity) {
                map.remove(head.next.key);
                remove(head.next);
            }
            DLL dll = new DLL(key, val);
            insert(dll);
            map.put(key, dll);
        }
    }
    public int last() {
        if (!map.containsKey(tail.pre.key)) return -1;
        return map.get(tail.pre.key).val;
    }
}
