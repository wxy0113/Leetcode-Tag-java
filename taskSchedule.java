import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Task {
    char name;
    int cnt;
    public Task(char name, int cnt) {
        this.name = name;
        this.cnt = cnt;
    }
}
class Solution {
    public static void main(String[] args) {
        char[] c = {'A', 'A', 'A', 'B', 'B', 'B'};
        Solution sol = new Solution();
        System.out.print(sol.taskSchedule5(c, 2));
    }
    // On
    public int taskSchedule(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        int[] cnt = new int[26];
        for (char c : tasks) {
            cnt[c-'A'] ++;
        }
        Arrays.sort(cnt);
        int i = 25;
        while (i >= 0 && cnt[i] == cnt[25]) i--;
        return Math.max(tasks.length, (cnt[25]-1)*(n+1)+25-i);
    }

    // Output task schedule
    public int taskSchedule2(char[] tasks, int n) {
        if (tasks == null || tasks.length == 0) return 0;
        Task[] count = new Task[26];
        for (char c = 'A'; c <= 'Z'; c++) {
            count[c-'A'] = new Task(c, 0);
        }
        for (char c : tasks) {
            count[c-'A'].cnt ++;
        }
        PriorityQueue<Task> pq = new PriorityQueue<>(new Comparator<Task>() {
            public int compare(Task t1, Task t2) {
                return t2.cnt-t1.cnt;
            }
        });
        for (Task t : count) {
            if (t.cnt > 0) pq.add(t);
        }
        int res = 0;
        List<Character> list = new ArrayList<>();
        while (!pq.isEmpty()) {
            List<Task> temp = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                res++;
                if (!pq.isEmpty()) {
                    Task t = pq.poll();
                    list.add(t.name);
                    if (--t.cnt > 0) temp.add(t);
                    if (pq.isEmpty() && temp.isEmpty()) break;
                }
            }
            for (Task task : temp) {
                pq.offer(task);
            }
        }
        for (Character c : list) {
            System.out.print(c + " - ");
        }
        return res;
    }
    // Follow up: Fixed tasks schedule, output time needed
    // When tasks << cooldown, using HashMap, time: On space: On
    public int taskSchedule3(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (Character c : tasks) {
            res = Math.max(res+1, map.getOrDefault(c, Integer.MIN_VALUE)+n+1);
            map.put(c, res);
        }
        return res;
    }
    // When tasks <= cooldown, using Queue, time: Okn space: Ok k is cooldown
    public int taskSchedule4(char[] tasks, int n) {
        Queue<Task> queue = new LinkedList<>();
        int res = 0;
        for (char c : tasks) {
            res++;
            for (Task t : queue) {
                if (t.name == c) {
                    res = t.cnt+n+1;
                    break;
                }
            }
            while (!queue.isEmpty() && queue.peek().cnt < res-n) queue.poll();
            queue.offer(new Task(c, res));
        }
        return res;
    }
    // Using HashMap + Queue, time: On space: Ok
    public int taskSchedule5(char[] tasks, int n) {
        Queue<Character> queue = new LinkedList<>();
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (char c : tasks) {
            res = Math.max(res+1, map.getOrDefault(c, Integer.MIN_VALUE)+n+1);
            while (!queue.isEmpty() && map.get(queue.peek()) < res-n) {
                map.remove(queue.poll());
            }
            map.put(c, res);
            queue.offer(c);
        }
        return res;
    }
}
