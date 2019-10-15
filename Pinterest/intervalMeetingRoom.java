import java.util.*;

public class intervalMeetingRoom {
    /**
     * 1. 类似meeting rooms，输⼊入是⼀一个int[][] meetings, int start, int end, 每个数都是时间，13：00 =》
     * 1300， 9：30 =》 930， 看新的meeting 能不不能安排到meetings
     * Example:
     *  {[1300, 1500], [930, 1200],[830, 845]},
     *  新的meeting[820, 830],return true;
     *  [1450, 1500] return false;
     * @param intervals intervals
     * @param insert insert target
     * @return true or false
     *
     * sort + one pass: time: O(nlogn) space: O(n)
     */
    static boolean insertInterval(int[][] intervals, int[] insert) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        int index = 0;
        while (index < intervals.length) {
            if (insert[1] <= intervals[index][0]) {
                if (index == 0) return true;
                if (insert[0] >= intervals[index-1][1]) return true;
            }
            index++;
        }
        if (insert[0] >= intervals[index-1][1]) return true;
        return false;
    }

    /**
     * 2. Merge intervals and output free interval
     * @param intervals intervals
     *
     * time: O(nlogn) space: O(n)
     */
    static void mergeIntervals(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int index = 0;
        int[] newInterval = intervals[0];
        List<int[]> list = new ArrayList<>();
        System.out.println("Intervals after merged: ");
        while (index < intervals.length) {
            if (intervals[index][0] > newInterval[1]) {
                list.add(newInterval);
                System.out.println(Arrays.toString(newInterval));
                newInterval = intervals[index];
            } else {
                newInterval[1] = Math.max(newInterval[1], intervals[index][1]);
            }
            index++;
        }
        System.out.println(Arrays.toString(newInterval));
        list.add(newInterval);

        System.out.println("Free intervals: ");
        int pre = 0;
        for (int[] i : list) {
            System.out.println(pre + " - " + i[0]);
            pre = i[1];
        }
    }

    public static void main(String[] args) {
        int[][] schedules = new int[][] {{1300,1500}, {930,1200}, {830,845}};
        int[] target1 = new int[] {820,830};
        int[] target2 = new int[] {1450,1500};

        System.out.println(insertInterval(schedules, target1));
        System.out.println(insertInterval(schedules, target2));
        System.out.println("----------------");

        int[][] arr =   {{1,3}, {2,4}, {5,7}, {6,8} };
        mergeIntervals(arr);
    }
}
