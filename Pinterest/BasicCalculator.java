import java.util.*;

public class BasicCalculator {
    /**
     * 1. Calculator only contains +, -, num
     * @param s String represents equations
     * @return result
     */
    public static int calculate1(String s) {
        int res = 0;
        int pre = 0;
        int sign = 1;
        for (int i = 0 ; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                pre = pre*10 + s.charAt(i)-'0';
            } else {
                res += pre*sign;
                sign = s.charAt(i) == '-' ? -1 : 1;
                pre = 0;
            }
        }
        res += pre*sign;
        return res;
    }

    /**
     * 2. Calculator contains parenthesis
     * @param s String represents equations
     * @return result
     */
    public static int calculate2(String s) {
        int res = 0;
        int pre = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                pre = pre*10 + s.charAt(i)-'0';
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+'){
                res += pre*sign;
                sign = s.charAt(i) == '-' ? -1 : 1;
                pre = 0;
            } else if (s.charAt(i) == '(') {
                stack.push(res);
                stack.push(sign);
                res = 0;
                sign = 1;
            } else {
                res += pre*sign;
                pre = 0;
                int temp = res*stack.pop();
                res = stack.pop()+temp;
            }
        }
        return res;
    }

    /**
     * 3. Calculator contains mapping values, try to simplify equations
     * @param s String represents equations
     * @param map Mapping for words
     * @return Simplified result
     */
    public static String calculate3(String s, Map<String, Integer> map) {
        List<String> list = new ArrayList<>();
        String ss = exchange(s.replaceAll(" ",""), map, list);
        System.out.println(ss);
        int numRes = calculate2(ss);
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(numRes));
        for (String str : list) {
            sb.append(str);
        }
        return sb.toString();
    }
    public static String exchange(String s, Map<String, Integer> map, List<String> list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        int pre = 0;
        int sign = 1;
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        while (index < s.length()) {
            if (Character.isAlphabetic(s.charAt(index))) {
                sb.append(s.substring(pre, index));
                pre = index;
                while (index < s.length() && Character.isAlphabetic(s.charAt(index))) {
                    index++;
                }
                String temp = s.substring(pre, index);
                if (map.containsKey(temp)) {
                    sb.append(String.valueOf(map.get(temp)));
                } else {
                    //System.out.println(stack.size());
                    sb.append(0);
                    int cur = sign*stack.peek();
                    if (cur == -1) {
                        temp = "-"+temp;
                    } else {
                        temp = "+"+temp;
                    }
                    list.add(temp);
                }
                pre = index;
                index--;
            } else if (s.charAt(index) == '-' || s.charAt(index) == '+') {
                sign = s.charAt(index) == '-' ? -1 : 1;
            } else if (s.charAt(index) == '(') {
                stack.push(sign*stack.peek());
                sign = 1;
            } else if (s.charAt(index) == ')') {
                //System.out.println(s.charAt(index-1));
                stack.pop();
            }
            index++;
        }
        String temp = s.substring(pre, s.length());
        if (map.containsKey(temp)) {
            sb.append(String.valueOf(map.get(temp)));
        } else {
            sb.append(temp);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(calculate1("1+2-3+4-5"));
        System.out.println(calculate2("(1+(4+5+2)-3)+(6+8)"));

        Map<String, Integer> map = new HashMap<>();
        map.put("temperature", 5);
        map.put("e", 1);
        map.put("y", 2);
        System.out.println(calculate3("1+(e + 8) - (pressure - 3 - (temperature + 8) + y) +yy", map));
    }
}
