class Solution {
    
    // Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
    
    // DP
    // dp[i] 记录最长匹配end with s.charAt(i) == ')'
    // If s[i] is '(', set longest[i] to 0,because any string end with '(' cannot be a valid one.
    // Else if s[i] is ')'
    //      If s[i-1] is '(', longest[i] = longest[i-2] + 2
    //      Else if s[i-1] is ')' and s[i-longest[i-1]-1] == '(', longest[i] = longest[i-1] + 2 + longest[i-longest[i-1]-2]
    public int longestValidParenthesesDP(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[s.length()+1];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')' && i-dp[i-1]-1 >= 0 && s.charAt(i-dp[i-1]-1) == '(') {
                dp[i] = dp[i-1] + 2 + (i-dp[i-1]-2 >= 0 ? dp[i-dp[i-1]-2] : 0);
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }
    
    // Stack:
    // 记录左括号的位置，当遇到右括号时，pop最近左括号的位置
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int max = 0;
        int temp = 0;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if (stack.isEmpty()) {
                    temp = 0;
                } else {
                    int j = stack.pop();
                    int len = i-j+1;
                    
                    if (stack.isEmpty()) {
                        temp += len;
                        len = temp;
                    } else {
                        len = i-stack.peek();
                    }
                    max = Math.max(max, len);
                }
            }
            
        }
        return max;
    }
}