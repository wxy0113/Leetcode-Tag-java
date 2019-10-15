import java.util.*;

public class SubdomainCount {
    /**
     * 1. Count domain and subdomain click times
     * Example:
     *  input: ["9001 discuss.leetcode.com"]
     *  output: ["9001 discuss.leetcode.com", "9001 leetcode.com", "9001 com"]
     * @param domains domains list
     * @return domain and subdomain click times
     *
     * time: O(n*String.length) space: O(n)
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

    /**
     * 2. Find longest continuous common visit records, given two users' browser history list
     *     Example:
     *     input:
     *     String[] user0 = new String[] {"/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html"};
     *     String[] user1 = new String[] {"/one.html", "/two.html", "/three.html", "/four.html", "/six.html"};
     *
     * @param user1 user1 history
     * @param user2 user2 history
     * @return common continuous history
     *
     * Hint: DP[2][length], only using current and one previous value
     * DP: time: O(mn), space: O(n)
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

    /**
     * 3. 给3个list, 然后要求找出每一个商品的conversion rate
     * Example:
     *  input: completedPurchaseUsers = ["id1", "id2", "id3"]
     *         ‍‍‍‌‌‌‌‍‍‌‌‍‌‍‍‍‌‌adClicks = ["ip1, timestamp, ad1", "ip2, timestamp, ad1", "ip3, timestamp, ad2", "ip4, timestamp, ad2"]
     *         userIp = ["id1, ip1", "id2, ip2", "id3, ip3", "id4, ip4"]
     * 比如ip1, ip2都点击并购买了ad1的商品，所以rateOfAd1 = 1, 同理rateOfAd2 = 0.5。
     * 应该是默认没个user最多买一件东西
     * @param completedPurchaseUsers 完成购买的用户的id
     * @param adClicks 点击广告的ip address
     * @param userIp 用户id对应的ip address
     */
    public static void findCommonPurchase(String[] completedPurchaseUsers, String[] adClicks, String[] userIp) {
        Map<String, String> map = new HashMap<>();
        Map<String, int[]> ads = new HashMap<>();

        for (String ipId : userIp) {
            String[] strs = ipId.split(", ");
            map.put(strs[0], strs[1]);
        }

        Set<String> purchased = new HashSet<>();
        for (String user : completedPurchaseUsers) {
            purchased.add(map.get(user));
        }

        for (String ad : adClicks) {
            String[] strs = ad.split(", ");
            if (!ads.containsKey(strs[2])) {
                ads.put(strs[2], new int[2]);
            }
            ads.get(strs[2])[1]++;
            if (purchased.contains(strs[0])) {
                ads.get(strs[2])[0]++;
            }
        }

        for (String ad : ads.keySet()) {
            System.out.println(ad + "'s conversion rate is: " + (double)ads.get(ad)[0]/ads.get(ad)[1]);
        }
    }
    public static void main(String[] args) {
        String[] user0 = new String[] {"/nine.html", "/four.html", "/six.html", "/seven.html", "/one.html"};
        String[] user1 = new String[] {"/one.html", "/two.html", "/three.html", "/four.html", "/six.html"};
        List<String> list = longestCommonHistory(user0, user1);
        for(String s : list) {
            System.out.println(s);
        }
        System.out.println("--------------------");

        String[] completedPurchaseUsers = new String[]{"id1", "id2", "id3"};
        String[] adClicks = new String[]{"ip1, timestamp, ad1", "ip2, timestamp, ad1", "ip3, timestamp, ad2", "ip4, timestamp, ad2"};
        String[] userIp = new String[]{"id1, ip1", "id2, ip2", "id3, ip3", "id4, ip4"};
        findCommonPurchase(completedPurchaseUsers, adClicks, userIp);
    }
}
