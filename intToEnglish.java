import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class intToEnglish {
    public static void main(String[] args) {
        intToEnglish sol = new intToEnglish();
        System.out.print(sol.intToEnglish(-1111111));
    }
    private String[] less20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private String[] tenth = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private String[] thousands = {"", "Thousand", "Million", "Billion"};
    public String intToEnglish(int num) {
        if (num == 0) return "Zero";
        boolean neg = false;
        if (num < 0) {
            num = -num;
            neg = true;
        }
        String res = "";
        int index = 0;
        while (num != 0) {
            if (num%1000 != 0) {
                res = helper(num%1000) + thousands[index] + " " + res;
            }
            index++;
            num /= 1000;
        }
        return neg ? "Negative "+res : res;
    }
    public String helper(int num) {
        if (num == 0) {
            return "";
        } else if (num <= 20) {
            return less20[num]+" ";
        } else if (num < 100) {
            return tenth[num/10]+" ";
        } else {
            return less20[num/100] + " Hundred "+helper(num%100);
        }
    }
}
