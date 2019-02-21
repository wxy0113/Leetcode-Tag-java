import java.io.*;
import java.util.*;

// Reorder logs file
// You have an array of logs.  Each log is a space delimited string of words.

// For each log, the first word in each log is an alphanumeric identifier.  Then, either:

// Each word after the identifier will consist only of lowercase letters, or;
// Each word after the identifier will consist only of digits.
// We will call these two varieties of logs letter-logs and digit-logs.  It is guaranteed that each log has at least one word after its identifier.

// Reorder the logs so that all of the letter-logs come before any digit-log.  The letter-logs are ordered lexicographically ignoring identifier, with the identifier used in case of ties.  The digit-logs should be put in their original order.

// Return the final order of the logs.

public class Solution {
  public static void main(String[] args) {
  
  }
  public String[] reorderLogFiles(String[] logs) {
        if (logs == null || logs.length == 0) return null;
        Arrays.sort(logs, new Comparator<String>() {
           public int compare(String s1, String s2) {
               int in1 = s1.indexOf(" ");
               int in2 = s2.indexOf(" ");
               char c1 = s1.charAt(in1+1);
               char c2 = s2.charAt(in2+1);
               
               if (Character.isDigit(c1)) {
                   if (Character.isDigit(c2)) {
                       return 0;
                   } else {
                       return 1;
                   }
               }
               
               if (Character.isDigit(c2)) return -1;
               
               int pre = s1.substring(in1+1, s1.length()).
                 compareTo(s2.substring(in2+1, s2.length()));
               if (pre == 0) return s1.substring(0, in1).
                 compareTo(s2.substring(0, in2));
               return pre;
           } 
        });
        return logs;
    }
}