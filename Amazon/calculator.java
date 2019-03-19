class Solution {
  public static void main(String[] args) {
    System.out.print(calculator2("(1 +(4+5 +2)-3) +(6 +8)"));
  }
  
  // Basic Calculator 1: 只包含加减括号
  // Implement a basic calculator to evaluate a simple expression string.
  // The expression string may contain open ( and closing parentheses ), 
  // the plus + or minus sign -, non-negative integers and empty spaces .
  // DFS O(n)
  public static int calculator(String s) {
    if (s == null) return 0;
    s = s.replaceAll("\\s+", "");
    return dfs(s, 0, 0, 1);
  }
  // Important!!!!: record ")" index, 避免重复计算括号内结果
  static int cur = 0;
  public static int dfs(String s, int index, int res, int sign) {
    if (index == s.length()) return res;
    
    for (int i = index; i < s.length(); i++) {
      char c = s.charAt(i);
      
      if (Character.isDigit(c)) {
        int temp = c-'0';
        while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
          temp = temp*10 + (s.charAt(i+1)-'0');
          i++;
        }
        res += sign*temp;
      } else if (c == '-') {
        sign = -1;
      } else if (c == '+') {
        sign = 1;
      } else if (c == '(') {
        res += sign*dfs(s, i+1, 0, 1);
        i = cur;
      } else if (c == ')'){
        cur = i;
        return res;
      }
    }
    return res;
  }
  // Stack: O(n)
  // 遇到左括号则将当前结果和符号存入stack
  // 遇到右括号则将当前结果*stack中的符号+stack中的结果
  public static int calculator1(String s) {
    if (s == null) return 0;
    s = s.replaceAll("\\s+", "");
    Stack<Integer> stack = new Stack<>();
    int res = 0;
    int sign = 1;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        int temp = c-'0';
        while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
          temp = temp*10+s.charAt(i+1)-'0';
          i++;
        }
        res += sign*temp;
      } else if (c == '+') {
        sign = 1;
      } else if (c == '-') {
        sign = -1;
      } else if (c == '(') {
        stack.push(res);
        stack.push(sign);
        res = 0;
        sign = 1;
      } else {
        res = stack.pop()*res + stack.pop();
      }
    }
    return res;
  }
  
  
  // Basic Calculator 2: 加减乘除
  // Using Stack: 将每个数放进stack中，如果有乘除则取出前一个数进行处理
  public static int calculator2(String s) {
    if (s == null) return 0;
    s = s.replaceAll("\\s+", "");
    Stack<Integer> stack = new Stack<>();
    int sum = 0;
    char sign = '+';
    
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (Character.isDigit(c)) {
        sum = sum*10 + c - '0';
      }
      if (!Character.isDigit(c) || i+1 == s.length()) {
        if (sign == '+') {
          stack.push(sum);
        } else if (sign == '-') {
          stack.push(-sum);
        } else if (sign == '*') {
          stack.push(sum*stack.pop());
        } else if (sign == '/'){
          stack.push(stack.pop()/sum);
        }
        sign = c;
        sum = 0;
      }
    }
    
    int res = 0;
    while (!stack.isEmpty()) {
      res += stack.pop();
    }
    return res;
  }
  
  // Not using Stack:
  // 利用pre 和 cur： 遇到加减 则将pre与res合并，并将pre更新为cur
  //                 遇到乘除， 则将pre与cur合并进行下次计算
  public static int calculator22(String s) {
    if (s == null) return 0;
    s = s.replaceAll("\\s+", "");
    int res = 0;
    int pre = 0;
    char sign = '+';
    int i = 0;
    
    while (i < s.length()) {
      int cur = 0;
      while (i < s.length() && Character.isDigit(s.charAt(i))) {
        cur = cur*10+s.charAt(i)-'0';
        i++;
      }
      if (sign == '+') {
        res += pre;
        pre = cur;
      } else if (sign == '-') {
        res += pre;
        pre = -cur;
      } else if (sign == '*') {
        pre = pre*cur;
      } else if (sign == '/') {
        pre = pre/cur;
      }
      if (i < s.length()) {
        sign = s.charAt(i);
        i++;
      }
    }
    
    return res+pre;
  }
}

// Calculator 2&3 通用:

// Caluculator 2:
public int calculate(String s) {
        if (s == null) return 0;
        s = s.replaceAll("\\s+", "");
        
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;
        int res = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                int num = c-'0';
                while (i+1 < s.length() && Character.isDigit(s.charAt(i+1))) {
                    i++;
                    num = num*10 + s.charAt(i)-'0';
                }
                l2 = (o2 == 1) ? l2*num : l2/num;
            } else if (c == '*' || c == '/') {
                o2 = (c == '*') ? 1 : -1;
            } else if (c == '+' || c == '-') {
                l1 += o1*l2;
                o1 = (c == '+') ? 1 : -1;
                l2 = 1;
                o2 = 1;
            }
        }
        
        return l1 + o1*l2;
    }
// Caluculator 3:
public int calculate(String s) {
        int l1 = 0, o1 = 1;
        int l2 = 1, o2 = 1;

        Stack<Integer> stack = new Stack<>(); // stack to simulate recursion

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                int num = c - '0';

                while (i + 1 < s.length() && Character.isDigit(s.charAt(i + 1))) {
                    num = num * 10 + (s.charAt(++i) - '0');
                }

                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '(') {
                // First preserve current calculation status
                stack.push(l1); stack.push(o1);
                stack.push(l2); stack.push(o2);

                // Then reset it for next calculation
                l1 = 0; o1 = 1;
                l2 = 1; o2 = 1;

            } else if (c == ')') {
                // First preserve the result of current calculation
                int num = l1 + o1 * l2;

                // Then restore previous calculation status
                o2 = stack.pop(); l2 = stack.pop();
                o1 = stack.pop(); l1 = stack.pop();

                // Previous calculation status is now in effect
                l2 = (o2 == 1 ? l2 * num : l2 / num);

            } else if (c == '*' || c == '/') {
                o2 = (c == '*' ? 1 : -1);

            } else if (c == '+' || c == '-') {
                l1 = l1 + o1 * l2;
                o1 = (c == '+' ? 1 : -1);

                l2 = 1; o2 = 1;
            }
        }

        return (l1 + o1 * l2);
    }