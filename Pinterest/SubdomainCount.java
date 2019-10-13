import java.util.*;

public class SubdomainCount {
    /*
    1. Count domain and subdomain click times
    Example:
        input: ["9001 discuss.leetcode.com"]
        output: ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
     */
    public static List<String> subdomainVisits(String[] domains) {
        Map<String, Integer> map = new HashMap<>();
        for (String d : domains) {
            String[] temp = d.split(" ");
            int cnt = Integer.parseInt(temp[0]);
            String domain = temp[1];
            int index = domain.length()-1;
            while(index >= -1) {
                if (index == -1 || domain.charAt(index) == '.') {
                    String sub = domain.substring(index+1, domain.length());
                    map.put(sub, map.getOrDefault(sub, 0)+cnt);
                }
                index--;
            }
        }
        List<String> list = new ArrayList<>();
        for (String d : map.keySet()) {
            list.add(map.get(d) + " " + d);
        }
        return list;
    }

    /*
    2. Find longest continuous common visit records, given two users' browser history list
    Example:
    input:
    String[] user0 = new String[] {"/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html"};
    String[] user1 = new String[] {"/one.html", "/two.html", "/three.html", "/four.html", "/six.html"};

    Hint: DP[2][length], only using current and one previous value
     */

    public static List<String> longestCommonHistory(String[] user1, String[] user2) {
        int max = 0, end = Integer.MIN_VALUE;
        int[][] dp = new int[2][user2.length+1];
        int swap = 0;

        for (int i = 0; i < user1.length; i++) {
            for (int j = 0; j < user2.length; j++) {
                if (user1[i].equals(user2[j])) {
                    dp[swap][j+1] = dp[1-swap][j]+1;
                }
                if (dp[swap][j+1] > max) {
                    max = dp[swap][j+1];
                    end = j;
                }
            }
            swap = 1 - swap;
        }
        List<String> res = new ArrayList<>();
        while(max > 0) {
            res.add(user2[end-max+1]);
            max--;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] user0 = new String[] {"/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html"};
        String[] user1 = new String[] {"/one.html", "/two.html", "/three.html", "/four.html", "/six.html"};
        List<String> list = longestCommonHistory(user0, user1);
        for(String s : list) {
            System.out.println(s);
        }
    }
}
