import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
//class Interval {
//    int start;
//    int end;
//    public Interval(int start, int end) {
//        this.start = start;
//        this.end = end;
//    }
//}
class mergeInterval {
    public static void main(String[] args) {
        List<Interval> a = new ArrayList<>();
        a.add(new Interval(1, 5));
        a.add(new Interval(8, 10));
        a.add(new Interval(15, 25));
        List<Interval> b = new ArrayList<>();
        b.add(new Interval(2, 6));
        b.add(new Interval(25, 100));
        Interval in = new Interval(9, 16);
        mergeInterval sol = new mergeInterval();
        Interval[] b = new Interval[] {new Interval(1, 5), new Interval(8, 10), new Interval(15,25)};
        Interval res= sol.intersect2(b, in);
        System.out.print(res.start+","+res.end);
    }
    public List<Interval> mergeInterval(List<Interval> intervals) {
        if (intervals == null || intervals.size() <= 1) return intervals;
        List<Interval> res = new ArrayList<>();
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start-i2.start;
            }
        });
        for (int i = 0; i < intervals.size(); i++) {
            Interval temp = new Interval(intervals.get(i).start, intervals.get(i).end);
            while (i+1 < intervals.size() && temp.end >= intervals.get(i+1).start) {
                temp.end = Math.max(temp.end, intervals.get(i+1).end);
                i++;
            }
            res.add(temp);
        }
        return res;
    }
    public List<Interval> merge(List<Interval> a, List<Interval> b) {
        int i = 0, j = 0;
        Interval last = null;
        List<Interval> ans = new ArrayList<>();
        while (i < a.size() || j < b.size()) {
            // pick the one with less start
            Interval cur = null;
            if (j == b.size() || (i < a.size() && a.get(i).start < b.get(j).start))
                cur = a.get(i ++);
            else cur = b.get(j ++);

            // merge intervals
            if (last == null) {last = cur; continue;}
            if (last.end < cur.start) {
                ans.add(last);
                last = cur;
            } else last.end = Math.max(last.end, cur.end);
        }
        if (last != null) ans.add(last);
        return ans;
    }

    public List<Interval> intersect(List<Interval> a, List<Interval> b) {
        int i = 0, j = 0;
        List<Interval> ans = new ArrayList<>();
        while (i < a.size() && j < b.size()) {

            Interval cura = a.get(i);
            Interval curb = b.get(j);
            if (!(cura.end < curb.start || curb.end < cura.start)) {
                Interval inter = new Interval(Math.max(cura.start, curb.start),
                        Math.min(cura.end, curb.end));
                ans.add(inter);
            }

            if (cura.end < curb.end) i ++;
            else j ++;
        }
        return ans;
    }
    public Interval intersect2(Interval[] list, Interval a) {
        int start = 0, end = list.length-1, mid = 0;
        while (start+1 < end) {
            mid = start+(end-start)/2;
            if (list[mid].end < a.start) {
                start = end;
            } else if (list[mid].start > a.end) {
                end = mid;
            } else {
                break;
            }
        }
        Interval res = new Interval(Math.max(a.start, list[mid].start), Math.min(a.end, list[mid].end));
        return res;
    }
}