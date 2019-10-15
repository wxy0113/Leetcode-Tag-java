import java.util.*;

public class studentClass {
    /**
     * 1. 给一些<studenID, course name>， 返回每两个学生间相同的课
     * Example:
     *  input:  {"1", "A"}, {"2", "B"}, {"5", "E"}, {"1", "B"}, {"2", "A"}, {"2", "E"}
     *  output: [1, 2]->[A, B]
     *          [1, 5]->[]
     *          [2, 5]->[E]
     * @param courses <studenID, course name>
     *
     * Map<String, set<String>>: time: O(n^2) space: O(n)
     */
    public static void findCommonCourse(String[][] courses) {
        Map<String, Set<String>> map = new HashMap<>();
        List<String> students = new ArrayList<>();
        for (String[] c : courses) {
            if (!map.containsKey(c[0])) {
                map.put(c[0], new HashSet<>());
                students.add(c[0]);
            }
            map.get(c[0]).add(c[1]);
        }
        for (int i = 0; i < students.size(); i++) {
            for (int j = i+1; j < students.size(); j++) {
                List<String> common = new ArrayList<>();
                for (String c : map.get(students.get(i))) {
                    if (map.get(students.get(j)).contains(c)) {
                        common.add(c);
                    }
                }
                System.out.print(Arrays.toString(new String[]{students.get(i), students.get(j)}) + "->");
                System.out.println(common.toString());
            }
        }
    }

    /**
     * 2. 给一些<course, course>代表prerequisite，只有一条可行的路径，返回中间的那节课
     * @param courses <course, course>
     *
     * Map<String, String> + traverse: time: O(n), space: O(n)
     */
    public static void findMiddleCourse(String[][] courses) {
        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        for (String[] c : courses) {
            map.put(c[0], c[1]);
            set.add(c[0]);
            set.remove(c[1]);
        }
        String first = "";
        for (String s : set) {
            first = s;
        }

        int index = map.size()/2;
        while(index > 0) {
            first = map.get(first);
            index--;
        }
        System.out.println(first);
    }

    /**
     * 3. 给一些<course, course>代表prerequisite，找到每条路径中间的那节课
     * @param courses <course, course>
     *
     * time: O(n) space: O(n)
     */
    public static void findMiddleCourse2(String[][] courses) {
        Set<String> set = new HashSet<>();
        Map<String, String> map = new HashMap<>();
        for (String[] c : courses) {
            map.put(c[0], c[1]);
            set.add(c[0]);
            set.remove(c[1]);
        }

        for (String first : set) {
            String temp = first;
            while (map.containsKey(map.get(temp))) {
                first = map.get(first);
                temp = map.get(temp);
                temp = map.get(temp);
            }
            System.out.println(first);
        }
    }

    public static void main(String[] args) {
        String[][] courses1 = new String[][]{
                {"1", "A"}, {"2", "B"}, {"5", "E"}, {"1", "B"}, {"2", "A"}, {"2", "E"}
        };
        findCommonCourse(courses1);
        System.out.println("------------------------");

        String[][] courses2 = new String[][] {
                {"A", "C"}, {"B", "D"}, {"D", "A"}, {"G", "E"}, {"C", "F"}, {"E", "B"}
        };
        findMiddleCourse(courses2);
        System.out.println("------------------------");

        String[][] courses3 = new String[][] {
                {"A", "B"}, {"B", "D"}, {"E", "B"}, {"E", "C"}, {"C", "F"}, {"E", "F"}
        };
        findMiddleCourse2(courses3);
    }
}
