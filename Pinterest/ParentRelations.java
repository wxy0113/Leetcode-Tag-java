import java.util.*;

public class ParentRelations {
    /*
    Given a graph
    Input: {{1,4}, {1,5}, {2,5}, {3,6},{6,7}
     */
    static Map<Integer, Integer> inorder = new HashMap<>();
    static Map<Integer, List<Integer>> children = new HashMap<>();

    /**
     * 1. Fina all nodes with no parents and 1 parent
     * @param graph graph
     *
     * time: O(n) space: O(n)
     */
    public static void findNodes(int[][] graph){
        for (int[] g : graph) {
            inorder.put(g[1], inorder.getOrDefault(g[1], 0)+1);
            if (!children.containsKey(g[0])) {
                children.put(g[0], new ArrayList<>());
            }
            children.get(g[0]).add(g[1]);
        }

        System.out.println("Nodes with no parents");
        for (int[] g : graph) {
            if (!inorder.containsKey(g[0])) {
                inorder.put(g[0], 0);
                System.out.println(g[0]);
            }
        }

        System.out.println("Nodes with 1 parent");
        for (int i : inorder.keySet()) {
            if (inorder.get(i) == 0) {
                for (int child : children.get(i)) {
                    if (inorder.get(child) == 1) {
                        System.out.println(child);
                    }
                }
            }
        }
    }

    static Map<Integer, List<Integer>> parents = new HashMap<>();

    /**
     * 2. Find whether two nodes have common ancestor
     * @param graph graph
     * @param n1 node 1
     * @param n2 node 2
     * @return true or false
     *
     * Find all parents: time: O(n) space: O(n)
     */
    public static boolean commonParent(int[][] graph, int n1, int n2) {
        for (int[] g : graph) {
            if (!parents.containsKey(g[1])) {
                parents.put(g[1], new ArrayList<>());
            }
            parents.get(g[1]).add(g[0]);
        }
        Set<Integer> p1 = findAllParents(parents, n1);
        Set<Integer> p2 = findAllParents(parents, n2);

        for (int i : p1) {
            if (p2.contains(i)) {
                return true;
            }
        }

        return false;
    }
    static Set<Integer> findAllParents(Map<Integer, List<Integer>> parents, int child) {
        Set<Integer> res = new HashSet<>();
        if (parents.containsKey(child)) {
            Queue<Integer> queue = new LinkedList<>();
            for (Integer i : parents.get(child)) {
                queue.offer(i);
            }
            while (!queue.isEmpty()) {
                int temp = queue.poll();
                res.add(temp);
                if (!parents.containsKey(temp)) continue;
                for (int parent : parents.get(temp)) {
                    if (!res.contains(parent)) {
                        queue.offer(parent);
                        res.add(parent);
                    }
                }
            }
        } else {
            res.add(child);
        }
        return res;
    }

    /**
     * 3. 找一个点的最远祖先
     * @param graph graph
     * @param n node
     * @return furthest ancestor
     */
    static int findAncestor(int[][] graph, int n) {
        if (parents.containsKey(n)) {
            for (int i : findAllParents(parents, n)) {
//                System.out.println(i);
                if (!parents.containsKey(i)) {
                    return i;
                }
            }
        } else {
            return n;
        }
        return -1;
    }


    public static void main(String[] args) {
        int[][] graph = new int[][]{{1,4}, {1,5}, {2,5}, {3,6},{6,7}};
        findNodes(graph);
        System.out.println(commonParent(graph, 3, 7));
        System.out.println(findAncestor(graph, 7));
    }
}
