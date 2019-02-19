import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */
class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
class Point {
    int time;
    int flag;

    public Point(int time, int flag) {
        this.time = time;
        this.flag = flag;
    }

    public static Comparator<Point> PointComparator= new Comparator<Point>() {
        public int compare(Point p1, Point p2) {
            if (p1.time == p2.time) {
                return p1.flag-p2.flag;
            } else {
                return p1.time-p2.time;
            }
        }
    };
}
class Room {
    int index;
    int end;
    public Room(int index, int end) {
        this.index = index;
        this.end = end;
    }
}
class meetingRoom {
    public static void main(String[] args) {
        List<Interval> list = new ArrayList<>();
        list.add(new Interval(0, 5));
        list.add(new Interval(5, 30));
        list.add(new Interval(15, 20));
        meetingRoom sol = new meetingRoom();
        System.out.print(sol.meetRoom3(list));
    }
    public boolean canAttend(List<Interval> list) {
        if (list == null || list.size() == 0) return false;
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (int i = 0; i < list.size()-1; i++) {
            if (list.get(i).end > list.get(i+1).start) return false;
        }
        return true;
    }
    //PriorityQueue nlogn
    public int meetRoom(List<Interval> list) {
        if (list == null || list.size() == 0) return 0;
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Interval i : list) {
            if (!pq.isEmpty() && pq.peek() <= i.start) {
                pq.poll();
            }
            pq.offer(i.end);
        }
        return pq.size();
    }

    // end & flag nlogn
    public int meetRoom2(List<Interval> intervals) {
        // Write your code here
        List<Point> list = new ArrayList<>(intervals.size()*2);
        for (Interval i : intervals) {
            list.add(new Point(i.start, 1));
            list.add(new Point(i.end, 0));
        }
        Collections.sort(list, Point.PointComparator);
        int count = 0, res = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).flag == 1) {
                count ++;
            } else {
                count --;
            }
            res = Math.max(res, count);
        }
        return res;
    }


    public int meetRoom3(List<Interval> list) {
        Collections.sort(list, new Comparator<Interval>() {
            @Override
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Room> pq = new PriorityQueue<>(new Comparator<Room>() {
            @Override
            public int compare(Room r1, Room r2) {
                return r1.end-r2.end;
            }
        });
        List<List<Interval>> rooms = new ArrayList<>();
        for (Interval in : list) {
            Room rm = null;
            if (pq.isEmpty() || pq.peek().end > in.start) {
                rm = new Room(pq.size(), 0);
                rooms.add(new ArrayList<>());
            } else {
                rm = pq.poll();
            }
            rm.end = in.end;
            rooms.get(rm.index).add(in);
            pq.offer(rm);
        }
        mergeInroom(rooms);
        return pq.size();
    }
    public void mergeInroom(List<List<Interval>> rooms) {
        for (int i = 0; i < rooms.size(); i++) {
            List<Interval> room = new ArrayList<>();
            Interval temp = rooms.get(i).get(0);
            for (int j = 1; j < rooms.get(i).size(); j++) {
                if (temp.end > rooms.get(i).get(j).start) {
                    temp.end = rooms.get(i).get(j).end;
                } else {
                    room.add(temp);
                    temp = rooms.get(i).get(j);
                }
                room.add(temp);
                rooms.set(i, room);
            }
        }
        for (int i = 0; i < rooms.size(); i++) {
            System.out.print("Room " + i + " [");
            for (Interval in : rooms.get(i)) {
                System.out.print("[" + in.start + "," + in.end + "]");
            }
            System.out.println("]");
        }
    }
}
