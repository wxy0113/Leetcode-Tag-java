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
class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        label = x; neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
class Solution {
    public static void main(String[] args) {
    }
    // BFS
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            UndirectedGraphNode temp = queue.poll();
            if (set.contains(temp)) continue;
            map.putIfAbsent(temp, new UndirectedGraphNode(temp.label));
            for (UndirectedGraphNode next : temp.neighbors) {
                map.putIfAbsent(next, new UndirectedGraphNode(next.label));
                map.get(temp).neighbors.add(map.get(next));
                queue.offer(next);
            }
            set.add(temp);
        }
        return map.get(node);
    }

    //DFS
    Map<UndirectedGraphNode, UndirectedGraphNode> m = new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) return null;
        if (m.containsKey(node)) return m.get(node);
        map.putIfAbsent(node, new UndirectedGraphNode(node.label));
        for (UndirectedGraphNode next : node.neighbors) {
            m.get(node).neighbors.add(cloneGraph(next));
        }
        return m.get(node);
    }
}