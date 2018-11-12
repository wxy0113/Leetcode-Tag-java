//import java.io.*;
//import java.util.*;
//
///*
// * To execute Java, please define "static void main" on a class
// * named Solution.
// *
// * If you need more classes, simply define them inline.
// */
//class Point {
//    int x;
//    int y;
//    public Point(int x, int y) {
//        this.x = x;
//        this.y = y;
//    }
//}
//class kNearestPoint {
//    public static void main(String[] args) {
//        kNearestPoint sol = new kNearestPoint();
//        int[] nums = {1,2,3,4,5};
//        System.out.println(sol.findClosestElements(nums, 4, 3));
//        Point[] points = new Point[5];
//        points[1] = new Point(1,1);
//        points[2] = new Point(2,3);
//        points[3] = new Point(5,5);
//        points[4] = new Point(3,4);
//        points[0] = new Point(1,2);
//        Point[] res = sol.findKthPoint2(points, 3);
//        for (Point p : res) {
//            System.out.print(p.x+","+p.y+" ");
//        }
//    }
//    class Element {
//        int val;
//        int len;
//        public Element(int val, int len) {
//            this.val = val;
//            this.len = len;
//        }
//    }
//    public List<Integer> findClosestElements(int[] arr, int k, int x) {
//        List<Integer> res = new ArrayList<>();
//        if (arr == null || arr.length == 0 || k <= 0) return res;
//        k = Math.min(k, arr.length);
//        PriorityQueue<Element> pq = new PriorityQueue<>(k, new Comparator<Element>(){
//            public int compare(Element e1, Element e2) {
//                if (e2.len == e1.len) return e2.val-e1.val;
//                return e2.len - e1.len;
//            }
//        });
//        pq.offer(new Element(arr[0], Math.abs(arr[0]-x)));
//        for (int i = 1; i < arr.length; i++) {
//            pq.offer(new Element(arr[i],Math.abs(arr[i]-x)));
//            if (pq.size() > k) pq.poll();
//        }
//        while (!pq.isEmpty()) {
//            res.add(pq.poll().val);
//        }
//        Collections.sort(res);
//        return res;
//    }
//
//    //PriorityQueue O(nlogk)
//    public Point[] findKthPoint(Point[] points, int k) {
//        if (points == null || points.length <= k) return points;
//        Point[] res = new Point[k];
//        PriorityQueue<Point> pq = new PriorityQueue<>(k, new Comparator<Point>(){
//            @Override
//            public int compare(Point p1, Point p2) {
//                return comparePoint(p1, p2);
//            }
//        });
//        for (Point p : points) {
//            pq.offer(p);
//            if (pq.size() > k) {
//                pq.poll();
//            }
//        }
//        int i = k-1;
//        while(i >= 0) {
//            res[i--] = pq.poll();
//        }
//        return res;
//    }
//    public int comparePoint(Point p1, Point p2) {
//        int dif = -(p1.x*p1.x+p1.y*p1.y-p2.x*p2.x-p2.y*p2.y);
//        if (dif == 0) {
//            if (p2.x - p1.x == 0) {
//                return p2.y-p1.y;
//            }
//            return p2.x-p1.x;
//        }
//        return dif;
//    }
//    // MinHeap Heapify O(n + klogn)
//    public Point[] findKthPoint2(Point[] points, int k) {
//        if (points == null || points.length <= k) return points;
//        Point[] res = new Point[k];
//        buildMinHeap(points);
//        for (int i = 0; i < k; i++) {
//            res[i] = popMin(points, k-i-1);
//        }
//        return res;
//    }
//    public void buildMinHeap(Point[] points) {
//        if (points == null || points.length < 2) return;
//        int len = points.length/2;
//        for (int i = len/2; i >= 0; i--) {
//            shiftDown(points, i, len);
//        }
//    }
//    public void shiftDown(Point[] points, int i, int len) {
//        while (i < len) {
//            int left = 2*i+1;
//            int right = 2*i+2;
//            int min = i;
//            if (comparePoint(points[left], points[min]) > 0) min = left;
//            if (comparePoint(points[right], points[min]) > 0) min = right;
//            if (min != i) {
//                swap(points, min, i);
//                i = min;
//            } else {
//                break;
//            }
//        }
//    }
//    public Point popMin(Point[] points, int len) {
//        Point temp = points[0];
//        points[0] = points[points.length-1];
//        shiftDown(points, 0, len);
//        return temp;
//    }
//    public void swap(Point[] points, int a, int b) {
//        Point temp = points[a];
//        points[a] = points[b];
//        points[b] = temp;
//    }
//    //QuickSelect O(klogk+n);
//    public Point[] findKthPoint3(Point[] points, int k) {
//        if (points == null || points.length <= k) return points;
//        quickSelect2(points, 0, points.length-1, k-1);
//        Point[] res = Arrays.copyOf(points, k);
//        return res;
//    }
//    public void quickSelect(Point[] points, int start, int end, int k) {
//        int i = start, j = end;
//        Point mid  = points[start];
//        while (i <= j) {
//            while (i <= j && comparePoint(points[i], mid) > 0) i++;
//            while (i <= j && comparePoint(mid, points[j]) > 0) j--;
//            if (i <= j) {
//                swap(points, i, j);
//                i++;
//                j--;
//            }
//        }
//        if (i <= k) quickSelect(points, i, end, k);
//        if (j >= k) quickSelect(points, start, j, k);
//    }
//    private void quickSelect2(Point[] points, int l, int r, int k) {
//        if (l == r) return;
//        Point mid = points[r];
//        int i = l, j = r - 1;
//        do {
//            while (i < r && comparePoint(points[i], mid) > 0) i ++;
//            while (l < j && comparePoint(mid, points[j]) > 0) j --;
//            if (i < j) swap(points, i ++, j --);
//        } while (i < j);
//        swap(points, i, r);
//        if (i == k) return;
//        if (k < i) quickSelect(points, l, i - 1, k);
//        else quickSelect(points, i + 1, r, k);
//    }
//}
