import java.util.*;

public class LargestKPairs {
    static class Pair {
        int sum;
        int i;
        int j;
        public Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }

        @Override
        public int hashCode() {
            return sum;
        }
        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null || obj.getClass() != this.getClass()) return false;
            Pair pair = (Pair)obj;
            return i == pair.i && j == pair.j;
        }
    }
    public static List<int[]> findLargestKPairs(int[] arr1, int[] arr2, int k) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        List<int[]> res = new ArrayList<>();

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.sum - o1.sum;
            }
        });
        Set<Pair> set = new HashSet<>();
        int i1 = arr1.length-1, i2 = arr2.length-1;
        pq.offer(new Pair(arr1[i1]+arr2[i2], i1, i2));
        while (!pq.isEmpty() && res.size() < k) {
            Pair pair = pq.poll();
            res.add(new int[]{arr1[pair.i], arr2[pair.j]});
            set.add(pair);
            if (pair.i > 0) {
                Pair next2 = new Pair(arr1[pair.i-1]+arr2[pair.j], pair.i-1, pair.j);
                if (!set.contains(next2)) {
                    pq.offer(next2);
                }
            }
            if (pair.j > 0) {
                Pair next1 = new Pair(arr1[pair.i]+arr2[pair.j-1], pair.i, pair.j-1);
                if (!set.contains(next1)) {
                    pq.offer(next1);
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, -1, 0, 5};
        int[] arr2 = new int[]{1, 4, 2};

        List<int[]> list = findLargestKPairs(arr1, arr2, 5);
        for (int[] i : list) {
            System.out.println(i[0] + ", " + i[1]);
        }
    }
}
