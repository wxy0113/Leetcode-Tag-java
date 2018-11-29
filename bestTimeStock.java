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

class Solution {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        Solution sol = new Solution();
        System.out.print(sol.maxProfit4(prices, 5));
    }
    // One transaction
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int buy = -prices[0];
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            sell = Math.max(sell, prices[i]+buy);
            buy = Math.max(buy, -prices[i]);
        }
        return sell;
    }
    // Infinite transactions
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sell = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i-1]) {
                sell += prices[i]-prices[i-1];
            }
        }
        return sell;
    }
    //Two transactions
    public int maxProfit3(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sell2 = 0, sell1 = 0, buy2 = -prices[0], buy1 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            sell2 = Math.max(sell2, buy2+prices[i]);
            buy2 = Math.max(buy2, sell1-prices[i]);
            sell1 = Math.max(sell1, buy1+prices[i]);
            buy1 = Math.max(buy1, -prices[i]);
        }
        return sell2;
    }
    public int maxProfit33(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int sell2 = 0, sell1 = 0, buy2 = Integer.MIN_VALUE, buy1 = Integer.MIN_VALUE;
        for (int p : prices) {
            sell2 = Math.max(sell2, buy2+p);
            buy2 = Math.max(buy2, sell1-p);
            sell1 = Math.max(sell1, buy1+p);
            buy1 = Math.max(buy1, -p);
        }
        return sell2;
    }
    // K transactions
    public int maxProfit4(int[] prices, int k) {
        if (prices == null || prices.length == 0) return 0;
        int len = prices.length;
        if (2*k >= len) return maxProfit2(prices);
        int[][] dp = new int[k+1][len];
        for (int i = 1; i <= k; i++) {
            int buy = -prices[0];
            for (int j = 1; j < prices.length; j++) {
                // prices[j]-prices[ii]+dp[i-1][ii]
                dp[i][j] = Math.max(dp[i][j-1], buy+prices[j]);
                buy = Math.max(buy, dp[i-1][j]-prices[j]);
            }
        }
        return dp[k][len-1];
    }
}